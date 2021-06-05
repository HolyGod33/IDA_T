package com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl;

import com.github.pagehelper.PageHelper;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.dao.SysStudentRecommendMapper;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.BaseRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wly
 * @date 2021/5/31 20:33
 */
@Component
public class KgatModelRecommendStrategy extends BaseRecommendStrategy {
    @Autowired
    private SysStudentRecommendMapper recommendMapper;
    @Autowired
    private SysStudentHolder studentHolder;

    @Override
    public int getType() {
        return RecommendStrategyType.KGAT.ordinal();
    }

    @Override
    public SimplePageInfo<TutorVO> recommendStrategy(Integer pageNum, Integer pageSize, String studySpeciality) {
        String studentId = studentHolder.getStudentId();
        // 若表中没有数据，则插入
        insertRecommendListIfAbsent(studentId);

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        SimplePageInfo<Long> pageInfo = new SimplePageInfo<>(recommendMapper.getTutorNeo4jIdListByStudentId(studentId, studySpeciality.trim()));

        // 获取导师其它信息
        List<TutorVO> recommendList = multiThreadGetTutorVOList(pageInfo.getList());

        return new SimplePageInfo<>(pageInfo, recommendList);
    }
}
