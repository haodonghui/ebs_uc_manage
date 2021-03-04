package com.yestae.user.manage.modular.privilege.common.enums;

public enum UserTypeEnum {

	NORMAL_CARD(1, "普通会员"),
    GOLD_CARD(2, "金卡会员"),
    DIAMOND_CARD(3, "钻卡会员"),
    
    ;

    int code;
    String message;

    UserTypeEnum(int code, String message) {
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
            for (UserTypeEnum s : UserTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
