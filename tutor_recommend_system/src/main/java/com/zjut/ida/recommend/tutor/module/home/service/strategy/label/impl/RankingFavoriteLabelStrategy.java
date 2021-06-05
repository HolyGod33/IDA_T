package com.zjut.ida.recommend.tutor.module.home.service.strategy.label.impl;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.label.ILabelStrategy;
import com.zjut.ida.recommend.tutor.utils.SortUtils;
import com.zjut.ida.recommend.tutor.utils.enums.RecommendStrategyType;
import org.apache.commons.lang3.StringUtils;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/6/1 16:27
 */
@Component
public class RankingFavoriteLabelStrategy implements ILabelStrategy {
    @Autowired
    private Driver driver;
    @Autowired
    private SysStudentHolder studentHolder;

    @Override
    public List<String> labelStrategy() {
        List<String> studySpecialityList = Arrays.stream(studentHolder.getStudent().getStudySpeciality().split("&"))
                .collect(Collectors.toList());
        studySpecialityList = studySpecialityList.stream().map(ele -> "'" + ele + "'").collect(Collectors.toList());

        try (Session session = driver.session()) {
            String cypher = "match (t:Teacher)--(a:Article)--(:ZJUT_TOP100) " +
                    "with t, count(a) as count_a " +
                    "order by count_a desc " +
                    String.format("where t.study_speciality in [%s] ", StringUtils.join(studySpecialityList, ",")) +
                    "with t " +
                    "limit 50 " +
                    "return t.study_speciality as study_speciality";
            Result result = session.run(cypher);
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
        return RecommendStrategyType.RANKING_FAVORITE.ordinal();
    }
}
