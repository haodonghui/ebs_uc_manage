package com.yestae.user.manage.common.constant;

/**
 * 用户发票审核状态常量类
 * @author liuqi
 * @date 2020/12/8 14:33
 */
public class UserInvoiceVerifyState {

    /**
     * 发票审核状态-待审核
     */
    public static final String VERIFY_STATE_WAIT = "1";
    /**
     * 发票审核状态-审核不通过
     */
    public static final String VERIFY_STATE_NOT_PASS = "2";
    /**
     * 发票审核状态-审核通过
     */
    public static final String VERIFY_STATE_PASS = "3";
    /**
     * 发票审核状态-审核通过已失效
     */
    public static final String VERIFY_STATE_PASS_INVALID = "4";
    /**
     * 发票审核状态-审核未通过已失效
     */
    public static final String VERIFY_STATE_NOT_PASS_INVALID = "5";
}
