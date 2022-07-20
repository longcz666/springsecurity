package com.blb.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: longcz
 * @Date: 2022/7/19 - 07 - 19 - 19:50
 * @Description: com.blb.springsecurity.controller
 * @Version: 1.0
 */
@Controller
public class PageController {
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
