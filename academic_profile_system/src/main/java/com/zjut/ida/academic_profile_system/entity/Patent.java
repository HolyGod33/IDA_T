package com.zjut.ida.academic_profile_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

/**
 * @author kokoryh on 2022/5/7
 */
@Data
@NoArgsConstructor
@NodeEntity
@ToString
public class Patent {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String inventors;
    private String firstInventor;

    private String applyDate;
    private String applyNumber;
    private String authorizationDate;
    private String state;
    private String type;

    private String applicant;
    private String organization;

    @Relationship(type = "FirstInvent", direction = Relationship.INCOMING)
    private Scholar firstScholar;

    @Relationship(type = "SecondInvent", direction = Relationship.INCOMING)
    private List<Scholar> scholarList;

}
