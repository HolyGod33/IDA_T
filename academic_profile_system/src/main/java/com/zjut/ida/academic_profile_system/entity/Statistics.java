package com.zjut.ida.academic_profile_system.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author kokoryh on 2022/6/1
 */
@Data
public class Statistics {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "papers")
    private Integer papers;

    @CsvBindByName(column = "citation")
    private Integer citation;

    @CsvBindByName(column = "partnerCount")
    private Integer partnerCount;

    @CsvBindByName(column = "keywordCount")
    private Integer keywordCount;

    @CsvBindByName(column = "nearlyTwoYearArticleCount")
    private Integer nearlyTwoYearArticleCount;

    @CsvBindByName(column = "citationSort")
    private String citationSort;

}
