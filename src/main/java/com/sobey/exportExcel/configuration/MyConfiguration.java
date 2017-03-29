package com.sobey.exportExcel.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lijunhong on 17/3/28.
 * 这里的配置支持全局跨域但是没有启用
 */
@Configuration
public class MyConfiguration extends WebMvcConfigurerAdapter{

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowCredentials(false)
//                .allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "TRACE");
//    }
}
