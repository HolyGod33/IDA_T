package com.zjut.ida.recommend.tutor.m2ndao;

import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Casterx on 2022/5/1.
 */
@Repository
public interface SysStudentDao extends Neo4jRepository<NSysStudent,Long> {


//    List<NSysStudent> findScholarsByNameContains(@Param("scholarName") String scholarName);

    NSysStudent findNSysStudentByStudentId(@Param("scholarID")String studentId);

    NSysStudent findNSysStudentById(Long id);


    /**
     * 按学生学号查询30天内有过交互的导师重映射id
     * @param studentId 学生学号
     * @return 导师重映射id
     */
    @Query("match (n:SysStudent)-[r:Choose]-(m:Scholar) where n.studentID={0} return m.tutorRemapId")
    List<Integer> findTutorRemapIdListByStudentId(String studentId);

    /**
     * 按学生学号查询30天内有过交互的导师neo4j id
     * @param studentId 学生学号
     * @return 导师neo4j id
     */
    @Query("match (n:SysStudent)-[r:Choose]-(m:Scholar) where n.studentID={0} return id(m)")
    List<Long> findTutorNeo4jIdListByStudentId(String studentId);

    /**
     * 获得协同过滤数据
     *
     * @return 整型
     */
    @Query("match (n:SysStudent)-[r:Choose]-(m:Scholar) where n.studentID={0} return count(r)")
    int hasEnoughHistory(String studentId);


//    @Query("match p=(n:SysStudent)-[r:Choose]-(m:Scholar) where n.studentID={0} return n")
    @Query("match p=(n:SysStudent)-[r:Choose]-[m:Scholar] where n.studentID={0} return n,m")
    NSysStudent findTutorsByStudentId(String studentId);

    @Query("match(n:SysStudent),(m:Scholar) where n.studentID={0} and id(m)={1} \n" +
            "merge(n)-[r:History{createTime:apoc.date.format(timestamp(),'ms','yyyy-MM-dd HH:mm:ss','CTT')}]-(m)\n")
    void saveHistory(String studentId,Long tutorId);



}
