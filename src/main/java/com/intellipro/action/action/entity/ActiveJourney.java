package com.intellipro.action.action.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="active_journey")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActiveJourney {
    @Id
    @GeneratedValue

    private Long id;
    private Long journeyId;

    @OneToMany(mappedBy = "activeJourney")
    private List<ActiveNode> activeNodeList = new ArrayList<>();


}
