package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户购买增值服务记录表
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-25
 */
@TableName("uc_vas_record")
public class Record extends Model<Record> {

    private static final long serialVersionUID = 1L;

	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 增值服务ID
     */
	@TableField("added_service_id")
	private String addedServiceId;
    /**
     * 增值服务有效开始时间
     */
	@TableField("begin_time")
	private Long beginTime;
    /**
     * 增值服务有效结束时间
     */
	@TableField("end_time")
	private Long endTime;
    /**
     * 增值服务是否停用，1：停用，0：启用
     */
	private String stop;
    /**
     * 增值服务是否有效,0:有效，1：无效
     */
	private String invalid;
    /**
     * 记录创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 记录更新时间
     */
	@TableField("update_time")
	private Long updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAddedServiceId() {
		return addedServiceId;
	}

	public void setAddedServiceId(String addedServiceId) {
		this.addedServiceId = addedServiceId;
	}

	public Long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

	public String getInvalid() {
		return invalid;
	}

	public void setInvalid(String invalid) {
		this.invalid = invalid;
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
		return "Record{" +
			"id=" + id +
			", userId=" + userId +
			", addedServiceId=" + addedServiceId +
			", beginTime=" + beginTime +
			", endTime=" + endTime +
			", stop=" + stop +
			", invalid=" + invalid +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
