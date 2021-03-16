package com.yestae.user.manage.modular.vas.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuqi
 * @since 2020-08-03
 */
@TableName("uc_user_invoice")
public class UserInvoice extends Model<UserInvoice> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 发票类型
     */
	@TableField("invoice_type")
	private Integer invoiceType;
    /**
     * 发票抬头
     */
	@TableField("invoice_title")
	private String invoiceTitle;
    /**
     * 个人名称
     */
	@TableField("personal_name")
	private String personalName;
    /**
     * 单位名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 纳税人识别号
     */
	@TableField("taxpayer_identification_number")
	private String taxpayerIdentificationNumber;
    /**
     * 收票人手机号
     */
	@TableField("taker_phone")
	private String takerPhone;
    /**
     * 收票人Email
     */
	@TableField("taker_email")
	private String takerEmail;
    /**
     * 公司注册地址
     */
	@TableField("company_registered_address")
	private String companyRegisteredAddress;
    /**
     * 公司注册电话
     */
	@TableField("company_registered_phone")
	private String companyRegisteredPhone;
    /**
     * 开户银行
     */
	@TableField("deposit_bank")
	private String depositBank;
    /**
     * 银行账号
     */
	@TableField("bank_account")
	private String bankAccount;
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

	/**
	 * 昵称
	 */
	private String name;
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 账号
	 */
	private String accountNo;

	/**
	 * 审核状态
	 */
	private String verifyState;

	/**
	 * 审核人
	 */
	private String verifyBy;

	/**
	 * 审核原因
	 */
	private String remark;

	/**
	 * 创建时间格式化
	 */
	private String createTimeStr;
	/**
	 * 更新时间格式化
	 */
	private String updateTimeStr;

	/**
	 * 用户Id字符串表示
	 */
	private String userIdStr;

	/**
	 * 增票有效资质数量
	 */
	private Integer validQuantity;

	/**
	 * 发票授权委托书图片
	 */
	private String thumb;
	/**
	 * 发票授权委托书图片
	 */
	private String large;

	/**
	 * 实名认证标识
	 */
	private Integer realNameFlag;

	/**
	 * 实名状态标识名称
	 */
	private String realNameFlagName;

	/**
	 * 用户账号-手机号
	 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealNameFlagName() {
		return realNameFlagName;
	}

	public void setRealNameFlagName(String realNameFlagName) {
		this.realNameFlagName = realNameFlagName;
	}

	public Integer getRealNameFlag() {
		return realNameFlag;
	}

	public void setRealNameFlag(Integer realNameFlag) {
		this.realNameFlag = realNameFlag;
	}

	/**
	 * 审核状态名称
	 */
	private String verifyStateName;

	public String getVerifyStateName() {
		return verifyStateName;
	}

	public void setVerifyStateName(String verifyStateName) {
		this.verifyStateName = verifyStateName;
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

	public Integer getValidQuantity() {
		return validQuantity;
	}

	public void setValidQuantity(Integer validQuantity) {
		this.validQuantity = validQuantity;
	}

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

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceTitle() {
		return invoiceTitle;
	}

	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}

	public String getPersonalName() {
		return personalName;
	}

	public void setPersonalName(String personalName) {
		this.personalName = personalName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxpayerIdentificationNumber() {
		return taxpayerIdentificationNumber;
	}

	public void setTaxpayerIdentificationNumber(String taxpayerIdentificationNumber) {
		this.taxpayerIdentificationNumber = taxpayerIdentificationNumber;
	}

	public String getTakerPhone() {
		return takerPhone;
	}

	public void setTakerPhone(String takerPhone) {
		this.takerPhone = takerPhone;
	}

	public String getTakerEmail() {
		return takerEmail;
	}

	public void setTakerEmail(String takerEmail) {
		this.takerEmail = takerEmail;
	}

	public String getCompanyRegisteredAddress() {
		return companyRegisteredAddress;
	}

	public void setCompanyRegisteredAddress(String companyRegisteredAddress) {
		this.companyRegisteredAddress = companyRegisteredAddress;
	}

	public String getCompanyRegisteredPhone() {
		return companyRegisteredPhone;
	}

	public void setCompanyRegisteredPhone(String companyRegisteredPhone) {
		this.companyRegisteredPhone = companyRegisteredPhone;
	}

	public String getDepositBank() {
		return depositBank;
	}

	public void setDepositBank(String depositBank) {
		this.depositBank = depositBank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public String getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getUserIdStr() {
		return userIdStr;
	}

	public void setUserIdStr(String userIdStr) {
		this.userIdStr = userIdStr;
	}

	@Override
	public String toString() {
		return "UserInvoice{" +
			"id=" + id +
			", userId=" + userId +
			", invoiceType=" + invoiceType +
			", invoiceTitle=" + invoiceTitle +
			", personalName=" + personalName +
			", companyName=" + companyName +
			", taxpayerIdentificationNumber=" + taxpayerIdentificationNumber +
			", takerPhone=" + takerPhone +
			", takerEmail=" + takerEmail +
			", companyRegisteredAddress=" + companyRegisteredAddress +
			", companyRegisteredPhone=" + companyRegisteredPhone +
			", depositBank=" + depositBank +
			", bankAccount=" + bankAccount +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
