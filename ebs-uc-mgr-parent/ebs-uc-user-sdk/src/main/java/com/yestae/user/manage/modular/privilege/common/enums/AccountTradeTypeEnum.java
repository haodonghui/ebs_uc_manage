package com.yestae.user.manage.modular.privilege.common.enums;

public enum AccountTradeTypeEnum {
	
	CONSUME(1, "消费"),
    RECHARGE(2, "充值"),
    REFUND(3, "退款"),
    WITHDRAW(4, "提现"),
    WITHHOLD(5, "扣款"),
    
    ;

    int code;
    String message;

    AccountTradeTypeEnum(int code, String message) {
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
            for (AccountTradeTypeEnum s : AccountTradeTypeEnum.values()) {
                if (s.getCode() == status) {
                    return s.getMessage();
                }
            }
            return "";
        }
    }
}
