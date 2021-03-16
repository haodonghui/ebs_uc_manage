package com.yestae.user.manage.modular.privilege.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 账户资金变动流水
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-20
 */
@TableName("uc_account_flow")
public class UserAccountFlow extends Model<UserAccountFlow> {

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
     * 账户ID
     */
	@TableField("account_id")
	private String accountId;
    /**
     * 交易号
     */
	@TableField("trade_no")
	private String tradeNo;
    /**
     * 帐务变动方向:1-入账 2-出帐
     */
	private String type;
    /**
     * 变更金额
     */
	private BigDecimal amount;
    /**
     * 交易前余额
     */
	@TableField("pre_amount")
	private BigDecimal preAmount;
    /**
     * 交易后余额
     */
	@TableField("later_amount")
	private BigDecimal laterAmount;
    /**
     * 备注
     */
	private String remark;
    /**
     * 交易年度
     */
	@TableField("create_year")
	private Integer createYear;
    /**
     * 交易月度
     */
	@TableField("create_month")
	private Integer createMonth;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPreAmount() {
		return preAmount;
	}

	public void setPreAmount(BigDecimal preAmount) {
		this.preAmount = preAmount;
	}

	public BigDecimal getLaterAmount() {
		return laterAmount;
	}

	public void setLaterAmount(BigDecimal laterAmount) {
		this.laterAmount = laterAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreateYear() {
		return createYear;
	}

	public void setCreateYear(Integer createYear) {
		this.createYear = createYear;
	}

	public Integer getCreateMonth() {
		return createMonth;
	}

	public void setCreateMonth(Integer createMonth) {
		this.createMonth = createMonth;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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
		return "UserAccountFlow{" +
			"id=" + id +
			", userId=" + userId +
			", accountId=" + accountId +
			", tradeNo=" + tradeNo +
			", type=" + type +
			", amount=" + amount +
			", preAmount=" + preAmount +
			", laterAmount=" + laterAmount +
			", remark=" + remark +
			", createYear=" + createYear +
			", createMonth=" + createMonth +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			"}";
	}
}
