package com.bxait.cumrs.controller;

import com.alibaba.fastjson.JSON;
import com.bxait.cumrs.entity.model.Apply;
import com.bxait.cumrs.entity.model.Student;
import com.bxait.cumrs.repo.ApplyRepo;
import com.bxait.cumrs.services.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    ApplyRepo applyRepo;

    @Autowired
    ApplyService applyService;

    /**
     * 列表查询
     * @param student
     * @return
     */
    @RequestMapping(value = "/getMore", method = RequestMethod.GET)
    public String getMore(@ModelAttribute Student student){
        List<Apply> applyList = applyRepo.findAll();
        return JSON.toJSONString(applyList) ;
    }

    /**
     * 邀请组队
     * @param param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/invite", method = RequestMethod.POST)
    public String invite(@RequestBody Map<String,Object> param)throws Exception{
        String res = applyService.invite(param);
        return JSON.toJSONString(res);
    }

    /**
     * 获取自身邀请列表
     * @param stuid
     * @return
     */
    @RequestMapping(value = "/getMyApply", method = RequestMethod.GET)
    public String getMyApply(@RequestParam(value = "stuid",required = false) String stuid){
        List<Apply> applys = applyRepo.findMyApply(stuid);
        return JSON.toJSONString(applys);
    }

    /**
     * 同意邀请
     * @param apply
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/agreeInvite", method = RequestMethod.POST)
    public String agreeInvite(@RequestBody Apply apply)throws Exception{
        String res =  applyService.agreeInvite(apply);
        return JSON.toJSONString(res) ;
    }

    /**
     * 拒绝邀请
     * @param apply
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/refusInvite", method = RequestMethod.POST)
    public String refusInvite(@RequestBody Apply apply)throws Exception{
        String res =  applyService.refusInvite(apply);
        return JSON.toJSONString(res) ;
    }
}
