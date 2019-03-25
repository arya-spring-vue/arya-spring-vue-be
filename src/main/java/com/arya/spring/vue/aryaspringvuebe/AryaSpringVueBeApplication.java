package com.arya.spring.vue.aryaspringvuebe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class AryaSpringVueBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AryaSpringVueBeApplication.class, args);
	}
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 映射项目路由，允许/spring/vue下的所有接口跨域。注意**才是代表所有路由，否则*仅仅是类似/spring/vue/update一级路由，不支持/spring/vue/update?id=1。
                registry.addMapping("/spring/vue/**")
                        // 指定
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                        // 设置Access-Control-Allow-Origin:http://localhost:3000，指定可访问源。注意PUT和DELETE引起的OPTIONS预检请求需要指定源，不可设置为*。
                        .allowedOrigins("http://localhost:3000")
                        // 设置Access-Control-Allow-Headers: content-type。
                        .allowedHeaders("Content-Type")
                        // 设置Access-Control-Max-Age: 3600。注意这里是Long类型的
                        .maxAge(3600L)
                        // 设置响应Access-Control-Allow-Credentials: true。
                        .allowCredentials(true);
            }
        };
    }
}


