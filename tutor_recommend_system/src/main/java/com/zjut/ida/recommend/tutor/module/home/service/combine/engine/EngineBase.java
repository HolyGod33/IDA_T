package com.zjut.ida.recommend.tutor.module.home.service.combine.engine;

import com.zjut.ida.recommend.tutor.module.home.service.combine.TreeRich;
import com.zjut.ida.recommend.tutor.module.home.service.combine.logic.LogicFilter;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.EngineResult;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNode;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeRoot;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;
import com.zjut.ida.recommend.tutor.utils.enums.TreeNodeType;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Slf4j
public abstract class EngineBase extends EngineConfig implements IEngine {
    @Override
    public abstract EngineResult process(TreeRich treeRich, Map<RuleKeyType, Integer> decisionMatter);

    protected TreeNode engineDecisionMaker(TreeRich treeRich, Map<RuleKeyType, Integer> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<String, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        String rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode currTreeNode = treeNodeMap.get(rootNodeId);
        // 节点类型；叶子、果实
        while (currTreeNode.getNodeType() == TreeNodeType.LEAF) {
            RuleKeyType ruleKey = currTreeNode.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            // 获取决策值
            Integer matterValue = logicFilter.matterValue(decisionMatter);
            // 决策，获取下一个节点ID
            String nextNodeId = logicFilter.filter(matterValue, currTreeNode.getTreeNodeLinkList());
            currTreeNode = treeNodeMap.get(nextNodeId);
            log.info("决策树引擎=>{} treeNode：{} ruleKey：{} matterValue：{}",
                    treeRoot.getTreeName(),
                    currTreeNode.getTreeNodeId(),
                    ruleKey,
                    matterValue);
        }
        return currTreeNode;
    }

}
