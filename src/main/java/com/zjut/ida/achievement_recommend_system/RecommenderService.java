package com.zjut.ida.achievement_recommend_system;

import org.neo4j.driver.v1.*;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.v1.Values.parameters;

public class RecommenderService {

    private final static String NEO4JURL="bolt://10.12.45.59:7687";
    private final static String NEO4JNAME="neo4j";
    private final static String NEO4JPWD="neo4j";


    //返回推荐信息列表
    public static List<ArticleBean> AllList(int userid) throws Exception {
        List<ArticleBean> list = new ArrayList<ArticleBean>();
        List<Integer> articleList = new ArrayList<Integer>();
        List<Integer> patentList = new ArrayList<Integer>();
        List<Integer> projectList = new ArrayList<Integer>();
        List<Integer> allList = new ArrayList<Integer>();

        String sql = "SELECT user_id, article_id, patent_id, project_id FROM user_recommender WHERE user_id = " + userid;
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        PreparedStatement prepareStatement1 = null;
        ResultSet rs = null;


        //通过用户id查询mysql中的article/Patent/Project id，并存到aticleList/patentList/projectList
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://10.12.45.59:3307/achievement_recommend_system?serverTimezone=GMT%2B8", "root", "root");
//            本地连接
//            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user?serverTimezone=GMT%2B8", "root", "52547wzdhappy.");

            // 获取statement，preparedStatement
            prepareStatement = connection.prepareStatement(sql);
            // 执行查询
            rs = prepareStatement.executeQuery();
            // 处理结果集

            String ss = "select user_likes FROM user_info WHERE user_id = " + userid;
            PreparedStatement ps = connection.prepareStatement(ss);
            ResultSet rs2 = ps.executeQuery();
            System.out.println(rs2);
            while (rs2.next()) {
//                System.out.println("next");
//                System.out.println(rs2.getString("user_likes"));
                if (rs2.getString("user_likes") == null) {
                    while (rs.next()) {
                        if (rs.getInt("article_id") != 0) {
                            articleList.add(rs.getInt("article_id"));
                        }
                        if (rs.getInt("patent_id") != 0) {
                            patentList.add(rs.getInt("patent_id"));
                        }
                        if (rs.getInt("project_id") != 0) {
                            projectList.add(rs.getInt("project_id"));
                        }
                    }
                } else if ((rs2.getString("user_likes")).equals("人工智能")) {
                    articleList.add(434138);
                    articleList.add(431561);
                    articleList.add(420207);
                    articleList.add(430707);
                    for (int i = 4; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(500883);
                    patentList.add(488908);
                    patentList.add(493268);
                    for (int i = 3; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(459323);
                    projectList.add(462525);
                    projectList.add(461198);
                    projectList.add(456574);
                    projectList.add(457819);
                    projectList.add(461096);
                    projectList.add(461504);
                    for (int i = 7; i < 20; i++) {
                        projectList.add(null);
                    }
                } else if (rs2.getString("user_likes").equals("计算机")) {
                    articleList.add(440341);
                    articleList.add(440131);
                    articleList.add(408105);
                    articleList.add(431021);
                    articleList.add(431150);
                    articleList.add(401855);
                    articleList.add(428848);
                    articleList.add(421293);
                    articleList.add(442486);
                    articleList.add(424842);
                    articleList.add(416718);
                    articleList.add(411745);
                    articleList.add(401868);
                    articleList.add(442180);
                    articleList.add(434539);
                    articleList.add(410156);
                    articleList.add(432950);
                    articleList.add(434535);
                    articleList.add(441505);
                    articleList.add(418088);

                    patentList.add(505393);
                    patentList.add(490766);
                    patentList.add(500727);
                    patentList.add(494310);
                    patentList.add(504964);
                    patentList.add(494417);
                    patentList.add(499157);
                    patentList.add(499960);
                    patentList.add(504378);
                    patentList.add(502369);
                    patentList.add(490804);
                    patentList.add(499927);
                    patentList.add(508517);
                    patentList.add(506451);
                    patentList.add(490918);
                    patentList.add(492903);
                    patentList.add(500136);
                    patentList.add(500085);
                    patentList.add(501944);
                    patentList.add(500033);

                    projectList.add(447479);
                    projectList.add(457023);
                    projectList.add(442792);
                    projectList.add(462947);
                    projectList.add(447094);
                    projectList.add(447996);
                    projectList.add(458171);
                    projectList.add(444796);
                    projectList.add(456586);
                    projectList.add(450028);
                    projectList.add(447842);
                    projectList.add(450024);
                    projectList.add(453507);
                    projectList.add(447701);
                    projectList.add(461990);
                    projectList.add(462578);
                    projectList.add(458785);
                    projectList.add(444817);
                    projectList.add(447965);
                    projectList.add(444430);

                }
                else if (rs2.getString("user_likes").equals("计算机视觉")) {
                    articleList.add(428848);
                    articleList.add(428509);
                    articleList.add(442180);
                    articleList.add(435144);
                    articleList.add(417022);
                    articleList.add(442202);
                    articleList.add(428268);
                    for (int i = 7; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(500138);
                    patentList.add(505393);
                    patentList.add(499446);
                    patentList.add(500137);
                    patentList.add(500168);
                    patentList.add(500118);
                    patentList.add(500080);
                    patentList.add(494310);
                    patentList.add(499157);
                    patentList.add(499960);
                    patentList.add(488223);
                    patentList.add(499962);
                    patentList.add(499927);
                    patentList.add(506451);
                    patentList.add(501943);
                    patentList.add(500148);
                    patentList.add(506413);
                    patentList.add(500136);
                    patentList.add(500085);
                    patentList.add(500104);

                    projectList.add(462577);
                    projectList.add(458785);
                    for (int i = 2; i < 20; i++) {
                        projectList.add(null);
                    }
                } else if (rs2.getString("user_likes").equals("物理")) {
                    articleList.add(421582);
                    articleList.add(418174);
                    articleList.add(417553);
                    articleList.add(436095);
                    articleList.add(431886);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                    patentList.add(509247);
                    patentList.add(506027);
                    patentList.add(509688);
                    patentList.add(492417);
                    patentList.add(506028);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(453524);
                    projectList.add(452168);
                    projectList.add(445692);
                    projectList.add(453405);
                    projectList.add(454686);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if (rs2.getString("user_likes").equals("数学")) {
                    articleList.add(426316);
                    articleList.add(413546);
                    articleList.add(404614);
                    articleList.add(426645);
                    articleList.add(411105);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                    patentList.add(503030);
                    patentList.add(504282);
                    patentList.add(503821);
                    patentList.add(503825);
                    patentList.add(495326);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(454782);
                    projectList.add(460565);
                    projectList.add(454779);
                    projectList.add(447170);
                    projectList.add(460101);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }

                else if ((rs2.getString("user_likes")).equals("经济")) {
                    articleList.add(417784);
                    articleList.add(413294);
                    articleList.add(423814);
                    articleList.add(433445);
                    articleList.add(402342);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(509635);
                    patentList.add(498262);
                    patentList.add(504973);
                    patentList.add(494085);
                    patentList.add(509512);
                    patentList.add(505015);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(454251);
                    projectList.add(454187);
                    projectList.add(448472);
                    projectList.add(454732);
                    projectList.add(462699);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("心理")) {
                    articleList.add(430159);
                    articleList.add(427247);
                    articleList.add(436341);
                    articleList.add(437502);
                    articleList.add(428169);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(510665);
                    patentList.add(503219);
                    patentList.add(500163);
                    patentList.add(507431);
                    patentList.add(501949);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("历史")) {
                    articleList.add(412388);
                    articleList.add(414023);
                    articleList.add(429575);
                    articleList.add(439354);
                    articleList.add(408065);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(505332);
                    for (int i = 1; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(452136);
                    projectList.add(455084);
                    projectList.add(454802);
                    projectList.add(459362);
                    projectList.add(459365);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("政治")) {
                    articleList.add(404508);
                    articleList.add(432925);
                    articleList.add(406227);
                    articleList.add(438662);
                    articleList.add(421445);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(452767);
                    for (int i = 1; i < 20; i++) {
                        patentList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("社会")) {
                    articleList.add(438235);
                    articleList.add(404985);
                    articleList.add(419603);
                    articleList.add(425218);
                    articleList.add(406574);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(500513);
                    patentList.add(498492);
                    patentList.add(503866);
                    patentList.add(498277);
                    patentList.add(507804);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(454236);
                    projectList.add(442710);
                    projectList.add(451806);
                    projectList.add(452734);
                    projectList.add(446956);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("教育")) {
                    articleList.add(408952);
                    articleList.add(419603);
                    articleList.add(418070);
                    articleList.add(402711);
                    articleList.add(431563);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(498489);
                    patentList.add(509357);
                    patentList.add(503306);
                    patentList.add(498480);
                    patentList.add(498490);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(451862);
                    projectList.add(452736);
                    projectList.add(446955);
                    projectList.add(447400);
                    projectList.add(450334);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("化学")) {
                    articleList.add(428629);
                    articleList.add(437911);
                    articleList.add(404371);
                    articleList.add(418150);
                    articleList.add(413017);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(491784);
                    patentList.add(491797);
                    patentList.add(492005);
                    patentList.add(494897);
                    patentList.add(491570);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(462679);
                    projectList.add(446754);
                    projectList.add(445692);
                    projectList.add(445507);
                    projectList.add(458007);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("生物")) {
                    articleList.add(425744);
                    articleList.add(411708);
                    articleList.add(404371);
                    articleList.add(409854);
                    articleList.add(404038);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(491810);
                    patentList.add(494994);
                    patentList.add(507275);
                    patentList.add(498383);
                    patentList.add(508660);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(459183);
                    projectList.add(448176);
                    projectList.add(450314);
                    projectList.add(461557);
                    projectList.add(460880);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("环境科学")) {
                    articleList.add(404296);
                    for (int i = 1; i < 20; i++) {
                        articleList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        patentList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("医学")) {
                    articleList.add(425188);
                    articleList.add(425181);
                    articleList.add(425141);
                    articleList.add(425142);
                    articleList.add(436018);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(490723);
                    patentList.add(508689);
                    patentList.add(490732);
                    patentList.add(508797);
                    patentList.add(508556);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(458129);
                    projectList.add(455353);
                    projectList.add(452789);
                    projectList.add(449873);
                    projectList.add(450069);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("材料")) {
                    articleList.add(440988);
                    articleList.add(430136);
                    articleList.add(431286);
                    articleList.add(436574);
                    articleList.add(411001);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(489393);
                    patentList.add(506097);
                    patentList.add(491111);
                    patentList.add(504150);
                    patentList.add(504230);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(462749);
                    projectList.add(463010);
                    projectList.add(446213);
                    projectList.add(456867);
                    projectList.add(446376);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("土木")) {
                    articleList.add(440306);
                    projectList.add(452407);
                    projectList.add(460216);
                    for (int i = 1; i < 20; i++) {
                        articleList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        patentList.add(null);
                    }
                    for (int i = 2; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("自动化")) {
                    articleList.add(413804);
                    articleList.add(412346);
                    articleList.add(426651);
                    articleList.add(405458);
                    articleList.add(408906);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(503958);
                    patentList.add(510701);
                    patentList.add(489024);
                    patentList.add(510105);
                    patentList.add(510790);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(461234);
                    projectList.add(460928);
                    projectList.add(447838);
                    projectList.add(460789);
                    projectList.add(447861);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("机械工程")) {
                    articleList.add(412346);
                    articleList.add(415047);
                    articleList.add(413238);
                    articleList.add(412347);
                    articleList.add(412618);
                    articleList.add(423701);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(443929);
                    for (int i = 1; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("金融")) {
                    articleList.add(426644);
                    articleList.add(418842);
                    articleList.add(440092);
                    articleList.add(436537);
                    articleList.add(430373);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(503880);
                    patentList.add(492544);
                    patentList.add(498519);
                    patentList.add(489616);
                    for (int i = 4; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(454460);
                    projectList.add(449976);
                    projectList.add(451761);
                    projectList.add(452050);
                    projectList.add(462955);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("管理")) {
                    articleList.add(411744);
                    articleList.add(420513);
                    articleList.add(424635);
                    articleList.add(431287);
                    articleList.add(411461);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(500642);
                    patentList.add(502871);
                    patentList.add(510285);
                    patentList.add(488745);
                    patentList.add(506114);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(443948);
                    projectList.add(456876);
                    projectList.add(456904);
                    projectList.add(459147);
                    projectList.add(451778);
                    for (int i = 5; i < 20; i++) {
                        projectList.add(null);
                    }

                }
                else if ((rs2.getString("user_likes")).equals("艺术")) {
                    articleList.add(408717);
                    articleList.add(437530);
                    articleList.add(441983);
                    articleList.add(418735);
                    articleList.add(408719);
                    articleList.add(416500);
                    articleList.add(413106);
                    articleList.add(407059);
                    articleList.add(402219);
                    articleList.add(421637);
                    for(int i = 10; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(500984);
                    patentList.add(499728);
                    patentList.add(500570);
                    patentList.add(509013);
                    patentList.add(499858);
                    patentList.add(499573);
                    patentList.add(499847);
                    patentList.add(496345);
                    patentList.add(509012);
                    patentList.add(510571);
                    for(int i = 10; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(454217);
                    projectList.add(459657);
                    projectList.add(456114);
                    projectList.add(459652);
                    projectList.add(448764);
                    projectList.add(448478);
                    projectList.add(459702);
                    projectList.add(458024);
                    projectList.add(443107);
                    projectList.add(459654);
                    for(int i = 10; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("平面设计")) {
                    for (int i = 0; i < 20; i++) {
                        articleList.add(null);
                    }
                    patentList.add(497158);
                    for (int i = 1; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(453099);
                    projectList.add(452559);
                    for(int i = 2; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("工业设计")) {
                    articleList.add(441990);
                    articleList.add(438693);
                    articleList.add(427768);
                    articleList.add(441984);
                    articleList.add(427769);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }
                    for (int i = 0; i < 20; i++) {
                        patentList.add(null);
                    }
                    projectList.add(452615);
                    projectList.add(454992);
                    for (int i = 2; i < 20; i++) {
                        projectList.add(null);
                    }
                }
                else if ((rs2.getString("user_likes")).equals("建筑")) {
                    articleList.add(417351);
                    articleList.add(425780);
                    articleList.add(408662);
                    articleList.add(432505);
                    articleList.add(437795);
                    for (int i = 5; i < 20; i++) {
                        articleList.add(null);
                    }

                    patentList.add(499853);
                    patentList.add(495250);
                    patentList.add(510782);
                    patentList.add(509365);
                    patentList.add(510573);
                    for (int i = 5; i < 20; i++) {
                        patentList.add(null);
                    }

                    projectList.add(460611);
                    projectList.add(460610);
                    projectList.add(459366);
                    projectList.add(461872);
                    for (int i = 4; i < 20; i++) {
                        projectList.add(null);
                    }
                }
            }
        }
        finally {
                // 关闭连接，释放资源
                if (rs != null) {
                    rs.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
        }

        System.out.println(articleList);
        System.out.println(patentList);
        System.out.println(projectList);
        allList.addAll(articleList);
        allList.addAll(patentList);
        allList.addAll(projectList);

        for (int i = 0; i <= allList.size(); i++) {
            ArticleBean article = new ArticleBean();
            list.add(article);
        }

        //连接neo4j
        Driver driver = GraphDatabase.driver(NEO4JURL, AuthTokens.basic(NEO4JNAME, NEO4JPWD));
        Session session = driver.session();
        String sql1 = "MATCH (a:Article) WHERE id(a) = {id}" +
                "RETURN id(a) AS id, a.title AS title, a.partner AS partner," +
                "a.citeCount AS citeCount, a.year AS year, a.keyWord AS keyword";

        String sql2 = "MATCH (p:Patent) WHERE id(p) = {id}" +
                "RETURN id(p) AS id, p.name AS title, p.inventors AS partner," +
                "p.applyDate AS year, p.type AS type, p.state AS state," +
                "p.applicant AS applicant, p.organization AS organization";

        String sql3 = "MATCH (h:HorizontalProject) WHERE id(h) = {id}" +
                "RETURN id(h) AS id, h.name AS title, h.leader AS partner," +
                "h.approvalDate AS year, h.nature AS nature, h.area AS area," +
                "h.boss AS boss, h.planedMoney AS planedMoney, h.organization AS organization";

        for (int i = 0; i < allList.size(); i++) {
            StatementResult result = null;
            //查询Article
            if (i < 20) {
                result = session.run(sql1, parameters("id", allList.get(i)));
            } else if (i >= 20 && i < 40) {
                result = session.run(sql2, parameters("id", allList.get(i)));
            } else {
                result = session.run(sql3, parameters("id", allList.get(i)));
            }

            while (result.hasNext()) {
                Record record = result.next();
                list.get(i).setId(allList.get(i));
                list.get(i).setTitle(record.get("title").asString());
                list.get(i).setPartner(record.get("partner").asString());
//                    System.out.println(record.get("title").asString());
                list.get(i).setCiteCount(record.get("citeCount").asString());
                list.get(i).setYear(record.get("year").asString());
                list.get(i).setKeyword(record.get("keyword").asString());

                list.get(i).setType(record.get("type").asString());
                list.get(i).setState(record.get("state").asString());
                list.get(i).setApplicant(record.get("applicant").asString());
                list.get(i).setOrganization(record.get("organization").asString());

                list.get(i).setNature(record.get("nature").asString());
                list.get(i).setArea(record.get("area").asString());
                list.get(i).setBoss(record.get("boss").asString());
                list.get(i).setPlanedMoney(record.get("planedMoney").asString());
            }
        }

        session.close();
        driver.close();

        return list;
    }
}
