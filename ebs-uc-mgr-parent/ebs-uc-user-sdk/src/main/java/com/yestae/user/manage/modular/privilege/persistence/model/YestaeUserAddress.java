package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-09
 */
@TableName("uc_user_address")
public class YestaeUserAddress extends Model<YestaeUserAddress> {

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
     * 收货人
     */
	private String consignee;
    /**
     * 收货人电话
     */
	@TableField("consignee_phone")
	private String consigneePhone;
    /**
     * 省份
     */
	@TableField("consignee_privince")
	private String consigneePrivince;
    /**
     * 省份编码
     */
	@TableField("consignee_privince_code")
	private String consigneePrivinceCode;
    /**
     * 城市
     */
	@TableField("consignee_city")
	private String consigneeCity;
    /**
     * 城市编码
     */
	@TableField("consignee_city_code")
	private String consigneeCityCode;
    /**
     * 区县
     */
	@TableField("consignee_area")
	private String consigneeArea;
    /**
     * 区县编码
     */
	@TableField("consignee_area_code")
	private String consigneeAreaCode;
    /**
     * 详细地址
     */
	@TableField("consignee_full_address")
	private String consigneeFullAddress;
    /**
     * 是否是默认地址，1：默认地址，0：非默认地址
     */
	@TableField("default_address")
	private String defaultAddress;
    /**
     * 1-正常 2-已删除
     */
	@TableField("if_del")
	private Integer ifDel;
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

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneePrivince() {
		return consigneePrivince;
	}

	public void setConsigneePrivince(String consigneePrivince) {
		this.consigneePrivince = consigneePrivince;
	}

	public String getConsigneePrivinceCode() {
		return consigneePrivinceCode;
	}

	public void setConsigneePrivinceCode(String consigneePrivinceCode) {
		this.consigneePrivinceCode = consigneePrivinceCode;
	}

	public String getConsigneeCity() {
		return consigneeCity;
	}

	public void setConsigneeCity(String consigneeCity) {
		this.consigneeCity = consigneeCity;
	}

	public String getConsigneeCityCode() {
		return consigneeCityCode;
	}

	public void setConsigneeCityCode(String consigneeCityCode) {
		this.consigneeCityCode = consigneeCityCode;
	}

	public String getConsigneeArea() {
		return consigneeArea;
	}

	public void setConsigneeArea(String consigneeArea) {
		this.consigneeArea = consigneeArea;
	}

	public String getConsigneeAreaCode() {
		return consigneeAreaCode;
	}

	public void setConsigneeAreaCode(String consigneeAreaCode) {
		this.consigneeAreaCode = consigneeAreaCode;
	}

	public String getConsigneeFullAddress() {
		return consigneeFullAddress;
	}

	public void setConsigneeFullAddress(String consigneeFullAddress) {
		this.consigneeFullAddress = consigneeFullAddress;
	}

	public String getDefaultAddress() {
		return defaultAddress;
	}

	public void setDefaultAddress(String defaultAddress) {
		this.defaultAddress = defaultAddress;
	}

	public Integer getIfDel() {
		return ifDel;
	}

	public void setIfDel(Integer ifDel) {
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
		return "YestaeUserAddress{" +
			"id=" + id +
			", userId=" + userId +
			", consignee=" + consignee +
			", consigneePhone=" + consigneePhone +
			", consigneePrivince=" + consigneePrivince +
			", consigneePrivinceCode=" + consigneePrivinceCode +
			", consigneeCity=" + consigneeCity +
			", consigneeCityCode=" + consigneeCityCode +
			", consigneeArea=" + consigneeArea +
			", consigneeAreaCode=" + consigneeAreaCode +
			", consigneeFullAddress=" + consigneeFullAddress +
			", defaultAddress=" + defaultAddress +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
