package com.zjut.ida.recommend.tutor.module.home.service.combine.engine;

import com.zjut.ida.recommend.tutor.module.home.service.combine.TreeRich;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.EngineResult;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public interface IEngine {

    EngineResult process(TreeRich treeRich, final Map<RuleKeyType, Integer> decisionMatter);

}
