package com.yestae.user.manage.modular.privilege.common.enums;

public enum PayMethodEnum {

	WEIXIN_PAY(1, "微信"),
    ALI_PAY(2, "支付宝"),
    UNION_PAY(3, "银联"),
    YESTAE_PAY(4, "益卡通"),
    
    ;

    int code;
    String message;

    PayMethodEnum(int code, String message) {
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
            for (PayMethodEnum s : PayMethodEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
