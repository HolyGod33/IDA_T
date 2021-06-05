package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 学院字典表
 *
 * @author wly
 * @date 2021/5/30 14:00
 */
@Data
@TableName("sys_dict_college")
public class SysDictCollege {

    /**
     * 学院主键
     */
    @TableId(value = "college_id", type = IdType.AUTO)
    private Long collegeId;

    /**
     * 学院名称
     */
    private String collegeName;
}
