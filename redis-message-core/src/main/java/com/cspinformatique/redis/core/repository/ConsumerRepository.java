package com.cspinformatique.redis.core.repository;

import java.util.List;

import com.cspinformatique.redis.core.entity.Consumer;

public interface ConsumerRepository {
	public void addConsumer(Consumer consumer);
	
	public void deleteConsumer(long consumerId);
	
	public long generateConsumerId();
	
	public Consumer getConsumer(long consumerId);
	
	public List<Consumer> getConsumers();
}
