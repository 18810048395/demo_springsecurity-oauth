package com.example.springsecurityoauth2.config;

import com.example.springsecurityoauth2.dao.UserMapper;
import com.example.springsecurityoauth2.pojo.PermissionEntity;
import com.example.springsecurityoauth2.pojo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1.登录的时候 调用该方法 userName查询账户是否存在 在验证账户的密码
        UserEntity userEntity = userMapper.findByUsername(username);
        if (null == userEntity){
            return null;
        }
        // 2.在根据该账户的 username 关联id查询 角色对应权限 动态添加
        List<PermissionEntity> permissionByUsername = userMapper.findPermissionByUsername(username);
        // 给当前查出的用户动态设置授权权限
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        permissionByUsername.forEach((p -> {
            // 相当于中的通过内存设置权限的代码片段
            //  auth.inMemoryAuthentication()
            //        .withUser("yanghe").password(passwordEncoder().encode("123456"))
            //                                      .authorities("addMember","delMember", "updateMember", "showMember")
            grantedAuthorities.add(new SimpleGrantedAuthority(p.getPermTag()));
        }));
        userEntity.setAuthorities(grantedAuthorities);
        return userEntity;
    }
}
