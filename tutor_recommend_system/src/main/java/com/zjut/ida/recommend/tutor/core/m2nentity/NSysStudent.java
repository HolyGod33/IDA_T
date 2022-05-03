package com.zjut.ida.recommend.tutor.core.m2nentity;


import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;


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
