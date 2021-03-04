package com.yestae.user.manage.modular.privilege.vo;

import java.io.Serializable;

public class YestaeQrcodeSceneVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4796204510458150608L;

	/**
     * 二维码场景表ID
     */
	private String id;
    /**
     * 场景名称
     */
	private String name;
    /**
     * 跳转类型
     */
	private Integer type;
	/**
	 * 跳转类型
	 */
	private String typeName;
    /**
     * 场景描述
     */
	private String description;
    /**
     * 状态 1：启用，0：停用
     */
	private Integer status;
	/**
     * 状态 1：启用，0：停用
     */
	private String statusName;
    /**
     * 创建时间
     */
	private String createTime;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
	
	
}
