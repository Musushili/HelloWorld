package com.example.ems_3.config;

import com.example.ems_3.LoginInterceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 注册处理器拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 创建拦截器对象
//        HandlerInterceptor interceptor = new LoginInterceptor();

        //白名单
        List<String> patterns = new ArrayList<String>();
        patterns.add("/user/login");
        patterns.add("/user/register");
        patterns.add("/product/findAllPro");
        patterns.add("/product/findAllProByType");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);

//        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
    }

    /*跨域问题*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
//                .allowedOrigins("*")
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }

}
