package com.zjut.ida.recommend.tutor.module.home.service;

import com.zjut.ida.recommend.tutor.core.neo4j.*;
import com.zjut.ida.recommend.tutor.dao.*;
import com.zjut.ida.recommend.tutor.module.home.vo.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wly
 * @date 2021/4/28 15:13
 */
@Service
public class Neo4jService {
    @Autowired
    private Driver driver;
    @Autowired
    private Neo4jTutorRepository tutorRepository;
    @Autowired
    private Neo4jArticleRepository articleRepository;
    @Autowired
    private Neo4jPatentRepository patentRepository;
    @Autowired
    private Neo4jHorizontalProjectRepository horizontalProjectRepository;
    @Autowired
    private Neo4jVerticalProjectRepository verticalProjectRepository;

    public TutorVO getTutorByNeo4jId(Long neo4jId) {
        Neo4jTutor tutor = tutorRepository.findOneByNeo4jId(neo4jId);
        TutorVO tutorVO = new TutorVO();
        BeanUtils.copyProperties(tutor, tutorVO);
        return tutorVO;
    }

    public List<ArticleVO> getArticleListByTutorId(Long tutorId) {
        List<Neo4jArticle> articleList = articleRepository.findTop5ByTutorId(tutorId);
        return articleList.stream().map(article -> {
                    ArticleVO articleVO = new ArticleVO();
                    BeanUtils.copyProperties(article, articleVO);
                    return articleVO;
                }).collect(Collectors.toList());
    }

    public List<PatentVO> getPatentListByTutorId(Long tutorId) {
        List<Neo4jPatent> patentList = patentRepository.findTop5ByTutorId(tutorId);
        return patentList.stream().map(patent -> {
                    PatentVO patentVO = new PatentVO();
                    BeanUtils.copyProperties(patent, patentVO);
                    return patentVO;
                }).collect(Collectors.toList());
    }

    public List<HorizontalProjectVO> getHorizontalProjectListByTutorId(Long tutorId) {
        List<Neo4jHorizontalProject> projectList = horizontalProjectRepository.findTop5ByTutorId(tutorId);
        return projectList.stream().map(project -> {
                    HorizontalProjectVO projectVO = new HorizontalProjectVO();
                    BeanUtils.copyProperties(project, projectVO);
                    return projectVO;
                }).collect(Collectors.toList());
    }

    public List<VerticalProjectVO> getVerticalProjectListByTutorId(Long tutorId) {
        List<Neo4jVerticalProject> projectList = verticalProjectRepository.findTop5ByTutorId(tutorId);
        return projectList.stream().map(project -> {
                    VerticalProjectVO projectVO = new VerticalProjectVO();
                    BeanUtils.copyProperties(project, projectVO);
                    return projectVO;
                }).collect(Collectors.toList());
    }

    public boolean matchAttrCondition(Long neo4jId, String entityType, String attribute, String value) {
        try (Session session = driver.session()) {
            String cypher = String.format(
                    "match (n:%s) where id(n) = %d and n.%s = '%s' return COUNT(n) > 0 as match",
                    entityType,
                    neo4jId,
                    attribute,
                    value.trim());
            Result result = session.run(cypher);
            if (result.hasNext()) {
                return result.next().get("match").asBoolean();
            }
            return false;
        }
    }
}
