package com.intellipro.action.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Journey {
    @Id
    @SequenceGenerator(
            name = "journey_sequence",
            sequenceName = "journey_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "journey_sequence"
    )
    private long journeyId;

    private String journeyName;
}
