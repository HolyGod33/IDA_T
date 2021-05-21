package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

/**
 * @author Veloma on 2020/10/21.
 */
@Data
@NoArgsConstructor
@ToString
@NodeEntity
public class Achievement {
    private List<Article> articleList;
    private List<VerticalProject> verticalProjectList;
    private List<HorizontalProject> horizontalProjectList;
    private List<Patent> patentList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    public List<VerticalProject> getVerticalProjectList() {
        return verticalProjectList;
    }

    public void setVerticalProjectList(List<VerticalProject> verticalProjectList) {
        this.verticalProjectList = verticalProjectList;
    }

    public List<HorizontalProject> getHorizontalProjectList() {
        return horizontalProjectList;
    }

    public void setHorizontalProjectList(List<HorizontalProject> horizontalProjectList) {
        this.horizontalProjectList = horizontalProjectList;
    }

    public List<Patent> getPatentList() {
        return patentList;
    }

    public void setPatentList(List<Patent> patentList) {
        this.patentList = patentList;
    }
}
