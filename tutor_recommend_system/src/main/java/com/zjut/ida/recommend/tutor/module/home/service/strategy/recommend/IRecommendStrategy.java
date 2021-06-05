package com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend;

import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.IStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;

/**
 * @author wly
 * @date 2021/5/31 20:23
 */
public interface IRecommendStrategy extends IStrategy {
    SimplePageInfo<TutorVO> recommendStrategy(Integer pageNum, Integer pageSize, String studySpeciality);
}
