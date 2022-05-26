package com.zjut.ida.recommend.tutor.core.m2nentity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zjut.ida.recommend.tutor.core.neo4j.Neo4jTutor;
import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;


/**
 * @author Casterx on 2022/5/1.
 */
@Node("SysStudent")
@Data
public class NSysStudent {


    @GeneratedValue
    @Id
    private Long id;

    /**
     * 学生学号
     */
    @Property("studentID")
    private String studentId;

    /**
     * 加密盐值
     */
    private String hashSalt;

    /**
     * 密码
     */
    private String password;

    /**
     * 学生姓名
     */
    @Property("name")
    private String studentName;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 学生班级
     */
    @Property("className")
    private String studentClass;

    /**
     * 0 男 1 女
     */
    private Integer studentGender;

    /**
     * 入学时间
     */
    private Integer admissionYear;

    /**
     * 研究方向
     */
    private String studySpeciality;


    @Relationship(value = "Choose")
    @JsonIgnoreProperties
    @JsonIgnore
    private List<Neo4jTutor>  tutorList;


    @Relationship(value = "History")
    @JsonIgnoreProperties
    @JsonIgnore
    private List<Neo4jTutor>  historyTutorList;
//    /**
//     * 逻辑删除
//     */
//    @TableLogic
//    private Boolean delFlag;

//    /**
//     * 创建时间
//     */
//    @TableField(fill = FieldFill.INSERT)
//    private LocalDateTime createTime;
//
//    /**
//     * 更新时间
//     */
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private LocalDateTime updateTime;



}
