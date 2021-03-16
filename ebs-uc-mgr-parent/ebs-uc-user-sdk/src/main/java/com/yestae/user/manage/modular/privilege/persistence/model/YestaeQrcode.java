package com.yestae.user.manage.modular.privilege.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 二维码表
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-05
 */
@TableName("uc_m_qrcode")
public class YestaeQrcode extends Model<YestaeQrcode> {

    private static final long serialVersionUID = 1L;

    /**
     * 二维码表ID
     */
	private String id;
	/**
	 * 二维码ID
	 */
	private String qrcodeId;
    /**
     * 推广人ID
     */
	@TableField("generalize_user_id")
	private String generalizeUserId;
    /**
     * 二维码场景名称
     */
	@TableField("scene_id")
	private String sceneId;
	/**
	 * 二维码业务标识
	 */
	@TableField("biz_id")
	private String bizId;
	/**
	 * 类型
	 */
	private Integer type;
	/**
	 * 跳转商品货号
	 */
	@TableField("goods_id")
	private String goodsId;
	/**
	 * 货号
	 */
	@TableField("product_no")
	private String productNo;
    /**
     * 备注
     */
	private String remark;
    /**
     * 二维码地址
     */
	@TableField("code_url")
	private String codeUrl;
    /**
     * 二维码包含信息
     */
	@TableField("code_info")
	private String codeInfo;
	/**
	 * 二维码包含信息
	 */
	@TableField("next_code_info")
	private String nextCodeInfo;
	/**
	 * 二维码场景名称
	 */
	@TableField("next_scene_id")
	private String nextSceneId;
	/**
	 * 二维码业务标识
	 */
	@TableField("next_biz_id")
	private String nextBizId;
    /**
     * 是否删除:1-正常 2-已删除
     */
	@TableField("if_del")
	private Integer ifDel;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Long createTime;
    /**
     * 创建人
     */
	@TableField("create_by")
	private String createBy;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Long updateTime;
    /**
     * 修改人
     */
	@TableField("update_by")
	private String updateBy;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGeneralizeUserId() {
		return generalizeUserId;
	}

	public void setGeneralizeUserId(String generalizeUserId) {
		this.generalizeUserId = generalizeUserId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(String prodId) {
		this.goodsId = prodId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getNextCodeInfo() {
		return nextCodeInfo;
	}

	public void setNextCodeInfo(String nextCodeInfo) {
		this.nextCodeInfo = nextCodeInfo;
	}

	public String getNextSceneId() {
		return nextSceneId;
	}

	public void setNextSceneId(String nextSceneId) {
		this.nextSceneId = nextSceneId;
	}

	public String getNextBizId() {
		return nextBizId;
	}

	public void setNextBizId(String nextBizId) {
		this.nextBizId = nextBizId;
	}

	public String getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(String qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

	@Override
	public String toString() {
		return "YestaeQrcode{" +
			"id=" + id +
			", generalizeUserId=" + generalizeUserId +
			", sceneId=" + sceneId +
			", remark=" + remark +
			", codeUrl=" + codeUrl +
			", codeInfo=" + codeInfo +
			", ifDel=" + ifDel +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
