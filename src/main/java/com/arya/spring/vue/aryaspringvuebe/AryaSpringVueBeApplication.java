package com.arya.spring.vue.aryaspringvuebe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
                registry.addMapping("/**")
                        // 设置Access-Control-Allow-Method: "GET", "POST", "PUT", "DELETE"
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
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
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName("localhost");
        jedisConFactory.setPort(6379);
        return jedisConFactory;
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
    }
    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }
}


