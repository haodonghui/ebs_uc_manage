package com.yestae.user.manage.modular.privilege.common.enums;

public enum AccountStateEnum {

	NORMAL(1, "正常"),
    FREEZE(2, "冻结"),
    CANCEL(3, "注销"),
    
    ;

    int code;
    String message;

    AccountStateEnum(int code, String message) {
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
            for (AccountStateEnum s : AccountStateEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
