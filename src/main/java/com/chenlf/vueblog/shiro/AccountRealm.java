package com.chenlf.vueblog.shiro;

import com.chenlf.vueblog.entity.User;
import com.chenlf.vueblog.service.UserService;
import com.chenlf.vueblog.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author ChenLF
 * @date 2022/02/19 21:29
 **/
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        JwtToken token = (JwtToken) authenticationToken;

        String userId = jwtUtils.getClaimByToken((String) token.getPrincipal()).getSubject();

        User user = userService.getById(Long.valueOf(userId));

        if (user == null){
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1){
            throw new UnknownAccountException("账户已被锁定");
        }

        AcountProfile profile = new AcountProfile();
        BeanUtils.copyProperties(user,profile);

        System.out.println("--------------------------------------");

        return new SimpleAuthenticationInfo(profile,token.getCredentials(),getName());
    }
}
