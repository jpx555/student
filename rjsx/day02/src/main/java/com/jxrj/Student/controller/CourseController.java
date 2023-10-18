package com.jxrj.Student.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jxrj.Student.model.Course;
import com.jxrj.Student.model.CourseCondition;
import com.jxrj.Student.service.CourseService;
import com.jxrj.common.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //模糊查询课程信息
    @RequestMapping("/getAllCourse")
    public ResultMessage getAllCourse(Course course) {
        QueryWrapper w = new QueryWrapper();
        if (course.getCoursename() != null) {
            w.like("coursename", course.getCoursename());
        }
        if (course.getTeacher() != null) {
            w.like("teacher", course.getTeacher());
        }
        List<Course> courseList = courseService.list(w);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(courseList);
        return rm;
    }

    // 根据条件分页查询课程数据
    @RequestMapping("/getPageCourse")
    public ResultMessage getPageCourse(Page<Course> page, Course course){
        QueryWrapper w = new QueryWrapper();
        if (course.getCoursename()!=null && !"".equals(course.getCoursename())) { w.like("coursename",course.getCoursename());}
        if (course.getTeacher()!=null && !"".equals(course.getTeacher())) { w.eq("teacher",course.getTeacher());}
//        Page<Student> page = new Page<Student>(1,2);
        Page<Course> coursePage= courseService.page(page,w);
        ResultMessage rm = new ResultMessage();
        rm.setStatus("200");
        rm.setMsg("查询成功！");
        rm.setFlag(true);
        rm.setResultData(coursePage);
        return rm;

    }

    //根据课程id查询课程信息
    @RequestMapping("/getCourseById")
    public ResultMessage getCourseById(Course course) {
        Course coursebyId = courseService.getById(course.getId());
        ResultMessage rm = new ResultMessage();
        if (coursebyId != null) {
            rm.setStatus("240");
            rm.setMsg("学号重复！");
            rm.setFlag(true);
            rm.setResultData(coursebyId);
        } else {
            rm.setStatus("241");
            rm.setMsg("学号可用！");
            rm.setFlag(false);
        }
        return rm;
    }
    // 根据图书编号查课程信息
    @RequestMapping("/getCourseByNo")
    public ResultMessage getCourseByNo(Course course){
        QueryWrapper w = new QueryWrapper();
        if (course.getCourseno() != null) { w.eq("courseno",course.getCourseno());}

        Course one = courseService.getOne(w);

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
    @RequestMapping("/addCourse")
    public ResultMessage addCourse(Course course){

        boolean save = courseService.save(course);

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
    @RequestMapping("/deleteCourse")
    public ResultMessage deleteCourse(CourseCondition courseCondition){
        ResultMessage rm = new ResultMessage();
        String ids = courseCondition.getIds();
        if (ids == null){
            rm.setStatus("271");
            rm.setMsg("删除失败！");
            rm.setFlag(false);
            return rm;
        }
        String[] idArray = ids.split(",");
        boolean remove = courseService.removeByIds(Arrays.asList(idArray));
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
    @RequestMapping("/updateCourse")
    public ResultMessage updateCourse(Course course){

        boolean update = courseService.updateById(course);

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
