package com.zjut.ida.achievement_recommend_system;

import net.sf.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zjut.ida.achievement_recommend_system.UserHistoryService.HistoryList;
import static com.zjut.ida.achievement_recommend_system.UserLoginController.reUserId;

@Controller
@RequestMapping("/user")
public class UserHistoryController {
    @RequestMapping("/info")
    public String Test() {

        return "userHistory";
    }

    @Resource
    private JdbcTemplate jdbcTemplate;

    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public void queryUser(HttpServletResponse resp) {
        try{
            int userid = reUserId();
//            int userid = 817;
            List<Integer> itemHis= new ArrayList<Integer>();  //存放历史信息
            String sql = "SELECT user_id,entity_id FROM user_history WHERE user_id = " + userid;

            List<UserBean> userList = jdbcTemplate.query(sql, new RowMapper<UserBean>() {
                UserBean user = null;
                @Override
                public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                    user = new UserBean();
                    user.setUserId(rs.getInt("user_id"));
                    itemHis.add(rs.getInt("entity_id"));
                    return user;
                }
            });

            System.out.println(itemHis);
            System.out.println(HistoryList(userid,itemHis));

            List<ArticleBean> articles = HistoryList(userid,itemHis);
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
