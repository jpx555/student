package com.jxrj.Student.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxrj.Student.model.Student;
import com.jxrj.Student.model.StudentCondition;
import com.jxrj.Student.model.UserInfo;
import com.jxrj.Student.model.UserInfoCondition;
import com.jxrj.Student.service.UserInfoService;
import com.jxrj.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@CrossOrigin
@RequestMapping("/userinfo")

public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    // 根据条件分页查询用户信息数据
    @RequestMapping("/getPageUserInfo")
    @ResponseBody
    public ResultMessage getPageUserInfo(Page<UserInfo> page){

        Page<UserInfo> userInfoPage=  userInfoService.page(page);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(userInfoPage);
        return rm;

    }


    // 添加一个用户信息数据
    @RequestMapping("/addUseInfo")
    @ResponseBody
    public ResultMessage addUseInfo(UserInfo userInfo){

        boolean save = userInfoService.save(userInfo);

        ResultMessage rm = new ResultMessage();
        if (save){
            rm.setStatus("250");
            rm.setMsg("添加成功！");
            rm.setFlag(true);
        }else{
            rm.setStatus("251");
            rm.setMsg("添加失败！");
            rm.setFlag(false);
        }
        return rm;
    }


    // 修改一个用户信息数据
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public ResultMessage updateUserInfo(UserInfo userInfo){

        boolean update = userInfoService.updateById(userInfo);
        ResultMessage rm = new ResultMessage();
        if (update){
            rm.setStatus("260");
            rm.setMsg("修改成功！");
            rm.setFlag(true);
        }else{
            rm.setStatus("261");
            rm.setMsg("修改失败！");
            rm.setFlag(false);
        }
        return rm;
    }



    // "1"  , "1,3,6"
    // 根据id删除学生
    @RequestMapping("/deleteUserInfo")
    @ResponseBody
    public ResultMessage deleteUserInfo(UserInfoCondition conn){
        ResultMessage rm = new ResultMessage();

        String ids = conn.getIds();
        if (ids == null){
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
            return rm;
        }
        String[] idArray = ids.split(",");
        boolean remove = userInfoService.removeByIds(Arrays.asList(idArray));

        if (remove){
            rm.setStatus("270");
            rm.setMsg("删除成功！");
            rm.setFlag(true);
        }else{
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
        }
        return rm;
    }


    // 根据id查一个学生信息详情
    @RequestMapping("/getUserInfoById")
    @ResponseBody
    public ResultMessage getUserInfoById(UserInfoCondition conn){

        UserInfo stu = userInfoService.getById(conn.getId());
        ResultMessage rm = new ResultMessage();
        if (stu!=null){
            rm.setStatus("230");
            rm.setMsg("查询学生成功！");
            rm.setFlag(true);
            rm.setResultData(stu);
        }else{
            rm.setStatus("231");
            rm.setMsg("查询学生失败！");
            rm.setFlag(false);
        }

        return rm;
    }

    // 根据学号查一个学生
    @RequestMapping("/getUserInfoByNo")
    @ResponseBody
    public ResultMessage getUserInfoByNo(UserInfoCondition conn){
        QueryWrapper w = new QueryWrapper();
        if (conn.getAccountnum()!=null) { w.eq("accountnum",conn.getAccountnum());}

        UserInfo userInfo = userInfoService.getOne(w);

        ResultMessage rm = new ResultMessage();
        if (userInfo!=null){
            rm.setStatus("240");
            rm.setMsg("账号重复！");
            rm.setFlag(true);
            rm.setResultData(userInfo);
        }else{
            rm.setStatus("241");
            rm.setMsg("账号可用！");
            rm.setFlag(false);
        }

        return rm;
    }




}
