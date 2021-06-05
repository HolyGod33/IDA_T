package com.zjut.ida.recommend.tutor.module.home.service.strategy.label;

import com.zjut.ida.recommend.tutor.module.home.service.strategy.IStrategy;

import java.util.List;

/**
 * @author wly
 * @date 2021/6/2 13:31
 */
public interface ILabelStrategy extends IStrategy {
    Integer LABEL_MAX_NUM = 5;
    List<String> labelStrategy();
}
