package com.zjut.ida.entity;

import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @author Casterx on 2020/7/17.
 */
@NodeEntity
@Data
@ToString
public class Pcs {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long pcsid;
    private String organization;
    private String affiliate;
    private Integer community;


}
