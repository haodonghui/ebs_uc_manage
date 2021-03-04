package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 城市码表
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-12
 */
@TableName("uc_m_city")
public class YestaeCity extends Model<YestaeCity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private String id;
    /**
     * 城市代码ID
     */
	@TableField("city_code_id")
	private Integer cityCodeId;
    /**
     * 城市名
     */
	private String name;
    /**
     * 父级ID
     */
	private Integer pid;
    /**
     * 创建人
     */
	@TableField("create_user")
	private String createUser;
    /**
     * 修改人
     */
	@TableField("update_user")
	private String updateUser;
    /**
     * 新建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Long updateTime;
    /**
     * 状态;0:无效;1:有效
     */
	private String state;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCityCodeId() {
		return cityCodeId;
	}

	public void setCityCodeId(Integer cityCodeId) {
		this.cityCodeId = cityCodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "YestaeCity{" +
			"id=" + id +
			", cityCodeId=" + cityCodeId +
			", name=" + name +
			", pid=" + pid +
			", createUser=" + createUser +
			", updateUser=" + updateUser +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			"}";
	}
}
