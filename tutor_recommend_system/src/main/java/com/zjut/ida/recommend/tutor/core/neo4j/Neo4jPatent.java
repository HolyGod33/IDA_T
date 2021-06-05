package com.zjut.ida.recommend.tutor.core.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author wly
 * @date 2021/4/29 14:57
 */
@Data
@Node("Patent")
public class Neo4jPatent {
    @Id
    @GeneratedValue
    private Long id;
    private String authorizationDate;
    private String firstInventor;
    private String inventors;
    private String bonus;
    private String type;
    private String applicant;
    private String organization;
    private String name;
    private String state;
    private String applyDate;
    private String honoree;
    private String applyNumber;
}
