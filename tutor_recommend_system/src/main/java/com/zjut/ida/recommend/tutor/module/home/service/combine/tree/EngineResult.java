package com.zjut.ida.recommend.tutor.module.home.service.combine.tree;

import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
@Getter
@Setter
@NoArgsConstructor
public class EngineResult {
    /**
     * 执行结果
     */
    private boolean isSuccess;
    /**
     * 果实节点ID
     */
    private String nodeId;
    /**
     * 果实节点值
     */
    private RecommendStrategyType nodeValue;

    public EngineResult(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public EngineResult(String nodeId, RecommendStrategyType nodeValue) {
        this.isSuccess = true;
        this.nodeId = nodeId;
        this.nodeValue = nodeValue;
    }
}
