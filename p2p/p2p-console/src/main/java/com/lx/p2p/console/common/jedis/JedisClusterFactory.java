package com.lx.p2p.console.common.jedis;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterFactory implements FactoryBean<JedisCluster>, InitializingBean {
	// 192.168.254.13:6379,192.168.254.13:6380,192.168.254.14:6379
	private String address;
	private JedisCluster jedisCluster;
	private Integer connectionTimeout = 2000;
	private Integer soTimeout = 3000;
	private Integer maxRedirections = 5;
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
			String[] addressArr = address.trim().split(",");
			Set<HostAndPort> haps = new HashSet<HostAndPort>();
			for (String addressStr : addressArr) {
				String[] ipAndPort = addressStr.trim().split(":");
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
		jedisCluster = new JedisCluster(haps, connectionTimeout, soTimeout, maxRedirections, genericObjectPoolConfig);
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param connectionTimeout
	 *            the connectionTimeout to set
	 */
	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * @param soTimeout
	 *            the soTimeout to set
	 */
	public void setSoTimeout(Integer soTimeout) {
		this.soTimeout = soTimeout;
	}

	/**
	 * @param maxRedirections
	 *            the maxRedirections to set
	 */
	public void setMaxRedirections(Integer maxRedirections) {
		this.maxRedirections = maxRedirections;
	}

	/**
	 * @param genericObjectPoolConfig
	 *            the genericObjectPoolConfig to set
	 */
	public void setGenericObjectPoolConfig(GenericObjectPoolConfig genericObjectPoolConfig) {
		this.genericObjectPoolConfig = genericObjectPoolConfig;
	}

}
