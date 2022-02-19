package com.chenlf.vueblog.shiro;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @author ChenLF
 * @date 2022/02/19 23:24
 **/
@Data
public class AcountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;
}
