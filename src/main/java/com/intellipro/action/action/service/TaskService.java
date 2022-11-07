package com.intellipro.action.action.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.intellipro.action.action.entity.CoreModuleTask;

public interface TaskService {
    CoreModuleTask filterByAudienceAction(CoreModuleTask coreModuleTask) throws JsonProcessingException;

    CoreModuleTask ifElseProperty(CoreModuleTask coreModuleTask);

    CoreModuleTask ifElsePropertyWithoutValue(CoreModuleTask coreModuleTask);
}
