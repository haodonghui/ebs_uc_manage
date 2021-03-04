package com.yestae.user.manage.utils;

import java.io.Serializable;

/**
 * 短信请求入参
 *
 * @Package com.yestae.user.account.request
 * @ClassName
 * @Author h_don
 * @Date 2020/3/16 10:43
 */

public class ValidCodeDto implements Serializable {
    private static final long serialVersionUID = -5711562792307083047L;
    private Integer bizType;
    private String code;
    private String mobile;
    private String platform;
    private Integer type;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 短信失效时间
     */
    private Integer expires;

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }
}
