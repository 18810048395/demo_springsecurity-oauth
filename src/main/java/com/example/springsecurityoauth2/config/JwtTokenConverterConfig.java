package com.example.springsecurityoauth2.config;

import com.example.springsecurityoauth2.pojo.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class JwtTokenConverterConfig {



    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter(){
            @Override
            // 重写enhance方法 可以将用户信息补充到jwt生成的token中
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                final Map<String, Object> additionalInformation = new HashMap<>();
                UserEntity userEntity = (UserEntity) authentication.getUserAuthentication().getPrincipal();
                //把用户的主键uin放进去
                additionalInformation.put("userId", userEntity.getId());
                additionalInformation.put("userName", userEntity.getUsername());
                additionalInformation.put("realName", userEntity.getRealname());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };
        jwtAccessTokenConverter.setSigningKey("yh-secret");
        return jwtAccessTokenConverter;
    }

}
