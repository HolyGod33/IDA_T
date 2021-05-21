package com.zjut.ida.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Casterx on 2019/11/8.
 */
@NodeEntity
@Data
public class Catalog {

    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String type;
    private Integer weight;


}
