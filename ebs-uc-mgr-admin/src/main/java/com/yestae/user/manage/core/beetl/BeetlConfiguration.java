package com.yestae.user.manage.core.beetl;

import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import com.yestae.user.common.util.ToolUtil;
import com.yestae.user.manage.core.util.KaptchaUtil;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {

        groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
        groupTemplate.registerFunctionPackage("tool", new ToolUtil());
        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());

    }

}
