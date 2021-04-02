package com.hanghae99.books.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean // 패스워드 인코딩
//    public BCryptPasswordEncoder encodePassword(){
//        return new BCryptPasswordEncoder();
//    }

    //    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception{
//        return super.authenticationManagerBean();
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.authorizeRequests()

                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()

                // 그 외 모든 요청은 인증과정 필요
                .anyRequest().authenticated() // 어떤 요청이 오든지 로그인을 하지않앗으면 로그인을 시키겟다
                .and()
                .formLogin()
                //.loginPage("/user/login")
                //.failureUrl("/user/login/error")
//               .loginProcessingUrl("/api/index") // Controller에 따로 만들지 않아도 login을 진행해준다. post인듯  get페이지는 controller에 작성되어있음
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout") // Controller에 따로 만들지 않아도 로그아웃을 진행해준다. index html에서 로그아웃 버튼을 누를때 a태그 href로 /user/logout을 부르는걸 보니까 get방식인것 같다.
                .logoutSuccessUrl("/user/login")
                .permitAll()
                .and()
                .exceptionHandling();
//                .accessDeniedPage("/user/forbidden"); // 인가가 되지 않았을 경우 forbidden 페이지로 이동
    }


}
