package com.cspinformatique.redis.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cspinformatique.redis.core.entity.Consumer;
import com.cspinformatique.redis.core.entity.Message;
import com.cspinformatique.redis.core.repository.MessageRepository;
import com.cspinformatique.redis.core.service.ConsumerService;
import com.cspinformatique.redis.server.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	@Autowired private ConsumerService consumerService;
	@Autowired private MessageRepository messageRepository;
	
	@Transactional
	public void publishMessage(String message) {
		Message messageDto = new Message(messageRepository.generateMessageId(), message);
		
		// Insert the message.
		messageRepository.publishMessage(messageDto);

		// Insert the message id in a queue for each consumer.
		for(Consumer consumer : this.consumerService.getConsumers()){
			this.messageRepository.addMessageToQueue(messageDto.getId(), consumer.getId());
		}
	}
}
