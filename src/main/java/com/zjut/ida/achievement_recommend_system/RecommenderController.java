package com.zjut.ida.achievement_recommend_system;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

import static com.zjut.ida.achievement_recommend_system.UserLoginController.reUserId;
import static com.zjut.ida.achievement_recommend_system.RecommenderService.AllList;


@Controller
@RequestMapping("recommend")
public class RecommenderController {
    @RequestMapping("/test")
    public String Test() {

        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/recommendList", method = RequestMethod.POST)
    public void query(HttpServletResponse resp) {
        try {

//            int user = 817;
            int user = reUserId();
            System.out.println("userid"+user);

            AllList(user);

            /*list集合中存放的是好多articles对象*/
            List<ArticleBean> articles = AllList(user);
            /*将list集合装换成json对象*/
            JSONArray data = JSONArray.fromObject(articles);
            //接下来发送数据
            /*设置编码，防止出现乱码问题*/
            resp.setCharacterEncoding("utf-8");
            /*得到输出流*/
            PrintWriter respWritter = resp.getWriter();
            /*将JSON格式的对象toString()后发送*/
            respWritter.append(data.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
