package com.bxait.cumrs.controller;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.PageQuery;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.StudentRepo;
import com.bxait.cumrs.repo.TeacherRepo;
import com.bxait.cumrs.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stu")
public class StudentController {
    @Autowired
    StudentRepo studentRepo;
    @Autowired
    StudentService studentService;

    /**
     * 列表查询
     * @param student
     * @return
     */
    @RequestMapping(value = "/getMore", method = RequestMethod.GET)
    public String getMore(@ModelAttribute PageQuery query, @ModelAttribute Student student){
        PageRequest pageRequest= PageRequest.of(query.getPage()-1,query.getLimit());
        Example<Student> example = Example.of(student, ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()));
        Page<Student> pages = studentRepo.findAll(example,pageRequest);
        return JSON.toJSONString(pages) ;
    }

    /**
     * 获取邀请列表
     * @param student
     * @param session
     * @return
     */
    @RequestMapping(value = "/getSelect", method = RequestMethod.GET)
    public String getSelect(@ModelAttribute Student student, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Student> students = studentRepo.findAllByNotStuidAAndOccupy(user.getUserName(),student.getOccupy());
        return JSON.toJSONString(students) ;
    }


    @RequestMapping(value = "/getTeamStu", method = RequestMethod.GET)
    public String getTeamStu(HttpSession session)throws Exception{
        User user = (User)session.getAttribute("user");
        List<Student> students = studentService.getTeamStu(user.getUserName());
        return JSON.toJSONString(students) ;
    }


    /**
     * 获取个人信息
     * @param stuid
     * @return
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public String getOne(@RequestParam("stuid") String stuid){
        Student student = studentRepo.findStuByStuId(stuid);
        return JSON.toJSONString(student) ;
    }

    /**
     * 注册
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String stuRegister(@RequestBody Map<String,Object> param)throws Exception{
        String res = studentService.stuRegiser(param);
        return JSON.toJSONString(res) ;
    }
}
