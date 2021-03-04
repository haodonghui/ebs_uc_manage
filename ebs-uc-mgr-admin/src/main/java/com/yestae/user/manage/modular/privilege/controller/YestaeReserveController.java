package com.yestae.user.manage.modular.privilege.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.common.annotion.BussinessLog;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeReserve;
import com.yestae.user.manage.modular.privilege.service.IYestaeReserveService;

/**
 * 保留词控制器
 *
 * @author fengshuonan
 * @Date 2018-01-03 14:46:30
 */
@Controller
@RequestMapping("/yestaeReserve")
public class YestaeReserveController extends BaseController {

    private String PREFIX = "/privilege/yestaeReserve/";

    @Autowired
    private IYestaeReserveService yestaeReserveService;

    /**
     * 跳转到保留词首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeReserve.html";
    }

    /**
     * 跳转到添加保留词
     */
    @RequestMapping("/yestaeReserve_add")
    public String yestaeReserveAdd() {
        return PREFIX + "yestaeReserve_add.html";
    }

    /**
     * 跳转到修改保留词
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeReserve_update/{yestaeReserveId}")
    public String yestaeReserveUpdate(@PathVariable String yestaeReserveId, Model model) {
        YestaeReserve yestaeReserve = yestaeReserveService.selectById(yestaeReserveId);
        model.addAttribute("yestaeReserve",yestaeReserve);
        LogObjectHolder.me().set(yestaeReserve);
        return PREFIX + "yestaeReserve_edit.html";
    }

    /**
     * 获取保留词列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String code, String name) {
    	EntityWrapper<YestaeReserve> wrapper = new EntityWrapper<>();
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("name", name);
    	}
    	if(StringUtils.isNotEmpty(code)){
    		wrapper.eq("code", code);
    	}
        return yestaeReserveService.selectList(wrapper);
    }

    /**
     * 新增保留词
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @BussinessLog(value = "新增保留词", json = "true")
    @ResponseBody
    public Object add(YestaeReserve yestaeReserve) {
    	
    	EntityWrapper<YestaeReserve> wrapper = new EntityWrapper<>();
    	wrapper.eq("name", yestaeReserve.getName());
    	int i = yestaeReserveService.selectCount(wrapper);
    	if(i > 0){
    		throw new BussinessException(BizExceptionEnum.RESERVE_NAME_EXISTED);
    	}
    	
    	wrapper = new EntityWrapper<>();
    	wrapper.eq("code", yestaeReserve.getCode());
    	i = yestaeReserveService.selectCount(wrapper);
    	if(i > 0){
    		throw new BussinessException(BizExceptionEnum.RESERVE_CODE_EXISTED);
    	}
        yestaeReserveService.insert(yestaeReserve);
        return SUCCESS_TIP;
    }

    /**
     * 删除保留词
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @BussinessLog(value = "删除保留词", json = "true")
    @ResponseBody
    public Object delete(@RequestParam String yestaeReserveId) {
        yestaeReserveService.deleteById(yestaeReserveId);
        return SUCCESS_TIP;
    }

    /**
     * 修改保留词
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @BussinessLog(value = "修改保留词", json = "true")
    @ResponseBody
    public Object update(YestaeReserve yestaeReserve) {
    	
    	EntityWrapper<YestaeReserve> wrapper = new EntityWrapper<>();
    	wrapper.eq("name", yestaeReserve.getName());
    	wrapper.ne("id", yestaeReserve.getId());
    	int i = yestaeReserveService.selectCount(wrapper);
    	if(i > 0){
    		throw new BussinessException(BizExceptionEnum.RESERVE_NAME_EXISTED);
    	}
    	
    	wrapper = new EntityWrapper<>();
    	wrapper.eq("code", yestaeReserve.getCode());
    	wrapper.ne("id", yestaeReserve.getId());
    	i = yestaeReserveService.selectCount(wrapper);
    	if(i > 0){
    		throw new BussinessException(BizExceptionEnum.RESERVE_CODE_EXISTED);
    	}
        yestaeReserveService.updateById(yestaeReserve);
        return SUCCESS_TIP;
    }

    /**
     * 保留词详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeReserveId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeReserveId") String yestaeReserveId) {
        return yestaeReserveService.selectById(yestaeReserveId);
    }
}
