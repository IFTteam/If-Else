package com.intellipro.action.action.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intellipro.action.action.entity.CoreModuleTask;
import com.intellipro.action.action.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/audienceAction/{node}/{user}/{target}/{interval}/{repeat}/{trigger}/{event}/{journey}")
    public CoreModuleTask filterByAudienceAction (CoreModuleTask coreModuleTask) throws JsonProcessingException {
        return taskService.filterByAudienceAction(coreModuleTask);
    }

    @GetMapping("/audiences/{property}/{condition}/{value}")
    public CoreModuleTask ifElseProperty(CoreModuleTask coreModuleTask) {
        return taskService.ifElseProperty(coreModuleTask);
    }

    @GetMapping("/audiences/{property}/{condition}")
    public CoreModuleTask ifElsePropertyWithoutValue(CoreModuleTask coreModuleTask) {
        return taskService.ifElsePropertyWithoutValue(coreModuleTask);
    }
}
