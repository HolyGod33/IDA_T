package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * @author Casterx on 2019/10/18.
 */
@Data
@NoArgsConstructor
@ToString
@NodeEntity
public class All {

    private List<Scholar> scholarList;
    private List<Article> articleList;
    private List<VerticalProject> verticalProjectList;
    private List<HorizontalProject> horizontalProjectList;
    private List<Patent> patentList;




}
