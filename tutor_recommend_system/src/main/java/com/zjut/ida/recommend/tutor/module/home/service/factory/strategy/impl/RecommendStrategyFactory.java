package com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl;

import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.IStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.IRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.KgatModelRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.RankingFavoriteRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.RankingHistoryRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.RankingRecommendStrategy;
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
public class RecommendStrategyFactory implements IStrategyFactory {
    @Autowired
    private KgatModelRecommendStrategy kgatModelStrategy;
    @Autowired
    private RankingRecommendStrategy rankingStrategy;
    @Autowired
    private RankingFavoriteRecommendStrategy favoriteStrategy;
    @Autowired
    private RankingHistoryRecommendStrategy historyStrategy;

    private Map<Integer, IRecommendStrategy> map;

    @PostConstruct
    private void postConstruct() {
        List<IRecommendStrategy> strategies = new ArrayList<>();

        strategies.add(kgatModelStrategy);
        strategies.add(rankingStrategy);
        strategies.add(favoriteStrategy);
        strategies.add(historyStrategy);

        map = strategies.stream().collect(Collectors.toMap(IRecommendStrategy::getType, strategy -> strategy));
    }

    @Override
    public IRecommendStrategy get(Integer type) {
        return map.get(type);
    }
}
