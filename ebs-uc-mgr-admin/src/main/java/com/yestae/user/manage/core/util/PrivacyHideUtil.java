package com.yestae.user.manage.core.util;

public class PrivacyHideUtil {

	public static String mobileHide(String mobile){
		if(mobile != null && mobile.length() == 11){
			return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
		}
		return null;
	}
}
