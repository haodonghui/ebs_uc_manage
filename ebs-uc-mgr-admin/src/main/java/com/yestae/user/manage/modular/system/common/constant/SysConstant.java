package com.yestae.user.manage.modular.system.common.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "sys-constant")
public class SysConstant {
	
	private String dictRoot = "ROOT";//字典顶级pcode

	public String getDictRoot() {
		return dictRoot;
	}

	public void setDictRoot(String dictRoot) {
		this.dictRoot = dictRoot;
	}
	
	
}
