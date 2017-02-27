package com.linkwee.xoss.util;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.linkwee.core.util.ConfigFileUtil;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterFactoryHelper implements FactoryBean<JedisCluster>, InitializingBean {
	
	Logger logger = LoggerFactory.getLogger(JedisClusterFactoryHelper.class);

	private Resource addressConfig;
	private String addressKeyPrefix ;

	private JedisCluster jedisCluster;
	private Integer timeout;
	private Integer maxRedirections;
	private GenericObjectPoolConfig genericObjectPoolConfig;
	
	private Pattern p = Pattern.compile("^.+[:]\\d{1,5}\\s*$");

	@Override
	public JedisCluster getObject() throws Exception {
		return jedisCluster;
	}

	@Override
	public Class<? extends JedisCluster> getObjectType() {
		return (this.jedisCluster != null ? this.jedisCluster.getClass() : JedisCluster.class);
	}

	@Override
	public boolean isSingleton() {
		return true;
	}



	private Set<HostAndPort> parseHostAndPort() throws Exception {
		try {
			Properties prop = new Properties();
			prop.load(this.addressConfig.getInputStream());

			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (Object key : prop.keySet()) {

				if (!((String) key).startsWith(addressKeyPrefix)) {
					continue;
				}

				String val = (String) prop.get(key);

				logger.info("redis集群ip地址：{}", val);
				
				boolean isIpPort = p.matcher(val).matches();

				if (!isIpPort) {
					throw new IllegalArgumentException("redis ip 或 port 不合法");
				}
				String[] ipAndPort = val.split(":");

				HostAndPort hap = new HostAndPort(ipAndPort[0].trim(), Integer.parseInt(ipAndPort[1].trim()));
				haps.add(hap);
			}

			return haps;
		} catch (IllegalArgumentException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new Exception("解析 jedis 配置文件失败", ex);
		}
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Set<HostAndPort> haps = this.parseHostAndPort();
		
		jedisCluster = new JedisCluster(haps, timeout, maxRedirections,genericObjectPoolConfig);
		
	}
	public void setAddressConfig(Resource addressConfig) {
		this.addressConfig = getResource(addressConfig);
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setMaxRedirections(int maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	public void setAddressKeyPrefix(String addressKeyPrefix) {
		this.addressKeyPrefix = addressKeyPrefix;
	}

	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

	private Resource getResource(Resource location){
		String path = ConfigFileUtil.findFile(location.getFilename());
		if(StringUtils.isNotBlank(path)){
			Resource resource = new FileSystemResource(path);
			if (resource.exists()) {
				location = null;
				logger.info(String.format("########################Loading resource file: %s",path));
				return resource;
			}
		}
		logger.info(String.format("########################Loading resource file: %s",location));
		return location;
	}
}