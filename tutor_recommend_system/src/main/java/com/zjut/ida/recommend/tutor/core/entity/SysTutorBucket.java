package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * LSH分桶
 *
 * @author wly
 * @date 2021/5/16 13:43
 */
@Deprecated
@Data
@TableName("sys_tutor_bucket")
public class SysTutorBucket {

    /**
     * 导师重映射id
     */
    @TableId("tutor_remap_id")
    private Long tutorRemapId;

    /**
     * LSH桶
     */
    private Integer tutorBucket;
}
