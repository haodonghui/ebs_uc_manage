package com.yestae.user.manage.modular.privilege.common.enums;

public enum InviterIsFCodeEnum {
	
	F_CODE_NONE(1, "无"),
	F_CODE_EXIST(2, "有资格"),
    
    ;

    int code;
    String message;

    InviterIsFCodeEnum(int code, String message) {
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
            for (InviterIsFCodeEnum s : InviterIsFCodeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
