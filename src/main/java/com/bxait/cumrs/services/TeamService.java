package com.bxait.cumrs.services;

import com.bxait.cumrs.entity.model.Team;

import javax.servlet.http.HttpSession;


public interface TeamService {

    /**
     * 创建队伍
     * @param team
     * @return
     * @throws Exception
     */
    String create(Team team)throws Exception;

    /**
     * 解散队伍
     * @param team
     * @return
     * @throws Exception
     */
    String dissolution(Team team)throws Exception;

    /**
     * 退出队伍
     * @param team
     * @param session
     * @return
     * @throws Exception
     */
    String quitTeam(Team team, HttpSession session)throws Exception;
}
