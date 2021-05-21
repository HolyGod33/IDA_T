package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.List;

/**
 * @author Casterx on 2019/10/23.
 */
@QueryResult
@Data
@NoArgsConstructor
@ToString
public class Partner {

    private Scholar scholar;
    private Integer count;
    private String belongGroup;
    private Article article;   //new
    private HorizontalProject horizontalProject;    //new
    private VerticalProject verticalProject;     //new
    private Patent patent; //new
    private int belongRank;

    public Scholar getScholar() {
        return scholar;
    }

    public void setScholar(Scholar scholar) {
        this.scholar = scholar;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getBelongGroup() {
        return belongGroup;
    }

    public void setBelongGroup(String belongGroup) {
        this.belongGroup = belongGroup;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public HorizontalProject getHorizontalProject() {
        return horizontalProject;
    }

    public void setHorizontalProject(HorizontalProject horizontalProject) {
        this.horizontalProject = horizontalProject;
    }

    public VerticalProject getVerticalProject() {
        return verticalProject;
    }

    public void setVerticalProject(VerticalProject verticalProject) {
        this.verticalProject = verticalProject;
    }

    public Patent getPatent() {
        return patent;
    }

    public void setPatent(Patent patent) {
        this.patent = patent;
    }

    public int getBelongRank() {
        return belongRank;
    }

    public void setBelongRank(int belongRank) {
        this.belongRank = belongRank;
    }
}
