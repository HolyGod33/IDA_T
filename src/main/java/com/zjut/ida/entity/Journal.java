package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Casterx on 2019/10/24.
 */
@Data
@ToString
@NodeEntity
@NoArgsConstructor
public class Journal {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer weight;

    @Relationship(type = "Contains",direction = Relationship.INCOMING)
    private Catalog catalog;

}
