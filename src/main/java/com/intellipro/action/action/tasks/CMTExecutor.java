package com.intellipro.action.action.tasks;

import com.intellipro.action.action.Repository.ActiveAudienceRepository;
import com.intellipro.action.action.Repository.ActiveJourneyRepository;
import com.intellipro.action.action.Repository.ActiveNodeRepository;
import com.intellipro.action.action.Repository.NodeRepository;
import com.intellipro.action.action.entity.ActiveAudience;
import com.intellipro.action.action.entity.ActiveNode;
import com.intellipro.action.action.entity.CoreModuleTask;
import com.intellipro.action.action.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CMTExecutor{
    private ActiveAudienceRepository activeAudienceRepository;

    private ActiveNodeRepository activeNodeRepository;

    private ActiveJourneyRepository activeJourneyRepository;

    private NodeRepository nodeRepository;

    RestTemplate restTemplate;
    //Chanage the below to actual API endpoints of functional urls
    private HashMap<String, String> urlDict = new HashMap<String, String>() {
        {
            put("TimeDelay", "http://localhost:8080/TimeDelay");
            put("APITrigger", "http://localhost:8080/API_trigger");
            put("End", "http://localhost:8080/End");
            put("TimeTrigger", "http://localhost:8080/TimeTrigger");
            put("SendEmail", "http://localhost:8080/SendEmail");
            put("If/else", "http://localhost:8080/If_Else");
            put("tag", "http://localhost:8080/Tag");
        }

    };

    @Autowired
    public CMTExecutor(NodeRepository nodeRepository, RestTemplate restTemplate, ActiveNodeRepository activeNodeRepository){
        this.nodeRepository = nodeRepository;
        this.restTemplate = restTemplate;
        this.activeNodeRepository = activeNodeRepository;
    }

    public CMTExecutor(CoreModuleTask coreModuleTask, ActiveAudienceRepository activeAudienceRepository, ActiveNodeRepository activeNodeRepository, ActiveJourneyRepository activeJourneyRepository, NodeRepository nodeRepository) {
        this.activeAudienceRepository = activeAudienceRepository;
        this.activeNodeRepository = activeNodeRepository;
        this.activeJourneyRepository = activeJourneyRepository;
        this.nodeRepository = nodeRepository;
    }

    public void execute(CoreModuleTask coreModuleTask) {

        //first, if this coremoduletask's type is "end", we don't do anythong and simply returns
        if (coreModuleTask.getType().equals("End")) return;
        CoreModuleTask restask = null;
        //else, we can first call the respective functional API's based on task type if the callapi attribute is 1:
        if(coreModuleTask.getCallapi()==1) {restask = restTemplate.exchange(urlDict.get(coreModuleTask.getType()), HttpMethod.POST, new HttpEntity<>(coreModuleTask), CoreModuleTask.class).getBody();}
        //now, if restask.makenext is set to 0, the task executor will simply return since it won't do anything
        if (restask.getMakenext() == 0) return;
        //else: first move audience from curnode to next node, or create new active audience in nextnode
        Long activeid;
        //change local host to server domain!!
        //moving active audience pool from current node to next node (via method in task controller)
        if (restask.getTaskType() == 0) {
            activeid = restTemplate.exchange("http://localhost:8080/move_user", HttpMethod.POST, new HttpEntity<>(restask), Long.class).getBody();
        } else {
            activeid = restTemplate.exchange("http://localhost:8080/create_user", HttpMethod.POST, new HttpEntity<>(restask), Long.class).getBody();
        }
        Node curnode = nodeRepository.findById(restask.getNodeId()).get();
        //finally, make and push new tasks based on next node
        for (int i = 0; i < curnode.getNexts().size(); i++) {
            Long id = curnode.getNexts().get(i);
            Node nextnode = nodeRepository.findById(id).get();
            CoreModuleTask newtask = new CoreModuleTask();
            newtask.setTaskType(0);                 //all tasks are move-user except for trigger hit, which is not taken care of here
            newtask.setType(nextnode.getType());
            newtask.setName(nextnode.getName());
            newtask.setSourceNodeId(nextnode.getNodeId());
            if(nextnode.getNexts().size()>0) newtask.setTargetNodeId(nodeRepository.findById(nextnode.getNexts().get(0)).get().getNodeId());         //this targetnodeid attribute is not really useful anymore
            //now we identify the current activeNode
            ActiveNode activeNode = activeNodeRepository.findByDBNodeId(id);
            List<ActiveAudience> activeAudienceList = activeNode.getActiveAudienceList();                       //since the corresponding active audience pool for the possible if/else nextnode is already taken care of in move audience, we simply assign the active audience list to the first AAL attribute of the node's CMT
            List<Long> activeIDs = new ArrayList<>();
            List<Long> IDs = new ArrayList<>();
            for (ActiveAudience aud : activeAudienceList) {
                activeIDs.add(aud.getAudienceId());
                IDs.add(aud.getAudienceId());
            }
            newtask.setActiveAudienceId1(activeIDs);
            newtask.setAudienceId1(IDs);
            String url = "http://localhost:8080/ReturnTask";
            HttpEntity<CoreModuleTask> httpEntity = new HttpEntity<>(newtask);
            Long taskid = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Long.class).getBody();              //successfully pushed a new task by calling task controller (return task id if successful)
        }
    }
}
