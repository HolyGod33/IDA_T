package com.zjut.ida.academic_profile_system.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @author kokoryh on 2022/5/7
 */
@Data
@NoArgsConstructor
@NodeEntity
@ToString
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String journalAttr;
    private String year;
    private String keyWord;
    @Property("abstract")
    private String articleAbstract;
    private String citeCount;
    private String partner;

    @Relationship(type = "Publish", direction = Relationship.INCOMING)
    private List<Scholar> scholarList;

}
