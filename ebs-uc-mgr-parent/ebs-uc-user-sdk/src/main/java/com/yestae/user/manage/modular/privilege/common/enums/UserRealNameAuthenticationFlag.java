package com.yestae.user.manage.modular.privilege.common.enums;

import com.baomidou.mybatisplus.toolkit.StringUtils;

public enum UserRealNameAuthenticationFlag {

	TRUE("0", "有效"),
	FALSE("1", "失效"),
    
    ;

	String code;
    String message;

    UserRealNameAuthenticationFlag(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String getMessage(String code) {
        if (StringUtils.isEmpty(code)) {
            return TRUE.message;
        } else {
            for (UserRealNameAuthenticationFlag s : UserRealNameAuthenticationFlag.values()) {
                if (s.getCode().equals(code)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
