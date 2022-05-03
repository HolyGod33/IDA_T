package com.zjut.ida.recommend.tutor.core.m2nentity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Casterx on 2022/5/2.
 */
@Node("SysCollege")
@Data
public class NSysCollege {


    @GeneratedValue
    @Id
    private Long id;


    /**
     * id
     */
    private int collegeId;

    /**
     * 学院名称
     */
    private String collegeName;


}
