/*
 * Copyright (C) 2015 ShenZhen QiHangChuangShi Co.,Ltd All Rights Reserved.
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 * 版权所有深圳起航创势科技有限公司 www.qihcs.com.
 */
package com.lx.p2p.console.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisCluster;

/**
 * redis集群工具类
 * 
 * @version 2017年7月29日上午9:29:46
 * @author zhuwenbin
 */
public class JedisClusterUtils {

	private static Logger logger = LoggerFactory.getLogger(JedisClusterUtils.class);

	/**
	 * 
	 * 设置对象缓存
	 * 
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param key
	 * @param object
	 * @param seconds
	 */
	public static void setObject(JedisCluster jedisCluster, String key, Object object, int seconds) {
		byte[] bytesKey = key.getBytes();
		jedisCluster.del(bytesKey);
		if (object == null) {
			return;
		}
		jedisCluster.set(bytesKey, ObjectUtils.serialize(object));
		jedisCluster.expire(bytesKey, seconds);
	}

	/**
	 * 
	 * 获取对象缓存
	 * 
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param key
	 * @return
	 */
	public static Object getObject(JedisCluster jedisCluster, String key) {
		byte[] bytesKey = key.getBytes();
		Object object = null;
		byte[] bytes = jedisCluster.get(bytesKey);
		object = toObject(bytes);
		return object;
	}

	/**
	 * 获取byte[]类型Key
	 * 
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param object
	 */
	public static byte[] getBytesKey(Object object) {
		if (object instanceof String) {
			return StringUtils.getBytes((String) object);
		} else {
			return ObjectUtils.serialize(object);
		}
	}

	/**
	 * Object转换byte[]类型
	 * 
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param object
	 * @return
	 */
	public static byte[] toBytes(Object object) {
		return ObjectUtils.serialize(object);
	}

	/**
	 * byte[]型转换Object
	 * 
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param bytes
	 * @return
	 */
	public static Object toObject(byte[] bytes) {
		return ObjectUtils.unserialize(bytes);
	}

	/**
	 * 设置list值
	 *
	 * @version 2016年12月12日下午2:12:31
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param key
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	public static void setList(JedisCluster jedisCluster, String key, List list, int seconds) {
		try {
			byte[] bytesKey = key.getBytes();

			jedisCluster.del(bytesKey);
			if (list == null || list.isEmpty()) {
				return;
			}

			for (Object object : list) {
				jedisCluster.lpush(bytesKey, ObjectUtils.serialize(object));
			}
			jedisCluster.expire(bytesKey, seconds);
		} catch (Exception e) {
			logger.error(Exceptions.getStackTraceAsString(e));
		}
	}

	/**
	 * 获取list值
	 *
	 * @version 2016年12月12日下午2:13:41
	 * @author baocheng.ren
	 * @param jedisCluster
	 * @param key
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getList(JedisCluster jedisCluster, String key) {
		byte[] bytesKey = key.getBytes();
		List result = new ArrayList();
		List<byte[]> lrange = jedisCluster.lrange(bytesKey, 0, -1);
		for (byte[] bs : lrange) {
			result.add(ObjectUtils.unserialize(bs));
		}
		return result;
	}

	/**
	 * 
	 * 设置String类型数据
	 * 
	 * @version 2016年12月22日上午10:22:02
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public static void setString(JedisCluster jedisCluster, String key, String value, int seconds) {
		jedisCluster.set(key, value);
		jedisCluster.expire(key, seconds);
	}

	/**
	 * 
	 * 获取String类型的数据
	 * 
	 * @version 2016年12月22日上午10:22:52
	 * @author wenbin.zhu
	 * @param jedisCluster
	 * @param key
	 * @return
	 */
	public static String getString(JedisCluster jedisCluster, String key) {
		return jedisCluster.get(key);
	}
}
