package com.qf.shop_back;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.service.IBackUserService;
import com.sun.org.apache.xpath.internal.operations.And;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * @author 王铭
 * @Date 2019/7/4
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Reference
    private IBackUserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/tologin")
                    .loginProcessingUrl("/login")
                    .failureUrl("/tologin?error=1")
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                    .mvcMatchers("/resources/**").permitAll()
                    .mvcMatchers("/").authenticated()
//                    .anyRequest().access("@perssionHandler.hasPerssion(request,authentication)")
                    .anyRequest().authenticated()
                .and()
                .csrf().disable()
                //处理iframe请求，让security放行
                .headers().frameOptions().sameOrigin()
                .and()
                .exceptionHandling().accessDeniedPage("/noperssion");
    }

    /**
     * 自定义身份认证的方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
