package com.zjut.ida.entity;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

/**
 * @author Casterx on 2020/7/17.
 */
@RelationshipEntity(type = "Pcl")
@Data
@ToString
public class Pcl {

    @Id
    @GeneratedValue
    private Long id;

    private Integer value;

//    @StartNode
//    private Pcs source;
//
//    @EndNode
//    private Pcs target;



}
