package com.jxrj.Student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrj.Student.dao.UserInfoDao;
import com.jxrj.Student.model.UserInfo;
import com.jxrj.Student.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {
}
