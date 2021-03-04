package com.yestae.user.manage.modular.vas.common.enums;

/**
 * 交易流水类型
 * @author fd
 *
 */
public enum PageContentBizTypeEnum {

	PC_BIZ_TYPE_ORGANIZ_STORY(1, "机构故事"),
	PC_BIZ_TYPE_SERVICE_TERMS(2, "服务条款"),
	PC_BIZ_TYPE_EQUITY_DETAIL(3, "权益详情"),
	PC_BIZ_TYPE_FAQ(4, "常见问题"),
    
    ;

    int code;
    String message;

    PageContentBizTypeEnum(int code, String message) {
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
            for (PageContentBizTypeEnum s : PageContentBizTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
