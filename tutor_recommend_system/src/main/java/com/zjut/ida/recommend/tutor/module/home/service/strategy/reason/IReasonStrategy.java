package com.zjut.ida.recommend.tutor.module.home.service.strategy.reason;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.IStrategy;

/**
 * @author wly
 * @date 2021/6/2 13:31
 */
public interface IReasonStrategy extends IStrategy {
    JSONObject recommendReasonStrategy(Long neo4jId);
}
