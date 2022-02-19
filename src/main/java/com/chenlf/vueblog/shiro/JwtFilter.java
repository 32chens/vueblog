package com.chenlf.vueblog.shiro;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.chenlf.vueblog.common.Result;
import com.chenlf.vueblog.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticatingFilter类，当执行登录时（executeLogin）会生成一个Token,
 * 然后交给Shiro最后委托给Realm处理
 * @author ChenLF
 * @date 2022/02/19 21:45
 **/
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("HttpServletRequest");
        if (StringUtils.isEmpty(jwt)){
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)){
            //没jwt直接放行
            return true;
        }else{
            //校验jwt
            Claims claimByToken = jwtUtils.getClaimByToken(jwt);
            if (claimByToken == null || jwtUtils.isTokenExpired(claimByToken.getExpiration())){
                throw new ExpiredCredentialsException("token已失效,请重新登录");
            }

            //执行登录
            return executeLogin(servletRequest,servletResponse);

        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable = null;
        if (e.getCause() != null) {
            throwable = e.getCause();
        }
        Result ret = Result.fail(throwable.getMessage());
        String json = JSONUtil.toJsonStr(ret);

        try {
            httpServletResponse.getWriter().print(json);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return false;
    }
}
