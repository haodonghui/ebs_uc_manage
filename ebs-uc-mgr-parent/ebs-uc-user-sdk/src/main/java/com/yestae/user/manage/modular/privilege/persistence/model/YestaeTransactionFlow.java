package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-16
 */
@TableName("uc_transaction_flow")
public class YestaeTransactionFlow extends Model<YestaeTransactionFlow> {

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
     * 第三方流水号
     */
	@TableField("flow_number")
	private String flowNumber;
    /**
     * 订单号
     */
	@TableField("order_number")
	private String orderNumber;
    /**
     * 交易单号
     */
	@TableField("trade_number")
	private String tradeNumber;
    /**
     * 支付方式:1-微信 2-支付宝 3-银联 4-益卡通
     */
	@TableField("pay_method")
	private String payMethod;
    /**
     * 金额
     */
	@TableField("amount_money")
	private BigDecimal amountMoney;
    /**
     * 流水类型:1-支付 2-充值 3-退款
     */
	@TableField("flow_type")
	private String flowType;
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

	public String getFlowNumber() {
		return flowNumber;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTradeNumber() {
		return tradeNumber;
	}

	public void setTradeNumber(String tradeNumber) {
		this.tradeNumber = tradeNumber;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public BigDecimal getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(BigDecimal amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
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
		return "YestaeTransactionFlow{" +
			"id=" + id +
			", userId=" + userId +
			", flowNumber=" + flowNumber +
			", orderNumber=" + orderNumber +
			", tradeNumber=" + tradeNumber +
			", payMethod=" + payMethod +
			", amountMoney=" + amountMoney +
			", flowType=" + flowType +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
