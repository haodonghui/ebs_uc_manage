package com.yestae.user.manage.modular.vas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.Equity;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IEquityService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 权益控制器
 *
 * @author fengshuonan
 * @Date 2018-07-10 15:39:21
 */

@Controller
@RequestMapping("/equity")
public class EquityController extends BaseController {

    private String PREFIX = "/vas/equity/";

    @Autowired
    private IEquityService equityService;
    @Autowired
    private IVasImageService vasImageService;
    @Resource
    UcConstant ucConstant;

    /**
     * 跳转到权益首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "equity.html";
    }

    /**
     * 跳转到添加权益
     */
    @RequestMapping("/equity_add")
    public String equityAdd() {
        return PREFIX + "equity_add.html";
    }

    /**
     * 跳转到修改权益
     */
    @RequestMapping("/equity_update/{equityId}")
    @DataSource(name="dataSourceUc")
    public String equityUpdate(@PathVariable String equityId, Model model) {
        Equity equity = equityService.getById(equityId);
        
        VasImage equityImage = vasImageService.findOneVasImage(equityId, VasConstants.VI_BIZ_TYPE_EQUITY);
        if(equityImage != null){
        	equity.setSurfaceId(equityImage.getId());
        	equity.setSurfaceUrl(ucConstant.getImageServer() + equityImage.getLarge());
        }
        
        model.addAttribute("equity",equity);
        LogObjectHolder.me().set(equity);
        return PREFIX + "equity_edit.html";
    }

    /**
     * 获取权益列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(@RequestParam(required=false)String equityCode,@RequestParam(required=false)String equityName,
    		@RequestParam(required=false)Integer status,@RequestParam(required=false)Integer type) {
    	Page<Map<String, Object>> page = new Page();
        QueryWrapper<Equity> wrapper = new QueryWrapper<>();
    	
    	if(StringUtils.isNotEmpty(equityCode)){
    		wrapper.like("equity_code", equityCode);
    	}
    	if(StringUtils.isNotEmpty(equityName)){
    		wrapper.like("equity_name", equityName);
    	}
    	if(status != null){
    		wrapper.eq("status", status);
    	}
    	if(type != null){
    		wrapper.eq("type", type);
    	}
    	wrapper.eq("del_flag", VasConstants.YES);
    	wrapper.orderBy(false,false,"create_time");
    	page = equityService.pageMaps(page, wrapper);
    	List<Map<String, Object>> list = page.getRecords();
    	for(Map<String, Object> map: list){
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "status:" + MapUtils.getObject(map, "status")));
    		map.put("typeName", CacheKit.get(Cache.CONSTANT, "equityType:" + MapUtils.getObject(map, "type")));
    	}
        return super.packForBT(page);
    }

    /**
     * 新增权益
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(Equity equity) {
        
        if(equity != null){
        	equity.setCreateBy(ShiroKit.getUser().getId());
    		equity.setCreateTime(new Date().getTime());
    		equityService.insertEquity(equity);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 删除权益
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String equityId) {
    	Equity equity = equityService.getById(equityId);
        if(equity != null){
        	equity.setUpdateBy(ShiroKit.getUser().getId());
    		equity.setUpdateTime(new Date().getTime());
    		equity.setDelFlag(VasConstants.NO);
    		equityService.updateById(equity);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 修改权益
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(Equity equity) {
        
        if(equity != null && !StringUtils.isEmpty(equity.getId())){
        	equity.setUpdateBy(ShiroKit.getUser().getId());
        	equity.setUpdateTime(new Date().getTime());
    		equityService.updateEquity(equity);
    	}
        return SUCCESS_TIP;
    }
    
    
    /**
     * 权益启用
     */
    @RequestMapping(value = "/online")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object online(@RequestParam String equityId) {
    	Equity equity = equityService.getById(equityId);
    	if(equity != null){
    		equity.setUpdateBy(ShiroKit.getUser().getId());
    		equity.setUpdateTime(new Date().getTime());
    		equity.setStatus(VasConstants.STATUS_ON);
    		equityService.updateById(equity);
    	}
    	return SUCCESS_TIP;
    }
    /**
     * 权益停用
     */
    @RequestMapping(value = "/offline")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object offline(@RequestParam String equityId) {
    	Equity equity = equityService.getById(equityId);
    	if(equity != null){
    		equity.setUpdateBy(ShiroKit.getUser().getId());
    		equity.setUpdateTime(new Date().getTime());
    		equity.setStatus(VasConstants.STATUS_OFF);
    		equityService.updateById(equity);
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 权益详情
     */
    @RequestMapping(value = "/detail/{equityId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("equityId") String equityId) {
        return equityService.getById(equityId);
    }
}
