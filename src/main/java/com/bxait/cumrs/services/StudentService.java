package com.bxait.cumrs.services;

import com.bxait.cumrs.entity.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * 學生注冊
     * @param param
     * @return
     * @throws Exception
     */
    String stuRegiser(Map<String,Object> param)throws Exception;

    /**
     * 获取团队成员信息
     * @param teaid
     * @return
     * @throws Exception
     */
    List<Student> getTeamStu(String teaid)throws Exception;

    /**
     * 修改个人信息
     * @param student
     * @return
     * @throws Exception
     */
    String update(Student student)throws Exception;
}
