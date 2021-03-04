package com.yestae.user.manage.modular.privilege.common.enums;

public enum TeaTeacherLevelEnum {
	
	TEA_TEACHER_LEVEL_NONE(0, "无"),
	TEA_TEACHER_LEVEL_FIRST(1, "初阶茶道师"),
	TEA_TEACHER_LEVEL_SECOND(2, "二阶茶道师"),
	TEA_TEACHER_LEVEL_THIRD(3, "三阶茶道师"),
	TEA_TEACHER_LEVEL_FOURTH(4, "四阶茶道师"),
    
    ;

    int code;
    String message;

    TeaTeacherLevelEnum(int code, String message) {
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
            for (TeaTeacherLevelEnum s : TeaTeacherLevelEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
