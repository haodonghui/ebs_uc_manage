package com.yestae.user.manage.modular.privilege.common.enums;


import org.apache.commons.lang3.StringUtils;

public enum VerifyEnum {
	
	UNCOMMITTED("0", "未提交"),
	COMMITTED("1", "待审核"),
	PASS("2", "审核通过"),
	NO_PASS("3", "审核不通过"),
	OTHER("4", "其它"),
    
    ;

	String code;
    String message;

    VerifyEnum(String code, String message) {
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
            return "";
        } else {
            for (VerifyEnum s : VerifyEnum.values()) {
                if (s.getCode().equals(code)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
