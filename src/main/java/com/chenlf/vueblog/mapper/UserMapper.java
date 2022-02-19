package com.chenlf.vueblog.mapper;

import com.chenlf.vueblog.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ChenLF
 * @since 2022-02-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
