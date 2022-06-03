package com.zjut.ida.academic_profile_system.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author kokoryh on 2022/6/1
 */
@Data
public class CoAuthor {

    @CsvBindByName(column = "sourceID")
    private String sourceID;

    @CsvBindByName(column = "source")
    private String source;

    @CsvBindByName(column = "sourceOrg")
    private String sourceOrg;

    @CsvBindByName(column = "targetID")
    private String targetID;

    @CsvBindByName(column = "target")
    private String target;

    @CsvBindByName(column = "targetOrg")
    private String targetOrg;

    @CsvBindByName(column = "value")
    private Integer value;

}
