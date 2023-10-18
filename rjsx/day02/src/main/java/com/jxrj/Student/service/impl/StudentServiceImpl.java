package com.jxrj.Student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrj.Student.dao.StudentDao;
import com.jxrj.Student.model.Student;
import com.jxrj.Student.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

}
