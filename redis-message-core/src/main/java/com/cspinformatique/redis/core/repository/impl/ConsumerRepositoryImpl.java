package com.cspinformatique.redis.core.repository.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import com.cspinformatique.redis.core.entity.Consumer;
import com.cspinformatique.redis.core.repository.ConsumerRepository;

@Repository
public class ConsumerRepositoryImpl implements ConsumerRepository {
	@Autowired private RedisAtomicLong consumerIdCounter;
	@Autowired private RedisTemplate<String, Consumer> consumerRedisTemplate;
	
	private BoundHashOperations<String, Long, Consumer> listOperations;
	
	@PostConstruct
	public void init(){
		this.listOperations = consumerRedisTemplate.boundHashOps("consumer");
	}
	
	@Override
	public void addConsumer(Consumer consumer){
		this.listOperations.put(consumer.getId(), consumer);
	}
	
	@Override
	public void deleteConsumer(long consumerId){
		this.listOperations.delete(consumerId);
	}
	
	@Override
	public long generateConsumerId(){
		return this.consumerIdCounter.incrementAndGet();
	}
	
	@Override
	public Consumer getConsumer(long consumerId){
		return this.listOperations.get(consumerId);
	}
	
	@Override
	public List<Consumer> getConsumers(){
		return listOperations.values();
	}

}
