package com.zjut.ida.academic_profile_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author kokoryh on 2022/5/7
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
    private String title;
    @Property("tutor_type")
    private String tutorType;

    private String affiliate;
    private String organization;
    @Property("sub_organization")
    private String subOrganization;
    private String doMain;
    @Property("study_speciality")
    private String studySpeciality;

    private String email;
    private String homepage;
    private String address;
    private String experience;
    private String education;
    private String bio;
    private String maxPageCount;

}
