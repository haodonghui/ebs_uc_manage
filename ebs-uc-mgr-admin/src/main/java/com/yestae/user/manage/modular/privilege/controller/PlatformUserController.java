package com.yestae.user.manage.modular.privilege.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.modular.privilege.persistence.model.PlatformUser;
import com.yestae.user.manage.modular.privilege.service.IPlatformUserService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 平台用户控制器
 *
 * @author fengshuonan
 * @Date 2018-07-26 20:42:16
 */
@Controller
@RequestMapping("/platformUser")
public class PlatformUserController extends BaseController {

    private String PREFIX = "/privilege/platformUser/";

    @Autowired
    private IPlatformUserService platformUserService;

    /**
     * 跳转到平台用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "platformUser.html";
    }
    /**
     * 跳转到平台用户首页
     */
    @RequestMapping("platformUser_select")
    public String platformUserSelect() {
    	return PREFIX + "platformUser_select.html";
    }

    /**
     * 跳转到添加平台用户
     */
    @RequestMapping("/platformUser_add")
    public String platformUserAdd() {
        return PREFIX + "platformUser_add.html";
    }

    /**
     * 跳转到修改平台用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/platformUser_update/{platformUserId}")
    public String platformUserUpdate(@PathVariable String platformUserId, Model model) {
        PlatformUser platformUser = platformUserService.getById(platformUserId);
        model.addAttribute("platformUser",platformUser);
        LogObjectHolder.me().set(platformUser);
        return PREFIX + "platformUser_edit.html";
    }

    /**
     * 获取平台用户列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        
        Page<Map<String, Object>> page = new Page<>();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	String userId = MapUtils.getString(map, "userId");
    	String name = MapUtils.getString(map, "name");
    	String mobile = MapUtils.getString(map, "mobile");
    	String userType = MapUtils.getString(map, "userType");
    	QueryWrapper<PlatformUser> wrapper = new QueryWrapper<>();
    	if(!StringUtils.isEmpty(userId)){
    		wrapper.eq("user_id", userId);
    	}
    	if(!StringUtils.isEmpty(userType)){
    		wrapper.eq("user_type", userType);
    	}
    	if(!StringUtils.isEmpty(name)){
    		wrapper.like("name", name);
    	}
    	if(!StringUtils.isEmpty(mobile)){
    		wrapper.like("mobile", mobile);
    	}
    	page = platformUserService.pageMaps(page, wrapper);
        
        List<Map<String, Object>> list = page.getRecords();
		for(Map<String, Object> m: list ){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		map.put("userId", MapUtils.getString(map, "userId"));
    		m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }

    /**
     * 新增平台用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PlatformUser platformUser) {
        platformUserService.save(platformUser);
        return SUCCESS_TIP;
    }

    /**
     * 删除平台用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String platformUserId) {
        platformUserService.removeById(platformUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改平台用户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PlatformUser platformUser) {
        platformUserService.updateById(platformUser);
        return SUCCESS_TIP;
    }

    /**
     * 平台用户详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{platformUserId}")
    @ResponseBody
    public Object detail(@PathVariable("platformUserId") String platformUserId) {
        return platformUserService.getById(platformUserId);
    }
}
