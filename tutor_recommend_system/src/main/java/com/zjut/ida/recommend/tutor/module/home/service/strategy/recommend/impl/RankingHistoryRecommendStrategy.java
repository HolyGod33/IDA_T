package com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl;

import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl.RankingHistoryLabelStrategy;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.BaseRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;
import com.zjut.ida.recommend.tutor.utils.PageUtils;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingHistoryRecommendStrategy extends BaseRecommendStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private RankingHistoryLabelStrategy labelStrategy;

    @Override
    public SimplePageInfo<TutorVO> recommendStrategy(Integer pageNum, Integer pageSize, String studySpeciality) {
        List<String> studySpecialityList = labelStrategy.labelStrategy();
        List<Long> neo4jIdList;
        try (Session session = driver.session()) {
            String cypher = "match (t:Teacher)--(a:Article)--(:ZJUT_TOP100) " +
                    "with t, count(a) as count_a " +
                    "order by count_a desc " +
                    String.format("where t.study_speciality in [%s] ", StringUtils.join(studySpecialityList, ",")) +
                    "with t " +
                    "limit 50 ";
            if (StringUtils.isNotBlank(studySpeciality)) {
                cypher = cypher + String.format("where t.study_speciality = '%s' ", studySpeciality.trim());
            }
            cypher = cypher + "return id(t) as t_id";
            Result result = session.run(cypher);
            neo4jIdList = result.list(record -> record.get("t_id").asLong());
        }
        // 分页
        PageUtils<Long> pageUtils = new PageUtils<>();
        SimplePageInfo<Long> pageInfo = pageUtils.pageInfo(neo4jIdList, pageNum, pageSize);

        // 获取导师其它信息
        List<TutorVO> recommendList = multiThreadGetTutorVOList(pageInfo.getList());

        return new SimplePageInfo<>(pageInfo, recommendList);
    }

    @Override
    public int getType() {
        return RecommendStrategyType.RANKING_HISTORY.ordinal();
    }
}
