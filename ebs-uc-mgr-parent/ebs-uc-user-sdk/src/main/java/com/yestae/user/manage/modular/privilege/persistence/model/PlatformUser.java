package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-26
 */
@TableName("uc_m_platform_user")
public class PlatformUser extends Model<PlatformUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 平台用户表id
     */
	private String id;
	/**
	 * 用户名
	 */
	private String name;
    /**
     * 用户id
     */
	@TableField("user_id")
	private String userId;
    /**
     * 用户类型
     */
	@TableField("user_type")
	private Integer userType;
    /**
     * 用户手机号
     */
	private String mobile;
    /**
     * 是否删除 1：未删除 2：删除
     */
	@TableField("if_del")
	private Integer ifDel;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "PlatformUser{" +
			"id=" + id +
			", userId=" + userId +
			", userType=" + userType +
			", mobile=" + mobile +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
