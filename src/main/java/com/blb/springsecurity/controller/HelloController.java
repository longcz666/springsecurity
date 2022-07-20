package com.blb.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: longcz
 * @Date: 2022/7/19 - 07 - 19 - 19:12
 * @Description: com.blb.springsecurity.controller
 * @Version: 1.0
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
       return "Hello";
}
}
