package com.zjut.ida.recommend.tutor.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 班级字典表
 *
 * @author wly
 * @date 2021/5/18 20:50
 */
@Data
@TableName("sys_dict_class")
public class SysDictClass {
    /**
     * 字典表主键
     */
    @TableId(value = "class_id", type = IdType.AUTO)
    private Long classId;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 入学年份
     */
    private Integer admissionYear;

    /**
     * 学院名称
     */
    private String collegeName;
}
