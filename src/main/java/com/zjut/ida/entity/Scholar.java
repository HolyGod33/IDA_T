package com.zjut.ida.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author Casterx on 2019/10/10.
 */
@Data
@NoArgsConstructor
@NodeEntity
@ToString
@JsonIgnoreProperties(value = {"scholarId", "entityId"})
public class Scholar {

    @Id
    @GeneratedValue
    private Long id;

    @Property("teacher_id")
    private String teacherId;

    private String name;
    private String sex;
    private String organization;
    @Property("sub_organization")
    private String subOrganization;
    private String education;
    @Property("study_speciality")
    private String studySpeciality;
    private String title;
    @Property("tutor_type")
    private String tutorType;
    private String email;
    private String maxPageCount;
    private String affiliate;
    //    private String scholarId;
//    private String entityId;
    private String doMain;

//    private Article article;

    private String homepage;
    private String address;
    private String experience;
    private String bio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Scholar(String name, String organization, String email, String studySpeciality) {
        this.name = name;
        this.organization = organization;
        this.email = email;
        this.studySpeciality = studySpeciality;
    }

}
