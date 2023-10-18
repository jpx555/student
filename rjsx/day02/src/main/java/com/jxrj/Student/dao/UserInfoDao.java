package com.jxrj.Student.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jxrj.Student.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfo> {

}
