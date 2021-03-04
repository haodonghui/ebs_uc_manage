package com.yestae.user.common.exception;

/**
 * @Description 所有业务异常的枚举
 * @author fengshuonan
 * @date 2016年11月12日 下午5:04:51
 */
public enum BizExceptionEnum {
	
	/**
	 * 排序重复
	 */
	NUM_REPEATED(400,"排序重复"),
	
	/**
	 * 删除失败，请重试
	 */
	DELETE_ERROE(400,"删除失败，请重试"),
	
	/**
	 * 导入失败，请重试
	 */
	IMPORT_ERROE(400,"导入失败，请重试"),
	
	/**
	 * 删除失败，请先删除关联的下级渠道
	 */
	DELETE_CHANNEL_ERROE(400,"删除失败，请先删除关联的下级渠道"),
	
	/**
	 * 删除失败，请先删除关联的下级角色
	 */
	DELETE_ROLE_ERROE(400,"删除失败，请先删除关联的下级角色"),
	
	/**
	 * 删除失败，请先删除关联的下级部门
	 */
	DELETE_DEPT_ERROE(400,"删除失败，请先删除关联的下级部门"),
	
	/**
	 * 该用户不可用，请重新选择
	 */
	YESTAE_USER_HAS_BEEN_USED(400,"该用户不可用，请重新选择"),
	
	/**
	 * 二维码已存在，请重新选择用户或场景
	 */
	QRCODE_EXISTED(400,"二维码已存在，请重新选择推广人或场景"),
	
	/**
	 * 二维码创建失败，请稍后重试
	 */
	QRCODE_GET_ERROR(500,"二维码创建失败，请稍后重试"),
	
	/**
	 * 数据过期
	 */
	 DATABASE_CHANGED(400,"数据过期，请重试"),
	 
	 /**
	  * 账户余额不足
	  */
	 NOT_SUFFICIENT_FUNDS(400,"账户余额不足"),
	 
	 /**
	  * 账户超额
	  */
	 OVERFLOW_FUNDS(400,"账户超额"),
	 
	/**
	 * 重置密码失败
	 */
	ERROR_RESERT_PASSWORD(400,"重置密码失败"),
	
	/**
	 * 密码重置成功，短信发送失败
	 */
	ERROR_SEND_SMS_SUCCESS_RESERT_PASSWORD(400,"密码重置成功，短信发送失败"),
	
	/**
	 * 手机号已经存在
	 */
	MOBILE_EXISTED(400,"手机号已经存在"),
	
	/**
	 * 渠道编码已经存在
	 */
	CHANNEL_CODE_EXISTED(400,"渠道编码已经存在"),
	
	/**
	 * 权益编码已经存在
	 */
	EQUITY_CODE_EXISTED(400,"权益编码已经存在"),
	
	/**
	 * 权益编码已经存在
	 */
	EQUITY_NAME_EXISTED(400,"权益名称已经存在"),
	
	/**
	 * 门店名称已经存在
	 */
	STORE_NAME_EXISTED(400,"门店名称已经存在"),
	/**
	 * 门店编码已经存在
	 */
	STORE_CODE_EXISTED(400,"门店编码已经存在"),
	/**
	 * 组织名称已经存在
	 */
	ORGANIZ_NAME_EXISTED(400,"机构名称已经存在"),
	/**
	 * 此机构已经存在该门店名称
	 */
	ORGANIZ_STORE_NAME_EXISTED(400,"此机构已经存在该门店名称"),
	/**
	 * 此机构已经存在该增值服务名称
	 */
	ORGANIZ_VAS_NAME_EXISTED(400,"此机构已经存在该增值服务名称"),
	/**
	 * 增值服务名称已经存在
	 */
	VAS_NAME_EXISTED(400,"服务名称已经存在"),
	/**
	 * 机构编码已经存在
	 */
	ORGANIZ_CODE_EXISTED(400,"此机构已经存在该服务名称"),
	/**
	 * 保留词名称已经存在
	 */
	RESERVE_NAME_EXISTED(400,"保留词名称已经存在"),
	
	/**
	 * 保留词编码已经存在
	 */
	RESERVE_CODE_EXISTED(400,"保留词编码已经存在"),

	/**
	 * 字典
	 */
	DICT_EXISTED(400,"字典已经存在"),
	ERROR_CREATE_DICT(500,"创建字典失败"),
	ERROR_WRAPPER_FIELD(500,"包装字典属性失败"),


	/**
	 * 文件上传
	 */
	FILE_READING_ERROR(400,"FILE_READING_ERROR!"),
	FILE_NOT_FOUND(400,"FILE_NOT_FOUND!"),
	UPLOAD_ERROR(500,"上传图片出错"),
	UPLOAD_TYPE_ERROR(400,"上传图片类型不匹配"),

	VALID_QUANTITY_CONFIG_ERROR(400,"增票有效资质数量配置不正确"),

	/**
	 * 权限和数据问题
	 */
	DB_RESOURCE_NULL(400,"数据库中没有该资源"),
	NO_PERMITION(405, "权限异常"),
	REQUEST_INVALIDATE(400,"请求数据格式不正确"),
	INVALID_KAPTCHA(400,"验证码不正确"),
	CANT_DELETE_ADMIN(600,"不能删除超级管理员"),
	CANT_FREEZE_ADMIN(600,"不能冻结超级管理员"),
	CANT_CHANGE_ADMIN(600,"不能修改超级管理员角色"),

	/**
	 * 账户问题
	 */
	USER_ALREADY_REG(401,"该用户已经注册"),
	NO_THIS_USER(400,"没有此用户"),
	USER_NOT_EXISTED(400, "没有此用户"),
	ACCOUNT_FREEZED(401, "账号被冻结"),
	OLD_PWD_NOT_RIGHT(402, "原密码不正确"),
	TWO_PWD_NOT_MATCH(405, "两次输入密码不一致"),

	/**
	 * 错误的请求
	 */
	MENU_PCODE_COINCIDENCE(400,"菜单编号和副编号不能一致"),
	EXISTED_THE_MENU(400,"菜单编号重复，不能添加"),
	DICT_MUST_BE_NUMBER(400,"字典的值必须为数字"),
	REQUEST_NULL(400, "请求有错误"),
	SESSION_TIMEOUT(400, "会话超时"),
	SERVER_ERROR(500, "服务器异常"),
	
	/**
	 * 
	 * 
	 * 实名相关
	 */
	IS_NOT_CHINESE_NAME(400, "真实姓名请输入中文"),
	IDCARD_ERROR(400, "身份证号不正确，请确认重新输入"),
	BANKCARDNO_ERROR(400, "银行卡只能填写5到25位数字"),
	ZODIAC_ERROR(400, "生肖不匹配"),


	/**
	 * 增票审核
	 */
	VERIFY_FAIL(400,"审核失败"),
	CANCEL_VERIFY_STATE_ERROR(400,"只可撤销审核通过或审核不通过的数据"),
	CANCEL_VERIFY_ERROR(400,"撤销审核失败");

	;

	BizExceptionEnum(int code, String message) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
	}
	
	BizExceptionEnum(int code, String message,String urlPath) {
		this.friendlyCode = code;
		this.friendlyMsg = message;
		this.urlPath = urlPath;
	}

	private int friendlyCode;

	private String friendlyMsg;
	
	private String urlPath;

	public int getCode() {
		return friendlyCode;
	}

	public void setCode(int code) {
		this.friendlyCode = code;
	}

	public String getMessage() {
		return friendlyMsg;
	}

	public void setMessage(String message) {
		this.friendlyMsg = message;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

}
