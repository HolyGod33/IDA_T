package com.zjut.ida.service;

import com.zjut.ida.controller.RootController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @author Casterx on 2022/6/6.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RootControllerTest {

    @Autowired
    private RootController rootController;

    @Test
    public void test1(){
//        List<Map<String,Object>> res= rootController.getSuibian("201806150128","Article");
//        System.out.println(res);
//
//
//        List<Map<String,Object>> res1= rootController.getSuibian(null,"Article");
//        System.out.println(res1);
    }
}
