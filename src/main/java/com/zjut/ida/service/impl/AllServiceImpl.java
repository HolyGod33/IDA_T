package com.zjut.ida.service.impl;

import com.zjut.ida.dao.*;
import com.zjut.ida.entity.*;
import com.zjut.ida.service.AllService;
import com.zjut.ida.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Casterx on 2019/10/19.
 */
@Service
public class AllServiceImpl implements AllService {


    @Autowired
    private ScholarDao scholarDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private VerticalProjectDao verticalProjectDao;

    @Autowired
    private HorizontalProjectDao horizontalProjectDao;

    @Autowired
    private PatentDao patentDao;


    @Override
    public All findAllByAnyWords(String words) {
        All all=new All();
        Set<Article> articleSet=new HashSet<>();
        List<Article> articleList=new ArrayList<>();
        articleSet.addAll(articleDao.findArticlesByTitleContains(words));
        articleSet.addAll(articleDao.findArticlesByKeyWordContains(words));
        articleSet.addAll(articleDao.findArticlesByScholarWords(words));
        articleList.addAll(articleSet);
        all.setArticleList(articleList);

        Set<Scholar> scholarSet=new HashSet<>();
        List<Scholar> scholarList=new ArrayList<>();
        scholarSet.addAll(scholarDao.findScholarsByDoMainContains(words));
        scholarSet.addAll(scholarDao.findScholarsByNameContains(words));
        scholarList.addAll(scholarSet);
        all.setScholarList(scholarList);

        Set<VerticalProject> verticalProjectSet=new HashSet<>();
        List<VerticalProject> verticalProjectList=new ArrayList<>();
        verticalProjectSet.addAll(verticalProjectDao.findVerticalProjectsByNameContains(words));
        verticalProjectSet.addAll(verticalProjectDao.findVerticalProjectsByCooperatorsContains(words));
        verticalProjectList.addAll(verticalProjectSet);
        all.setVerticalProjectList(verticalProjectList);

        Set<HorizontalProject> horizontalProjectSet=new HashSet<>();
        List<HorizontalProject> horizontalProjectList=new ArrayList<>();
        horizontalProjectSet.addAll(horizontalProjectDao.findHorizontalProjectsByNameContains(words));
        horizontalProjectSet.addAll(horizontalProjectDao.findHorizontalProjectsByCooperatorsContains(words));
        horizontalProjectList.addAll(horizontalProjectSet);
        all.setHorizontalProjectList(horizontalProjectList);

        Set<Patent> patentSet=new HashSet<>();
        List<Patent> patentList=new ArrayList<>();
        patentSet.addAll(patentDao.findPatentsByNameContains(words));
        patentSet.addAll(patentDao.findPatentsByInventorsContains(words));
        patentList.addAll(patentSet);
        all.setPatentList(patentList);
        return all;
    }
}
