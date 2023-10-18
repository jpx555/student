package com.jxrj.user.control;

import com.jxrj.common.ResultMessage;
import com.jxrj.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserAction {


    @RequestMapping("/login")
    @ResponseBody
    public ResultMessage login(User user){
        System.out.println(user);
        // 请求服务层处理数据

        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("登录成功！");
        rm.setFlag(true);
        rm.setResultData(user);
        return rm;
    }

}
