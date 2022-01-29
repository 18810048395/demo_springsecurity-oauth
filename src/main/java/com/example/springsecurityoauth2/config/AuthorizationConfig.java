package com.example.springsecurityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

/**
 * 认证授权Server端
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTokenStore jdbcTokenStore;

    @Autowired
    private MemberDetailsService memberDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;



    /**
     * client_id = client_yh
     * client_secret = 123456
     * @param clients
     * @throws Exception
     */
    @Override
    // 用来配置客户端详情服务
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.inMemory()
//                // appid
//                .withClient("client_yh")
//                // appsecret
//                .secret(passwordEncoder.encode("123456"))
//                // 授权码
//                .authorizedGrantTypes("authorization_code")
//                // 作用域
//                .scopes("test")
//                // 资源的id
//                .resourceIds("resource_yh")
//                // 回调地址
//                .redirectUris("http://localhost:8080/hello");

        //将基于内存存储client信息改为jdbc存储
        clients
                .jdbc(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    // 用来配置令牌（token）的访问端点和令牌服务
    // 本次demo分别配置了授权码模式及密码模式对应的服务配置
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // token的存储位置
                .tokenStore(jdbcTokenStore)
                // 密码模式必须配置authenticationManager,否则会不支持密码模式
                .authenticationManager(authenticationManager)
                 // 刷新token时必须设置userDetailsService,否则刷新时会报错
                .userDetailsService(memberDetailsService)
                // 配置token转换器，将token转换为jwt
                .accessTokenConverter(jwtAccessTokenConverter);
    }

    /**
     * 允许所有人请求令牌
     * 已验证的可客户端才能请求check_token端点
     * @param security
     * @throws Exception
     */
    @Override
    // 用来配置令牌端点的安全约束
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security
                .passwordEncoder(passwordEncoder)
                // 授权端点开放
                // 开启/oauth/token_key验证端口权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("permitAll()")
                // 配置后调用/oauth/token获取token时，可使用表单参数配置客户端id和密码
                // client_id = client_yh
                // client_secret = 123456
                .allowFormAuthenticationForClients();
    }

}
