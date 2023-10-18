package com.jxrj.Student.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.jxrj.Student.dao.RoleInfoDao;

import com.jxrj.Student.model.RoleInfo;

import com.jxrj.Student.service.RoleInfoService;
import org.springframework.stereotype.Service;

@Service
public class RoleInfoServiceImpl extends ServiceImpl<RoleInfoDao, RoleInfo> implements RoleInfoService {
}
