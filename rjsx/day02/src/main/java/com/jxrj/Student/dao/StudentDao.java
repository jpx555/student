package com.jxrj.Student.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxrj.Student.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
}
