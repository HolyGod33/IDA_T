package com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl;

import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.IStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.ILabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl.KgatModelLabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl.RankingFavoriteLabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl.RankingHistoryLabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl.RankingLabelStrategy;
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
public class LabelStrategyFactory implements IStrategyFactory {
    @Autowired
    private KgatModelLabelStrategy kgatModelStrategy;
    @Autowired
    private RankingLabelStrategy rankingStrategy;
    @Autowired
    private RankingFavoriteLabelStrategy favoriteStrategy;
    @Autowired
    private RankingHistoryLabelStrategy historyStrategy;

    private Map<Integer, ILabelStrategy> map;

    @PostConstruct
    private void postConstruct() {
        List<ILabelStrategy> strategies = new ArrayList<>();

        strategies.add(kgatModelStrategy);
        strategies.add(rankingStrategy);
        strategies.add(favoriteStrategy);
        strategies.add(historyStrategy);

        map = strategies.stream().collect(Collectors.toMap(ILabelStrategy::getType, strategy -> strategy));
    }

    @Override
    public ILabelStrategy get(Integer type) {
        return map.get(type);
    }
}
