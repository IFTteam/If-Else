package com.intellipro.action.action.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intellipro.action.action.entity.CoreModuleTask;
import com.intellipro.action.action.service.IfElseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IfElseTaskController {

    @Autowired
    private IfElseTaskService ifElseTaskService;


    @GetMapping("/audienceAction/{node}/{user}/{target}/{interval}/{repeat}/{trigger}/{event}/{journey}")
    public CoreModuleTask filterByAudienceAction (CoreModuleTask coreModuleTask) throws JsonProcessingException {
        return ifElseTaskService.filterByAudienceAction(coreModuleTask);
    }

    @GetMapping("/audiences/{property}/{condition}/{value}")
    public CoreModuleTask ifElseProperty(CoreModuleTask coreModuleTask) {
        return ifElseTaskService.ifElseProperty(coreModuleTask);
    }

    @GetMapping("/audiences/{property}/{condition}")
    public CoreModuleTask ifElsePropertyWithoutValue(CoreModuleTask coreModuleTask) {
        return ifElseTaskService.ifElsePropertyWithoutValue(coreModuleTask);
    }
}
