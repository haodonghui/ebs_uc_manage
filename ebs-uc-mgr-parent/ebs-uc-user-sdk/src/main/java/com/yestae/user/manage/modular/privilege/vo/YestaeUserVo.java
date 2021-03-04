package com.yestae.user.manage.modular.privilege.vo;

import java.io.Serializable;

public class YestaeUserVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 537168222655229162L;
	
	/**
     * 主键ID
     */
	private String id;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 茶道师级别 1：初阶茶道师 2：二阶茶道师 3：三阶茶道师 4：四阶茶道师
	 */
	private Integer teaTeacherLevel;
    /**
     * 类型 1：普卡 2：金卡 3：钻卡
     */
	private Integer type;
    /**
     * 用户ID
     */
	private String userId;
    /**
     * 登录名
     */
	private String mobile;
	/**
	 * 备注
	 */
	private String remark;
    /**
     * 昵称
     */
	private String name;
    /**
     * 注册用户真实姓名
     */
	private String realName;
	/**
	 * 实体卡号
	 */
	private String cardNo;
    /**
     * 注册用户生日
     */
	private String birthday;
    /**
     * 性别 1：男，2：女，0：保密
     */
	private Integer gender;
	/**
     * 来源 0:未知,1:,2:益友会APP,3:益友会WEB,4:总裁茶室APP,5:益购APP,6:益购WEB,9:益购微信小程序
     */
	private Integer source;
    /**
     * 升级金卡时间
     */
	private String upgradeTime;
	/**
	 * 用户默认地址
	 */
	private String yestaeUserAddress;
	/**
     * 创建时间
     */
	private String createTime;
	/**
     * 用户状态 1：启用，0：停用
     */
	private Integer status;
    /**
     * 操作人ID
     */
	private String operatorId;
    /**
     * 操作人名称
     */
	private String operatorName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTeaTeacherLevel() {
		return teaTeacherLevel;
	}
	public void setTeaTeacherLevel(Integer teaTeacherLevel) {
		this.teaTeacherLevel = teaTeacherLevel;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getUpgradeTime() {
		return upgradeTime;
	}
	public void setUpgradeTime(String upgradeTime) {
		this.upgradeTime = upgradeTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getYestaeUserAddress() {
		return yestaeUserAddress;
	}
	public void setYestaeUserAddress(String yestaeUserAddress) {
		this.yestaeUserAddress = yestaeUserAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}
