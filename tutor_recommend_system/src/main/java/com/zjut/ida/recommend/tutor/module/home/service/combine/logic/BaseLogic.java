package com.zjut.ida.recommend.tutor.module.home.service.combine.logic;

import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNodeLink;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public abstract class BaseLogic implements LogicFilter {

    @Override
    public String filter(Integer matterValue, List<TreeNodeLink> treeNodeLinkList) {
        for (TreeNodeLink nodeLink : treeNodeLinkList) {
            if (decisionLogic(matterValue, nodeLink)) {
                return nodeLink.getChildNodeId();
            }
        }
        return "0";
    }

    @Override
    public abstract Integer matterValue(Map<RuleKeyType, Integer> decisionMatter);

    private boolean decisionLogic(Integer matterValue, TreeNodeLink nodeLink) {
        return matterValue.equals(nodeLink.getRuleLimitValue());
    }

}
