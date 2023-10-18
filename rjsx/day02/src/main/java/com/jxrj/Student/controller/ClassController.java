package com.jxrj.Student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxrj.Student.model.ClassCondition;
import com.jxrj.Student.model.ClassMessage;
import com.jxrj.Student.model.Course;
import com.jxrj.Student.model.CourseCondition;
import com.jxrj.Student.service.ClassService;
import com.jxrj.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;
    //模糊查询课程信息
    @RequestMapping("/getAllClass")
    public ResultMessage getAllClass(ClassMessage classMessage) {
        QueryWrapper w = new QueryWrapper();
        if (classMessage.getCounsellor()!= null) {
            w.like("counsellor", classMessage.getCounsellor());
        }
        List<ClassMessage> classList = classService.list(w);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(classList);
        return rm;
    }

    // 根据条件分页查询课程数据
    @RequestMapping("/getPageClass")
    public ResultMessage getPageClass(Page<ClassMessage> page, ClassMessage classMessage){
        QueryWrapper w = new QueryWrapper();
        if (classMessage.getCounsellor()!=null && !"".equals(classMessage.getCounsellor())) { w.like("counsellor",classMessage.getCounsellor());}
//        Page<Student> page = new Page<Student>(1,2);
        Page<ClassMessage> classPage= classService.page(page,w);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(classPage);
        return rm;

    }

    //根据课程id查询课程信息
    @RequestMapping("/getClassById")
    public ResultMessage getClassById(ClassMessage classMessage) {
        ClassMessage classbyId = classService.getById(classMessage.getId());
        ResultMessage rm = new ResultMessage();
        if (classbyId != null) {
            rm.setStatus("240");
            rm.setMsg("学号重复！");
            rm.setFlag(true);
            rm.setResultData(classbyId);
        } else {
            rm.setStatus("241");
            rm.setMsg("学号可用！");
            rm.setFlag(false);
        }
        return rm;
    }
    // 根据图书编号查课程信息
    @RequestMapping("/getClassByNo")
    public ResultMessage getCourseByNo(ClassMessage classMessage){
        QueryWrapper w = new QueryWrapper();
        if (classMessage.getClassno() != null) { w.eq("classno",classMessage.getClassno());}

        ClassMessage one = classService.getOne(w);

        ResultMessage rm = new ResultMessage();
        if (one!=null){
            rm.setStatus("240");
            rm.setMsg("学号重复！");
            rm.setFlag(true);
            rm.setResultData(one);
        }else{
            rm.setStatus("241");
            rm.setMsg("学号可用！");
            rm.setFlag(false);
        }

        return rm;
    }


    // 添加一个课程数据
    @RequestMapping("/addClass")
    public ResultMessage addClass(ClassMessage classMessage){

        boolean save = classService.save(classMessage);

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

    // "1"  , "1,3,6"
    // 根据id删除课程信息
    @RequestMapping("/deleteClass")
    public ResultMessage deleteClass(ClassCondition classCondition){
        ResultMessage rm = new ResultMessage();
        String ids = classCondition.getIds();
        if (ids == null){
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
            return rm;
        }
        String[] idArray = ids.split(",");
        boolean remove = classService.removeByIds(Arrays.asList(idArray));
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

    // 修改一个学生数据
    @RequestMapping("/updateClass")
    public ResultMessage updateClass(ClassMessage classMessage){

        boolean update = classService.updateById(classMessage);

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

}
