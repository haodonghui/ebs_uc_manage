package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-06
 */
@TableName("uc_m_generalize")
public class YestaeGeneralize extends Model<YestaeGeneralize> {

    private static final long serialVersionUID = 1L;

    /**
     * 推广明细表ID
     */
	private String id;
    /**
     * 二维码表ID
     */
	@TableField("qrcode_id")
	private String qrcodeId;
    /**
     * 用户表ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 推广类型：1：推广注册 2：推广金卡 
     */
	private Integer type;
    /**
     * 推广时间
     */
	@TableField("create_time")
	private Long createTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(String qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "YestaeGeneralize{" +
			"id=" + id +
			", qrcodeId=" + qrcodeId +
			", userId=" + userId +
			", type=" + type +
			", createTime=" + createTime +
			"}";
	}
}
