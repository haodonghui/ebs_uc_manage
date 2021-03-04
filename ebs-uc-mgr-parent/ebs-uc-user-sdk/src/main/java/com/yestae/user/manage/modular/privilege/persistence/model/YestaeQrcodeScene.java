package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 二维码场景表
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-04
 */
@TableName("uc_m_qrcode_scene")
public class YestaeQrcodeScene extends Model<YestaeQrcodeScene> {

    private static final long serialVersionUID = 1L;

    /**
     * 二维码场景表ID
     */
	private String id;
    /**
     * 场景名称
     */
	private String name;
    /**
     * 应用范围
     */
	@TableField("apply_scope")
	private Integer applyScope;
	/**
	 * 跳转页面类型
	 */
	@TableField("jump_type")
	private Integer jumpType;
	/**
	 * 跳转类型
	 */
	private Integer type;
	/**
	 * H5外链页URL
	 */
	@TableField("jump_page_url")
	private String jumpPageUrl;
    /**
     * 场景描述
     */
	private String description;
    /**
     * 状态 1：启用，0：停用
     */
	private Integer status;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getApplyScope() {
		return applyScope;
	}

	public void setApplyScope(Integer applyScope) {
		this.applyScope = applyScope;
	}

	public Integer getJumpType() {
		return jumpType;
	}

	public void setJumpType(Integer jumpType) {
		this.jumpType = jumpType;
	}

	public String getJumpPageUrl() {
		return jumpPageUrl;
	}

	public void setJumpPageUrl(String jumpPageUrl) {
		this.jumpPageUrl = jumpPageUrl;
	}

	@Override
	public String toString() {
		return "YestaeQrcodeScene{" +
			"id=" + id +
			", name=" + name +
			", type=" + type +
			", description=" + description +
			", status=" + status +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
