package com.ardijorganxhi.studentinformationsystem.config.security;

import com.ardijorganxhi.studentinformationsystem.config.PasswordEncoder;
import com.ardijorganxhi.studentinformationsystem.service.StudentService;
import com.ardijorganxhi.studentinformationsystem.service.TeacherService;
import com.ardijorganxhi.studentinformationsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final CustomRequestFilter customRequestFilter;

    public SecurityConfiguration(UserService userService, StudentService studentService, TeacherService teacherService, PasswordEncoder passwordEncoder, JwtAuthEntryPoint jwtAuthEntryPoint, CustomRequestFilter customRequestFilter) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthEntryPoint = jwtAuthEntryPoint;
        this.customRequestFilter = customRequestFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
        http.addFilterBefore(customRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(studentService).passwordEncoder(passwordEncoder.bCryptPasswordEncoder());
        auth.userDetailsService(teacherService).passwordEncoder(passwordEncoder.bCryptPasswordEncoder());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedHeaders("*").allowedOrigins("*");
            }
        };
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
