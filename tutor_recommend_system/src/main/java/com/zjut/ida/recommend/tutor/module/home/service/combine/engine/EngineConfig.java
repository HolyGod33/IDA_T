package com.zjut.ida.recommend.tutor.module.home.service.combine.engine;

import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.LogicFilter;
import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.impl.ModelTrainFilter;
import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.impl.UserFavoriteFilter;
import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.impl.UserHistoryFilter;
import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.impl.UserRegisterFilter;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Getter
@Setter
public class EngineConfig {

    static Map<RuleKeyType, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put(RuleKeyType.IS_REGISTER, new UserRegisterFilter());
        logicFilterMap.put(RuleKeyType.IS_TRAIN, new ModelTrainFilter());
        logicFilterMap.put(RuleKeyType.HAS_FAVORITE, new UserFavoriteFilter());
        logicFilterMap.put(RuleKeyType.HAS_HISTORY, new UserHistoryFilter());
    }
}
