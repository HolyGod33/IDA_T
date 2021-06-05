package com.zjut.ida.recommend.tutor.utils.enums;

/**
 * @author wly
 * @date 2021/6/1 15:06
 */
public enum RecommendStrategyType {
    // 按模型预测结果获取推荐列表
    KGAT,
    // 按榜单获取推荐列表
    RANKING,
    // 按榜单和兴趣爱好获取推荐列表
    RANKING_FAVORITE,
    // 按榜单和历史记录获取推荐列表
    RANKING_HISTORY
}
