package com.cspinformatique.redis.core.repository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import com.cspinformatique.redis.core.entity.Consumer;
import com.cspinformatique.redis.core.entity.Message;

@Configuration
@PropertySource("classpath:persistence/redis.properties")
public class RedisConfig {
	@Autowired
	private Environment environment;

	public @Bean
	JedisConnectionFactory jedisConnectionFactory() {
		String hostname = environment.getProperty("redis.hostname");
		Integer port = environment.getProperty("redis.port", Integer.class);
		String password = environment.getProperty("redis.password");

		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

		if (hostname != null)
			jedisConnectionFactory.setHostName(hostname);
		if (port != null)
			jedisConnectionFactory.setPort(port);
		if (password != null)
			jedisConnectionFactory.setPassword(password);
		
		jedisConnectionFactory.setUsePool(true);

		return jedisConnectionFactory;
	}

	public @Bean
	RedisAtomicLong consumerIdCounter() {
		return new RedisAtomicLong("consumerId", jedisConnectionFactory());
	}

	public @Bean
	RedisAtomicLong messageIdCounter() {
		return new RedisAtomicLong("messageId", jedisConnectionFactory());
	}

	public @Bean
	RedisTemplate<String, Consumer> consumerRedisTemplate() {
		RedisTemplate<String, Consumer> consumerRedisTemplate = new RedisTemplate<String, Consumer>();

		consumerRedisTemplate.setConnectionFactory(jedisConnectionFactory());

		consumerRedisTemplate.setKeySerializer(new StringRedisSerializer());
		consumerRedisTemplate
				.setDefaultSerializer(new Jackson2JsonRedisSerializer<Consumer>(
						Consumer.class));

		return consumerRedisTemplate;
	}

	public @Bean
	RedisTemplate<String, Message> messageRedisTemplate() {
		RedisTemplate<String, Message> messageRedisTemplate = new RedisTemplate<String, Message>();

		messageRedisTemplate.setConnectionFactory(jedisConnectionFactory());

		messageRedisTemplate.setKeySerializer(new StringRedisSerializer());
		messageRedisTemplate
				.setDefaultSerializer(new Jackson2JsonRedisSerializer<Message>(
						Message.class));

		return messageRedisTemplate;
	}

	public @Bean
	RedisTemplate<String, Long> messageQueueRedisTemplate() {
		RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();

		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<Long>(Long.class));

		return redisTemplate;
	}
}
