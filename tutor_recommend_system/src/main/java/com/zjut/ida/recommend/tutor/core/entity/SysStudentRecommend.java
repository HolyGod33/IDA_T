package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 学生推荐表
 *
 * @author wly
 * @date 2021/5/13 15:07
 */
@Data
@TableName("sys_student_recommend")
public class SysStudentRecommend {

    /**
     * 推荐列表id
     */
    @TableId(value = "recommend_id", type = IdType.AUTO)
    private Long recommendId;

    /**
     * 学生学号
     */
    private String studentId;

    /**
     * 导师在neo4j中的id
     */
    private Long tutorNeo4jId;

    /**
     * 推荐顺序
     */
    private Integer recommendOrder;

    /**
     * 导师研究方向
     */
    private String tutorStudySpeciality;

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
