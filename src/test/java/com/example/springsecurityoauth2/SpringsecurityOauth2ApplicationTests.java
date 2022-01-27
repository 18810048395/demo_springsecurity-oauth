package com.example.springsecurityoauth2;

import com.example.springsecurityoauth2.dao.PermissionMapper;
import com.example.springsecurityoauth2.dao.UserMapper;
import com.example.springsecurityoauth2.pojo.PermissionEntity;
import com.example.springsecurityoauth2.pojo.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringsecurityOauth2ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;


    @Test
    void findPermissionByUsername() {
        UserEntity userEntity = userMapper.findByUsername("yanghe");
        System.out.println(userEntity);

    }

    @Test
    void findAllPermission() {
        List<PermissionEntity> permissionEntityList = permissionMapper.findAllPermission();
        permissionEntityList.forEach(permissionEntity -> {
            System.out.println(permissionEntity);
        });
    }

    @Test
    void contextLoads() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("123456");
        System.out.println(pass);
    }

}
