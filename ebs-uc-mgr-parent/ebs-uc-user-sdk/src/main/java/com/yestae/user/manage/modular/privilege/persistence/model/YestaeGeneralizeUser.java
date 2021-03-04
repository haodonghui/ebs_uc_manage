package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 推广人表
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
@TableName("uc_m_generalize_user")
public class YestaeGeneralizeUser extends Model<YestaeGeneralizeUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 推广人表ID
     */
	private String id;
    /**
     * 推广人名称
     */
	private String name;
    /**
     * 推广人手机号
     */
	private String mobile;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 推荐码
     */
	@TableField("recommend_code")
	private String recommendCode;
    /**
     * 渠道ID
     */
	@TableField("channel_id")
	private String channelId;
    /**
     * 邀请人是否支持F码，1：不支持，2：支持
     */
	@TableField("inviter_is_f_code")
	private Integer inviterIsFCode;
    /**
     * 备注
     */
	private String remark;
    /**
     * 是否删除:1-正常 2-已删除
     */
	@TableField("if_del")
	private Integer ifDel;
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
     * 修改时间
     */
	@TableField("update_time")
	private Long updateTime;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;


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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecommendCode() {
		return recommendCode;
	}

	public void setRecommendCode(String recommendCode) {
		this.recommendCode = recommendCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getInviterIsFCode() {
		return inviterIsFCode;
	}

	public void setInviterIsFCode(Integer inviterIsFCode) {
		this.inviterIsFCode = inviterIsFCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "YestaeGeneralizeUser{" +
			"id=" + id +
			", name=" + name +
			", mobile=" + mobile +
			", userId=" + userId +
			", recommendCode=" + recommendCode +
			", channelId=" + channelId +
			", inviterIsFCode=" + inviterIsFCode +
			", remark=" + remark +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
