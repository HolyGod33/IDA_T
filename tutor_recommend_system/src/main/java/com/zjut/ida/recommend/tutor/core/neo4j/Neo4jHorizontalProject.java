package com.zjut.ida.recommend.tutor.core.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author wly
 * @date 2021/4/29 15:40
 */
@Data
@Node("HorizontalProject")
public class Neo4jHorizontalProject {
    @Id
    private String id;
    private String area;
    private String leader;
    private String actualMoney;
    private String boss;
    private String approvalDate;
    private String endDate;
    private String nature;
    private String cooperators;
    private String organization;
    private String name;
    private String planedMoney;
    private String startDate;
}
