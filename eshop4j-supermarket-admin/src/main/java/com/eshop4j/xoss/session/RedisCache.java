package com.eshop4j.xoss.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eshop4j.core.util.SerializeUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class RedisCache<K,V> implements Cache<K,V> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
     * The wrapped Jedis instance.
     */
	private JedisCluster cache;
	
	/**
	 * The Redis key prefix for the sessions 
	 */
	private String keyPrefix = "shiro_redis_session:";
	
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
	
	/**
	 * 通过一个JedisManager实例构造RedisCache
	 */
	public RedisCache(JedisCluster cache){
		 if (cache == null) {
	         throw new IllegalArgumentException("Cache argument cannot be null.");
	     }
	     this.cache = cache;
	}
	
	/**
	 * Constructs a cache instance with the specified
	 * Redis manager and using a custom key prefix.
	 * @param cache The cache manager instance
	 * @param prefix The Redis key prefix
	 */
	public RedisCache(JedisCluster cache, 
				String prefix){
		this( cache );
		// set the prefix
		this.keyPrefix = prefix;
	}
	
	/**
	 * 获得带前缀的key
	 * @param key
	 * @return
	 */
	private String getByteKey(K key){
			return  this.keyPrefix + key;
	}
 	
	@Override
	public V get(K key) throws CacheException {
		logger.debug("根据key从Redis中获取对象 key [" + key + "]");
		try {
			if (key == null) {
	            return null;
	        }else{
	        	return (V)SerializeUtils.deserialize(Base64.decodeBase64(cache.get(getByteKey(key))));
	        }
		} catch (Exception t) {
			throw new CacheException(t);
		}

	}

	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("根据key从存储 key [" + key + "]");
		 try {
			 	cache.set(getByteKey(key), Base64.encodeBase64String(SerializeUtils.serialize(value)));
	            return value;
	        } catch (Exception t) {
	            throw new CacheException(t);
	        }
	}

	@Override
	public V remove(K key) throws CacheException {
		logger.debug("从redis中删除 key [" + key + "]");
		try {
            V previous = get(key);
            cache.del(getByteKey(key));
            return previous;
        } catch (Exception t) {
            throw new CacheException(t);
        }
	}

	@Override
	public void clear() throws CacheException {
		logger.debug("从redis中删除所有元素");
		try {
            cache.flushDB();
        } catch (Exception t) {
            throw new CacheException(t);
        }
	}

	@Override
	public int size() {
		try {
			Long longSize = Long.valueOf(cache.dbSize());
            return longSize.intValue();
        } catch (Exception t) {
            throw new CacheException(t);
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		try {
            Set<String> keys = keys(this.keyPrefix + "*");
            if (CollectionUtils.isEmpty(keys)) {
            	return Collections.emptySet();
            }else{
            	Set<K> newKeys = new HashSet<K>();
            	for(String key:keys){
            		newKeys.add((K)key);
            	}
            	return newKeys;
            }
        } catch (Exception t) {
            throw new CacheException(t);
        }
	}
	
	public TreeSet<String> keys(String pattern){  
        logger.debug("Start getting keys...");  
        TreeSet<String> keys = new TreeSet<String>();  
        Map<String, JedisPool> clusterNodes = cache.getClusterNodes();  
        for(String k : clusterNodes.keySet()){  
            logger.debug("Getting keys from: {}", k);  
            JedisPool jp = clusterNodes.get(k);  
            Jedis connection = jp.getResource();  
            try {  
                keys.addAll(connection.keys(pattern));  
            } catch(Exception e){  
                logger.error("Getting keys error: {}", e);  
            } finally{  
                logger.debug("Connection closed.");  
                connection.close();//用完一定要close这个链接！！！  
            }  
        }  
        logger.debug("Keys gotten!");  
        return keys;  
    }  

	@Override
	public Collection<V> values() {
		try {
            Set<String> keys = keys(this.keyPrefix + "*");
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (String key : keys) {
					V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Exception t) {
            throw new CacheException(t);
        }
	}
}
