package com.yestae.user.manage.modular.vas.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 权益
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-10
 */
@TableName("uc_m_vas_equity")
public class Equity extends Model<Equity> {

    private static final long serialVersionUID = 1L;

    /**
     * 权益表id
     */
	private String id;
    /**
     * 权益编码
     */
	@TableField("equity_code")
	private String equityCode;
    /**
     * 权益名称
     */
	@TableField("equity_name")
	private String equityName;
    /**
     * 权益简介
     */
	private String intro;
    /**
     * 权益状态：1-启用，2-停用
     */
	private Integer status;
    /**
     * 线上线下类型：1-线上，2-线下
     */
	private Integer type;
    /**
     * 删除标识：1-未删除，2-已删除
     */
	@TableField("del_flag")
	private Integer delFlag;
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

	/**
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceId;
	
	/**
	 * 封面图url
	 */
	@TableField(exist=false)
	private String surfaceUrl;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEquityCode() {
		return equityCode;
	}

	public void setEquityCode(String equityCode) {
		this.equityCode = equityCode;
	}

	public String getEquityName() {
		return equityName;
	}

	public void setEquityName(String equityName) {
		this.equityName = equityName;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	public String getSurfaceId() {
		return surfaceId;
	}

	public void setSurfaceId(String surfaceId) {
		this.surfaceId = surfaceId;
	}

	public String getSurfaceUrl() {
		return surfaceUrl;
	}

	public void setSurfaceUrl(String surfaceUrl) {
		this.surfaceUrl = surfaceUrl;
	}

	@Override
	public String toString() {
		return "Equity [id=" + id + ", equityCode=" + equityCode + ", equityName=" + equityName + ", intro=" + intro
				+ ", status=" + status + ", type=" + type + ", delFlag=" + delFlag + ", createTime=" + createTime
				+ ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", surfaceId="
				+ surfaceId + ", surfaceUrl=" + surfaceUrl + "]";
	}

	
}
