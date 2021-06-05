package com.zjut.ida.recommend.tutor.module.home.service.factory.combine;

import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNode;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNodeLink;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;
import com.zjut.ida.recommend.tutor.utils.enums.TreeNodeType;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/6/3 15:18
 */
public abstract class BaseDecisionTreeFactory implements IDecisionTreeFactory  {
    protected TreeNode createLeafNode(String nodeId, RuleKeyType ruleKey, String ruleDesc) {
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeNodeId(nodeId);
        treeNode.setNodeType(TreeNodeType.LEAF);
        treeNode.setNodeValue(null);
        treeNode.setRuleKey(ruleKey);
        treeNode.setRuleDesc(ruleDesc);
        return treeNode;
    }

    protected TreeNode createFruitNode(String nodeId, RecommendStrategyType nodeValue) {
        TreeNode treeNode = new TreeNode();
        treeNode.setTreeNodeId(nodeId);
        treeNode.setNodeType(TreeNodeType.FRUIT);
        treeNode.setNodeValue(nodeValue);
        return treeNode;
    }

    protected TreeNodeLink createNodeLink(String parentId, String childId, Integer ruleLimitValue) {
        TreeNodeLink treeNodeLink = new TreeNodeLink();
        treeNodeLink.setParentNodeId(parentId);
        treeNodeLink.setChildNodeId(childId);
        treeNodeLink.setRuleLimitValue(ruleLimitValue);
        return treeNodeLink;
    }
    protected void createSubTree(TreeNode treeNode, TreeNodeLink... treeNodeLinks) {
        treeNode.setTreeNodeLinkList(Arrays.stream(treeNodeLinks).collect(Collectors.toList()));
    }
}
