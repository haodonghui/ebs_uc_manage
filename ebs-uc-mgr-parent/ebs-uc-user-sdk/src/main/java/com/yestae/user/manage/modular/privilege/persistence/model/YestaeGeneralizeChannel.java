package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 推广渠道表
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-28
 */
@TableName("uc_m_generalize_channel")
public class YestaeGeneralizeChannel extends Model<YestaeGeneralizeChannel> {

    private static final long serialVersionUID = 1L;

    /**
     * 推广渠道表ID
     */
	private String id;
    /**
     * 推广渠道编码
     */
	@TableField("channel_code")
	private String channelCode;
    /**
     * 渠道名称
     */
	private String name;
    /**
     * 上级渠道ID
     */
	private String pid;
    /**
     * 是否末级 1：否 2：是
     */
	@TableField("if_end")
	private Integer ifEnd;
    /**
     * 是否删除 1：否 2：是
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

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getIfEnd() {
		return ifEnd;
	}

	public void setIfEnd(Integer ifEnd) {
		this.ifEnd = ifEnd;
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
		return "YestaeGeneralizeChannel{" +
			"id=" + id +
			", channelCode=" + channelCode +
			", name=" + name +
			", pid=" + pid +
			", ifEnd=" + ifEnd +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
