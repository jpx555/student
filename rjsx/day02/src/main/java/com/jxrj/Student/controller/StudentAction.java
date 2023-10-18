package com.jxrj.Student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxrj.Student.model.Student;
import com.jxrj.Student.model.StudentCondition;
import com.jxrj.Student.service.StudentService;
import com.jxrj.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/student")
public class StudentAction {

    @Autowired
    private StudentService studentService;

    // 根据条件查询所有学生数据
    @RequestMapping("/getAllStudent")
    @ResponseBody
    public ResultMessage getAllStudent(StudentCondition conn){
        QueryWrapper w = new QueryWrapper();
        if (conn.getName()!=null) { w.like("name",conn.getName());}
        if (conn.getGrade()!=null) { w.eq("grade",conn.getGrade());}


        List<Student> stus= studentService.list(w);

        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(stus);
        return rm;
    }

    // 根据条件分页查询学生数据
    @RequestMapping("/getPageStudent")
    @ResponseBody
    public ResultMessage getPageStudent(Page<Student> page,StudentCondition conn){
        QueryWrapper w = new QueryWrapper();
        if (conn.getName()!=null && !"".equals(conn.getName())) { w.like("name",conn.getName());}
        if (conn.getGrade()!=null && !"".equals(conn.getGrade())) { w.eq("grade",conn.getGrade());}
//        Page<Student> page = new Page<Student>(1,2);
        Page<Student> stus= studentService.page(page,w);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(stus);
        return rm;

    }


    // 根据id查一个学生详情
    @RequestMapping("/getStudentById")
    @ResponseBody
    public ResultMessage getStudentById(StudentCondition conn){

        Student stu = studentService.getById(conn.getId());

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
    @RequestMapping("/getStudentByNo")
    @ResponseBody
    public ResultMessage getStudentByNo(StudentCondition conn){
        QueryWrapper w = new QueryWrapper();
        if (conn.getStuno()!=null) { w.eq("stuno",conn.getStuno());}

        Student stu = studentService.getOne(w);

        ResultMessage rm = new ResultMessage();
        if (stu!=null){
            rm.setStatus("240");
            rm.setMsg("学号重复！");
            rm.setFlag(true);
            rm.setResultData(stu);
        }else{
            rm.setStatus("241");
            rm.setMsg("学号可用！");
            rm.setFlag(false);
        }

        return rm;
    }


    // 添加一个学生数据
    @RequestMapping("/addStudent")
    @ResponseBody
    public ResultMessage addStudent(Student stu){

        boolean save = studentService.save(stu);

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
    // 根据id删除学生
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public ResultMessage deleteStudent(StudentCondition conn){
        ResultMessage rm = new ResultMessage();

        String ids = conn.getIds();
        if (ids == null){
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
            return rm;
        }
        String[] idArray = ids.split(",");
        boolean remove = studentService.removeByIds(Arrays.asList(idArray));


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
    @RequestMapping("/updateStudent")
    @ResponseBody
    public ResultMessage updateStudent(Student stu){

        boolean update = studentService.updateById(stu);

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
