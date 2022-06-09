package com.zjut.ida.service;

import com.zjut.ida.IdaApplication;
import com.zjut.ida.dao.ScholarDao;
import com.zjut.ida.entity.Achievement;
import com.zjut.ida.entity.Article;
import com.zjut.ida.entity.Partner;
import com.zjut.ida.entity.Scholar;
import com.zjut.ida.service.impl.HorizontalProjectServiceImpl;
import com.zjut.ida.tool.ExcelFormatUtil;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.util.*;

/**
 * @author Casterx on 2020/7/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest//(classes = IdaApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ScholarServiceTest {

    @Autowired
    private ScholarService scholarService;

    @Autowired
    private ScholarDao scholarDao;

    @Autowired
    private AchievementService achievementService;

    @Autowired
    private HorizontalProjectServiceImpl service;

    @Test
    public void findPartnersByScholarNameForCountTest() {
        String scholarName = "张元鸣";
        int count = 50;
        List<Partner> partnerList = scholarService.findPartnersByScholarNameForCount(scholarName, count);
        for (Partner partner : partnerList) {
            if (partner.getCount() >= 15) {
                System.out.println(partner);
            }
        }
    }

    @Test
    public void test1() throws Exception {
        List<List<Object>> bank = ExcelFormatUtil.getBankListByExcel();
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        XSSFSheet sheet = xssfWorkbook.createSheet();
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("教师id1");
        row.createCell(1).setCellValue("教师id2");
        row.createCell(2).setCellValue("合作详情");
        XSSFCellStyle cellStyle = xssfWorkbook.createCellStyle();
        XSSFDataFormat format = xssfWorkbook.createDataFormat();
        cellStyle.setDataFormat(format.getFormat("@"));

        for (int n = 0; n < bank.size(); n++) {
            Long id1 = Long.parseLong(bank.get(n).get(0).toString().trim());
            Long id2 = Long.parseLong(bank.get(n).get(1).toString().trim());
            Scholar scholar1 = achievementService.findScholarByScholarId(id1);
            Scholar scholar2 = achievementService.findScholarByScholarId(id2);
            Achievement achievement = achievementService.findAchievementByName(scholar1.getName(), scholar2.getName());
            XSSFRow row1 = sheet.createRow(n + 1);
            ArrayList<String> list = new ArrayList<String>();
            if (achievement.getArticleList() != null) {
                for (int j = 0; j < achievement.getArticleList().size(); j++) {
                    list.add(achievement.getArticleList().get(j).getId().toString());
                    if (j > 2) {
                        break;
                    }
                }
            }
            if (achievement.getHorizontalProjectList() != null) {
                for (int j = 0; j < achievement.getHorizontalProjectList().size(); j++) {
                    list.add(achievement.getHorizontalProjectList().get(j).getId().toString());
                    if (j > 2) {
                        break;
                    }
                }
            }
            if (achievement.getPatentList() != null) {
                for (int j = 0; j < achievement.getPatentList().size(); j++) {
                    list.add(achievement.getPatentList().get(j).getId().toString());
                    if (j > 2) {
                        break;
                    }
                }
            }
            if (achievement.getVerticalProjectList() != null) {
                for (int j = 0; j < achievement.getVerticalProjectList().size(); j++) {
                    list.add(achievement.getVerticalProjectList().get(j).getId().toString());
                    if (j > 2) {
                        break;
                    }
                }
            }
            String tmp = "";
            for (int i = 0; i < list.size(); i++) {
                tmp += list.get(i);
//                if(i<list.size()-1){
                    tmp += ",";
//                }
            }
            row1.createCell(0).setCellValue(scholar1.getName());
            row1.getCell(0).setCellStyle(cellStyle);
            row1.createCell(1).setCellValue(scholar2.getName());
            row1.getCell(1).setCellStyle(cellStyle);
            row1.createCell(2).setCellValue(tmp);
            row1.getCell(2).setCellStyle(cellStyle);
        }
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/zhouweiyue/Downloads/教师表10.csv");
        xssfWorkbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        xssfWorkbook.close();
    }

    @Test
    public void test2(){
        System.out.println(service.findHorizontalProjectsByScholarName("张元鸣"));
    }


    @Test
    public void test3(){
//        List<Map<String,Object>> list=scholarDao.findHistoryCountByScholarId(401428);
//        System.out.println(list);

//        List<Integer> scholarIdList=new ArrayList<Integer>(){{
//            add(461334);
//            add(447719);
//        }};
//        List<Map<String,Object>> list=scholarDao.findHistoryCountByScholarIdList(scholarIdList);
//        System.out.println(list);

    }
}
