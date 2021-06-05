package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
@Data
@TableName("model_student_map")
public class ModelStudentMap {
    @TableId
    private Long studentId;
    private String studentName;
}
