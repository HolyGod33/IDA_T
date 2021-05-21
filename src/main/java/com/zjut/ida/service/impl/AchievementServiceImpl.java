package com.zjut.ida.service.impl;

import com.zjut.ida.dao.*;
import com.zjut.ida.entity.*;
import com.zjut.ida.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Veloma on 2020/10/21.
 */
@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementDao achievementDao;



    @Override
    public Achievement findAchievementByName(String name1, String name2) {

        Achievement achievement = new Achievement();
        Set<Article> articleSet = new HashSet<>();
        List<Article> articleList = new ArrayList<>();
        articleSet.addAll(achievementDao.findArticlesByScholarName(name1,name2));
        articleList.addAll(articleSet);
        achievement.setArticleList(articleList);

        Set<HorizontalProject> horizontalProjectSet = new HashSet<>();
        List<HorizontalProject> horizontalProjectList = new ArrayList<>();
        horizontalProjectSet.addAll(achievementDao.findHorizontalProjectsByScholarName(name1,name2));
        horizontalProjectList.addAll(horizontalProjectSet);
        achievement.setHorizontalProjectList(horizontalProjectList);

        Set<VerticalProject> verticalProjectSet = new HashSet<>();
        List<VerticalProject> verticalProjectList = new ArrayList<>();
        verticalProjectSet.addAll(achievementDao.findVerticalProjectsByScholarName(name1,name2));
        verticalProjectList.addAll(verticalProjectSet);
        achievement.setVerticalProjectList(verticalProjectList);

        Set<Patent> patentSet = new HashSet<>();
        List<Patent> patentList = new ArrayList<>();
        patentSet.addAll(achievementDao.findPatentsByScholarName(name1,name2));
        patentList.addAll(patentSet);
        achievement.setPatentList(patentList);

        return achievement;

    }

    @Override
    public Scholar findScholarByScholarId(Long id) {
        return achievementDao.findScholarByScholarId(id);
    }


}
