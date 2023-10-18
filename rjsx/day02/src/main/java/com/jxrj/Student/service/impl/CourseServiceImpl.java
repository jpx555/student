package com.jxrj.Student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrj.Student.dao.CourseDao;
import com.jxrj.Student.model.Course;
import com.jxrj.Student.service.CourseService;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course>implements CourseService {
}
