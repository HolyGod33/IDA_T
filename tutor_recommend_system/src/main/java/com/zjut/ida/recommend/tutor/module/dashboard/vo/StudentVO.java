package com.zjut.ida.recommend.tutor.module.dashboard.vo;

import lombok.Data;

/**
 * @author wly
 * @date 2021/5/2 17:51
 */
@Data
public class StudentVO {
    private String studentId;
    private String studentName;
    private String collegeName;
    private String studentClass;
    private Integer studentGender;
    private Integer admissionYear;
    private String studySpeciality;
}
