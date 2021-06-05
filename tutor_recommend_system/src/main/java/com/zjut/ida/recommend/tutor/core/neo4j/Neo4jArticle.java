package com.zjut.ida.recommend.tutor.core.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author wly
 * @date 2021/4/29 14:42
 */
@Data
@Node("Article")
public class Neo4jArticle {
    @Id
    @GeneratedValue
    private Long id;
    private String journalAttr;
    private String partner;
    private String year;
    private String citeCount;
    private String title;
    private String keyWord;
}
