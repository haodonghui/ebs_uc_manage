package com.yestae.user.manage.config;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.util.StringUtils;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.modular.system.persistence.model.Dict;
import com.yestae.user.manage.modular.system.service.IDictService;

@Configuration
public class DictConfig implements ServletContextListener{
	
	@Autowired
    private IDictService dictService;
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		List<Dict> list = dictService.list(null);
		for(Dict d: list){
			if(!StringUtils.isEmpty(d.getPcode()) && !StringUtils.isEmpty(d.getCode())){
				
				if("0".equals(d.getPcode())){
					CacheKit.put(Cache.CONSTANT, d.getCode(), d.getName());
				}else{
					CacheKit.put(Cache.CONSTANT, d.getPcode() + ":" + d.getCode(), d.getName());
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
