package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 增值服务图片
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@TableName("uc_m_vas_image")
public class VasImage extends Model<VasImage> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
	private String id;
    /**
     * 业务表id
     */
	@TableField("biz_id")
	private String bizId;
    /**
     * 业务类型：1-增值服务，2-权益
     */
	@TableField("biz_type")
	private Integer bizType;
    /**
     * 缩略图
     */
	private String thumb;
    /**
     * 大图路径
     */
	private String large;
    /**
     * 图大小
     */
	private Long size;
    /**
     * 图片比率：1-16:9，2-16:10，3-4:3，4-18:9，5-19.5:9
     */
	private Integer ratio;
    /**
     * 应用平台：1-app，2-小程序，3-PC
     */
	private Integer platform;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Long updateTime;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public Integer getBizType() {
		return bizType;
	}

	public void setBizType(Integer bizType) {
		this.bizType = bizType;
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
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
		return "VasImage{" +
			"id=" + id +
			", bizId=" + bizId +
			", bizType=" + bizType +
			", thumb=" + thumb +
			", large=" + large +
			", size=" + size +
			", ratio=" + ratio +
			", platform=" + platform +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
