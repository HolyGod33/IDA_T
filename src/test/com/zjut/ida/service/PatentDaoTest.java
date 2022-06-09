package com.zjut.ida.service;

import com.zjut.ida.dao.PatentDao;
import com.zjut.ida.entity.Patent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Casterx on 2022/6/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatentDaoTest {

    @Autowired
    private PatentDao patentDao;

    @Test
    public void test1(){
        String words="高飞";
        Set<Patent> patentSet=new HashSet<>();
        List<Patent> patentList=new ArrayList<>();
        patentSet.addAll(patentDao.findPatentsByNameContains(words));
        patentSet.addAll(patentDao.findPatentsByInventorsContains(words));
        patentList.addAll(patentSet);
        System.out.println(patentList);
    }
}
