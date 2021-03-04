package com.yestae.user.manage.modular.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptUtil {
	
	private static String key = "13579!@#$%^&*()24680";
	
	//MD5加密
	public static String encodeMd5(String srcPwd){
		try{
			MessageDigest encrypt = getMdInstance("MD5", key);  
			      
			// 对信息加密  
			byte[] md5 = encrypt.digest(srcPwd.getBytes());   
	
			return byte2hex(md5);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	private static MessageDigest getMdInstance(String algorithm, String key){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
			md.update(key.getBytes());
			
			return md;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md;
	}
	
	/**
	  * 2进制转16进制
	  * @param b
	  * @return
	  */
	 private static String byte2hex(byte[] b) {  
	      String hs = "";  
	      String stmp = "";  
	      for (int n = 0; n < b.length; n++){  
	          stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));  
	             
	          if (stmp.length() == 1){  
	               hs = hs + "0" + stmp;  
	          }else{  
	               hs = hs + stmp;  
	          }  
	      }  
	       
	      return hs.toUpperCase();        
	}
	
	//SHA1加密
	public static String encodeSha1(String srcPwd){
		String encrypt = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] digest = md.digest(srcPwd.getBytes());// 对字符串进行sha1加密
			return encrypt = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encrypt;
	}
	
	//加密方法
	private static String byteToStr(byte[] byteArray) {
		String str = "";
		for (int i = 0; i < byteArray.length; i++) {
			str += byteToHex(byteArray[i]);
		}
		return str;
	}
	
	//加密方法
	private static String byteToHex(byte b) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] arr = new char[2];
		arr[0] = Digit[(b >>> 4) & 0X0F];
		arr[1] = Digit[b & 0X0F];
		String s = new String(arr);
		return s;
	}
	
	//Base64加密(commons-codec包)
	public static String encodeBase64(String srcPwd){
		Base64 base64 =  new  Base64();
	    byte[] bytes = base64.encode(srcPwd.getBytes());
	    return new String(bytes);
	}
	
	//Base64解密(commons-codec包)
	public static String decodeBase64(String encrypt){
		Base64 base64 =  new  Base64();
	    byte[] bytes = base64.decode(encrypt.getBytes());
	    return new String(bytes);
	}
	
	//DES加密需要配合base64
	public static String encodeDes(String srcPwd){
		try{
			SecureRandom random =  new  SecureRandom();  
		    DESKeySpec keySpec =  new  DESKeySpec(key.getBytes());  
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
		    SecretKey secretKey = keyFactory.generateSecret(keySpec);  
		      
		    Cipher cipher = Cipher.getInstance("DES");  
		    cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);  
		    byte [] bytes = cipher.doFinal(srcPwd.getBytes());  
		    return new String(new Base64().encode(bytes));  
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	//DES解密需要配合base64
	public static String decodeDes(String encrypt){
		try{
			SecureRandom random =  new  SecureRandom();  
		    DESKeySpec keySpec =  new  DESKeySpec(key.getBytes());  
		    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");  
		    SecretKey secretKey = keyFactory.generateSecret(keySpec);  
		      
		    Cipher cipher = Cipher.getInstance("DES");  
		    cipher.init(Cipher.DECRYPT_MODE, secretKey, random); 
		    byte [] bytes = cipher.doFinal(new Base64().decode(encrypt.getBytes()));  
		    return new String (bytes);  
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(encodeDes("wxf1cb37f6a9831e9f"));
		System.out.println(encodeDes("7ed98716c1431ea15b943aebc3ac97ee"));
	}
}
