package com.eshop4j.test.redis;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import com.eshop4j.test.TestSupport;

public class RedisClusterTest extends TestSupport{
	private static JedisCluster jc;  
	  static {  
	       //只给集群里一个实例就可以  
	        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();  
	        jedisClusterNodes.add(new HostAndPort("10.16.0.205", 7003));  
	        jedisClusterNodes.add(new HostAndPort("10.16.0.205", 7004));  
	        jedisClusterNodes.add(new HostAndPort("10.16.0.205", 7005));  
	        jc = new JedisCluster(jedisClusterNodes);  
	    }  
	  public SortedSet<String> keys(String pattern){  
		  System.out.println("Start getting keys...");  
		  SortedSet<String> keys = Collections.synchronizedSortedSet(new TreeSet<String>());
		  Map<String, JedisPool> clusterNodes = jc.getClusterNodes();  
	        for (Map.Entry<String,JedisPool> entry : clusterNodes.entrySet()) {
	        	String key = entry.getKey();
	        	System.out.printf("Getting keys from: {}\n", key);  
	        	JedisPool jp = entry.getValue();  
	        	Jedis connection = jp.getResource();  
	            try {  
	                keys.addAll(connection.keys(pattern));  
	            } catch(Exception e){  
	            	System.err.printf("Getting keys error: {}\n", e);  
	            } finally{  
	            	System.out.println("Connection closed.");  
	                connection.close();//用完一定要close链接 
	            }  
	        }
	        System.out.println("Keys gotten!");  
	        return keys;  
	    }  
	  
	 public static void main(String[] args) {
//		 jc.setex("test:1", 3, JSON.toJSONString(new Object()));
//		 System.out.println(JSON.parseObject(jc.get("test:1")));
//		 jc.setex("a", 10, "1");
//		 jc.set("a", "2");
//		 Map<String, String> hash =  new HashMap<String, String>();
//		 hash.put("a", "1");
//		 hash.put("b", "2");
//		 jc.hmset("c", hash);
//		 System.out.println(jc.hgetAll("c"));
		System.out.println(jc.exists("c"));
		 
	}  
}
