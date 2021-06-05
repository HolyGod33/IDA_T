package com.zjut.ida.recommend.tutor.module.home.service.factory.strategy;

import com.zjut.ida.recommend.tutor.module.home.service.strategy.IStrategy;

/**
 * @author wly
 * @date 2021/6/2 14:28
 */
public interface IStrategyFactory {
    IStrategy get(Integer type);
}
