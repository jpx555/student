package com.jxrj.Student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrj.Student.dao.ClassDao;
import com.jxrj.Student.model.ClassMessage;
import com.jxrj.Student.service.ClassService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends ServiceImpl<ClassDao, ClassMessage> implements ClassService {
}
