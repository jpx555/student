package com.jxrj.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxrj.user.dao.IUserDao;
import com.jxrj.user.model.User;
import com.jxrj.user.service.IUserService;

import org.springframework.stereotype.Service;


@Service
public class UserService extends ServiceImpl<IUserDao, User> implements IUserService {

}
