package com.yestae.user.manage.modular.vas.common.constant;

public interface VasConstants {

	public static final Integer YES = 1;
	public static final Integer NO = 2;
	
	public static final Integer STOP_ON = 1;
	public static final Integer STOP_OFF = 2;
	
	public static final Integer STATUS_ON = 1;
	public static final Integer STATUS_OFF = 2;
	
	public static final String VAS_ORDER_PAY_STATE_PAY_FINISH = "3";
	
	/**
	 * 图片类型-增值服务
	 */
	public static final Integer VI_BIZ_TYPE_VAS = 1;
	public static final String VI_BIZ_NAME_VAS = "vas";
	/**
	 * 图片类型-权益
	 */
	public static final Integer VI_BIZ_TYPE_EQUITY = 2;
	public static final String VI_BIZ_NAME_EQUITY = "equity";
	/**
	 * 图片类型-机构
	 */
	public static final Integer VI_BIZ_TYPE_ORGANIZ = 3;
	public static final String VI_BIZ_NAME_ORGANIZ = "organiz";
	/**
	 * 图片类型-机构
	 */
	public static final Integer VI_BIZ_TYPE_ORGANIZ_VALID_LOGO = 31;
	public static final String VI_BIZ_NAME_ORGANIZ_VALID_LOGO = "organizvl";
	/**
	 * 图片类型-机构
	 */
	public static final Integer VI_BIZ_TYPE_ORGANIZ_INVALID_LOGO = 32;
	public static final String VI_BIZ_NAME_ORGANIZ_INVALID_LOGO = "organizivl";
	/**
	 * 图片类型-机构
	 */
	public static final Integer VI_BIZ_TYPE_ORGANIZ_LOGO = 33;
	public static final String VI_BIZ_NAME_ORGANIZ_LOGO = "organizl";
	/**
	 * 图片类型-门店
	 */
	public static final Integer VI_BIZ_TYPE_STORE = 4;
	public static final String VI_BIZ_NAME_STORE = "store";
	/**
	 * 图片类型-商品
	 */
	public static final Integer VI_BIZ_TYPE_GOODS = 5;
	public static final String VI_BIZ_NAME_GOODS = "goods";
	
	
	/**
	 * 机构故事
	 */
	public static final Integer PC_BIZ_TYPE_ORGANIZ_STORY = 1;
	/**
	 * 服务条款
	 */
	public static final Integer PC_BIZ_TYPE_SERVICE_TERMS = 2;
	/**
	 * 权益详情
	 */
	public static final Integer PC_BIZ_TYPE_EQUITY_DETAIL = 3;
	/**
	 * 常见问题
	 */
	public static final Integer PC_BIZ_TYPE_FAQ = 4;
	
	
	/**
	 * 内容类型-文本
	 */
	public static final Integer PC_CONTENT_TYPE_TEXT = 1;
	/**
	 * 内容类型-富文本
	 */
	public static final Integer PC_CONTENT_TYPE_UEDITOR = 2;
	/**
	 * 内容类型-url
	 */
	public static final Integer PC_CONTENT_TYPE_URL = 3;
	
	
	/**
	 * 增值服务未发布
	 */
	public static final Integer VAS_STATUS_NO_PUBLISH = 1;
	/**
	 * 增值服务上线
	 */
	public static final Integer VAS_STATUS_ONLINE = 2;
	/**
	 * 增值服务下线
	 */
	public static final Integer VAS_STATUS_OFFLINE = 3;
}
