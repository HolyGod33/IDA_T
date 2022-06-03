package com.zjut.ida.academic_profile_system.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author kokoryh on 2022/6/1
 */
@Data
public class ScholarRelation {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "relation")
    private String relation;

}
