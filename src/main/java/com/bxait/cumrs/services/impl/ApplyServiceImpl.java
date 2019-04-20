package com.bxait.cumrs.services.impl;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.*;
import com.bxait.cumrs.repo.*;
import com.bxait.cumrs.services.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyRepo applyRepo;

    @Autowired
    TeamRepo teamRepo;

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    UserRepo userRepo;

    /**
     * 邀请入队
     *
     * @param param
     * @return
     * @throws Exception
     */
    @Override
    public String invite(Map<String, Object> param) throws Exception {
        //获取参数
        String applyType = (String) param.get("applyType");
        String inviter = (String) param.get("userName");
        String students = (String) param.get("students");
        String teamName = (String) param.get("teamName");

        //获取邀请的团队
        Team team = teamRepo.findTeamByStuOne(inviter);
        //邀请老师or学生
        if (Const.VACANCY_INVITE.equals(applyType)) {
            List<Student> selectStu = JSON.parseArray(students).toJavaList(Student.class);
            for (Student student : selectStu) {
                if (applyRepo.findByInviterAndInvited(inviter, student.getStuid()) == null) {
                    Apply apply = new Apply();
                    apply.setApplyDate(new Date());
                    apply.setTeamName(teamName);
                    apply.setInviter(inviter);
                    apply.setInvited(student.getStuid());
                    apply.setInviterName(studentRepo.findStuByStuId(inviter).getName());
                    apply.setInvitedName(student.getName());
                    apply.setApplyType(Const.VACANCY_INVITE);
                    applyRepo.save(apply);
                }
            }
        } else {
            if (team.getStudentOne() == null || team.getStudentTwo() == null || team.getStudentThree() == null) {
                return "该队伍成员未满，无法邀请老师报名";
            }
            List<Teacher> seleteTea = JSON.parseArray(students).toJavaList(Teacher.class);
            for (Teacher teacher : seleteTea) {
                if (applyRepo.findByInviterAndInvited(inviter, teacher.getTeaId()) == null) {
                    Apply apply = new Apply();
                    apply.setApplyDate(new Date());
                    apply.setTeamName(teamName);
                    apply.setInviter(inviter);
                    apply.setInvited(teacher.getTeaId());
                    apply.setInviterName(studentRepo.findStuByStuId(inviter).getName());
                    apply.setInvitedName(teacher.getName());
                    apply.setApplyType(Const.APPLYTYPE_ENROLL);
                    applyRepo.save(apply);
                }
            }
        }
        return "邀请成功";
    }

    /**
     * 同意邀请
     *
     * @param apply
     * @return
     * @throws Exception
     */
    @Override
    public String agreeInvite(Apply apply) throws Exception {

        User user = userRepo.findUserByUserName(apply.getInviter());
        //根据邀请人获取团队信息
        Team team = teamRepo.findTeamByStuOne(apply.getInviter());

        if (Const.VACANCY_INVITE.equals(apply.getApplyType())) {
            Student student = studentRepo.findStuByStuId(apply.getInvited());
            if (Const.IN_THE_TEAM.equals(student.getOccupy())) {
                applyRepo.delete(apply);
                return "你已在队伍中，无法接受邀请";
            }
            if (team.getVacancy().equals(Const.VACANCY_YES)) {
                return "该队伍人数已满";
            }
            //学生同意邀请后更新自身状态
            if (team.getStudentTwo() == null) {
                team.setStudentTwo(apply.getInvited());
                team.setStudentTwoName(apply.getInvitedName());
                studentRepo.updateStuOccupyInTeam(apply.getInvited(), Const.IN_THE_TEAM);
                applyRepo.deleteApply(apply.getInvited());
                if (team.getStudentThree() != null) {
                    team.setTeamState(Const.TEAM_STATE_VERIFY);
                    team.setVacancy(Const.VACANCY_YES);
                }
            } else if (team.getStudentThree() == null) {
                //如果事最后加入，则队伍已满
                team.setStudentThree(apply.getInvited());
                team.setStudentThreeName(apply.getInvitedName());
                studentRepo.updateStuOccupyInTeam(apply.getInvited(), Const.IN_THE_TEAM);
                team.setTeamState(Const.TEAM_STATE_VERIFY);
                team.setVacancy(Const.VACANCY_YES);
                applyRepo.deleteApply(apply.getInvited());
            }
        } else {
            Teacher teacher = teacherRepo.findTeaByTeaId(apply.getInvited());
            if (Const.IN_THE_TEAM.equals(teacher.getOccupy())) {
                applyRepo.delete(apply);
                return "你已在队伍中，无法接受邀请";
            }
            if (team.getTeacherId() != null) {
                return "该队伍人数已满";
            }
            //老师同意申请，则通过审核报名成功，锁定所有成员，更新状态
            team.setTeacherId(apply.getInvited());
            team.setTeacherName(apply.getInvitedName());
            team.setVacancy(Const.VACANCY_YES);
            team.setTeamState(Const.TEAM_STATE_VERIFY_SUCCESS);
            teacherRepo.updateTeaOccupyInTeam(apply.getInvited(), Const.IN_THE_TEAM);
            studentRepo.updateStuLock(team.getStudentOne(), Const.LOCKED);
            studentRepo.updateStuLock(team.getStudentTwo(), Const.LOCKED);
            studentRepo.updateStuLock(team.getStudentThree(), Const.LOCKED);
            teacherRepo.updateTeaLock(team.getTeacherId(), Const.LOCKED);
            List<Apply> applies = applyRepo.findApplyByTeamName(team.getTeamName());
            //报名成功则删除该队伍相关邀请信息
            if (!applies.isEmpty()) {
                applyRepo.deleteAll(applies);
            }
        }
        teamRepo.save(team);
        return "操作成功";
    }

    @Override
    public String refusInvite(Apply apply) throws Exception {
        //拒绝邀请时删除当前邀请记录其余状态不做改变
        applyRepo.delete(apply);
        return "操作成功";
    }


}
