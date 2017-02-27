package com.eshop4j.xoss.session;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eshop4j.core.util.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
/**
 * 重写Session的存储，将其存入Redis以便分布式系统公用
 * @author Mignet
 * @since 2015-03-20 11:12
 */
public class RedisSessionDAO extends AbstractSessionDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSessionDAO.class);
	
	private JedisCluster jedisCluster;
	
	/**
	 * shiro-redis的session对象前缀
	 */
	private String keyPrefix = "shiro_redis_session:";
	
	@Override
	public void update(Session session){
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session){
		if(session == null || session.getId() == null){
			LOGGER.error("session or session id is null");
			return;
		}
		String key = getByteKey(session.getId());
		String value = Base64.encodeBase64String(SerializeUtils.serialize(session));
		session.setTimeout(3600*1000L);	
		this.jedisCluster.set(key,value);
		this.jedisCluster.expire(key, 3600);
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			LOGGER.error("session or session id is null");
			return;
		}
		jedisCluster.del(this.getByteKey(session.getId()));

	}
	/**
	 * 在集群环境下，keys命令是不支持的。
	 * @param pattern
	 * @return
	 */
	public SortedSet<String> keys(String pattern){  
        LOGGER.debug("Start getting keys...");
        SortedSet<String> keys = Collections.synchronizedSortedSet(new TreeSet<String>()); 
        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();  
        for (Map.Entry<String,JedisPool> entry : clusterNodes.entrySet()) {
        	String key = entry.getKey();
        	LOGGER.debug("Getting keys from: {}", key);  
        	JedisPool jp = entry.getValue();  
        	Jedis connection = jp.getResource();  
            try {  
                keys.addAll(connection.keys(pattern));  
            } catch(Exception e){  
                LOGGER.error("Getting keys error: {}", e);  
            } finally{  
                LOGGER.debug("Connection closed.");  
                connection.close();//用完一定要close链接 
            }  
        }
        LOGGER.debug("Keys gotten!");  
        return keys;  
    }  

	//用来统计当前活动的session
	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = new HashSet<Session>();
		
		Set<String> keys = keys(this.keyPrefix + "*");
		if(keys != null && !keys.isEmpty()){
			for(String key:keys){
				Session s = (Session)SerializeUtils.deserialize(Base64.decodeBase64(jedisCluster.get(key)));
				sessions.add(s);
			}
		}
		
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			LOGGER.error("session id is null");
			return null;
		}
		String seid = jedisCluster.get(this.getByteKey(sessionId));
		if(seid==null){
			return null;
		}
		return (Session)SerializeUtils.deserialize(Base64.decodeBase64(seid));
	}
	
	/**
	 * 获得加前缀的key
	 * @param key
	 * @return
	 */
	private String getByteKey(Serializable sessionId){
		return this.keyPrefix + sessionId;
	}

	public JedisCluster getJedisCluster() {
		return jedisCluster;
	}

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	/**
	 * Returns the Redis session keys
	 * prefix.
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key 
	 * prefix.
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
}