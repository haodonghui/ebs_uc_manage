package com.yestae.user.manage.modular.weixin.util;

import org.springframework.stereotype.Component;

import com.yestae.user.common.cache.EhcacheFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

@Component
public class WrapCache {
	
	private Cache cache = null;	
	private void init(){
		if (cache == null){
			cache = EhcacheFactory.getOrAddCache(Constant.EHCACHE_NAME);
		}
	}
	
	/**
	 * 增加对象到缓存
	 * @param key
	 * @param obj
	 */
	public void addCacheObj(String key, Object obj){
		init();
		cache.put(new Element(key, obj));
	}
	
	/**
	 * 得到缓存对象
	 * @param key
	 * @return
	 */
	public Object getCacheObj(String key){
		init();
		if (isCache(key)){
			return cache.get(key).getObjectValue();
		}
		return null;
	}
	
	/**
	 * 移除缓存
	 * @param key
	 */
	public void removeCacheObj(String key){
		init();
		cache.remove(key);
	}
	
	/**
	 * 判断是否缓存
	 * @param key
	 * @return
	 */
	public boolean isCache(String key){
		init();
		Element element = cache.getQuiet(key);
		if (element!=null){
			return true;
		}
		return false;
	}
}
