package com.zjut.ida.recommend.tutor.core.m2nentity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @author Casterx on 2022/5/2.
 */
@Node("SysClass")
@Data
public class NSysClass {


    @GeneratedValue
    @Id
    private Long id;


    /**
     * id
     */
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
