package com.chenlf.vueblog.shiro;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 
 * @author ChenLF
 * @date 2022/02/19 21:55
 **/

@Getter
public class JwtToken implements AuthenticationToken {
    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
