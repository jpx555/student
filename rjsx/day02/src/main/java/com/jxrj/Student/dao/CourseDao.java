package com.jxrj.Student.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxrj.Student.model.Course;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CourseDao extends BaseMapper<Course> {
}
