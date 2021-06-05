package com.zjut.ida.recommend.tutor.module.home.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author wly
 * @date 2021/4/22 20:54
 */
@Data
@Builder
public class KgatModelRequest {
    List<Long> userId;
    List<Integer> itemRange;
    List<Integer> messDropout;
}
