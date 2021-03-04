package com.yestae.user.manage.modular.privilege.common.enums;

public enum SourceEnum {
	
	/**
	 * 来源 0:未知,1:,2:益友会APP,3:益友会WEB,4:总裁茶室APP,5:益购APP,6:益购WEB,9:益购微信小程序
	 */
	UNKNOWN(0, "未知"),
	EMPTY(1, "益友会APP"),
    YYH_APP(2, "益友会微信公众号"),
    YYH_WEB(3, "益友会WEB"),
    ZCCS_APP(4, "总裁茶室APP"),
    YG_APP(5, "益购APP"),
    YG_WEB(6, "益购WEB"),
    YG_WEIXIN(9, "益购微信小程序"),
    
    ;

    int code;
    String message;

    SourceEnum(int code, String message) {
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
            for (SourceEnum s : SourceEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }

}
