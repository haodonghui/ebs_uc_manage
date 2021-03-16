package com.yestae.user.manage.modular.privilege.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.yestae.user.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2018-05-24
 */
@TableName("uc_m_ibeacon_connect_log")
public class IbeaconConnectLog extends Model<IbeaconConnectLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 茶博会访问日志表id
     */
	private String id;
    /**
     * 设备名、Mac地址
     */
	private String mac;
    /**
     * 访问时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * ibeacon设备uuid
     */
	private String uuid;
    /**
     * 1-有效 2-无效
     */
	private Integer status;

	@TableField(exist=false)
	private String createTimeStr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getCreateTimeStr() {
		if(StringUtils.isEmpty(createTimeStr)){
			setCreateTimeStr(DateUtils.toDatetimeString(createTime));
		}
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	@Override
	public String toString() {
		return "IbeaconConnectLog{" +
			"id=" + id +
			", mac=" + mac +
			", createTime=" + createTime +
			", uuid=" + uuid +
			", status=" + status +
			"}";
	}
}
