package com.intellipro.action.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class CoreModuleTask extends BaseTaskEntity{

    @Id
    @SequenceGenerator(
            name = "coreTask_sequence",
            sequenceName = "coreTask_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "coreTask_sequence"
    )
    private long taskId;

    private int taskType;       //0 for move audience, 1 for create audience
    private String createModule;
    private String type;        //this is the general type of tasks
    private String name;        //this is the specific description of this task of this type; each api has its own coding
    private int makenext=1;     //If set to 1, means the task (when returned to core module) need to make the next task based on next nodes; if 0 then core module will not make a new task when this task is returned
    private int callapi=1;
}