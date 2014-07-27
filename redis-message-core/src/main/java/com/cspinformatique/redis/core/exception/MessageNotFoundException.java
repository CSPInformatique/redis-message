package com.cspinformatique.redis.core.exception;

public class MessageNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -5403757382697773520L;
	
	private long messageId;
	
	public MessageNotFoundException(long messageId){
		this.messageId = messageId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
}
