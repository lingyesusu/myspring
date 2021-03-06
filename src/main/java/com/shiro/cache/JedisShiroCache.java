package com.shiro.cache;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import com.shiro.cache.redis.impl.JedisManager;
import com.shiro.manager.JedisShiroContant;
import com.shiro.util.LoggerUtils;
import com.shiro.util.SerializeUtil;

/**
 * jedis对缓存的操作
 */
@SuppressWarnings("unchecked")
public class JedisShiroCache<K, V> implements Cache<K, V> {

    private JedisManager jedisManager;
    
    private String name;
    
	@SuppressWarnings("rawtypes")
	static final Class<JedisShiroCache> SELF = JedisShiroCache.class;
    public JedisShiroCache(String name, JedisManager jedisManager) {
        this.name = name;
        this.jedisManager = jedisManager;
    }

    /**
     * 自定义relm中的授权/认证的类名加上授权/认证英文名字
     */
    public String getName() {
        if (name == null)
            return "";
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public V get(K key) throws CacheException {
        byte[] byteKey = SerializeUtil.serialize(buildCacheKey(key));
        byte[] byteValue = new byte[0];
        try {
            byteValue = jedisManager.getValueByKey(JedisShiroContant.REDIS_SHIRO_CACHE_DB_INDEX, byteKey);
        } catch (Exception e) {
            LoggerUtils.error(SELF, "get value by cache throw exception",e);
        }
        return (V) SerializeUtil.deserialize(byteValue);
    }

    @Override
    public V put(K key, V value) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.saveValueByKey(JedisShiroContant.REDIS_SHIRO_CACHE_DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)),
                    SerializeUtil.serialize(value), -1);
        } catch (Exception e) {
        	 LoggerUtils.error(SELF, "put cache throw exception",e);
        }
        return previos;
    }

    @Override
    public V remove(K key) throws CacheException {
        V previos = get(key);
        try {
            jedisManager.deleteByKey(JedisShiroContant.REDIS_SHIRO_CACHE_DB_INDEX, SerializeUtil.serialize(buildCacheKey(key)));
        } catch (Exception e) {
            LoggerUtils.error(SELF, "remove cache  throw exception",e);
        }
        return previos;
    }

    @Override
    public void clear() throws CacheException {
        //TODO--
    }

    @Override
    public int size() {
        if (keys() == null)
            return 0;
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        //TODO
        return null;
    }

    @Override
    public Collection<V> values() {
        //TODO
        return null;
    }

    private String buildCacheKey(Object key) {
        return JedisShiroContant.REDIS_SHIRO_CACHE + getName() + ":" + key;
    }

}
