package com.example.ems_3.config;

import com.example.ems_3.filter.MyTestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class FilterConfig {

    @Resource
    private MyTestFilter myTestFilter;

    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean(myTestFilter);
        registration.addUrlPatterns("/filter/*");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }
}