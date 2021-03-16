package com.yestae.user.manage.modular.vas.controller;

import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.modular.vas.persistence.model.VasEquity;
import com.yestae.user.manage.modular.vas.service.IVasEquityService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 增值服务权益控制器
 *
 * @author fengshuonan
 * @Date 2018-07-04 10:47:30
 */
@Controller
@RequestMapping("/vasEquity")
public class VasEquityController extends BaseController {

    private String PREFIX = "/vas/vasEquity/";

    @Autowired
    private IVasEquityService vasEquityService;

    /**
     * 跳转到增值服务权益首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vasEquity.html";
    }

    /**
     * 跳转到添加增值服务权益
     */
    @RequestMapping("/vasEquity_add")
    public String vasEquityAdd() {
        return PREFIX + "vasEquity_add.html";
    }

    /**
     * 跳转到修改增值服务权益
     */
    @RequestMapping("/vasEquity_update/{vasEquityId}")
    @DataSource(name="dataSourceUc")
    public String vasEquityUpdate(@PathVariable String vasEquityId, Model model) {
        VasEquity vasEquity = vasEquityService.getById(vasEquityId);
        model.addAttribute("vasEquity",vasEquity);
        LogObjectHolder.me().set(vasEquity);
        return PREFIX + "vasEquity_edit.html";
    }

    /**
     * 获取增值服务权益列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(@RequestParam String vasId) {
    	if(StringUtils.isEmpty(vasId)){
    		return new ArrayList<>();
    	}
    	Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
		List<Map<String, Object>> list = vasEquityService.selectVasEquityList(paramMap);
		for(Map<String, Object> map: list){
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "status:" + MapUtils.getObject(map, "status")));
    		map.put("typeName", CacheKit.get(Cache.CONSTANT, "equityType:" + MapUtils.getObject(map, "type")));
    	}
        return list;
    }

    /**
     * 新增增值服务权益
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(VasEquity vasEquity) {
        vasEquityService.save(vasEquity);
        return SUCCESS_TIP;
    }

    /**
     * 删除增值服务权益
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String vasEquityId) {
        vasEquityService.removeById(vasEquityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改增值服务权益
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(VasEquity vasEquity) {
        vasEquityService.updateById(vasEquity);
        return SUCCESS_TIP;
    }

    /**
     * 增值服务权益详情
     */
    @RequestMapping(value = "/detail/{vasEquityId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("vasEquityId") String vasEquityId) {
        return vasEquityService.getById(vasEquityId);
    }
}
