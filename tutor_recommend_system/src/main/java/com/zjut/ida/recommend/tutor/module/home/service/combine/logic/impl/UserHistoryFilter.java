package com.zjut.ida.recommend.tutor.module.home.service.combine.logic.impl;

import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.BaseLogic;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public class UserHistoryFilter extends BaseLogic {

    @Override
    public Integer matterValue(Map<RuleKeyType, Integer> decisionMatter) {
        return decisionMatter.get(RuleKeyType.HAS_HISTORY);
    }

}
