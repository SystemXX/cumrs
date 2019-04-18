package com.bxait.cumrs.services;

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
}
