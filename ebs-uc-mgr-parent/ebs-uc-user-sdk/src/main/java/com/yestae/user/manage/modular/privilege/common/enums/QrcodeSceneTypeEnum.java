package com.yestae.user.manage.modular.privilege.common.enums;

public enum QrcodeSceneTypeEnum {

	/**
	 * 推广注册type
	 */
	WEIXIN_A_REGIST(1, "推广注册"),
	/**
	 * 升级金卡type
	 */
	WEIXIN_A_UPGRADE(2, "升级金卡"),
	/**
	 * 登录注册type
	 */
	WEIXIN_A_LOGIN(3, "登录注册"),
	/**
	 * 茶博会type
	 */
	WEIXIN_A_TEA_EXPO(4, "茶博会"),
	
	/**
	 * url与对应类型差值
	 */
	DIFFERENCE_VALUE(100, "url与对应类型差值"),
    
	/**
	 * 推广注册url
	 */
	WEIXIN_A_REGIST_URL(QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode() 
			+ QrcodeSceneTypeEnum.WEIXIN_A_REGIST.getCode(), "pages/register/register"),
	
	/**
	 * 升级金卡url
	 */
	WEIXIN_A_UPGRADE_URL(QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode() 
			+ QrcodeSceneTypeEnum.WEIXIN_A_UPGRADE.getCode(), "pages/gold-up/gold-up"),
	
	/**
	 * 登录注册url
	 */
	WEIXIN_A_LOGIN_URL(QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode() 
			+ QrcodeSceneTypeEnum.WEIXIN_A_LOGIN.getCode(), "pages/login/login"),
	
	/**
	 * 茶博会url
	 */
	WEIXIN_A_TEA_EXPO_URL(QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode() 
			+ QrcodeSceneTypeEnum.WEIXIN_A_TEA_EXPO.getCode(), "pages/tea-expo/view/view"),
	
    ;

    int code;
    String message;

    QrcodeSceneTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String valueOf(Integer status) {
        if (status == null) {
            return "";
        } else {
            for (QrcodeSceneTypeEnum s : QrcodeSceneTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
