package com.intellipro.action.action.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="active_audience")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveAudience implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name="AudienceId")
    private Long AudienceId;

    @ManyToOne
    @JoinColumn(name="audience_node_id")
    @JsonIgnore
    private ActiveNode activeNode;
}
