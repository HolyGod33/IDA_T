package com.zjut.ida.recommend.tutor.module.home.service.combine.engine.impl;

import com.zjut.ida.recommend.tutor.module.home.service.combine.TreeRich;
import com.zjut.ida.recommend.tutor.module.home.service.combine.engine.EngineBase;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.EngineResult;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNode;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public class TreeEngineHandle extends EngineBase {

    @Override
    public EngineResult process(TreeRich treeRich, Map<RuleKeyType, Integer> decisionMatter) {
        // 决策流程
        TreeNode treeNode = engineDecisionMaker(treeRich, decisionMatter);
        // 决策结果
        return new EngineResult(treeNode.getTreeNodeId(), treeNode.getNodeValue());
    }

}
