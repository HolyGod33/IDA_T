package com.zjut.ida.recommend.tutor.module.home.service.strategy.recommend;

import com.zjut.ida.recommend.tutor.config.SysStudentHolder;
import com.zjut.ida.recommend.tutor.core.entity.SysStudentRecommend;
import com.zjut.ida.recommend.tutor.dao.SysStudentRecommendMapper;
import com.zjut.ida.recommend.tutor.module.common.service.CommonFavoriteService;
import com.zjut.ida.recommend.tutor.module.home.service.DockerService;
import com.zjut.ida.recommend.tutor.module.home.service.Neo4jService;
import com.zjut.ida.recommend.tutor.module.home.service.TutorService;
import com.zjut.ida.recommend.tutor.module.home.vo.*;
import com.zjut.ida.recommend.tutor.utils.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author wly
 * @date 2021/6/2 20:43
 */
@Component
public abstract class BaseRecommendStrategy implements IRecommendStrategy {
    @Autowired
    private DockerService dockerService;
    @Autowired
    private Neo4jService neo4jService;
    @Autowired
    private CommonFavoriteService favoriteService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private SysStudentHolder studentHolder;

    @Autowired
    private SysStudentRecommendMapper recommendMapper;

    @Autowired
    private RedisLock redisLock;

    /**
     * 如果 sys_student_recommend 推荐表没有数据，则插入
     *
     * @param studentId 学生学号
     */
    public void insertRecommendListIfAbsent(String studentId) {
        redisLock.lock(studentId);
        // 判断学生是否存在
        if (!recommendMapper.existStudent(studentId)) {
            // 发起 docker 请求
            List<Long> remapIdList = dockerService.requestKgatRecommendList(50);

            // 插入数据
            for (int i = 0; i < remapIdList.size(); i++) {
                Long neo4jId = tutorService.getNeo4jIdByRemapId(remapIdList.get(i));
                TutorVO tutorVO = neo4jService.getTutorByNeo4jId(neo4jId);

                SysStudentRecommend recommend = new SysStudentRecommend();
                recommend.setStudentId(studentId);
                recommend.setTutorNeo4jId(neo4jId);
                recommend.setRecommendOrder(i);
                recommend.setTutorStudySpeciality(tutorVO.getStudySpeciality());
                recommendMapper.insert(recommend);
            }
        }
        redisLock.unlock(studentId);
    }

    /**
     * 多线程异步获取导师各类信息
     *
     * @param tutorNeo4jIdList 导师 neo4j id 列表
     * @return VO 列表
     */
    protected List<TutorVO> multiThreadGetTutorVOList(List<Long> tutorNeo4jIdList) {
        if (CollectionUtils.isEmpty(tutorNeo4jIdList)) {
            return new ArrayList<>();
        }
        List<TutorVO> recommendList = new ArrayList<>();
        // 异步 Stream
        if (studentHolder.exist()) {
            String studentId = studentHolder.getStudentId();
            tutorNeo4jIdList.parallelStream().forEach(neo4jId -> {
                try {
                    recommendList.add(constructTutorFuture(studentId, neo4jId).get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } else {
            tutorNeo4jIdList.parallelStream().forEach(neo4jId -> {
                try {
                    recommendList.add(constructTutorFuture(neo4jId).get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }
        return recommendList;
    }

    /**
     * 构造异步 Future
     *
     * @param neo4jId 导师 neo4j id
     * @return future
     */
    public CompletableFuture<TutorVO> constructTutorFuture(Long neo4jId) {
        CompletableFuture<List<ArticleVO>> articleFuture = CompletableFuture.supplyAsync(() ->
                neo4jService.getArticleListByTutorId(neo4jId)
        );
        CompletableFuture<List<PatentVO>> patentFuture = CompletableFuture.supplyAsync(() ->
                neo4jService.getPatentListByTutorId(neo4jId)
        );
        CompletableFuture<List<HorizontalProjectVO>> hProjectFuture = CompletableFuture.supplyAsync(() ->
                neo4jService.getHorizontalProjectListByTutorId(neo4jId)
        );
        CompletableFuture<List<VerticalProjectVO>> vProjectFuture = CompletableFuture.supplyAsync(() ->
                neo4jService.getVerticalProjectListByTutorId(neo4jId)
        );

        CompletableFuture<TutorVO> tutorFuture = CompletableFuture.supplyAsync(() ->
                neo4jService.getTutorByNeo4jId(neo4jId)
        ).thenCombineAsync(articleFuture, (tutor, article) -> {
            tutor.setArticleList(article);
            return tutor;
        }).thenCombineAsync(patentFuture, (tutor, patent) -> {
            tutor.setPatentList(patent);
            return tutor;
        }).thenCombineAsync(hProjectFuture, (tutor, hProject) -> {
            tutor.setHorizontalProjectList(hProject);
            return tutor;
        }).thenCombineAsync(vProjectFuture, (tutor, vProject) -> {
            tutor.setVerticalProjectList(vProject);
            return tutor;
        });

        return tutorFuture;
    }

    /**
     * 构造异步 Future
     *
     * @param studentId 学生学号
     * @param neo4jId   导师 neo4j id
     * @return future
     */
    public CompletableFuture<TutorVO> constructTutorFuture(String studentId, Long neo4jId) {
        CompletableFuture<Boolean> favoriteFuture = CompletableFuture.supplyAsync(() ->
                favoriteService.isFavorite(studentId, neo4jId)
        );

        return constructTutorFuture(neo4jId).thenCombineAsync(favoriteFuture, (tutor, favorite) -> {
            tutor.setFavorite(favorite);
            return tutor;
        });
    }
}
