package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 用户会员等级变更日志
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@TableName("uc_user_modify_grade_log")
public class YestaeUserModifyGradeLog extends Model<YestaeUserModifyGradeLog> {

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
     * 操作人ID
     */
	@TableField("operator_id")
	private String operatorId;
    /**
     * 操作人名称
     */
	@TableField("operator_name")
	private String operatorName;
    /**
     * 原卡级别
     */
	@TableField("source_card")
	private String sourceCard;
    /**
     * 目标卡级别
     */
	@TableField("target_card")
	private String targetCard;
    /**
     * 备注
     */
	private String remark;
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getSourceCard() {
		return sourceCard;
	}

	public void setSourceCard(String sourceCard) {
		this.sourceCard = sourceCard;
	}

	public String getTargetCard() {
		return targetCard;
	}

	public void setTargetCard(String targetCard) {
		this.targetCard = targetCard;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "YestaeUserModifyGradeLog{" +
			"id=" + id +
			", userId=" + userId +
			", operatorId=" + operatorId +
			", operatorName=" + operatorName +
			", sourceCard=" + sourceCard +
			", targetCard=" + targetCard +
			", remark=" + remark +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
