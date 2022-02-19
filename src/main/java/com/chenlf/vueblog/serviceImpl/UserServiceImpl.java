package com.chenlf.vueblog.serviceImpl;

import com.chenlf.vueblog.entity.User;
import com.chenlf.vueblog.mapper.UserMapper;
import com.chenlf.vueblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ChenLF
 * @since 2022-02-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
