package com.shiro.cache.redis;

import org.apache.shiro.cache.Cache;

/**
 * JRedis管理cache接口
 */
public interface ShiroCacheManager {

    <K, V> Cache<K, V> getCache(String name);

    void destroy();

}
