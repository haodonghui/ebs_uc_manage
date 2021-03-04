package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户会员等级
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@TableName("uc_m_user_grade")
public class YestaeUserGrade extends Model<YestaeUserGrade> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户会员等级表ID
     */
	private String id;
    /**
     * 等级名称
     */
	private String name;
    /**
     * 等级类型
     */
	private Integer type;
    /**
     * 所需消费金额
     */
	private BigDecimal money;
    /**
     * 折扣
     */
	private Double discount;
    /**
     * 是否默认 1：是 2：否
     */
	@TableField("if_default")
	private Integer ifDefault;
    /**
     * 删除标识 1-正常 2-已删除
     */
	@TableField("if_del")
	private Integer ifDel;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Long updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getIfDefault() {
		return ifDefault;
	}

	public void setIfDefault(Integer ifDefault) {
		this.ifDefault = ifDefault;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
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
		return "YestaeUserGrade{" +
			"id=" + id +
			", name=" + name +
			", type=" + type +
			", money=" + money +
			", discount=" + discount +
			", ifDefault=" + ifDefault +
			", ifDel=" + ifDel +
			", createBy=" + createBy +
			", createTime=" + createTime +
			", updateBy=" + updateBy +
			", updateTime=" + updateTime +
			"}";
	}
}
