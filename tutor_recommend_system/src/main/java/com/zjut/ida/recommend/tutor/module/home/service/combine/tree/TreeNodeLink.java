package com.zjut.ida.recommend.tutor.module.home.service.combine.tree;

import lombok.Getter;
import lombok.Setter;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Getter
@Setter
public class TreeNodeLink {
    /**
     * 父节点
     */
    private String parentNodeId;
    /**
     * 子节点
     */
    private String childNodeId;
    /**
     * 限定值
     */
    private Integer ruleLimitValue;
}
