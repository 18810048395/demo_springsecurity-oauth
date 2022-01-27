package com.example.springsecurityoauth2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    /**
     * 增加用户
     *
     * @return
     */
    @RequestMapping("/addMember")
    public String addMember() {
        return "addMember";
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping("/delMember")
    public String delMember() {
        return "delMember";
    }

    /**
     * updateMember
     *
     * @return
     */
    @RequestMapping("/updateMember")
    public String updateMember() {
        return "updateMember";
    }

    /**
     * showMember
     *
     * @return
     */
    @RequestMapping("/showMember")
    public String showMember() {
        return "showMember";
    }
}
