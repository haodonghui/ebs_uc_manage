package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户购买增值服务订单记录表
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-24
 */
@TableName("uc_vas_order")
public class Order extends Model<Order> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
	@TableField("added_service_id")
	private String addedServiceId;
    /**
     * 会员名称
     */
	private String name;
    /**
     * 会员手机号
     */
	private String mobile;
    /**
     * 订单编号
     */
	@TableField("order_no")
	private String orderNo;
    /**
     * 支付单号
     */
	@TableField("pay_no")
	private String payNo;
    /**
     * 订单支付总金额
     */
	@TableField("pay_amount")
	private BigDecimal payAmount;
    /**
     * 支付平台
     */
	@TableField("pay_pt")
	private String payPt;
    /**
     * 支付平台订单号
     */
	@TableField("pay_order_no")
	private String payOrderNo;
    /**
     * 支付方式
     */
	@TableField("pay_type")
	private String payType;
    /**
     * 支付币种
     */
	private String currency;
    /**
     * 支付币种汇率
     */
	@TableField("cur_rate")
	private BigDecimal curRate;
    /**
     * 发起支付时间
     */
	@TableField("init_pay_time")
	private Long initPayTime;
    /**
     * 实际支付时间
     */
	@TableField("finish_pay_time")
	private Long finishPayTime;
    /**
     * 支付平台回调时间
     */
	@TableField("notify_pay_time")
	private Long notifyPayTime;
    /**
     * 支付状态
     */
	@TableField("pay_state")
	private String payState;
    /**
     * 年度
     */
	@TableField("create_year")
	private Integer createYear;
    /**
     * 月份
     */
	@TableField("create_month")
	private Integer createMonth;
    /**
     * 状态
     */
	@TableField("order_state")
	private String orderState;
	@TableField("order_type")
	private String orderType;
	@TableField("create_time")
	private Long createTime;
	@TableField("update_time")
	private Long updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddedServiceId() {
		return addedServiceId;
	}

	public void setAddedServiceId(String addedServiceId) {
		this.addedServiceId = addedServiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPayPt() {
		return payPt;
	}

	public void setPayPt(String payPt) {
		this.payPt = payPt;
	}

	public String getPayOrderNo() {
		return payOrderNo;
	}

	public void setPayOrderNo(String payOrderNo) {
		this.payOrderNo = payOrderNo;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getCurRate() {
		return curRate;
	}

	public void setCurRate(BigDecimal curRate) {
		this.curRate = curRate;
	}

	public Long getInitPayTime() {
		return initPayTime;
	}

	public void setInitPayTime(Long initPayTime) {
		this.initPayTime = initPayTime;
	}

	public Long getFinishPayTime() {
		return finishPayTime;
	}

	public void setFinishPayTime(Long finishPayTime) {
		this.finishPayTime = finishPayTime;
	}

	public Long getNotifyPayTime() {
		return notifyPayTime;
	}

	public void setNotifyPayTime(Long notifyPayTime) {
		this.notifyPayTime = notifyPayTime;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
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

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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
		return "Order{" +
			"id=" + id +
			", userId=" + userId +
			", addedServiceId=" + addedServiceId +
			", name=" + name +
			", mobile=" + mobile +
			", orderNo=" + orderNo +
			", payNo=" + payNo +
			", payAmount=" + payAmount +
			", payPt=" + payPt +
			", payOrderNo=" + payOrderNo +
			", payType=" + payType +
			", currency=" + currency +
			", curRate=" + curRate +
			", initPayTime=" + initPayTime +
			", finishPayTime=" + finishPayTime +
			", notifyPayTime=" + notifyPayTime +
			", payState=" + payState +
			", createYear=" + createYear +
			", createMonth=" + createMonth +
			", orderState=" + orderState +
			", orderType=" + orderType +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
