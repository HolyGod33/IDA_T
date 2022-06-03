package com.zjut.ida.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * @author Casterx on 2019/10/10.
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
    private String keyWord;
    private String year;
    private String citeCount;
    private String partner;

    private String journalAttr;
    @Property("abstract")
    private String articleAbstract;

    @Relationship(type = "Publish", direction = Relationship.INCOMING)
    private List<Scholar> scholarList;

    @Relationship(type = "Published", direction = Relationship.OUTGOING)
    private Journal journal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCiteCount() {
        return citeCount;
    }

    public void setCiteCount(String citeCount) {
        this.citeCount = citeCount;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

}
