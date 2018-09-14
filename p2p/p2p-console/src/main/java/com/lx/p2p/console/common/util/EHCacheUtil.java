package com.lx.p2p.console.common.util;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * ehcache缓存工具类
 * 
 * @version 2017年5月24日下午7:53:11
 * @author zhuwenbin
 */
public class EHCacheUtil {

	/**
	 * 缓存管理器
	 */
	private static CacheManager cacheManager;

	private EHCacheUtil() {
		// 私有化构造方法
	}

	// 静态代码块,保证singleton。
	static {
		cacheManager = CacheManager.create();
	}

	/**
	 * 根据缓存名字获得某个缓存
	 * 
	 * @param cacheName
	 * @return
	 */
	public static Cache getCache(String cacheName) {
		return cacheManager.getCache(cacheName);
	}

	/**
	 * 根据缓存名字,元素的key值,获得缓存中对应的value值对象。
	 * 
	 * @param cacheName
	 * @param key
	 * @param isRemoveKey
	 * @return
	 */
	public static Object getValue(String cacheName, Object key, boolean isRemoveKey) {
		Cache cache = getCache(cacheName);
		Element e = null;
		if (isRemoveKey) {
			e = cache.get(key);
		} else {
			e = cache.getQuiet(key);
		}
		if (e == null) {
			return null;
		}
		return e.getObjectValue();
	}

	/**
	 * 根据缓存名字,元素的key值,获得缓存中对应的value值对象。
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Object getValue(String cacheName, Object key) {
		return getValue(cacheName, key, false);
	}

	/**
	 * 静态的获取元素，不会产生update.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Element getElementByQuite(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		return cache.getQuiet(key);
	}

	/**
	 * 动态的获取元素，会产生update.
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static Element getElementByDynic(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		return cache.get(key);
	}

	/**
	 * 向某个缓存中添加元素
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public static void put(String cacheName, Object key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	/**
	 * 移除某个缓存中的元素
	 * 
	 * @param cacheName
	 * @param key
	 */
	public static void remove(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		if (cache != null) {
			cache.remove(key);
		}
	}

	/**
	 * 判断某个缓存是否包含某个元素
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public static boolean contains(String cacheName, Object key) {
		Cache cache = getCache(cacheName);
		Element e = cache.get(key);
		if (e != null) {
			return true;
		}
		return false;
	}

	/**
	 * 获取某个缓存中所有的key
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getKeys(String cacheName, Class<T> t) {
		Cache cache = getCache(cacheName);
		return (List<T>) cache.getKeys();
	}

	public static void main(String[] args) {
		String key = "hello";
		String value = "world";
		EHCacheUtil.put("articleCache", key, value);
		System.out.println(EHCacheUtil.getValue("articleCache", key));
	}
}
