package com.jxrj.user.control;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jxrj.Exception.ServiceException;
import com.jxrj.common.ResultMessage;
import com.jxrj.user.model.User;
import com.jxrj.user.service.IUserService;
import com.jxrj.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserAction {


    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public ResultMessage login(@RequestBody User user){
        ResultMessage rm = new ResultMessage();
        // 请求参数
        QueryWrapper  w = new QueryWrapper();
        w.eq("username",user.getUsername());
        w.eq("password",user.getPassword());

        User loginUser = userService.getOne(w);
        if(loginUser != null){
            //保存对象信息并加密
            HashMap<String, Object> map = new HashMap<>();
            map.put("user",loginUser.getId());
            String token = TokenUtils.genToken(loginUser.getId().toString(),loginUser.getPassword());
            user.setToken(token);
            rm.setStatus("200");
            rm.setMsg("登录成功！");
            rm.setFlag(true);
            rm.setResultData(user);
            return rm;

        }
        else{
            throw new ServiceException("600", "用户名或密码错误");
        }

    }

    @RequestMapping("/register")
    public ResultMessage register(@RequestBody User user){
        // 请求服务层处理数据
        user.setId(new Date().getTime()+"");
        boolean save = userService.save(user);
        ResultMessage rm = new ResultMessage();
        if (save){
            rm.setStatus("210");
            rm.setMsg("注册成功！");
            rm.setFlag(true);
            rm.setResultData(user);
        }else{
            rm.setStatus("211");
            rm.setMsg("注册失败！");
            rm.setFlag(false);
        }
        return rm;
    }
    @RequestMapping("/queryByName")
    public ResultMessage queryByName(@RequestBody User user){
        // 请求参数
        QueryWrapper  w = new QueryWrapper();
        w.eq("username",user.getUsername());
        User one = userService.getOne(w);// username=?
        ResultMessage rm = new ResultMessage();
        if (one != null){
            rm.setStatus("210");
            rm.setMsg("用户已存在！");
            rm.setFlag(true);
            rm.setResultData(user);
        }else{
            rm.setStatus("211");
            rm.setMsg("用户不存在");
            rm.setFlag(false);
        }
        return rm;
    }


    @RequestMapping("/userInfo")
    public ResultMessage userInfo(User user){
//        User userInfo = userService.getById(user.getNo());

        // 请求参数
        QueryWrapper  w = new QueryWrapper();
        w.eq("no",user.getId());
        User userInfo = userService.getOne(w);
        ResultMessage rm = new ResultMessage();
        if (userInfo!=null){
            rm.setStatus("220");
            rm.setMsg("查询用户详情成功！");
            rm.setFlag(true);
            rm.setResultData(userInfo);
        }else{
            rm.setStatus("221");
            rm.setMsg("查询用户详情失败！");
            rm.setFlag(false);
        }
        return rm;
    }


    @RequestMapping("/userList")
    public ResultMessage userList(User user){
//        User userInfo = userService.getById(user.getNo());

        // 请求参数
        QueryWrapper  w = new QueryWrapper();
        if (user.getUsername()!=null){
            w.like("username",user.getUsername());
        }
        List<User> list = userService.list(w);
        ResultMessage rm = new ResultMessage();
        if (list!=null){
            rm.setStatus("230");
            rm.setMsg("查询用户列表成功！");
            rm.setFlag(true);
            rm.setResultData(list);
        }else{
            rm.setStatus("231");
            rm.setMsg("查询用户列表失败！");
            rm.setFlag(false);
        }
        return rm;
    }
}
