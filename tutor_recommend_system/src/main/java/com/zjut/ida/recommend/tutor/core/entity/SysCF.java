package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 协同过滤数据
 *
 * @author wly
 * @date 2021/4/22 10:42
 */
@Data
@TableName("sys_cf")
public class SysCF {

    /**
     * 协同过滤数据id
     */
    @TableId(value = "cf_id", type = IdType.AUTO)
    private Long cfId;

    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 学生重映射id
     */
    private Long studentRemapId;

    /**
     * 教师在neo4j中的id
     */
    private Long tutorNeo4jId;

    /**
     * 教师重映射id
     */
    private Long tutorRemapId;

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
