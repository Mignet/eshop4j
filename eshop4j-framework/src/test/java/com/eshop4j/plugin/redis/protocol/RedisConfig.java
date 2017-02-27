package com.eshop4j.plugin.redis.protocol;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("redisConfig")
public class RedisConfig {
	
	@Value("${redis.host}")
	private String host = "127.0.0.1";
	
	@Value("${redis.port}")
	private int port = 6379;
	
	@Value("${redis.password}")
	private String password = null;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
