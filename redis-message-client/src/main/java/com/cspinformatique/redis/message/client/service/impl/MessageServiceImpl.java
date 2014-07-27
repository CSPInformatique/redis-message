package com.cspinformatique.redis.message.client.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cspinformatique.redis.core.entity.Consumer;
import com.cspinformatique.redis.core.entity.Message;
import com.cspinformatique.redis.core.repository.MessageRepository;
import com.cspinformatique.redis.core.service.ConsumerService;

import com.cspinformatique.redis.message.client.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired private ConsumerService consumerService;
	@Autowired private MessageRepository messageRepository;
	
	private Consumer consumer;
	
	@PostConstruct
	public void init(){
		try {
			this.consumer = consumerService.getConsumer(InetAddress.getLocalHost().getHostName(), 0);			
		} catch (UnknownHostException unknownHostEx) {
			throw new RuntimeException(unknownHostEx);
		}
	}
	
	public Message consumeMessageFromQueue(){
		Long messageId = this.messageRepository.consumeMessageFromQueue(consumer.getId());
		
		if(messageId != null){
			return messageRepository.getMessage(messageId);
		}

		return null;
	}
}
