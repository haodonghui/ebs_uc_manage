package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 用户邀请关系
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@TableName("yestae_user_relation")
public class YestaeUserRelation extends Model<YestaeUserRelation> {

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
     * 邀请人用户ID
     */
	@TableField("inviter_id")
	private String inviterId;
    /**
     * 邀请人关系路径
     */
	@TableField("inviter_path")
	private String inviterPath;
    /**
     * 邀请人是否支持F码，1：不支持，2：支持
     */
	@TableField("inviter_is_f_code")
	private Integer inviterIsFCode;
    /**
     * 升级推荐人用户ID
     */
	@TableField("upgrade_id")
	private String upgradeId;
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

	public String getInviterId() {
		return inviterId;
	}

	public void setInviterId(String inviterId) {
		this.inviterId = inviterId;
	}

	public String getInviterPath() {
		return inviterPath;
	}

	public void setInviterPath(String inviterPath) {
		this.inviterPath = inviterPath;
	}

	public Integer getInviterIsFCode() {
		return inviterIsFCode;
	}

	public void setInviterIsFCode(Integer inviterIsFCode) {
		this.inviterIsFCode = inviterIsFCode;
	}

	public String getUpgradeId() {
		return upgradeId;
	}

	public void setUpgradeId(String upgradeId) {
		this.upgradeId = upgradeId;
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
		return "YestaeUserRelation{" +
			"id=" + id +
			", userId=" + userId +
			", inviterId=" + inviterId +
			", inviterPath=" + inviterPath +
			", inviterIsFCode=" + inviterIsFCode +
			", upgradeId=" + upgradeId +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
