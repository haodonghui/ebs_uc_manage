package com.yestae.user.manage.modular.privilege.common.enums;

/**
 * 账户流水类型
 * @author fd
 *
 */
public enum UserCoinDetailTypeEnum {

	INCREASE(1, "入账"),
    DECREASE(2, "出账"),

    ;

    int code;
    String message;

    UserCoinDetailTypeEnum(int code, String message) {
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
            for (UserCoinDetailTypeEnum s : UserCoinDetailTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
