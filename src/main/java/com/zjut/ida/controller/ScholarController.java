package com.zjut.ida.controller;

import com.zjut.ida.entity.HorizontalProject;
import com.zjut.ida.entity.Partner;
import com.zjut.ida.entity.PublishArticleCount;
import com.zjut.ida.entity.Scholar;
import com.zjut.ida.service.ScholarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Casterx on 2019/10/10.
 */
@Controller
@CrossOrigin //所有域名均可访问该类下所有接口
@RequestMapping("/scholar")
public class ScholarController {

    @Autowired
    private ScholarService scholarService;

    //测试用
    @RequestMapping("/index1")
    public String toIndex1(Model model) {
        List<Scholar> scholarList = scholarService.findScholarsByNameContains("张元鸣");
        model.addAttribute("scholarList", scholarList);
        return "Test02";
    }

    //根据学者姓名模糊查询详情信息
    @RequestMapping("/findscholarsbynamelike")
    public String findScholarsByNameLike(@RequestParam String scholarName, Model model) {
        //查询所有学者的信息
        List<Scholar> scholarList = scholarService.findScholarsByNameContains(scholarName);
        //查询目标学者的合作学者姓名和合作次数
        List<Partner> partnerList = scholarService.findPartnersByScholarName(scholarName);
        //查询目标学者在近4年发表论文数量
        List<PublishArticleCount> publishArticleCountList = scholarService.findPublishArticleCountByScholarName(scholarName);
        //查询目标学者论文的关键字
        List<String> keyWordsList = scholarService.findKeyWordsByScholarName(scholarName);
        model.addAttribute("scholarList", scholarList);
        model.addAttribute("partnerList", partnerList);
        model.addAttribute("publishArticleCountList", publishArticleCountList);
        model.addAttribute("keyWordsList", keyWordsList);
        return "Scholar";
    }

    //根据学者姓名模糊查询详情信息
    @RequestMapping("/findscholarsbynamelike1")
    @ResponseBody
    public Object findScholarsByNameLike1(@RequestParam String scholarName, Model model) {
        //查询目标学者论文的关键字
        return scholarService.findKeyWordsByScholarName1(scholarName);
    }

    //根据目标学者查询与其合作的学者及次数
    @RequestMapping("/getPartners")
    @ResponseBody
    public Object getPartners(final String scholarName, int count) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        List<Partner> partners = scholarService.findPartnersByScholarNameForCount(scholarName, count);
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        Map<String, Object> source = new HashMap<>();
        source.put("name", scholarName);
        source.put("id", 0);
        data.add(source);

        Map<String, Object> sourceLink = new HashMap<>();
        sourceLink.put("source", 0);
        sourceLink.put("target", 0);
        links.add(sourceLink);


        partners.forEach(partner -> {
            HashMap<Object, Object> itemStyle = new HashMap<>();
            int id = atomicInteger.getAndIncrement();
            Map<String, Object> dataTemp = new HashMap<>();
            dataTemp.put("name", partner.getScholar().getName());
            dataTemp.put("value", partner.getCount());
            if (partner.getCount() <= 50) {
                dataTemp.put("symbolSize", 20);
            } else if (partner.getCount() <= 100) {
                dataTemp.put("symbolSize", 25);
                itemStyle.put("color", "#CA8622");
            } else if (partner.getCount() <= 150) {
                dataTemp.put("symbolSize", 30);
                itemStyle.put("color", "#ADB387");
            } else if (partner.getCount() <= 200) {
                dataTemp.put("symbolSize", 35);
                itemStyle.put("color", "#749F83");
            } else if (partner.getCount() <= 250) {
                dataTemp.put("symbolSize", 40);
                itemStyle.put("color", "#17A2B8");
            } else if (partner.getCount() <= 300) {
                dataTemp.put("symbolSize", 45);
                itemStyle.put("color", "#C23531");
            } else {
                dataTemp.put("symbolSize", 60);
                itemStyle.put("color", "#C23531");
            }
            dataTemp.put("itemStyle", itemStyle);
            dataTemp.put("id", id);
            data.add(dataTemp);


            Map<String, Object> linkTemp = new HashMap<>();
            linkTemp.put("source", 0);
            linkTemp.put("target", id);
            HashMap<Object, Object> label = new HashMap<>();
            label.put("show", true);
            linkTemp.put("label", label);
            links.add(linkTemp);

        });

        Map<String, Object> results = new HashMap<>();

        results.put("links", links);
        results.put("data", data);

        return results;
    }

    @RequestMapping("/getPartners1")
    @ResponseBody
    public Object getPartners1(final String scholarName, int count) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        List<Partner> partners = scholarService.findPartnersByScholarNameForCount(scholarName, count);
        List<Map<String, Object>> data = new ArrayList<>();
        List<Map<String, Object>> links = new ArrayList<>();
        Map<String, Object> source = new HashMap<>();
        source.put("name", scholarName);
        source.put("id", 0);
        data.add(source);

        Map<String, Object> sourceLink = new HashMap<>();
        sourceLink.put("source", 0);
        sourceLink.put("target", 0);
        links.add(sourceLink);


        partners.forEach(partner -> {
//            HashMap<Object, Object> itemStyle = new HashMap<>();
            int id = atomicInteger.getAndIncrement();
            Map<String, Object> dataTemp = new HashMap<>();
            dataTemp.put("name", partner.getScholar().getName());
            dataTemp.put("value", partner.getCount());
//            if (partner.getCount() <= 50) {
//                dataTemp.put("symbolSize", 20);
//            } else if (partner.getCount() <= 100) {
//                dataTemp.put("symbolSize", 25);
//                itemStyle.put("color", "#CA8622");
//            } else if (partner.getCount() <= 150) {
//                dataTemp.put("symbolSize", 30);
//                itemStyle.put("color", "#ADB387");
//            } else if (partner.getCount() <= 200) {
//                dataTemp.put("symbolSize", 35);
//                itemStyle.put("color", "#749F83");
//            } else if (partner.getCount() <= 250) {
//                dataTemp.put("symbolSize", 40);
//                itemStyle.put("color", "#17A2B8");
//            } else if (partner.getCount() <= 300) {
//                dataTemp.put("symbolSize", 45);
//                itemStyle.put("color", "#C23531");
//            } else {
//                dataTemp.put("symbolSize", 60);
//                itemStyle.put("color", "#C23531");
//            }
//            dataTemp.put("itemStyle", itemStyle);
            dataTemp.put("id", id);
            data.add(dataTemp);


            Map<String, Object> linkTemp = new HashMap<>();
            linkTemp.put("source", 0);
            linkTemp.put("target", id);
            HashMap<Object, Object> label = new HashMap<>();
            label.put("show", true);
            linkTemp.put("label", label);
            links.add(linkTemp);

        });

        Map<String, Object> results = new HashMap<>();
        results.put("links", links);
        results.put("data", data);
        return results;
    }

    @RequestMapping("/getPartners2")
    @ResponseBody
    public Object getPartners2(final String scholarName, int count) {
        Object results = scholarService.findPartnersByScholarNameForCount1(scholarName, count);
        return results;
    }


    //测试用
    @RequestMapping("/findscholarsbynamelike2")
    @ResponseBody
    public List<String> findScholarsByNameLike2(@RequestParam String scholarName, Model model) {
        List<String> keyWordsList = scholarService.findKeyWordsByScholarName(scholarName);
        model.addAttribute("keyWordsList", keyWordsList);
        return keyWordsList;
    }

    @RequestMapping("/findpublisharticlecountbyscholarname")
    @ResponseBody
    public List<PublishArticleCount> findPublishArticleCountByScholarName(@RequestParam String scholarName, Model model) {
        List<PublishArticleCount> publishArticleCountList = scholarService.findPublishArticleCountByScholarName(scholarName);
        return publishArticleCountList;
    }

    @RequestMapping("/findpublisharticlecountbyscholarname1")
    @ResponseBody
    public Object findPublishArticleCountByScholarName1(@RequestParam String scholarName, Model model) {
        System.out.println("findpublisharticlecountbyscholarname1");
        return scholarService.findPublishArticleCountByScholarName1(scholarName);
    }
}

//    @RequestMapping("/findpublisharticlecountbyscholarname12")
//    @ResponseBody
//    public List<HorizontalProject>  a(){
//        String scholarName="张元鸣";
//        int count=5;
//        List<HorizontalProject> partnerList = scholarService.findPartnersByScholarNameForCount5(scholarName,count);
//        System.out.println(partnerList);
//        return partnerList;
//    }
//}
