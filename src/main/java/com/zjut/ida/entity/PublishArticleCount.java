package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * @author Casterx on 2019/10/31.
 */
@QueryResult
@Data
@NoArgsConstructor
@ToString
public class PublishArticleCount {

    private String year;
    private Integer count;

}
