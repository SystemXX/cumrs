package com.bxait.cumrs.controller;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.PageQuery;
import com.bxait.cumrs.entity.model.Teacher;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.TeacherRepo;
import com.bxait.cumrs.services.TeacherService;
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
@RequestMapping("/tea")
public class TeacherController {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    TeacherService teacherService;

    /**
     * 列表查询
     * @param teacher
     * @param query
     * @param session
     * @return
     */
    @RequestMapping(value = "/getMore", method = RequestMethod.GET)
    public String getMore(@ModelAttribute Teacher teacher, @ModelAttribute PageQuery query, HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user.getIdentity().equals(Const.USER_IDENTITY_TEA_C)){
            teacher.setType(Const.TEA_TYPE_B);
        }
        PageRequest pageRequest = PageRequest.of(query.getPage()-1,query.getLimit());
        Example<Teacher> example = Example.of(teacher, ExampleMatcher.matchingAll()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()));
        Page<Teacher> page = teacherRepo.findAll(example,pageRequest);
        return JSON.toJSONString(page) ;
    }

    @RequestMapping(value = "/getSelect", method = RequestMethod.GET)
    public String getSelect(@ModelAttribute Teacher teacher, HttpSession session){
        User user = (User)session.getAttribute("user");
        List<Teacher> teachers = teacherRepo.findAllByNotTeaidAAndOccupy(user.getUserName(),teacher.getOccupy());
        return JSON.toJSONString(teachers) ;
    }

    /**
     * 获取个人信息
     * @param teaid
     * @return
     */
    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    public String getOne(@RequestParam("teaid") String teaid){
        Teacher teacher = teacherRepo.findTeaByTeaId(teaid);
        return JSON.toJSONString(teacher) ;
    }

    /**
     * 修改个人信息
     * @param teacher
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody Teacher teacher)throws Exception{
        String res = teacherService.update(teacher);
        return JSON.toJSONString(res) ;
    }

    /**
     * 注册
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String teaRegister(@RequestBody Map<String,Object> param)throws Exception{
        String res = teacherService.teaRegiser(param);
        return JSON.toJSONString(res) ;
    }

    /**
     * 授权
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/empower", method = RequestMethod.POST)
    public String empower(@RequestBody Long id)throws Exception{
        String res = teacherService.empower(id);
        return JSON.toJSONString(res) ;
    }

    /**
     * 取消授权
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/revoke", method = RequestMethod.POST)
    public String revoke(@RequestBody Long id)throws Exception{
        String res = teacherService.revoke(id);
        return JSON.toJSONString(res) ;
    }
}
