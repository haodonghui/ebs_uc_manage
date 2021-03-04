package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 增值服务权益关联表
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-04
 */
@TableName("uc_m_vas_vas_equity")
public class VasEquity extends Model<VasEquity> {

    private static final long serialVersionUID = 1L;

    /**
     * 增值服务权益关联表id
     */
	private String id;
    /**
     * 增值服务id
     */
	@TableField("vas_id")
	private String vasId;
    /**
     * 增值服务编码
     */
	@TableField("vas_code")
	private String vasCode;
    /**
     * 权益id
     */
	@TableField("equity_id")
	private String equityId;
    /**
     * 权益编码
     */
	@TableField("equity_code")
	private String equityCode;
    /**
     * 排序
     */
	private Integer sort;
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

	public String getVasId() {
		return vasId;
	}

	public void setVasId(String vasId) {
		this.vasId = vasId;
	}

	public String getVasCode() {
		return vasCode;
	}

	public void setVasCode(String vasCode) {
		this.vasCode = vasCode;
	}

	public String getEquityId() {
		return equityId;
	}

	public void setEquityId(String equityId) {
		this.equityId = equityId;
	}

	public String getEquityCode() {
		return equityCode;
	}

	public void setEquityCode(String equityCode) {
		this.equityCode = equityCode;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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
		return "VasEquity{" +
			"id=" + id +
			", vasId=" + vasId +
			", vasCode=" + vasCode +
			", equityId=" + equityId +
			", equityCode=" + equityCode +
			", sort=" + sort +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
