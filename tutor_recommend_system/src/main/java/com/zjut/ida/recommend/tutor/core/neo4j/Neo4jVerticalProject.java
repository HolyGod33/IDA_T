package com.zjut.ida.recommend.tutor.core.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author wly
 * @date 2021/4/29 15:40
 */
@Data
@Node("VerticalProject")
public class Neo4jVerticalProject {
    @Id
    private String sourceId;
    private String leader;
    private String actualMoney;
    private String approvalDate;
    private String endDate;
    private String nature;
    private String level;
    private String cooperators;
    private String source;
    private String organization;
    private String name;
    private String state;
    private String planedMoney;
    private String startDate;
}
