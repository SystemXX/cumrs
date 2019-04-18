package com.bxait.cumrs.controller;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.Const;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.entity.model.User;
import com.bxait.cumrs.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo ;

    /**
     * 登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String stuLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        String res = "";
        User user=userRepo.findUserByUserName(username);
        if(user == null){
            res = "该用户不存在";
        }else{
            if(!user.getPassWord().equals(password)){
                res = "密码错误";
            }else{
                session.setAttribute("user",user);
            }
        }
        return JSON.toJSONString(res);
    }

    /**
     * 获取session
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public User getSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        return user;
    }

    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public String deleteSession(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return JSON.toJSONString(Const.SUCCESS);
    }

    /**
     * 重置密码
     * @param username
     * @return
     */
    @RequestMapping(value = "/reset", method = RequestMethod.POST)
    public String resetPassWord(String username){
        if(!StringUtils.isEmpty(username)){
            User user = userRepo.findUserByUserName(username);
            user.setPassWord("123456");
            userRepo.save(user);
        }
        return JSON.toJSONString(Const.SUCCESS);
    }

}
