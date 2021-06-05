package com.zjut.ida.recommend.tutor.module.home.service.combine;

import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeNode;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.TreeRoot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Getter
@Setter
@AllArgsConstructor
public class TreeRich {
    /**
     * 树根信息
     */
    private TreeRoot treeRoot;
    /**
     * 树节点ID -> 子节点
     */
    private Map<String, TreeNode> treeNodeMap;
}
