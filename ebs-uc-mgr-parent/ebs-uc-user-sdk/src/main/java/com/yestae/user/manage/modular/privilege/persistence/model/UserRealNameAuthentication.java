package com.yestae.user.manage.modular.privilege.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 实名认证
 * </p>
 *
 * @author chenfeida
 * @since 2018-03-23
 */
@TableName("uc_user_real_name_authentication")
public class UserRealNameAuthentication extends Model<UserRealNameAuthentication> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private String id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 登录名
     */
	private String mobile;
    /**
     * 性别 1：男，2：女，0：保密
     */
	private String gender;
    /**
     * 昵称
     */
	private String name;
    /**
     * 真实姓名
     */
	@TableField("real_name")
	private String realName;
    /**
     * 生肖：1：鼠，2：牛，3、虎，4：兔，5：龙，6：蛇,7:马，8:羊，9:猴，10:鸡，11:狗，12:猪
     */
	private String born;
    /**
     * 手机号照片
     */
	@TableField("mobile_pic")
	private String mobilePic;
    /**
     * 证件号1
     */
	@TableField("id1_no")
	private String id1No;
    /**
     * 证件类型1
     */
	@TableField("id1_type")
	private String id1Type;
    /**
     * 证件1正面照片
     */
	@TableField("id1_front_img")
	private String id1FrontImg;
    /**
     * 证件1反面照片
     */
	@TableField("id1_back_img")
	private String id1BackImg;
    /**
     * 证件号2
     */
	@TableField("id2_no")
	private String id2No;
    /**
     * 证件类型2
     */
	@TableField("id2_type")
	private String id2Type;
    /**
     * 证件2反面照片
     */
	@TableField("id2_front_img")
	private String id2FrontImg;
    /**
     * 证件2反面照片
     */
	@TableField("id2_back_img")
	private String id2BackImg;
    /**
     * 证件号3
     */
	@TableField("id3_no")
	private String id3No;
    /**
     * 证件类型3
     */
	@TableField("id3_type")
	private String id3Type;
    /**
     * 证件3反面照片
     */
	@TableField("id3_front_img")
	private String id3FrontImg;
    /**
     * 证件3反面照片
     */
	@TableField("id3_back_img")
	private String id3BackImg;
    /**
     * 第三方验证：0：失败，1：成功
     */
	@TableField("third_party_veryify")
	private Integer thirdPartyVeryify;
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
     * 审核人ID
     */
	@TableField("verify_id")
	private String verifyId;
    /**
     * 审核人姓名
     */
	@TableField("verify_name")
	private String verifyName;
    /**
     * 审核备注
     */
	@TableField("verify_desc")
	private String verifyDesc;
	/**
	 * 银行卡号
	 */
	@TableField("bank_card_no")
	private String bankCardNo;
	/**
	 * 数据来源
	 */
	@TableField("data_source")
	private String dataSource;
    /**
     * 审核时间
     */
	@TableField("verify_time")
	private Long verifyTime;
    /**
     * 0：未提交，1：等待审核，2：审核通过，3：审核失败,4:其他
     */
	private String verify;
    /**
     * 0：真，1：假
     */
	private String flag;


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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getMobilePic() {
		return mobilePic;
	}

	public void setMobilePic(String mobilePic) {
		this.mobilePic = mobilePic;
	}

	public String getId1No() {
		return id1No;
	}

	public void setId1No(String id1No) {
		this.id1No = id1No;
	}

	public String getId1Type() {
		return id1Type;
	}

	public void setId1Type(String id1Type) {
		this.id1Type = id1Type;
	}

	public String getId1FrontImg() {
		return id1FrontImg;
	}

	public void setId1FrontImg(String id1FrontImg) {
		this.id1FrontImg = id1FrontImg;
	}

	public String getId1BackImg() {
		return id1BackImg;
	}

	public void setId1BackImg(String id1BackImg) {
		this.id1BackImg = id1BackImg;
	}

	public String getId2No() {
		return id2No;
	}

	public void setId2No(String id2No) {
		this.id2No = id2No;
	}

	public String getId2Type() {
		return id2Type;
	}

	public void setId2Type(String id2Type) {
		this.id2Type = id2Type;
	}

	public String getId2FrontImg() {
		return id2FrontImg;
	}

	public void setId2FrontImg(String id2FrontImg) {
		this.id2FrontImg = id2FrontImg;
	}

	public String getId2BackImg() {
		return id2BackImg;
	}

	public void setId2BackImg(String id2BackImg) {
		this.id2BackImg = id2BackImg;
	}

	public String getId3No() {
		return id3No;
	}

	public void setId3No(String id3No) {
		this.id3No = id3No;
	}

	public String getId3Type() {
		return id3Type;
	}

	public void setId3Type(String id3Type) {
		this.id3Type = id3Type;
	}

	public String getId3FrontImg() {
		return id3FrontImg;
	}

	public void setId3FrontImg(String id3FrontImg) {
		this.id3FrontImg = id3FrontImg;
	}

	public String getId3BackImg() {
		return id3BackImg;
	}

	public void setId3BackImg(String id3BackImg) {
		this.id3BackImg = id3BackImg;
	}

	public Integer getThirdPartyVeryify() {
		return thirdPartyVeryify;
	}

	public void setThirdPartyVeryify(Integer thirdPartyVeryify) {
		this.thirdPartyVeryify = thirdPartyVeryify;
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

	public String getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	public String getVerifyName() {
		return verifyName;
	}

	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}

	public String getVerifyDesc() {
		return verifyDesc;
	}

	public void setVerifyDesc(String verifyDesc) {
		this.verifyDesc = verifyDesc;
	}

	public Long getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Long verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserRealNameAuthentication{" +
			"id=" + id +
			", userId=" + userId +
			", mobile=" + mobile +
			", gender=" + gender +
			", name=" + name +
			", realName=" + realName +
			", born=" + born +
			", mobilePic=" + mobilePic +
			", id1No=" + id1No +
			", id1Type=" + id1Type +
			", id1FrontImg=" + id1FrontImg +
			", id1BackImg=" + id1BackImg +
			", id2No=" + id2No +
			", id2Type=" + id2Type +
			", id2FrontImg=" + id2FrontImg +
			", id2BackImg=" + id2BackImg +
			", id3No=" + id3No +
			", id3Type=" + id3Type +
			", id3FrontImg=" + id3FrontImg +
			", id3BackImg=" + id3BackImg +
			", thirdPartyVeryify=" + thirdPartyVeryify +
			", bankCardNo=" + bankCardNo +
			", dataSource=" + dataSource +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", verifyId=" + verifyId +
			", verifyName=" + verifyName +
			", verifyDesc=" + verifyDesc +
			", verifyTime=" + verifyTime +
			", verify=" + verify +
			", flag=" + flag +
			"}";
	}
}
