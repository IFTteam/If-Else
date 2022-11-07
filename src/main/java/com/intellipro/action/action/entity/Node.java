package com.intellipro.action.action.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class Node extends BaseEntity{
    @Id
    @SequenceGenerator(
            name = "node_sequence",
            sequenceName = "node_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "node_sequence"
    )
    private long nodeId;

    private Long frontEndId;
    private String name;
    private String type;

    private Integer headOrTail;
    private String status;

    @ElementCollection
    private List<Long> nexts = new ArrayList<>();
    @ElementCollection
    private List<Long> lasts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY,targetEntity = triggerType_node_relation.class)
    @JoinColumn(name="TNR_id",referencedColumnName = "id")
    private triggerType_node_relation triggertype_node_relation;

    private String sNexts;
    private String sLasts;



    //Make Sure that sNexts is not empty when call this. sNexts->nexts
    public void nextsDeserialize(){
        nexts = new ArrayList<>();
        String[] s = sNexts.split(" ");
        for (String value : s) {
            if(!value.isEmpty()){
                nexts.add(Long.parseLong(value));
            }

        }
    }
    //nexts->sNexts
    public void nextsSerialize(){
        StringBuffer buffer = new StringBuffer();
        for(Long num:nexts){
            buffer.append(num.toString());
        }
        sNexts = buffer.toString();
    }

}
