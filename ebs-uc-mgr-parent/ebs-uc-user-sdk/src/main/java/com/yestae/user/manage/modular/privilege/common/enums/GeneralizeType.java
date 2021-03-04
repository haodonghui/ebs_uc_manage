package com.yestae.user.manage.modular.privilege.common.enums;

public enum GeneralizeType {


	UPGRADE(2, "推广金卡"),
    REGIST(1, "推广注册"),
    
    ;

    int code;
    String message;

    GeneralizeType(int code, String message) {
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
            for (GeneralizeType s : GeneralizeType.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
