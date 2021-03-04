package com.yestae.user.manage.core.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "uc-constant")
public class UcConstant {

	private String defaultPassword = "tae7542";//初始密码
	
	/**
	 * 服务器图片存放路径
	 */
	//测试环境
	private String imageDir = "/data/image/test-image-yyh";//图片存放路径
	private String imageServer = "http://uc-manage-test.yestae.com/imageServer";//图片服务器

	/**
	 * 用户图片路径
	 */
	private String userImageDir = "/app/user";//用户图片路径
	/**
	 * 用户图片路径
	 */
	private String userRealNameImageDir = "/websupport/idcard";//用户实名制图片路径
	/**
	 * 微信小程序码图片路径
	 */
	private String weixinQrCodeDir = "/app/qrcode";//微信小程序码图片路径
	
	private String vasImageDir = "/vas/vas";//增值服务图片路径
	private String equityImageDir = "/vas/equity";//权益图片路径
	private String organizImageDir = "/vas/organiz";//机构图片路径
	private String organizValidLogoDir = "/vas/organiz/logo";//机构logo图片路径
	private String organizInvalidLogoDir = "/vas/organiz/logo";//机构logo图片路径
	private String organizLogoDir = "/vas/organiz/logo";//机构logo图片路径
	private String storeImageDir = "/vas/store";//门店图片路径

	private String poaTmplFileDir = "/app/poa"; //授权委托书模板文件

	public String getPoaTmplFileDir() {
		return poaTmplFileDir;
	}

	public void setPoaTmplFileDir(String poaTmplFileDir) {
		this.poaTmplFileDir = poaTmplFileDir;
	}
	
	
	public String getDefaultPassword() {
		return defaultPassword;
	}
	public void setDefaultPassword(String defaultPassword) {
		this.defaultPassword = defaultPassword;
	}
	public String getImageDir() {
		return imageDir;
	}
	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}
	public String getImageServer() {
		return imageServer;
	}
	public void setImageServer(String imageServer) {
		this.imageServer = imageServer;
	}
	public String getUserImageDir() {
		return userImageDir;
	}
	public void setUserImageDir(String userImageDir) {
		this.userImageDir = userImageDir;
	}
	public String getWeixinQrCodeDir() {
		return weixinQrCodeDir;
	}
	public void setWeixinQrCodeDir(String weixinQrCodeDir) {
		this.weixinQrCodeDir = weixinQrCodeDir;
	}
	public String getUserRealNameImageDir() {
		return userRealNameImageDir;
	}
	public void setUserRealNameImageDir(String userRealNameImageDir) {
		this.userRealNameImageDir = userRealNameImageDir;
	}
	public String getVasImageDir() {
		return vasImageDir;
	}
	public void setVasImageDir(String vasImageDir) {
		this.vasImageDir = vasImageDir;
	}
	public String getEquityImageDir() {
		return equityImageDir;
	}
	public void setEquityImageDir(String equityImageDir) {
		this.equityImageDir = equityImageDir;
	}
	public String getOrganizImageDir() {
		return organizImageDir;
	}
	public void setOrganizImageDir(String organizImageDir) {
		this.organizImageDir = organizImageDir;
	}
	public String getOrganizValidLogoDir() {
		return organizValidLogoDir;
	}
	public void setOrganizValidLogoDir(String organizValidLogoDir) {
		this.organizValidLogoDir = organizValidLogoDir;
	}
	public String getOrganizInvalidLogoDir() {
		return organizInvalidLogoDir;
	}
	public void setOrganizInvalidLogoDir(String organizInvalidLogoDir) {
		this.organizInvalidLogoDir = organizInvalidLogoDir;
	}
	public String getStoreImageDir() {
		return storeImageDir;
	}
	public void setStoreImageDir(String storeImageDir) {
		this.storeImageDir = storeImageDir;
	}
	public String getOrganizLogoDir() {
		return organizLogoDir;
	}
	public void setOrganizLogoDir(String organizLogoDir) {
		this.organizLogoDir = organizLogoDir;
	}
	
	
}
