package com.zjut.ida.recommend.tutor.module.home.service.combine.logic;


import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNodeLink;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     *
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点Id
     */
    String filter(Integer matterValue, List<TreeNodeLink> treeNodeLineInfoList);

    /**
     * 获取决策值
     *
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    Integer matterValue(Map<RuleKeyType, Integer> decisionMatter);

}
