package com.intellipro.action.action.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="active_node")
@NoArgsConstructor
@Data
public class ActiveNode implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="nodeId")
    private Long nodeId;

    @ManyToOne(targetEntity = ActiveJourney.class)
    @JoinColumn(name="node_journey_id",referencedColumnName = "id")
    private ActiveJourney activeJourney;

    @OneToMany(mappedBy = "activeNode")
    private List<ActiveAudience> activeAudienceList = new ArrayList<>();

    public ActiveNode(Long nodeId, ActiveJourney activeJourney) {
        this.nodeId = nodeId;
        this.activeJourney = activeJourney;
    }

}
