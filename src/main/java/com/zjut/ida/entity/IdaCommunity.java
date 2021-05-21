package com.zjut.ida.entity;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Veloma on 2021/4/28.
 */
@NodeEntity
@Data
@ToString
public class IdaCommunity {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String community;


}
