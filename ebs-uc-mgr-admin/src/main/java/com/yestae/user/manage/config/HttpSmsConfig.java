package com.yestae.user.manage.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * http短信平台配置
 *
 * @Package com.yestae.user.configuration
 * @ClassName
 * @Author h_don
 * @Date 2020/3/16 10:52
 */
@Component
@ConfigurationProperties(prefix = "smshttp")
public class HttpSmsConfig {
    private String validapiUrl;
    private String platform;

    public String getValidapiUrl() {
        return validapiUrl;
    }

    public void setValidapiUrl(String validapiUrl) {
        this.validapiUrl = validapiUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
