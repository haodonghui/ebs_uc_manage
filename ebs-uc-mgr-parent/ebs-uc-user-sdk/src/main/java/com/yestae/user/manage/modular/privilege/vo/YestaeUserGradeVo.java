package com.yestae.user.manage.modular.privilege.vo;

import java.io.Serializable;
import java.math.BigDecimal;


public class YestaeUserGradeVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3347078163808660778L;
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
	private String ifDefault;
    /**
     * 删除标识 1：正常，2：删除
     */
	private Integer ifDel;
	
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
	public String getIfDefault() {
		return ifDefault;
	}
	public void setIfDefault(String ifDefault) {
		this.ifDefault = ifDefault;
	}
	public Integer getIfDel() {
		return ifDel;
	}
	public void setIfDel(Integer ifDel) {
		this.ifDel = ifDel;
	}
	
	
}
