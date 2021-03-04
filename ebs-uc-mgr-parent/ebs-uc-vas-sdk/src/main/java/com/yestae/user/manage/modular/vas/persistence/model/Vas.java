package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-10
 */
@TableName("uc_m_vas")
public class Vas extends Model<Vas> {

    private static final long serialVersionUID = 1L;

    /**
     * 增值服务表id
     */
	private String id;
    /**
     * 增值服务编码
     */
	@TableField("vas_code")
	private String vasCode;
    /**
     * 增值服务名称
     */
	@TableField("vas_name")
	private String vasName;
    /**
     * 机构id
     */
	@TableField("organiz_id")
	private String organizId;
    /**
     * 机构编码
     */
	@TableField("organiz_code")
	private String organizCode;
		
    /**
     * 增值服务简介
     */
	private String intro;
    /**
     * 增值服务售价
     */
	@TableField("sale_price")
	private BigDecimal salePrice;
    /**
     * 到期提醒
     */
	@TableField("remind_days")
	private Integer remindDays;
    /**
     * 增值服务有效期类型：1-永久，2-年，3-季度，4-月
     */
	@TableField("valid_type")
	private Integer validType;
    /**
     * 增值服务状态：1-未发布，2-启用，3-停用
     */
	private Integer status;
    /**
     * 删除标识：1-未删除，2-已删除
     */
	@TableField("del_flag")
	private Integer delFlag;
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

	@TableField(exist=false)
	private List<VasEquity> vasEquityList;
	
	/**
	 * 服务条款
	 */
	@TableField(exist=false)
	private String serviceTerms;
	
	/**
	 * 权益详情
	 */
	@TableField(exist=false)
	private String equityDetail;
	
	/**
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceId;
	
	/**
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceUrl;
	
	/**
	 * 常见问题
	 */
	@TableField(exist=false)
	private String faq;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Integer getRemindDays() {
		return remindDays;
	}

	public void setRemindDays(Integer remindDays) {
		this.remindDays = remindDays;
	}

	public Integer getValidType() {
		return validType;
	}

	public void setValidType(Integer validType) {
		this.validType = validType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
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

	public List<VasEquity> getVasEquityList() {
		return vasEquityList;
	}

	public void setVasEquityList(List<VasEquity> vasEquityList) {
		this.vasEquityList = vasEquityList;
	}

	public String getServiceTerms() {
		return serviceTerms;
	}

	public void setServiceTerms(String serviceTerms) {
		this.serviceTerms = serviceTerms;
	}

	public String getEquityDetail() {
		return equityDetail;
	}

	public void setEquityDetail(String equityDetail) {
		this.equityDetail = equityDetail;
	}

	public String getFaq() {
		return faq;
	}

	public void setFaq(String faq) {
		this.faq = faq;
	}

	public String getSurfaceId() {
		return surfaceId;
	}

	public void setSurfaceId(String surfaceId) {
		this.surfaceId = surfaceId;
	}

	public String getSurfaceUrl() {
		return surfaceUrl;
	}

	public void setSurfaceUrl(String surfaceUrl) {
		this.surfaceUrl = surfaceUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Vas [id=" + id + ", vasCode=" + vasCode + ", vasName=" + vasName + ", organizId=" + organizId
				+ ", organizCode=" + organizCode + ", intro=" + intro + ", salePrice=" + salePrice + ", remindDays="
				+ remindDays + ", validType=" + validType + ", status=" + status + ", delFlag=" + delFlag
				+ ", createTime=" + createTime + ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy="
				+ updateBy + ", vasEquityList=" + vasEquityList + ", serviceTerms=" + serviceTerms + ", equityDetail="
				+ equityDetail + ", surfaceId=" + surfaceId + ", surfaceUrl=" + surfaceUrl + ", faq=" + faq + "]";
	}


	
}
