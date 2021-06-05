package com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl;

import com.alibaba.fastjson.JSONObject;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.IReasonStrategy;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingHistoryReasonStrategy implements IReasonStrategy {
    @Autowired
    private RankingReasonStrategy rankingReasonStrategy;

    @Override
    public JSONObject recommendReasonStrategy(Long neo4jId) {
        return rankingReasonStrategy.recommendReasonStrategy(neo4jId);
    }

    @Override
    public int getType() {
        return RecommendStrategyType.RANKING_HISTORY.ordinal();
    }
}
