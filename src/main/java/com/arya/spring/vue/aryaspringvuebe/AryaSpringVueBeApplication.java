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
                registry.addMapping("/spring/vue/*") //映射项目路由，允许/spring/vue下的所有接口跨域
                        .allowedOrigins("*") //允许所有源访问，后期可以添加指定源访问提升安全性
                        .allowCredentials(true); // 设置响应Access-Control-Allow-Credentials: true
            }
        };
    }
}


