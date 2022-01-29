package com.example.springsecurityoauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 用于存储token令牌的对象
 */
@Configuration
public class JdbcTokenStoreConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTokenStore jdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}
