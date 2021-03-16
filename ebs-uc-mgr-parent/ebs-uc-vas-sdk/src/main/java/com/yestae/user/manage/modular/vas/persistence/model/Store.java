package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 门店
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@TableName("uc_m_vas_store")
public class Store extends Model<Store> {

    private static final long serialVersionUID = 1L;

    /**
     * 门店表id
     */
	private String id;
    /**
     * 门店名称
     */
	@TableField("store_name")
	private String storeName;
    /**
     * 门店编码
     */
	@TableField("store_code")
	private String storeCode;
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
     * 门店简介
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
     * 详细地址
     */
	private String address;
    /**
     * 省份编码
     */
	@TableField("province_code")
	private String provinceCode;
    /**
     * 省份名称
     */
	@TableField("province_name")
	private String provinceName;
    /**
     * 城市编码
     */
	@TableField("city_code")
	private String cityCode;
    /**
     * 城市名称
     */
	@TableField("city_name")
	private String cityName;
    /**
     * 地区编码
     */
	@TableField("area_code")
	private String areaCode;
    /**
     * 地区名称
     */
	@TableField("area_name")
	private String areaName;
    /**
     * 无线账号
     */
	@TableField("wifi_name")
	private String wifiName;
    /**
     * 无线密码
     */
	@TableField("wifi_password")
	private String wifiPassword;
    /**
     * 开始营业时间
     */
	@TableField("start_time")
	private Long startTime;
    /**
     * 结束营业时间
     */
	@TableField("end_time")
	private Long endTime;
    /**
     * 经度
     */
	private BigDecimal lon;
    /**
     * 纬度
     */
	private BigDecimal lat;
    /**
     * 推荐标识：1-推荐，2-不推荐
     */
	@TableField("recommend_flag")
	private Integer recommendFlag;
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
	 * 封面图
	 */
	@TableField(exist=false)
	private String surfaceId;
	
	/**
	 * 封面图url
	 */
	@TableField(exist=false)
	private String surfaceUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getWifiName() {
		return wifiName;
	}

	public void setWifiName(String wifiName) {
		this.wifiName = wifiName;
	}

	public String getWifiPassword() {
		return wifiPassword;
	}

	public void setWifiPassword(String wifiPassword) {
		this.wifiPassword = wifiPassword;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getLon() {
		return lon;
	}

	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}

	public BigDecimal getLat() {
		return lat;
	}

	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}

	public Integer getRecommendFlag() {
		return recommendFlag;
	}

	public void setRecommendFlag(Integer recommendFlag) {
		this.recommendFlag = recommendFlag;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
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
	public String toString() {
		return "Store [id=" + id + ", storeName=" + storeName + ", storeCode=" + storeCode + ", organizId=" + organizId
				+ ", organizCode=" + organizCode + ", intro=" + intro + ", tel=" + tel + ", linkman=" + linkman
				+ ", address=" + address + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName
				+ ", cityCode=" + cityCode + ", cityName=" + cityName + ", areaCode=" + areaCode + ", areaName="
				+ areaName + ", wifiName=" + wifiName + ", wifiPassword=" + wifiPassword + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", lon=" + lon + ", lat=" + lat + ", recommendFlag=" + recommendFlag
				+ ", status=" + status + ", delFlag=" + delFlag + ", createTime=" + createTime + ", createBy="
				+ createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", surfaceId=" + surfaceId
				+ ", surfaceUrl=" + surfaceUrl + "]";
	}

	
}
