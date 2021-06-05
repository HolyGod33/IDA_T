package com.zjut.ida.recommend.tutor.module.dashboard.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author wly
 * @date 2021/4/25 16:44
 */
@Data
public class TutorVO {
    private Long id;
    private String name;
    private String title;
    private String tutorType;
    private String organization;
    private String subOrganization;
    private String studySpeciality;
    private String email;
    private LocalDateTime createTime;
}
