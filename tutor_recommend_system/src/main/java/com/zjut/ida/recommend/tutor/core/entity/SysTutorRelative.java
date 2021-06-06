package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * lsh结果集
 *
 * @author wly
 * @date 2021/6/6 20:37
 */
@Data
@TableName("sys_tutor_relative")
public class SysTutorRelative {
    /**
     * 自增id
     */
    @TableId(value = "relative_id", type = IdType.AUTO)
    private Long relativeId;

    /**
     * 导师重映射id
     */
    private Long tutorRemapId;

    /**
     * 相关导师列表
     */
    private String tutorRelativeIdList;
}
