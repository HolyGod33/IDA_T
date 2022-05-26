package com.zjut.ida.recommend.tutor.config;

import com.zjut.ida.recommend.tutor.core.entity.SysStudent;
import com.zjut.ida.recommend.tutor.core.m2nentity.NSysStudent;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author wly
 * @date 2021/4/22 10:42
 */
@Component
public class SysStudentHolder {
    private static final ThreadLocal<NSysStudent> THREAD_STUDENT = new ThreadLocal<>();

    public void setStudent(NSysStudent student){
        THREAD_STUDENT.set(student);
    }

    public NSysStudent getStudent(){
        return THREAD_STUDENT.get();
    }

    public String getStudentId() {
        return THREAD_STUDENT.get().getStudentId();
    }

    public String getStudentIdOrElseNull() {
        return Optional.ofNullable(THREAD_STUDENT.get()).orElseGet(NSysStudent::new).getStudentId();
    }

    public boolean exist() {
        return THREAD_STUDENT.get() != null && THREAD_STUDENT.get().getStudentId() != null;
    }

    public void clear(){
        THREAD_STUDENT.remove();
    }
}
