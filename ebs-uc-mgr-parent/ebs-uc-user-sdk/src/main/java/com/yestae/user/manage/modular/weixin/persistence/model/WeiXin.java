package com.yestae.user.manage.modular.weixin.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("uc_m_weixin")
public class WeiXin extends Model<WeiXin>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3949772551993375713L;

	/**
     * 主键ID
     */
	private String id;
    /**
     * 微信标识, 唯一键
     */
	private String code;
    /**
     * 微信名称
     */
	private String name;
    /**
     * 应用ID
     */
	private String appid;
    /**
     * 应用密钥
     */
	private String secret;
    /**
     * 服务器地址
     */
	private String url;
    /**
     * 令牌
     */
	private String token;
    /**
     * access令牌
     */
	@TableField("access_token")
	private String accessToken;
    /**
     * access令牌有效期
     */
	@TableField("access_token_expires_in")
	private Long accessTokenExpiresIn;
    /**
     * access令牌获得时间
     */
	@TableField("access_token_time")
	private Long accessTokenTime;
    /**
     * jsapi_ticket
     */
	@TableField("jsapi_ticket")
	private String jsapiTicket;
    /**
     * jsapi_ticket有效期
     */
	@TableField("jsapi_ticket_expires_in")
	private Long jsapiTicketExpiresIn;
    /**
     * jsapi_ticket获得时间
     */
	@TableField("jsapi_ticket_time")
	private Long jsapiTicketTime;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getAccessTokenExpiresIn() {
		return accessTokenExpiresIn;
	}

	public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
		this.accessTokenExpiresIn = accessTokenExpiresIn;
	}

	public Long getAccessTokenTime() {
		return accessTokenTime;
	}

	public void setAccessTokenTime(Long accessTokenTime) {
		this.accessTokenTime = accessTokenTime;
	}

	public String getJsapiTicket() {
		return jsapiTicket;
	}

	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}

	public Long getJsapiTicketExpiresIn() {
		return jsapiTicketExpiresIn;
	}

	public void setJsapiTicketExpiresIn(Long jsapiTicketExpiresIn) {
		this.jsapiTicketExpiresIn = jsapiTicketExpiresIn;
	}

	public Long getJsapiTicketTime() {
		return jsapiTicketTime;
	}

	public void setJsapiTicketTime(Long jsapiTicketTime) {
		this.jsapiTicketTime = jsapiTicketTime;
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
		return "WeiXin{" +
			"id=" + id +
			", code=" + code +
			", name=" + name +
			", appid=" + appid +
			", secret=" + secret +
			", url=" + url +
			", token=" + token +
			", accessToken=" + accessToken +
			", accessTokenExpiresIn=" + accessTokenExpiresIn +
			", accessTokenTime=" + accessTokenTime +
			", jsapiTicket=" + jsapiTicket +
			", jsapiTicketExpiresIn=" + jsapiTicketExpiresIn +
			", jsapiTicketTime=" + jsapiTicketTime +
			", createTime=" + createTime +
			", createBy=" + createBy +
			", updateTime=" + updateTime +
			", updateBy=" + updateBy +
			"}";
	}
}
