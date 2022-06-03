package com.zjut.ida.academic_profile_system.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author kokoryh on 2022/6/1
 */
@Data
public class Interest {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "year")
    private String year;

    @CsvBindByName(column = "count")
    private Integer count;

    @CsvBindByName(column = "keyword")
    private String keyword;

}
