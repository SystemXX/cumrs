package com.bxait.cumrs.services.impl;
import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.Team;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.ApplyRepo;
import com.bxait.cumrs.repo.StudentRepo;
import com.bxait.cumrs.repo.TeamRepo;
import com.bxait.cumrs.repo.UserRepo;
import com.bxait.cumrs.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;

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
     * @param team
     * @return
     * @throws Exception
     */
    @Override
    public String create(Team team) throws Exception {
        Team t = teamRepo.findMyTeam(team.getStudentOne());
        if(t != null){
            return JSON.toJSONString("你在队伍，不能重复创建");
        }
        Team tt = teamRepo.findTeamByName(team.getTeamName());
        if(tt != null){
            return JSON.toJSONString("队伍名称已存在");
        }
        Student student = studentRepo.findStuByStuId(team.getStudentOne());
        student.setOccupy(Const.IN_THE_TEAM);
        team.setTeamState(Const.TEAM_STATE_CREATE);
        team.setVacancy(Const.VACANCY_NO);
        team.setEducation(student.getEducation());
        team.setStudentOneName(student.getName());
        teamRepo.save(team);
        studentRepo.save(student);
        return "创建成功";
    }

    /**
     * 解散队伍
     * @param team
     * @return
     * @throws Exception
     */
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

    /**
     * 退出队伍
     * @param team
     * @param session
     * @return
     * @throws Exception
     */
    @Override
    public String quitTeam(Team team, HttpSession session) throws Exception {
        User user = (User)session.getAttribute("user");
        if(team.getStudentTwo().equals(user.getUserName())){
            team.setStudentTwo(null);
            team.setStudentTwoName(null);
        }else if(team.getStudentThree().equals(user.getUserName())){
            team.setStudentThree(null);
            team.setStudentThreeName(null);
        }
        if(team.getVacancy().equals(Const.VACANCY_YES)){
            team.setVacancy(Const.VACANCY_NO);
        }
        Student student = studentRepo.findStuByStuId(user.getUserName());
        student.setOccupy(Const.NOT_IN_THE_TEAM);
        teamRepo.save(team);
        studentRepo.save(student);
        return "退出成功";
    }
}
