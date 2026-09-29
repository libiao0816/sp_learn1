package com.libiao.lblearn1.service.impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.libiao.lblearn1.domain.po.User;
import com.libiao.lblearn1.mapper.UserMapper;
import com.libiao.lblearn1.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    
}
