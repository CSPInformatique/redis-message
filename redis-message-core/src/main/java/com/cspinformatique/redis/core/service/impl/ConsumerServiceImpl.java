package com.cspinformatique.redis.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.redis.core.entity.Consumer;
import com.cspinformatique.redis.core.repository.ConsumerRepository;
import com.cspinformatique.redis.core.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	@Autowired private ConsumerRepository consumerRepository;
	
	@Override
	public void addNewConsumer(String host, int port) {
		this.consumerRepository.addConsumer(new Consumer(consumerRepository.generateConsumerId(), host, port));
	}

	@Override
	public void deleteConsumer(long consumerId) {
		this.consumerRepository.deleteConsumer(consumerId);
	}

	@Override
	public Consumer getConsumer(long consumerId) {
		return this.consumerRepository.getConsumer(consumerId);
	}
	
	@Override
	public Consumer getConsumer(String hostname, int port) {
		for(Consumer consumer : consumerRepository.getConsumers()){
			if(consumer.getHostname().equals(hostname) && consumer.getPort() == port){
				return consumer;				
			}
		}
		
		// Consumer not found. Generating a new one.
		Consumer consumer = new Consumer(this.consumerRepository.generateConsumerId(), hostname, port);
		
		consumerRepository.addConsumer(consumer);
		
		return consumer;	
	}

	@Override
	public List<Consumer> getConsumers() {
		return this.consumerRepository.getConsumers();
	}

}
