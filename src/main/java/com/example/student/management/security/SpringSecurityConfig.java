package com.example.student.management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/student/getAllStudents").hasRole(ADMIN)
                .antMatchers("/api/student/countAllStudents").hasRole(ADMIN)
                .antMatchers("/api/student/countStudentsFromDeparted").hasRole(ADMIN)
                .antMatchers("/api/student/insertStudent").hasRole(ADMIN)
                .antMatchers("/api/student/updateStudent").hasRole(ADMIN)
                .antMatchers("/api/student/deleteStudent").hasRole(ADMIN)
                .antMatchers("/api/student/getStudentById").hasAnyRole()
                .antMatchers("/api/student/getStudentByName").hasAnyRole()
                .and()
                .formLogin();
    }
}
