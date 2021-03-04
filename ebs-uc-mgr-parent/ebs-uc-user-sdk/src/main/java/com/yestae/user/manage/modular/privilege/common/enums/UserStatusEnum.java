package com.yestae.user.manage.modular.privilege.common.enums;

public enum UserStatusEnum {
	
	STATUS_ON(1, "正常"),
    STATUS_OFF(0, "停用"),
    
    ;

    int code;
    String message;

    UserStatusEnum(int code, String message) {
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
            for (UserStatusEnum s : UserStatusEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
