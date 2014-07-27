package com.cspinformatique.redis.core.entity;

public class Consumer {
	private long id;
	private String hostname;
	private int port;
	
	public Consumer(){
		
	}
	
	public Consumer(long id, String hostname, int port) {
		this.id = id;
		this.hostname = hostname;
		this.port = port;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
}
