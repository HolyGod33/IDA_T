package com.zjut.ida.recommend.tutor.module.home.service;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.dao.ModelTutorMapMapper;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.KgatModelRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/4/28 15:14
 */
@Service
public class TutorService {
    @Autowired
    private Driver driver;
    @Autowired
    private SysStudentHolder studentHolder;

    @Autowired
    private ModelTutorMapMapper tutorMapMapper;

    @Autowired
    private KgatModelRecommendStrategy kgatModelStrategy;

    private static final Integer MAX_STUDY_SPECIALITY_SIZE = 20;
    private static final String STUDY_SPECIALITY_CYPHER = "match(n:Teacher)-[*1]-(:Organization{name:'%s'}) " +
            "where n.study_speciality is not null and n.study_speciality <> '' " +
            "return n.study_speciality as studySpeciality";

    @Deprecated
    public List<Long> getNeo4jIdListByRemapIds(List<Integer> remapIdList) {
        return tutorMapMapper.findNeo4jIdsByRemapIds(remapIdList);
    }

    public Long getNeo4jIdByRemapId(Long remapId) {
        return tutorMapMapper.findNeo4jIdByRemapId(remapId);
    }

    public Long getRemapIdByNeo4jId(Long tutorNeo4jId) {
        return tutorMapMapper.findRemapIdByNeo4jId(tutorNeo4jId);
    }

    public TutorVO getTutorByNeo4jId(Long tutorNeo4jId) {
        String studentId = studentHolder.getStudent().getStudentId();
        try {
            return kgatModelStrategy.constructTutorFuture(tutorNeo4jId).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据学院名称获取研究领域标签
     *
     * @param collegeName 学院名称
     * @return 研究领域列表
     */
    public List<String> getStudySpecialityList(String collegeName) {
        try (Session session = driver.session()) {
            Result result = session.run(String.format(STUDY_SPECIALITY_CYPHER, collegeName));
            Map<String, Integer> studySpecialityMap = new HashMap<>();
            // 计数
            result.list(record -> record.get("studySpeciality").asString())
                    .forEach(doMain -> studySpecialityMap.merge(doMain, 1, Integer::sum));

            // 降序排序
            List<Map.Entry<String, Integer>> sorted = new ArrayList<>(studySpecialityMap.entrySet());
            sorted.sort((x, y) -> y.getValue() - x.getValue());
            List<String> studySpecialityList = sorted.stream().map(Map.Entry::getKey).collect(Collectors.toList());

            // 限制个数
            if (studySpecialityList.size() > MAX_STUDY_SPECIALITY_SIZE) {
                return studySpecialityList.subList(0, MAX_STUDY_SPECIALITY_SIZE);
            } else {
                return studySpecialityList;
            }
        }
    }
}
