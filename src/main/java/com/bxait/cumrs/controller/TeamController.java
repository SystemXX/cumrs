package com.bxait.cumrs.controller;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.PageQuery;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.Team;
import com.bxait.cumrs.repo.StudentRepo;
import com.bxait.cumrs.repo.TeacherRepo;
import com.bxait.cumrs.repo.TeamRepo;
import com.bxait.cumrs.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamRepo teamRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    TeamService teamService;

    /**
     * 获取所有
     * @return
     */
    @RequestMapping(value = "/getMore", method = RequestMethod.GET)
    public String getMore(@ModelAttribute PageQuery query, @ModelAttribute Team team){
        PageRequest pageRequest=new PageRequest(query.getPage()-1,query.getLimit());
        Example<Team> example = Example.of(team, ExampleMatcher.matchingAll()
                .withMatcher("teamName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("education", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("teamState", ExampleMatcher.GenericPropertyMatchers.contains()));
        Page<Team> pages = teamRepo.findAll(example,pageRequest);
        return JSON.toJSONString(pages) ;
    }

    @RequestMapping(value = "/getCreateTeam", method = RequestMethod.GET)
    public String getCreateTeam(@ModelAttribute PageQuery query, @ModelAttribute Team team){
        PageRequest pageRequest=new PageRequest(query.getPage()-1,query.getLimit());
        Example<Team> example = Example.of(team, ExampleMatcher.matchingAll());
        Page<Team> pages = teamRepo.findAll(example,pageRequest);
        return JSON.toJSONString(pages) ;
    }

    @RequestMapping(value = "/getMyTeam", method = RequestMethod.GET)
    public String getMyTeam(@RequestParam(value = "stuid",required = false) String stuid){
        Team team = teamRepo.findMyTeam(stuid);
        List<Team> teamList = new ArrayList<>();
        if(team!=null)
            teamList.add(team);
        return JSON.toJSONString(teamList);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody Team team)throws Exception{
        Team t = teamRepo.findMyTeam(team.getStudentOne());
        if(t != null){
            return JSON.toJSONString("你在队伍，不能重复创建");
        }
        Team tt = teamRepo.findTeamByName(team.getTeamName());
        if(tt != null){
            return JSON.toJSONString("队伍名称已存在");
        }
        Student student = studentRepo.findStuByStuId(team.getStudentOne());
        team.setTeamState(Const.TEAM_STATE_CREATE);
        team.setVacancy(Const.VACANCY_NO);
        team.setEducation(student.getEducation());
        team.setStudentOneName(student.getName());
        teamRepo.save(team);
        return JSON.toJSONString("创建成功");
    }

    /**
     * 解散队伍
     * @param team
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/dissolution", method = RequestMethod.POST)
    public String dissolution(@RequestBody Team team)throws Exception{
        String res = teamService.dissolution(team);
        return JSON.toJSONString(res);
    }
}
