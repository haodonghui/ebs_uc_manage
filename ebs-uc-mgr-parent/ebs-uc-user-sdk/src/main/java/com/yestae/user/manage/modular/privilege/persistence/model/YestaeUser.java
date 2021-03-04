package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2017-11-03
 */
@TableName("uc_user")
public class YestaeUser extends Model<YestaeUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private String id;
	/**
	 * 茶道师级别 1：初阶茶道师 2：二阶茶道师 3：三阶茶道师 4：四阶茶道师
	 */
	@TableField("tea_teacher_level")
	private Integer teaTeacherLevel;
    /**
     * 类型 1：普卡 2：金卡 3：钻卡
     */
	private Integer type;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private String userId;
    /**
     * 登录名
     */
	private String mobile;
    /**
     * 登录密码
     */
	private String password;
    /**
     * 支付密码
     */
	@TableField("pay_password")
	private String payPassword;
    /**
     * 加密因子
     */
	private String salt;
    /**
     * 昵称
     */
	private String name;
    /**
     * 注册用户真实姓名
     */
	@TableField("real_name")
	private String realName;
    /**
     * 所属平台 0：UNKNOWN，1：IOS，2：ANDRIOD，3：PC
     */
	private Integer platform;
    /**
     * 来源 0:未知,1:,2:益友会APP,3:益友会WEB,4:总裁茶室APP,5:益购APP,6:益购WEB,9:益购微信小程序
     */
	private Integer source;
	/**
	 * 实体卡号
	 */
	@TableField("card_no")
	private String cardNo;
    /**
     * 用户编号
     */
	@TableField("user_num")
	private Integer userNum;
    /**
     * 真实IP地址
     */
	@TableField("real_ip")
	private String realIp;
    /**
     * 注册用户生日
     */
	private Long birthday;
    /**
     * 性别 1：男，2：女，0：保密
     */
	private Integer gender;
    /**
     * 升级金卡时间
     */
	@TableField("upgrade_time")
	private Long upgradeTime;
    /**
     * 用户状态 1：启用，0：停用
     */
	private Integer status;
    /**
     * 删除标识 1-正常 2-已删除
     */
	@TableField("if_del")
	private Integer ifDel;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public String getRealIp() {
		return realIp;
	}

	public void setRealIp(String realIp) {
		this.realIp = realIp;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Long getUpgradeTime() {
		return upgradeTime;
	}

	public void setUpgradeTime(Long upgradeTime) {
		this.upgradeTime = upgradeTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getTeaTeacherLevel() {
		return teaTeacherLevel;
	}

	public void setTeaTeacherLevel(Integer teaTeacherLevel) {
		this.teaTeacherLevel = teaTeacherLevel;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	@Override
	public String toString() {
		return "YestaeUser{" +
			"id=" + id +
			", teaTeacherLevel=" + teaTeacherLevel +
			", type=" + type +
			", userId=" + userId +
			", mobile=" + mobile +
			", password=" + password +
			", payPassword=" + payPassword +
			", salt=" + salt +
			", name=" + name +
			", realName=" + realName +
			", platform=" + platform +
			", source=" + source +
			", cardNo=" + cardNo +
			", userNum=" + userNum +
			", realIp=" + realIp +
			", birthday=" + birthday +
			", gender=" + gender +
			", upgradeTime=" + upgradeTime +
			", status=" + status +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
