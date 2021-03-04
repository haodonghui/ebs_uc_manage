package com.yestae.user.manage.core.util;

import org.apache.commons.lang3.RandomUtils;

public class UserCenterTools {

	public static String generalMD5(String salt,String password) throws Exception{
		
		StringBuffer paswordStringBuffer = new StringBuffer();
		paswordStringBuffer.append(password);
		paswordStringBuffer.append(salt);
		
		String passwordMD5 = MD5Util.MD5(paswordStringBuffer.toString());
		
		return passwordMD5;
	}
	
	public static String generalSalt(){
		int salt = RandomUtils.nextInt(0, 10000000);
		StringBuffer saltStringBuffer = new StringBuffer();
		saltStringBuffer.append(salt);
		return saltStringBuffer.toString();
	}
	
	
}
