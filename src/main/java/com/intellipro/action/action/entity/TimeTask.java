package com.intellipro.action.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name="time_task")
public class TimeTask extends BaseTaskEntity{

    @Id
    @SequenceGenerator(
            name = "timeTask_sequence",
            sequenceName = "timeTask_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "timeTask_sequence"
    )
    private long timetaskId;

    private Integer repeatTimes;
    private String repeatInterval;

    private Long triggerTime;

    private Integer taskStatus;

    public TimeTask(BaseTaskEntity baseTaskEntity){
        super(baseTaskEntity);
    }
}
