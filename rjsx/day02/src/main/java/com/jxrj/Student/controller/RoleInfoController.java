package com.jxrj.Student.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxrj.Student.model.RoleInfo;
import com.jxrj.Student.model.RoleInfoCondition;
import com.jxrj.Student.service.RoleInfoService;
import com.jxrj.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

@Controller
@CrossOrigin
@RequestMapping("/roleinfo")
public class RoleInfoController {

    @Autowired
    private RoleInfoService roleInfoService;


    // 根据条件分页查询用户信息数据
    @RequestMapping("/getPageRoleInfo")
    @ResponseBody
    public ResultMessage getPageRoleInfo(Page<RoleInfo> page){

        Page<RoleInfo> roleInfoPage= roleInfoService.page(page);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(roleInfoPage);
        return rm;

    }

    // 添加一个用户信息数据
    @RequestMapping("/addRoleInfo")
    @ResponseBody
    public ResultMessage addRoleInfo(RoleInfo roleInfo){

        boolean save = roleInfoService.save(roleInfo);

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
    @RequestMapping("/updateRoleInfo")
    @ResponseBody
    public ResultMessage updateRoleInfo(RoleInfo roleInfo){

        boolean update = roleInfoService.updateById(roleInfo);
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
    @RequestMapping("/deleteRoleInfo")
    @ResponseBody
    public ResultMessage deleteRoleInfo(RoleInfoCondition conn){
        ResultMessage rm = new ResultMessage();

        String ids = conn.getIds();
        if (ids == null){
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
            return rm;
        }
        String[] idArray = ids.split(",");
        boolean remove = roleInfoService.removeByIds(Arrays.asList(idArray));

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
    @RequestMapping("/getRoleInfoById")
    @ResponseBody
    public ResultMessage getRoleInfoById(RoleInfoCondition conn){

        RoleInfo stu = roleInfoService.getById(conn.getId());
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
    @RequestMapping("/getRoleInfoByNo")
    @ResponseBody
    public ResultMessage getRoleInfoByNo(RoleInfoCondition conn){
        QueryWrapper w = new QueryWrapper();
        if (conn.getName()!=null) { w.eq("name",conn.getName());}

        RoleInfo roleInfo = roleInfoService.getOne(w);

        ResultMessage rm = new ResultMessage();
        if (roleInfo!=null){
            rm.setStatus("240");
            rm.setMsg("账号重复！");
            rm.setFlag(true);
            rm.setResultData(roleInfo);
        }else{
            rm.setStatus("241");
            rm.setMsg("账号可用！");
            rm.setFlag(false);
        }

        return rm;
    }






}
