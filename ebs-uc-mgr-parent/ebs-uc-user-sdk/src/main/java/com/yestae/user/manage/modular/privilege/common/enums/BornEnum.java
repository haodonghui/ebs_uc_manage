package com.yestae.user.manage.modular.privilege.common.enums;


import org.apache.commons.lang3.StringUtils;

public enum BornEnum {

	MOUSE("1", "鼠"),
	COW("2", "牛"),
	TIGER("3", "虎"),
	RABBIT("4", "兔"),
	DRAGON("5", "龙"),
	SNAKE("6", "蛇"),
	HORSE("7", "马"),
	SHEEP("8", "羊"),
	MONKEY("9", "猴"),
	CHICKEN("10", "鸡"),
	DOG("11", "狗"),
	PIG("12", "猪"),
    
    ;

	String code;
    String message;

    BornEnum(String code, String message) {
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
            for (BornEnum s : BornEnum.values()) {
                if (s.getCode().equals(code)) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
