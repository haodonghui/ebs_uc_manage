package com.yestae.user.manage.modular.privilege.common.enums;

public enum GenderEnum {

	GENDER_PRIVARY(0, "保密"),
    GENDER_MAN(1, "男"),
    GENDER_WOMAN(2, "女"),
    
    ;

    int code;
    String message;

    GenderEnum(int code, String message) {
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
            for (GenderEnum s : GenderEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
