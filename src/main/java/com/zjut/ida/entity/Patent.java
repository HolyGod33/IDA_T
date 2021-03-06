package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author Casterx on 2019/10/27.
 */
@Data
@NoArgsConstructor
@NodeEntity
@ToString
public class Patent {

    @Id
    @GeneratedValue
    private Long id;

    private String schoolId;
    private String firstInventor;
    private String inventors;
    private String organization;
    private String name;
    private String type;
    private String applyNumber;
    private String applicant;
    private String applyDate;
    private String state;
    private String honoree;
    Achievement achievement;

    private String authorizationDate;

    @Relationship(type = "FirstInvent", direction = Relationship.INCOMING)
    private Scholar firstScholar;

    @Relationship(type = "SecondInvent", direction = Relationship.INCOMING)
    private List<Scholar> scholarList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
