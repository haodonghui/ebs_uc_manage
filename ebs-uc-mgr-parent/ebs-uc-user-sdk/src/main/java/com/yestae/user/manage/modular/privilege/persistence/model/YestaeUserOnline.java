package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-07
 */
@TableName("uc_user_online")
public class YestaeUserOnline extends Model<YestaeUserOnline> {

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
     * 真实IP地址
     */
	@TableField("real_ip")
	private String realIp;
    /**
     * 设备ID
     */
	@TableField("device_id")
	private String deviceId;
    /**
     * 0:未知,1:IOS,2:andriod,3:pc
     */
	private Integer platform;
    /**
     * 最后一次上线时间
     */
	@TableField("online_time")
	private Long onlineTime;
	private String token;
    /**
     * 0:未知,1:益友会APP,2:益友会微信公众号,3:益友会WEB,4:总裁茶室APP,5:益购APP,6:益购WEB,7:益购微信小程序
     */
	private Integer source;
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

	public String getRealIp() {
		return realIp;
	}

	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Long getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Long onlineTime) {
		this.onlineTime = onlineTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
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
		return "YestaeUserOnline{" +
			"id=" + id +
			", userId=" + userId +
			", realIp=" + realIp +
			", deviceId=" + deviceId +
			", platform=" + platform +
			", onlineTime=" + onlineTime +
			", token=" + token +
			", source=" + source +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
