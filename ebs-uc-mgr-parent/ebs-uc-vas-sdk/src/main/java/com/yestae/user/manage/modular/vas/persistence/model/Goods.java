package com.yestae.user.manage.modular.vas.persistence.model;

import java.io.Serializable;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author chenfeida
 * @since 2018-07-09
 */
@TableName("uc_m_vas_goods")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 商品表id
     */
	private String id;
    /**
     * 商品名称
     */
	@TableField("goods_name")
	private String goodsName;
    /**
     * 商品编码
     */
	@TableField("goods_no")
	private String goodsNo;
    /**
     * 商品规格
     */
	private String specification;
    /**
     * 商品售价
     */
	private BigDecimal price;
    /**
     * 门店id
     */
	@TableField("organiz_id")
	private String organizId;
    /**
     * 门店编码
     */
	@TableField("organiz_code")
	private String organizCode;
    /**
     * 排序
     */
	private Integer sort;
    /**
     * 热销商品：1-热销，2-非热销
     */
	@TableField("hot_flag")
	private Integer hotFlag;
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


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsNo() {
		return goodsNo;
	}

	public void setGoodsNo(String goodsNo) {
		this.goodsNo = goodsNo;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getHotFlag() {
		return hotFlag;
	}

	public void setHotFlag(Integer hotFlag) {
		this.hotFlag = hotFlag;
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

	@Override
	public String toString() {
		return "Goods{" +
			"id=" + id +
			", goodsName=" + goodsName +
			", goodsNo=" + goodsNo +
			", specification=" + specification +
			", price=" + price +
			", organizId=" + organizId +
			", organizCode=" + organizCode +
			", sort=" + sort +
			", hotFlag=" + hotFlag +
			", delFlag=" + delFlag +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
