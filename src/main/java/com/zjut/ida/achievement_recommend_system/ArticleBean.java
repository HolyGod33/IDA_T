package com.zjut.ida.achievement_recommend_system;

//包括Article、Patent、Horizontal Project中的各属性
public class ArticleBean {

    private String label; //判断是Article、Patent或者Horizontal Project
    private int id;
    private String title;
    private String partner;
    private String citeCount;
    private String year;
    private String keyword;
    private String type;
    private String state;
    private String applicant;
    private String organization;
    private String nature;
    private String area;
    private String boss;
    private String planedMoney;

    public ArticleBean() {
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getTitle() {
        return title;
    }

    public String getPartner() {
        return partner;
    }

    public String getCiteCount() {
        return citeCount;
    }

    public String getYear() {
        return year;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getType() {
        return type;
    }

    public String getState() {
        return state;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getOrganization() {
        return organization;
    }

    public String getNature() {
        return nature;
    }

    public String getArea() {
        return area;
    }

    public String getBoss() {
        return boss;
    }

    public String getPlanedMoney() {
        return planedMoney;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public void setPartner(String partner) {
        this.partner=partner;
    }

    public void setCiteCount(String citeCount) {
        this.citeCount=citeCount;
    }

    public void setYear(String year) {
        this.year=year;
    }

    public void setKeyword(String keyword) {
        this.keyword=keyword;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public void setPlanedMoney(String planedMoney) {
        this.planedMoney = planedMoney;
    }

}
