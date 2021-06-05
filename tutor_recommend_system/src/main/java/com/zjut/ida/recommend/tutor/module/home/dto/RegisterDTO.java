package com.zjut.ida.recommend.tutor.module.home.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/26 19:24
 */
@Data
public class RegisterDTO {
    private String studentId;
    private String password;
    private String studentName;
    private String collegeName;
    private String studentClass;
    private Integer studentGender;
    private Integer admissionYear;
    private List<String> studySpecialityList;
}
