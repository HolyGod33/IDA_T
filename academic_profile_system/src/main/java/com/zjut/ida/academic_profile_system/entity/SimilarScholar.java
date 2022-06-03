package com.zjut.ida.academic_profile_system.entity;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

/**
 * @author kokoryh on 2022/6/1
 */
@Data
public class SimilarScholar {

    @CsvBindByName(column = "ID1")
    private String ID1;

    @CsvBindByName(column = "Scholar1")
    private String Scholar1;

    @CsvBindByName(column = "ID2")
    private String ID2;

    @CsvBindByName(column = "Scholar2")
    private String Scholar2;

    @CsvBindByName(column = "similarity")
    private String similarity;

}
