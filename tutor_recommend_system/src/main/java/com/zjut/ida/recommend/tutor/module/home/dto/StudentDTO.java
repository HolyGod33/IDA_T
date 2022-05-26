package com.zjut.ida.recommend.tutor.module.home.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author wly
 * @date 2021/6/4 10:18
 */
@Data
public class StudentDTO {
    private String studentId;
    private String password;
    private String studentName;
    private String studentClass;
    private Integer studentGender;
    private Integer admissionYear;
    private String collegeName;
    private List<String> studySpecialityList;

    public void checkField() {
        if (StringUtils.isBlank(password)) {
            password = null;
        }
        if (StringUtils.isBlank(studentName)) {
            studentName = null;
        }
        if (StringUtils.isBlank(studentClass)) {
            studentClass = null;
        }
    }
}
