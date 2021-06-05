package com.zjut.ida.recommend.tutor.core.neo4j;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author wly
 * @date 2021/4/28 19:36
 */
@Data
@Node("Teacher")
public class Neo4jTutor {
    @Id
    @GeneratedValue
    private Long id;
    @Property("tutor_type")
    private String tutorType;
    private String doMain;
    private String scholarId;
    private String education;
    private String teacherId;
    private String sex;
    private String entityId;
    @Property("sub_organization")
    private String subOrganization;
    private String title;
    private String maxPageCount;
    @Property("study_speciality")
    private String studySpeciality;
    private String organization;
    private String name;
    private String affiliate;
    private String email;
}
