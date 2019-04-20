package com.bxait.cumrs.services;


import com.bxait.cumrs.entity.model.Apply;

import java.util.Map;


public interface ApplyService {
    /**
     * 邀请入队
     * @param param
     * @return
     * @throws Exception
     */
    String invite(Map<String,Object> param)throws Exception;


    /**
     * 同意邀请
     * @param apply
     * @return
     * @throws Exception
     */
    String agreeInvite(Apply apply)throws Exception;

    /**
     * 拒绝邀请
     * @param apply
     * @return
     * @throws Exception
     */
    String refusInvite(Apply apply)throws Exception;
}
