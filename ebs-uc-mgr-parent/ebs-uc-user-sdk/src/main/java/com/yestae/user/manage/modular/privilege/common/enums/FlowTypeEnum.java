package com.yestae.user.manage.modular.privilege.common.enums;

/**
 * 交易流水类型
 * @author fd
 *
 */
public enum FlowTypeEnum {

	CONSUME(1, "消费"),
    RECHARGE(2, "充值"),
    REFUND(3, "退款"),
    WITHHOLD(4, "扣款"),
    
    ;

    int code;
    String message;

    FlowTypeEnum(int code, String message) {
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
            for (FlowTypeEnum s : FlowTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
