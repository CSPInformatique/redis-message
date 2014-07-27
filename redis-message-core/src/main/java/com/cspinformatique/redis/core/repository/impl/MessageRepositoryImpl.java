package com.cspinformatique.redis.core.repository.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Repository;

import com.cspinformatique.redis.core.entity.Message;
import com.cspinformatique.redis.core.repository.MessageRepository;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
	@Autowired
	private RedisAtomicLong messageIdCounter;
	
	@Autowired
	private RedisTemplate<String, Message> messageRedisTemplate;
	
	@Autowired
	private RedisTemplate<String, Long> messageQueueRedisTemplate;

	private BoundHashOperations<String, Long, Message> messageListOps;

	@PostConstruct
	public void init() {
		this.messageListOps = this.messageRedisTemplate.boundHashOps("message");
	}

	@Override
	public void addMessageToQueue(long messageId, long consumerId) {
		BoundListOperations<String, Long> queueListOperations = this.messageQueueRedisTemplate
				.boundListOps("consumer:" + consumerId + ":queue");

		queueListOperations.rightPush(messageId);

		queueListOperations.expire(7, TimeUnit.DAYS);
	}
	
	@Override
	public Long consumeMessageFromQueue(long consumerId){
		BoundListOperations<String, Long> queueListOperations = this.messageQueueRedisTemplate
				.boundListOps("consumer:" + consumerId + ":queue");
		
		return queueListOperations.leftPop();
	}

	@Override
	public Long generateMessageId() {
		return messageIdCounter.incrementAndGet();
	}
	
	@Override
	public Message getMessage(long messageId){
		return messageListOps.get(messageId);
	}

	@Override
	public void publishMessage(Message message) {
		this.messageListOps.put(message.getId(), message);

		this.messageListOps.expire(7, TimeUnit.DAYS);
	}
}
