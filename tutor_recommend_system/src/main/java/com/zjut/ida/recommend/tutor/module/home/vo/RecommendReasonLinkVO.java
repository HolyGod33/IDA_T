package com.zjut.ida.recommend.tutor.module.home.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wly
 * @date 2021/5/12 14:14
 */
@Data
@AllArgsConstructor
public class RecommendReasonLinkVO {
    private String source;
    private String target;
    private String value;
}
