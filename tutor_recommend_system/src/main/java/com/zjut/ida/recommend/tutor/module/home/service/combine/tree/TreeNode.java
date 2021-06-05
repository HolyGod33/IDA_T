package com.zjut.ida.recommend.tutor.module.home.service.combine.tree;

import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;
import com.zjut.ida.recommend.tutor.utils.enums.TreeNodeType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Getter
@Setter
public class TreeNode {
    /**
     * 规则树节点ID
     */
    private String treeNodeId;
    /**
     * 节点类型；叶子、果实
     */
    private TreeNodeType nodeType;
    /**
     * 果实值
     */
    private RecommendStrategyType nodeValue;
    /**
     * 规则Key
     */
    private RuleKeyType ruleKey;
    /**
     * 节点链路
     */
    private List<TreeNodeLink> treeNodeLinkList;
    /**
     * 规则描述
     */
    private String ruleDesc;
}
