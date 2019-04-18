package com.bxait.cumrs.services.impl;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.Apply;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.Team;
import com.bxait.cumrs.repo.ApplyRepo;
import com.bxait.cumrs.repo.StudentRepo;
import com.bxait.cumrs.repo.TeamRepo;
import com.bxait.cumrs.repo.UserRepo;
import com.bxait.cumrs.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("teamService")
public class TeamServiceImpl implements TeamService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    ApplyRepo applyRepo;

    @Autowired
    TeamRepo teamRepo;

    /**
     * 创建队伍
     * @param t
     * @return
     * @throws Exception
     */
    @Override
    public String create(Team t) throws Exception {
        Team team = new Team();
//        Student studentOne = studentRepo.findStuByStuId(t.getStudentOne());
//        Student studentTwo = studentRepo.findStuByStuId(t.getStudentTwo());
//        Student studentThree = studentRepo.findStuByStuId(t.getStudentThree());
      /*  team.setTeamName(t.getTeamName());
        team.setStudentOne(t.getStudentOne());
        team.setStudentTwo(t.getStudentTwo());
        team.setStudentThree(t.getStudentThree());
        team.setTeacherId(t.getTeacherId());
        team.setEducation(studentOne.getEducation());
        team.setTeamState(Const.TEAM_STATE_CREATE);
        studentRepo.updateStuOccupyInTeam(t.getStudentOne(),Const.IN_THE_TEAM);
        studentRepo.updateStuOccupyInTeam(t.getStudentTwo(),Const.IN_THE_TEAM);
        studentRepo.updateStuOccupyInTeam(t.getStudentThree(),Const.IN_THE_TEAM);
        team.setTeamState();*/
        return null;
    }

    @Override
    public String dissolution(Team team) throws Exception {
        Student s1 = studentRepo.findStuByStuId(team.getStudentOne());
        Student s2 = studentRepo.findStuByStuId(team.getStudentTwo());
        Student s3 = studentRepo.findStuByStuId(team.getStudentThree());
        //设置未在队伍中
        s1.setOccupy(Const.NOT_IN_THE_TEAM);
        if(s2 != null){
            s2.setOccupy(Const.NOT_IN_THE_TEAM);
        }
        if(s3 != null){
            s3.setOccupy(Const.NOT_IN_THE_TEAM);
        }
        //删除自己发布的邀请
        applyRepo.deleteApply(team.getStudentOne());
        //删除队伍
        teamRepo.delete(team);
        return "操作成功";
    }
}
