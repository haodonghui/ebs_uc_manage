package com.yestae.user.manage.modular.privilege.common.enums;

public enum SysEnum {
	
	YES(2, "是"),
    NO(1, "否"),
    
    ;

    int code;
    String message;

    SysEnum(int code, String message) {
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
            for (SysEnum s : SysEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
