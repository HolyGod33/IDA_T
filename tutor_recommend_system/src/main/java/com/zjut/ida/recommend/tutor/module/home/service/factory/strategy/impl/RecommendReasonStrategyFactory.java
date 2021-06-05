package com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl;

import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.IStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.IReasonStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl.KgatModelReasonStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl.RankingFavoriteReasonStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl.RankingHistoryReasonStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.reason.impl.RankingReasonStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/6/1 15:10
 */
@Component
public class RecommendReasonStrategyFactory implements IStrategyFactory {
    @Autowired
    private KgatModelReasonStrategy kgatModelStrategy;
    @Autowired
    private RankingReasonStrategy rankingStrategy;
    @Autowired
    private RankingFavoriteReasonStrategy favoriteStrategy;
    @Autowired
    private RankingHistoryReasonStrategy historyStrategy;

    private Map<Integer, IReasonStrategy> map;

    @PostConstruct
    private void postConstruct() {
        List<IReasonStrategy> strategies = new ArrayList<>();

        strategies.add(kgatModelStrategy);
        strategies.add(rankingStrategy);
        strategies.add(favoriteStrategy);
        strategies.add(historyStrategy);

        map = strategies.stream().collect(Collectors.toMap(IReasonStrategy::getType, strategy -> strategy));
    }

    @Override
    public IReasonStrategy get(Integer type) {
        return map.get(type);
    }
}
