package com.bxait.cumrs.services.impl;


import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.Teacher;
import com.bxait.cumrs.entity.model.Team;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.StudentRepo;
import com.bxait.cumrs.repo.TeacherRepo;
import com.bxait.cumrs.repo.TeamRepo;
import com.bxait.cumrs.repo.UserRepo;
import com.bxait.cumrs.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    TeamRepo teamRepo;

    /**
     * 学生注册
     * @param param
     * @throws Exception
     */
    @Override
    public String stuRegiser(Map<String, Object> param) throws Exception {
        String stuid = (String) param.get("stuid");
        String password = (String) param.get("password");
        String name = (String) param.get("name");
        String age = (String) param.get("age");
        String sex = (String) param.get("sex");
        String phone = (String) param.get("phone");
        String email = (String) param.get("email");
        String speciality = (String) param.get("speciality");
        String departments = (String) param.get("departments");
        String special = (String) param.get("special");
        String education = (String) param.get("education");
        String remarks = (String) param.get("remarks");
        Student stu = studentRepo.findStuByStuId(stuid);
        if(stu == null){
            User user = new User();
            Student student = new Student();
            student.setName(name);
            student.setAge(age);
            student.setSex(sex);
            student.setEmail(email);
            student.setPhone(phone);
            student.setSpeciality(speciality);
            departments = departments.split("_")[0]+"-"+special;
            student.setDepartments(departments);
            student.setRemarks(remarks);
            student.setEducation(education);
            student.setStuid(stuid);
            student.setOccupy(Const.NOT_IN_THE_TEAM);
            student.setLocked(Const.NOT_LOCKED);
            user.setIdentity(Const.USER_IDENTITY_STU);
            user.setUserName(stuid);
            user.setPassWord(password);
            userRepo.save(user);
            studentRepo.save(student);
            return "";
        }else{
            return "该用户已存在";
        }
    }

    /**
     * 获取团队成员信息
     * @param teaid
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> getTeamStu(String teaid) throws Exception {
        Team team = teamRepo.findTeamByTeaid(teaid);
        List<Student> students = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        if(team != null){
            ids.add(team.getStudentOne());
            ids.add(team.getStudentTwo());
            ids.add(team.getStudentThree());
            students = studentRepo.findStuByStuIds(ids);
        }
        return students;
    }
}
