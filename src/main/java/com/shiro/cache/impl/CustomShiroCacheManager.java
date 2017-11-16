package com.shiro.cache.impl;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.util.Destroyable;

import com.shiro.cache.redis.ShiroCacheManager;

/**
 * 用户自定义管理shiro的cache
 */
public class CustomShiroCacheManager implements CacheManager, Destroyable {

	private ShiroCacheManager shiroCacheManager;

	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		return getShiroCacheManager().getCache(name);
	}

	@Override
	public void destroy() throws Exception {
		shiroCacheManager.destroy();
	}

	public ShiroCacheManager getShiroCacheManager() {
		return shiroCacheManager;
	}

	public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
		this.shiroCacheManager = shiroCacheManager;
	}

}
