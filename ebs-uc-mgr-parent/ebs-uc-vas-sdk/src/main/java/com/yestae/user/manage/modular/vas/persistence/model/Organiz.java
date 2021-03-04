package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@TableName("uc_m_vas_organiz")
public class Organiz extends Model<Organiz> {

    private static final long serialVersionUID = 1L;

    /**
     * 组织机构表id
     */
	private String id;
    /**
     * 机构名称
     */
	@TableField("organiz_name")
	private String organizName;
    /**
     * 机构编码
     */
	@TableField("organiz_code")
	private String organizCode;
	/**
     * 机构简称
     */
	@TableField("simple_name")
	private String simpleName;
    /**
     * 接口调用凭证
     */
	@TableField("access_token")
	private String accessToken;
    /**
     * AccessKeyID
     */
	@TableField("access_key_id")
	private String accessKeyId;
    /**
     * AccessKeySecret
     */
	@TableField("access_key_secret")
	private String accessKeySecret;
    /**
     * 机构简介
     */
	private String intro;
    /**
     * 联系方式
     */
	private String tel;
    /**
     * 联系人
     */
	private String linkman;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 传真
     */
	private String fax;
    /**
     * 详细地址
     */
	private String address;
	/**
	 * 商品分类
	 */
	private String category;
    /**
     * 状态：1-正常，2-停用
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

	/**
	 * 普通logo
	 */
	@TableField(exist=false)
	private String organizLogoInvalid;
	/**
	 * logo
	 */
	@TableField(exist=false)
	private String logo;
	/**
	 * 彩色logo
	 */
	@TableField(exist=false)
	private String organizLogo;
	
	/**
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceId;
	/**
	 * 普通logo
	 */
	@TableField(exist=false)
	private String organizLogoInvalidUrl;
	/**
	 * 彩色logo
	 */
	@TableField(exist=false)
	private String organizLogoUrl;
	/**
	 * logo
	 */
	@TableField(exist=false)
	private String logoUrl;
	
	/**
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceUrl;
	
	/**
	 * 机构故事
	 */
	@TableField(exist=false)
	private String content;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrganizName() {
		return organizName;
	}

	public void setOrganizName(String organizName) {
		this.organizName = organizName;
	}

	public String getOrganizCode() {
		return organizCode;
	}

	public void setOrganizCode(String organizCode) {
		this.organizCode = organizCode;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getSimpleName() {
		return simpleName;
	}

	public void setSimpleName(String simpleName) {
		this.simpleName = simpleName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getOrganizLogoInvalid() {
		return organizLogoInvalid;
	}

	public void setOrganizLogoInvalid(String organizLogoInvalid) {
		this.organizLogoInvalid = organizLogoInvalid;
	}

	public String getOrganizLogo() {
		return organizLogo;
	}

	public void setOrganizLogo(String organizLogo) {
		this.organizLogo = organizLogo;
	}

	public String getSurfaceId() {
		return surfaceId;
	}

	public void setSurfaceId(String surfaceId) {
		this.surfaceId = surfaceId;
	}

	public String getOrganizLogoInvalidUrl() {
		return organizLogoInvalidUrl;
	}

	public void setOrganizLogoInvalidUrl(String organizLogoInvalidUrl) {
		this.organizLogoInvalidUrl = organizLogoInvalidUrl;
	}

	public String getOrganizLogoUrl() {
		return organizLogoUrl;
	}

	public void setOrganizLogoUrl(String organizLogoUrl) {
		this.organizLogoUrl = organizLogoUrl;
	}

	public String getSurfaceUrl() {
		return surfaceUrl;
	}

	public void setSurfaceUrl(String surfaceUrl) {
		this.surfaceUrl = surfaceUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	@Override
	public String toString() {
		return "Organiz{" +
			"id=" + id +
			", organizName=" + organizName +
			", organizCode=" + organizCode +
			", simpleName=" + simpleName +
			", accessToken=" + accessToken +
			", accessKeyId=" + accessKeyId +
			", accessKeySecret=" + accessKeySecret +
			", intro=" + intro +
			", tel=" + tel +
			", linkman=" + linkman +
			", email=" + email +
			", fax=" + fax +
			", address=" + address +
			", status=" + status +
			", delFlag=" + delFlag +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
