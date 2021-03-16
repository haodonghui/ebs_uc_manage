package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.privilege.common.enums.YestaeCityStateEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeCity;
import com.yestae.user.manage.modular.privilege.service.IYestaeCityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 地址控制器
 *
 * @author fengshuonan
 * @Date 2017-11-12 17:24:30
 */
@Controller
@RequestMapping("/yestaeCity")
public class YestaeCityController extends BaseController {

    private String PREFIX = "/privilege/yestaeCity/";

    @Autowired
    private IYestaeCityService yestaeCityService;

    /**
     * 跳转到地址首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeCity.html";
    }

    /**
     * 跳转到添加地址
     */
    @RequestMapping("/yestaeCity_add")
    public String yestaeCityAdd() {
        return PREFIX + "yestaeCity_add.html";
    }

    /**
     * 跳转到修改地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeCity_update/{yestaeCityId}")
    public String yestaeCityUpdate(@PathVariable Integer yestaeCityId, Model model) {
        YestaeCity yestaeCity = yestaeCityService.getById(yestaeCityId);
        model.addAttribute("item",yestaeCity);
        LogObjectHolder.me().set(yestaeCity);
        return PREFIX + "yestaeCity_edit.html";
    }

    /**
     * 获取地址列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	QueryWrapper<YestaeCity> wrapper = new QueryWrapper<>();
    	wrapper.eq("state", YestaeCityStateEnum.YES.getCode() + "");
    	String pid = getHttpServletRequest().getParameter("pid");
    	if(StringUtils.isNotEmpty(pid)){
    		wrapper.eq("pid", Integer.parseInt(pid));
    	}
        return yestaeCityService.list(wrapper);
    }

    /**
     * 新增地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeCity yestaeCity) {
        yestaeCityService.save(yestaeCity);
        return SUCCESS_TIP;
    }

    /**
     * 删除地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer yestaeCityId) {
        yestaeCityService.removeById(yestaeCityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeCity yestaeCity) {
        yestaeCityService.updateById(yestaeCity);
        return SUCCESS_TIP;
    }

    /**
     * 地址详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeCityId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeCityId") Integer yestaeCityId) {
        return yestaeCityService.getById(yestaeCityId);
    }
}
