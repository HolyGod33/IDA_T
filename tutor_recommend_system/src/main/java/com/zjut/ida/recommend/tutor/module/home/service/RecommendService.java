package com.zjut.ida.recommend.tutor.module.home.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.ModelStudentMap;
import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.entity.SysTutorRelative;
import com.zjut.ida.recommend.tutor.core.page.SimplePageInfo;
import com.zjut.ida.recommend.tutor.dao.ModelStudentMapMapper;
import com.zjut.ida.recommend.tutor.dao.SysCFMapper;
import com.zjut.ida.recommend.tutor.dao.SysTutorRelativeMapper;
import com.zjut.ida.recommend.tutor.module.home.service.combine.TreeRich;
import com.zjut.ida.recommend.tutor.module.home.service.combine.engine.IEngine;
import com.zjut.ida.recommend.tutor.module.home.service.combine.engine.impl.TreeEngineHandle;
import com.zjut.ida.recommend.tutor.module.home.service.combine.tree.EngineResult;
import com.zjut.ida.recommend.tutor.module.home.service.factory.combine.impl.RecommendDecisionTreeFactory;
import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl.LabelStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl.RecommendReasonStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.factory.strategy.impl.RecommendStrategyFactory;
import com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend.impl.KgatModelRecommendStrategy;
import com.zjut.ida.recommend.tutor.module.home.vo.TutorVO;
import com.zjut.ida.recommend.tutor.utils.enums.RuleKeyType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/4/22 19:39
 */
@Service
public class RecommendService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SysStudentHolder studentHolder;
    @Autowired
    private SysCFMapper cfMapper;
    @Autowired
    private ModelStudentMapMapper studentMapMapper;
    @Autowired
    private SysTutorRelativeMapper relativeMapper;

    @Autowired
    private TutorService tutorService;
    @Autowired
    private RecommendStrategyFactory recommendStrategyFactory;
    @Autowired
    private RecommendReasonStrategyFactory reasonStrategyFactory;
    @Autowired
    private LabelStrategyFactory labelStrategyFactory;
    @Autowired
    private KgatModelRecommendStrategy kgatModelStrategy;


    /**
     * 推荐列表标签
     *
     * @return 研究方向列表
     */
    public List<String> getStudySpecialityList() {
        // 获取决策树
        EngineResult decisionResult = getDecisionResult();
        return labelStrategyFactory.get(decisionResult.getNodeValue().ordinal())
                .labelStrategy();
    }

    /**
     * 获取推荐列表
     *
     * @param pageNum         第几页
     * @param pageSize        页大小
     * @param studySpeciality 研究方向
     * @return 分页信息
     */
    public SimplePageInfo<TutorVO> getRecommendList(Integer pageNum, Integer pageSize, String studySpeciality) {
        EngineResult decisionResult = getDecisionResult();
        return recommendStrategyFactory.get(decisionResult.getNodeValue().ordinal())
                .recommendStrategy(pageNum, pageSize, studySpeciality);
    }

    /**
     * 获取推荐理由
     *
     * @param neo4jId 导师 neo4j id
     * @return 三元组信息
     */
    public JSONObject getRecommendReason(Long neo4jId) {
        // 获取决策树
        EngineResult decisionResult = getDecisionResult();
        return reasonStrategyFactory.get(decisionResult.getNodeValue().ordinal())
                .recommendReasonStrategy(neo4jId);
    }

    /**
     * 获取决策结果
     *
     * @return 决策结果
     */
    private EngineResult getDecisionResult() {
        // 获取决策树
        TreeRich tree = RecommendDecisionTreeFactory.instance().getTree();
        Map<RuleKeyType, Integer> matterValue = getMatterValues();

        // 获取决策结果
        IEngine engine = new TreeEngineHandle();
        return engine.process(tree, matterValue);
    }

    /**
     * 获取决策值
     *
     * @return 决策值键值对
     */
    private Map<RuleKeyType, Integer> getMatterValues() {
        SysStudent student = studentHolder.getStudent();
        // 构造决策值
        Map<RuleKeyType, Integer> matterValue = new HashMap<>();
        if (student == null) {
            // 未注册
            matterValue.put(RuleKeyType.IS_REGISTER, 0);
        } else {
            // 已注册
            matterValue.put(RuleKeyType.IS_REGISTER, 1);
            if (isTrain()) {
                matterValue.put(RuleKeyType.IS_TRAIN, 1);
            } else {
                matterValue.put(RuleKeyType.IS_TRAIN, 0);
                if (StringUtils.isNotEmpty(student.getStudySpeciality())) {
                    matterValue.put(RuleKeyType.HAS_FAVORITE, 1);
                } else {
                    matterValue.put(RuleKeyType.HAS_FAVORITE, 0);
                    matterValue.put(RuleKeyType.HAS_HISTORY, hasHistory() ? 1 : 0);
                }
            }
        }
        return matterValue;
    }

    /**
     * 判断用户是否被模型训练
     *
     * @return 布尔值
     */
    public boolean isTrain() {
        SysStudent student = studentHolder.getStudent();
        if (student == null) {
            // 未注册
            return false;
        } else {
            // 已注册
            return Optional.ofNullable(studentMapMapper.selectOne(Wrappers.<ModelStudentMap>lambdaQuery()
                    .select(ModelStudentMap::getStudentId)
                    .eq(ModelStudentMap::getStudentName, student.getStudentName())))
                    .isPresent();
        }
    }

    /**
     * 判断用户是否有历史记录
     *
     * @return 布尔值
     */
    public boolean hasHistory() {
        SysStudent student = studentHolder.getStudent();
        if (student == null) {
            // 未注册
            return false;
        } else {
            // 已注册
            return cfMapper.hasEnoughHistory(student.getStudentId());
        }
    }

    /**
     * 获取相似导师信息
     *
     * @param tutorNeo4jId 导师 neo4j id
     * @return VO 列表
     */
    public List<TutorVO> getRelativeList(Long tutorNeo4jId) {
        Long remapId = tutorService.getRemapIdByNeo4jId(tutorNeo4jId);
        SysTutorRelative relative = relativeMapper.selectOne(Wrappers.<SysTutorRelative>lambdaQuery()
                .eq(SysTutorRelative::getTutorRemapId, remapId));
        List<String> relativeRemapIdList = Arrays.stream(relative.getTutorRelativeIdList().split(","))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(relativeRemapIdList)) {
            return null;
        }

        List<TutorVO> relativeList = new ArrayList<>();
        relativeRemapIdList.parallelStream().forEach(relativeRemapId -> {
            Long neo4jId = tutorService.getNeo4jIdByRemapId(Long.parseLong(relativeRemapId));
            try {
                relativeList.add(kgatModelStrategy.constructTutorFuture(neo4jId).get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        return relativeList;
    }
}
