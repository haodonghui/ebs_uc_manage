package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 用户头像
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-17
 */
@TableName("uc_user_image")
public class YestaeUserImage extends Model<YestaeUserImage> {

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
     * 缩略图路径
     */
	private String thumb;
    /**
     * 大图路径
     */
	private String large;
    /**
     * 图大小
     */
	private Integer size;
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

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getLarge() {
		return large;
	}

	public void setLarge(String large) {
		this.large = large;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
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
		return "YestaeUserImage{" +
			"id=" + id +
			", userId=" + userId +
			", thumb=" + thumb +
			", large=" + large +
			", size=" + size +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
