package com.yestae.user.manage.modular.privilege.common.enums;

public enum QrcodeTypeEnum {
	/**
	 * 茶博会
	 */
	CITTE(1, "茶博会"),
	/**
	 * 钻卡
	 */
	DCARD(2, "钻卡"),
	/**
	 * 茶票
	 */
	TEATICKET(2, "茶票"),
	
    ;

    int code;
    String message;

    QrcodeTypeEnum(int code, String message) {
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
            for (QrcodeTypeEnum s : QrcodeTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
