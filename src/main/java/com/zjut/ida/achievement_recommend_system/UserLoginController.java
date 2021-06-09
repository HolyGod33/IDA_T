package com.zjut.ida.achievement_recommend_system;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserLoginController {
//    @RequestMapping("/login")
//    public String Test() {
//
//        return "login";
//    }
    static int user_id;

    //  通过login的username查询userid并返回
    @Resource
    private JdbcTemplate jdbcTemplate;
    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public String queryUserId(@RequestBody(required=false) UserBean user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.toString());

        String sql = "SELECT user_id FROM user_info WHERE user_name = '" + user.getUsername() + "'";

        List<UserBean> userIdList = jdbcTemplate.query(sql, new RowMapper<UserBean>() {
            UserBean user = null;
            @Override
            public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
                user = new UserBean();
                user.setUserId(rs.getInt("user_id"));
                user_id = rs.getInt("user_id");
//                System.out.println(user_id);
                return user;
            }
        });
        System.out.println("id"+user_id);
        return "index";
    }

    //  注册后将新用户信息插入MySQL中
    @RequestMapping(value="/insert",method = RequestMethod.POST)
    @ResponseBody
    public String insertUser(@RequestBody(required=false) UserBean user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getGender());
        System.out.println(user.getLikes());
        System.out.println(user.getPhoneNum());
        System.out.println(user.toString());

//        int userid = 3000;

        String sql = "insert into user_info(user_name,password,gender,user_likes,phone_Num) values(" +
                "'" + user.getUsername() + "'," + "'" + user.getPassword() + "'" + "," +
                "'" + user.getGender() + "'" + "," + "'" + user.getLikes() + "'," + user.getPhoneNum() + ")";

        jdbcTemplate.execute(sql);

        System.out.println("插入成功"+user.getUsername());

        String sql1 = "select user_id from user_info where user_name = ?";

        user_id = jdbcTemplate.queryForObject(sql1, new Object[]{user.getUsername()}, Integer.class);
        System.out.println("user_id"+user_id);
        return "index";
    }

    public static int reUserId() {
        return user_id;
    }
}

