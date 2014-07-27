package com.cspinformatique.redis.message.client.service;

import com.cspinformatique.redis.core.entity.Message;

public interface MessageService{
	public Message consumeMessageFromQueue();
}
