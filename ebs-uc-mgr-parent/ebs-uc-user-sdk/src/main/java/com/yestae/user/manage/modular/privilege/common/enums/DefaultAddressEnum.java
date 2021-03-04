package com.yestae.user.manage.modular.privilege.common.enums;

public enum DefaultAddressEnum {
	
	YES(1, "是"),
    NO(0, "否"),
    
    ;

    int code;
    String message;

    DefaultAddressEnum(int code, String message) {
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
            for (DefaultAddressEnum s : DefaultAddressEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
