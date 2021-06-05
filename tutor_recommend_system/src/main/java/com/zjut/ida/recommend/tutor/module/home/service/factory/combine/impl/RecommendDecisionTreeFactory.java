package com.zjut.ida.recommend.tutor.module.home.service.factory.combine.impl;

import com.zjut.ida.recommend.tutor.module.home.service.combine.TreeRich;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNode;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNodeLink;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeRoot;
import com.zjut.ida.recommend.tutor.module.home.service.factory.combine.BaseDecisionTreeFactory;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wly
 * @date 2021/6/1 15:24
 */
public class RecommendDecisionTreeFactory extends BaseDecisionTreeFactory {
    private final TreeRich treeRich;

    public RecommendDecisionTreeFactory() {
        // 节点：1
        TreeNode treeNode_01 = createLeafNode("1", RuleKeyType.IS_REGISTER, "用户是否注册");
        // 链接：1->11
        TreeNodeLink treeNodeLink_11 = createNodeLink("1", "11", 1);
        // 链接：1->12
        TreeNodeLink treeNodeLink_12 = createNodeLink("1", "12", 0);
        // 构建子树
        createSubTree(treeNode_01, treeNodeLink_11, treeNodeLink_12);

        // 节点：11
        TreeNode treeNode_11 = createLeafNode("11", RuleKeyType.IS_TRAIN, "模型是否训练该用户");
        // 链接：11->111
        TreeNodeLink treeNodeLink_111 = createNodeLink("11", "111", 1);
        // 链接：11->112
        TreeNodeLink treeNodeLink_112 = createNodeLink("11", "112", 0);
        // 构建子树
        createSubTree(treeNode_11, treeNodeLink_111, treeNodeLink_112);

        // 节点：12
        TreeNode treeNode_12 = createFruitNode("12", RecommendStrategyType.RANKING);

        // 节点：111
        TreeNode treeNode_111 = createFruitNode("111", RecommendStrategyType.KGAT);

        // 节点：112
        TreeNode treeNode_112 = createLeafNode("112", RuleKeyType.HAS_FAVORITE, "用户是否设置兴趣爱好");
        // 链接：112->1111
        TreeNodeLink treeNodeLink_1111 = createNodeLink("112", "1111", 1);
        // 链接：112->1112
        TreeNodeLink treeNodeLink_1112 = createNodeLink("112", "1112", 0);
        // 构建子树
        createSubTree(treeNode_112, treeNodeLink_1111, treeNodeLink_1112);

        // 节点：1111
        TreeNode treeNode_1111 = createFruitNode("1111", RecommendStrategyType.RANKING_FAVORITE);

        // 节点：1112
        TreeNode treeNode_1112 = createLeafNode("1112", RuleKeyType.HAS_HISTORY, "用户是否有历史记录");
        // 链接：1112->11111
        TreeNodeLink treeNodeLink_11111 = createNodeLink("1112", "11111", 1);
        // 链接：1112->11112
        TreeNodeLink treeNodeLink_11112 = createNodeLink("1112", "11112", 0);
        // 构建子树
        createSubTree(treeNode_1112, treeNodeLink_11111, treeNodeLink_11112);

        // 节点：11111
        TreeNode treeNode_11111 = createFruitNode("11111", RecommendStrategyType.RANKING_HISTORY);

        // 节点：11112
        TreeNode treeNode_11112 = createFruitNode("11112", RecommendStrategyType.RANKING);

        // 树根
        TreeRoot treeRoot = new TreeRoot();
        treeRoot.setTreeRootNodeId("1");
        treeRoot.setTreeName("推荐规则决策树");

        Map<String, TreeNode> treeNodeMap = new HashMap<>();
        treeNodeMap.put("1", treeNode_01);
        treeNodeMap.put("11", treeNode_11);
        treeNodeMap.put("12", treeNode_12);
        treeNodeMap.put("111", treeNode_111);
        treeNodeMap.put("112", treeNode_112);
        treeNodeMap.put("1111", treeNode_1111);
        treeNodeMap.put("1112", treeNode_1112);
        treeNodeMap.put("11111", treeNode_11111);
        treeNodeMap.put("11112", treeNode_11112);

        treeRich = new TreeRich(treeRoot, treeNodeMap);
    }

    @Override
    public TreeRich getTree() {
        return treeRich;
    }

    public static RecommendDecisionTreeFactory instance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        public static final RecommendDecisionTreeFactory INSTANCE = new RecommendDecisionTreeFactory();
    }
}
