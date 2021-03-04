package com.yestae.user.manage.modular.privilege.controller;

import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserAddress;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserAddressService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户地址控制器
 *
 * @author fengshuonan
 * @Date 2017-11-09 20:02:15
 */
@Controller
@RequestMapping("/yestaeUserAddress")
public class YestaeUserAddressController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserAddress/";

    @Autowired
    private IYestaeUserAddressService yestaeUserAddressService;

    /**
     * 跳转到用户地址首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserAddress.html";
    }

    /**
     * 跳转到添加用户地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserAddress_add")
    public String yestaeUserAddressAdd() {
        return PREFIX + "yestaeUserAddress_add.html";
    }

    /**
     * 跳转到修改用户地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserAddress_update/{yestaeUserAddressId}")
    public String yestaeUserAddressUpdate(@PathVariable Integer yestaeUserAddressId, Model model) {
        YestaeUserAddress yestaeUserAddress = yestaeUserAddressService.selectById(yestaeUserAddressId);
        model.addAttribute("item",yestaeUserAddress);
        LogObjectHolder.me().set(yestaeUserAddress);
        return PREFIX + "yestaeUserAddress_edit.html";
    }

    /**
     * 获取用户地址列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return yestaeUserAddressService.selectList(null);
    }

    /**
     * 新增用户地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeUserAddress yestaeUserAddress) {
        yestaeUserAddressService.insert(yestaeUserAddress);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer yestaeUserAddressId) {
        yestaeUserAddressService.deleteById(yestaeUserAddressId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户地址
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeUserAddress yestaeUserAddress) {
        yestaeUserAddressService.updateById(yestaeUserAddress);
        return SUCCESS_TIP;
    }

    /**
     * 用户地址详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeUserAddressId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeUserAddressId") Integer yestaeUserAddressId) {
        return yestaeUserAddressService.selectById(yestaeUserAddressId);
    }
}
