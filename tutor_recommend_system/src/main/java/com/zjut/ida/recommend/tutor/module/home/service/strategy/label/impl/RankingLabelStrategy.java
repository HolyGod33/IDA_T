package com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl;

import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.ILabelStrategy;
import com.zjut.ida.recommend.tutor.utils.SortUtils;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingLabelStrategy implements ILabelStrategy {
    @Autowired
    private Driver driver;

    private static final String CYPHER = "match (t:Teacher)--(a:Article)--(:ZJUT_TOP100) " +
            "with t, count(a) as count_a " +
            "order by count_a desc limit 50 " +
            "with t " +
            "where t.study_speciality is not null " +
            "return t.study_speciality as study_speciality ";

    @Override
    public List<String> labelStrategy() {
        List<String> studySpecialityList;
        try (Session session = driver.session()) {
            Result result = session.run(CYPHER);
            studySpecialityList = result.list(record -> record.get("study_speciality").asString());
        }
        // 计数
        Map<String, Integer> labelMap = new HashMap<>();
        studySpecialityList.forEach(studySpeciality -> labelMap.merge(studySpeciality, 1, Integer::sum));
        // 排序
        return SortUtils.sortByValue(labelMap, LABEL_MAX_NUM, false);
    }

    @Override
    public int getType() {
        return RecommendStrategyType.RANKING.ordinal();
    }
}
