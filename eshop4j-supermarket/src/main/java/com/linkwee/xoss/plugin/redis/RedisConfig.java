package com.linkwee.xoss.plugin.redis;

public class RedisConfig {
	
	private String host = "127.0.0.1";
	
	private int port = 6379;
	
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
