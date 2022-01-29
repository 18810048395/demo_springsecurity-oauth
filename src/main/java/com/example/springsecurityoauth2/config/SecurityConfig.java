package com.example.springsecurityoauth2.config;

import com.example.springsecurityoauth2.dao.PermissionMapper;
import com.example.springsecurityoauth2.pojo.PermissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberDetailsService memberDetailsService;

    @Autowired
    private PermissionMapper permissionMapper;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                authorizeRequests = http.authorizeRequests();
//        authorizeRequests
//                .antMatchers("/addMember").hasAnyAuthority("addMember")
//                .antMatchers("/delMember").hasAnyAuthority("delMember")
//                .antMatchers("/updateMember").hasAnyAuthority("updateMember")
//                .antMatchers("/showMember").hasAnyAuthority("showMember");

        // 将上面通过手动设置授权规则改为数据库动态设置
        List<PermissionEntity> allPermission = permissionMapper.findAllPermission();
        allPermission.forEach(permissionEntity -> {
            authorizeRequests.antMatchers(permissionEntity.getUrl()).hasAnyAuthority(permissionEntity.getPermTag());
        });

        authorizeRequests
        // 可以允许hello,loginPage 不被拦截
        .antMatchers("/hello").permitAll()
        .antMatchers("/toLogin").permitAll()
        .antMatchers("/oauth/*").permitAll()
        .antMatchers("/**").fullyAuthenticated()
                // 设置登录相关配置 (默认登陆页面地址是/login,默认的登出页面地址是/logout)
                .and().formLogin().loginPage("/toLogin")
                                  .loginProcessingUrl("/login")  //设置真正登陆地址的url,前台登陆地址必须以此为准
                .and().logout().logoutSuccessUrl("/toLogin")
                .and().csrf().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//        .withUser("yanghe").password(passwordEncoder().encode("123456"))
//                                      .authorities("addMember","delMember", "updateMember", "showMember")
//        .and()
//        .withUser("xiaohei").password(passwordEncoder().encode("123456"))
//                                       .authorities("showMember");

        // 将上面通过内存添加用户名密码，改为动态查询数据库设置
        auth.userDetailsService(memberDetailsService).passwordEncoder(passwordEncoder);
    }


}
