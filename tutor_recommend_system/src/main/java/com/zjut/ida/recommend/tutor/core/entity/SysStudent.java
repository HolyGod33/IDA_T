package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Data
@TableName("sys_student")
public class SysStudent {

    /**
     * 学生学号
     */
    @TableId
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
    private String studentName;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 学生班级
     */
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

    /**
     * 逻辑删除
     */
    @TableLogic
    private Boolean delFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
