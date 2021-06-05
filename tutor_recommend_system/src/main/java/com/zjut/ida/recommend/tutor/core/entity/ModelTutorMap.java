package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author wly
 * @date 2021/4/26 14:05
 */
@Data
@TableName("model_tutor_map")
public class ModelTutorMap {
    @TableId
    private Long tutorId;
    private Long neo4jId;
    private String tutorName;
}
