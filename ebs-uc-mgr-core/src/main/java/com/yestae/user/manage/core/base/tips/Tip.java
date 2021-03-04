package com.yestae.user.manage.core.base.tips;

/**
 * 返回给前台的提示（最终转化为json形式）
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午11:58:00
 */
public abstract class Tip {

    public Tip() {
		super();
	}
    public Tip(int code, String message) {
    	setCode(code);
    	setMessage(message);
    }

	protected int code;
    protected String message;

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
	@Override
	public String toString() {
		return "Tip [code=" + code + ", message=" + message + "]";
	}
    
    
}
