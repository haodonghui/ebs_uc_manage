package com.yestae.user.manage.modular.privilege.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum IdNoEnum {
	
	DALU_PASSPORT("1", "台胞证"),
	TAIWAN_ID_CARD("2", "台湾身份证"),
	ID_CARD("3", "身份证"),
	MILITARY_OFFICER_CERTIFICATE("4", "军官证"),
	
    
    ;

	String code;
    String message;

    IdNoEnum(String code, String message) {
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
            for (IdNoEnum s : IdNoEnum.values()) {
                if (s.getCode().equals(code)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
