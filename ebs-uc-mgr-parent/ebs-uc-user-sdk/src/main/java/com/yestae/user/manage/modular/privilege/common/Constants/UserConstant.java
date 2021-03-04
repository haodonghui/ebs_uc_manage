package com.yestae.user.manage.modular.privilege.common.Constants;

public interface UserConstant {

	/**
	 * 二维码场景-应用范围：1-app专享
	 */
	public static final Integer APPLY_SCOPE_APP = 1;
	/**
	 * 二维码场景-应用范围：2-小程序专享
	 */
	public static final Integer APPLY_SCOPE_WEIXIN = 2;
	/**
	 * 二维码场景-应用范围：3-通用
	 */
	public static final Integer APPLY_SCOPE_COMMON = 3;
	/**
	 * 二维码场景-跳转页面类型：1-原生页
	 */
	public static final Integer JUMP_TYPE_ORIGINAL = 1;
	
	/**
	 * 二维码场景-类型：12-获取茶票
	 */
	public static final Integer TYPE_GET_TICKET = 12;
	
	/**
	 * 二维码场景-类型：12-获取茶票扫码跳转页面
	 */
	public static final String TYPE_GET_TICKET_URL = "pages/middle-pages/garden/garden";
	
	/**
	 * 用户名长度限制
	 */
	public static final Integer NAME_LENGTH = 16;
	
	/**
	 * 平台用户-用户类型 1-普金卡会员
	 */
	public static final Integer USER_TYPE_COMMON = 1;
	
	/**
	 * 钻卡推广注册场景
	 */
	public static final String DCARD_SCENE_NAME = "钻卡推广注册";
	
	/**
	 * 大益茶庭渠道编码
	 */
	public static final String CHANNEL_CODE_DYCT = "DYCT";
	
	/**
	 * 上海餐茶渠道编码
	 */
	public static final String CHANNEL_CODE_SHCC = "SHCC";
	
	/**
	 * 扫码获取茶票场景
	 */
	public static final String TAE_TICKET_SCENE_NAME = "扫码获取茶票";
	
	/**
	 * 有效增值服务
	 */
	public static final Integer VAS_RECORD_INVALID = 0;
	/**
	 * 是
	 */
	public static final Integer YES = 1;
	/**
	 * 否
	 */
	public static final Integer NO = 2;
}
