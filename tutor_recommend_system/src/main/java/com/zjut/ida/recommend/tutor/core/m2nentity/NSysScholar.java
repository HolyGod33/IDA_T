package com.zjut.ida.recommend.tutor.core.m2nentity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


/**
 * @author Casterx on 2022/5/1.
 */
@Node("Scholar")
@Data
public class NSysScholar {


    @GeneratedValue
    @Id
    private Long neo4jId;

    @Property("tutorRemapId")
    private Long tutorId;

    @Property("name")
    private String tutorName;


    @Relationship("Choose")
    @JsonIgnoreProperties
    private List<NSysStudent> studentList;


}
