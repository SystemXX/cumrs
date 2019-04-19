package com.bxait.cumrs.services.impl;

import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.Teacher;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.TeacherRepo;
import com.bxait.cumrs.repo.UserRepo;
import com.bxait.cumrs.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    UserRepo userRepo;

    /**
     * 老师注册
     * @param param
     * @throws Exception
     */
    @Override
    public String teaRegiser(Map<String, Object> param) throws Exception {
        String teaid = (String) param.get("teaid");
        String password = (String) param.get("password");
        String name = (String) param.get("name");
        String sex = (String) param.get("sex");
        String phone = (String) param.get("phone");
        String email = (String) param.get("email");
        String departments = (String) param.get("departments");
        String speciality = (String) param.get("speciality");
        String title = (String) param.get("title");
//        String teaType = "";
//        if(param.get("teaType") == null){
//            teaType = "A";
//        }else{
//            teaType = teaType;
//        }
        Teacher tea = teacherRepo.findTeaByTeaId(teaid);
        if(tea == null){
            User user = new User();
            Teacher teacher = new Teacher();
            teacher.setName(name);
            teacher.setSex(sex);
            teacher.setEmail(email);
            teacher.setPhone(phone);
            departments = departments.split("_")[0]+"-"+speciality;
            teacher.setDepartments(departments);
            teacher.setTitle(title);
            teacher.setTeaId(teaid);
            teacher.setType(Const.TEA_TYPE_B);
            teacher.setOccupy(Const.NOT_IN_THE_TEAM);
            //默认可以修改自身信息，如果报名成功则无法修改
            teacher.setLocked(Const.NOT_LOCKED);
            user.setIdentity(Const.USER_IDENTITY_TEA_B);
            user.setUserName(teaid);
            user.setPassWord(password);
            userRepo.save(user);
            teacherRepo.save(teacher);
            return "";
        }else{
            return "该用户已存在";
        }
    }

    @Override
    public String empower(Long id) throws Exception {
        teacherRepo.empower(id);
        return Const.SUCCESS;
    }

    @Override
    public String revoke(Long id) throws Exception {
        teacherRepo.revoke(id);
        return Const.SUCCESS;
    }
}
