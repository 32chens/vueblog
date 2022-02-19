package com.chenlf.vueblog.serviceImpl;

import com.chenlf.vueblog.entity.Blog;
import com.chenlf.vueblog.mapper.BlogMapper;
import com.chenlf.vueblog.service.BlogService;
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
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
