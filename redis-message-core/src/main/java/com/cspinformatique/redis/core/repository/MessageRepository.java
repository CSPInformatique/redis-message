package com.cspinformatique.redis.core.repository;

import com.cspinformatique.redis.core.entity.Message;

public interface MessageRepository {
	public void addMessageToQueue(long messageId, long consumerId);
	
	public Long consumeMessageFromQueue(long consumerId);
	
	public Long generateMessageId();
	
	public Message getMessage(long messageId);
	
	public void publishMessage(Message message);
	
}
