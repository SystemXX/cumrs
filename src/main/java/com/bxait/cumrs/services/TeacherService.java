package com.bxait.cumrs.services;

import com.bxait.cumrs.entity.model.Teacher;

import java.util.Map;

public interface TeacherService {

    /**
     * 注册
     * @param param
     * @return
     * @throws Exception
     */
    String teaRegiser(Map<String, Object> param)throws Exception;

    /**
     * 授权
     * @param id
     * @return
     * @throws Exception
     */
    String empower(Long id)throws Exception;

    /**
     * 取消授权
     * @param id
     * @return
     * @throws Exception
     */
    String revoke(Long id)throws Exception;

    /**
     * 修改个人信息
     * @param teacher
     * @return
     * @throws Exception
     */
    String update(Teacher teacher)throws Exception;
}
