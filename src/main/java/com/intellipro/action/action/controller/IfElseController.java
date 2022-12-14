package com.intellipro.action.action.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intellipro.action.action.entity.CoreModuleTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IfElseController {
    @Autowired
    IfElseTaskController ifElseTaskController;


    @PostMapping("/API_trigger")
    public CoreModuleTask redirect(@RequestBody CoreModuleTask task) throws JsonProcessingException {
        CoreModuleTask nullTask = new CoreModuleTask();
        nullTask.setName("nullTask");

        // {'property': 'XXX', 'condition': 'YYY', 'value': 'ZZZ'}
        // {'property': 'XXX', 'condition': 'YYY', 'value': null}
        // {'repeatInterval': 'XXX', 'repeat': #, 'triggerTime': #, 'eventType': 'WWW', 'httpEntity': [{'aaa'},{'bbb'}, ... ,{'ccc'}]}

        String json_text = task.getName();


        if (json_text.contains("property") && json_text.contains("condition") && json_text.contains("value")) {
            String find = "value";
            String substr = "";
            int i  = json_text.indexOf(find);
            substr = json_text.substring(i + find.length() + 3, json_text.length() - 1);
            if (substr.contains("null")) {
                return ifElseTaskController.ifElsePropertyWithoutValue(task);
            } else if (!substr.contains("null")) {
                return ifElseTaskController.ifElseProperty(task);
            }
        } else if (json_text.contains("httpEntity") && json_text.contains("repeatInterval") && json_text.contains("triggerTime")) {
            return ifElseTaskController.filterByAudienceAction(task);
        }
        return nullTask;
    }
}
