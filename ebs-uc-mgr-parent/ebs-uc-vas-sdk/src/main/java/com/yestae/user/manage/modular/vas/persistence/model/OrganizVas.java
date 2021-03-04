package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

/**
 * <p>
 * 组织机构增值服务关联表
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
public class OrganizVas implements Serializable{

    private static final long serialVersionUID = 1L;

	/**
     * 机构id
     */
	private String organizId;
    /**
     * 机构编码
     */
	private String organizCode;
	/**
     * 机构名称
     */
	private String organizName;
	/**
     * 机构简称
     */
	private String organizSimpleName;
	/**
     * 增值服务id
     */
	private String vasId;
    /**
     * 增值服务编码
     */
	private String vasCode;
	/**
     * 增值服务名称
     */
	private String vasName;
	/**
     * 增值服务简介
     */
	private String intro;
	public String getOrganizId() {
		return organizId;
	}
	public void setOrganizId(String organizId) {
		this.organizId = organizId;
	}
	public String getOrganizCode() {
		return organizCode;
	}
	public void setOrganizCode(String organizCode) {
		this.organizCode = organizCode;
	}
	public String getOrganizName() {
		return organizName;
	}
	public void setOrganizName(String organizName) {
		this.organizName = organizName;
	}
	
	public String getOrganizSimpleName() {
		return organizSimpleName;
	}
	public void setOrganizSimpleName(String organizSimpleName) {
		this.organizSimpleName = organizSimpleName;
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
	public String getVasName() {
		return vasName;
	}
	public void setVasName(String vasName) {
		this.vasName = vasName;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	
}
