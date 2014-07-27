package com.cspinformatique.redis.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.cspinformatique.redis"})
public class RedisServer {
	public static void main(String[] args) {
		SpringApplication.run(RedisServer.class);
	}
}
