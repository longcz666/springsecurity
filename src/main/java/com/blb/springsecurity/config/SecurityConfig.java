package com.blb.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: longcz
 * @Date: 2022/7/19 - 07 - 19 - 19:53
 * @Description: com.blb.springsecurity.config
 * @Version: 1.0
 */
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //在内存中创建用户
        auth.inMemoryAuthentication()
                //账号
                .withUser("admin")
                //密码，需要加密
                .password(new BCryptPasswordEncoder().encode("123"))
                //添加角色
                .roles("ADMIN","USER")
                //创建另一个用户
                .and()
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("123"))
                .roles("USER");

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //指定登录相关的请求，permitAll是不需要验证
                .antMatchers("/login").permitAll()
                //指定/user/** 需要USER角色
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                //其它所有URL都需要验证
                .anyRequest().authenticated()
                .and()
                //配置登录URL为login，登录成功后跳转main
                .formLogin().loginPage("/login").defaultSuccessUrl("/main")
                .and()
                //配置注销url，注销后到登录页面
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
