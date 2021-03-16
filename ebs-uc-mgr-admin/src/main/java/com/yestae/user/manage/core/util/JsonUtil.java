package com.yestae.user.manage.core.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

public class JsonUtil {

	/**
	 * json字符串转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clazz){
		T t = null;
		if(StringUtils.isNotEmpty(json)){
			t = JSONObject.parseObject(json, clazz);
		}
		return t;
	}
}
