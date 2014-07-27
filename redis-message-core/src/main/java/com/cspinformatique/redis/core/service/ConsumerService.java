package com.cspinformatique.redis.core.service;

import java.util.List;

import com.cspinformatique.redis.core.entity.Consumer;

public interface ConsumerService {
	public void addNewConsumer(String host, int port);
	
	public void deleteConsumer(long consumerId);
	
	public Consumer getConsumer(long consumerId);
	
	public Consumer getConsumer(String hostname, int port);
	
	public List<Consumer> getConsumers();
}
