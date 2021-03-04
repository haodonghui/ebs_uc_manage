package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户账户信息
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
@TableName("uc_account")
public class UserAccount extends Model<UserAccount> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 账户编号
     */
	@TableField("account_no")
	private String accountNo;
    /**
     * 账户余额
     */
	private BigDecimal amount;
    /**
     * 账户状态:1-生效 2-冻结 3-注销
     */
	private String state;
    /**
     * 是否删除:1-正常 2-已删除
     */
	@TableField("if_del")
	private String ifDel;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Long updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIfDel() {
		return ifDel;
	}

	public void setIfDel(String ifDel) {
		this.ifDel = ifDel;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
			"id=" + id +
			", userId=" + userId +
			", accountNo=" + accountNo +
			", amount=" + amount +
			", state=" + state +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
