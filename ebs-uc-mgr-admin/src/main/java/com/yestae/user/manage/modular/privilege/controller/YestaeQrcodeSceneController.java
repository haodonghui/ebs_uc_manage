package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.common.enums.UserStatusEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcodeScene;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeSceneService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 二维码场景控制器
 *
 * @author fengshuonan
 * @Date 2017-12-04 19:00:39
 */
@Controller
@RequestMapping("/yestaeQrcodeScene")
public class YestaeQrcodeSceneController extends BaseController {

    private String PREFIX = "/privilege/yestaeQrcodeScene/";

    @Autowired
    private IYestaeQrcodeSceneService yestaeQrcodeSceneService;

    /**
     * 跳转到二维码场景首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeQrcodeScene.html";
    }
    
    /**
     * 跳转到用户选择列表
     */
    @RequestMapping("yestaeQrcodeScene_select")
    public String yestaeQrcodeSceneSelect() {
    	return PREFIX + "yestaeQrcodeScene_select.html";
    }

    /**
     * 跳转到添加二维码场景
     */
    @RequestMapping("/yestaeQrcodeScene_add")
    public String yestaeQrcodeSceneAdd() {
        return PREFIX + "yestaeQrcodeScene_add.html";
    }

    /**
     * 跳转到修改二维码场景
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeQrcodeScene_update/{yestaeQrcodeSceneId}")
    public String yestaeQrcodeSceneUpdate(@PathVariable String yestaeQrcodeSceneId, Model model) {
        YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.getById(yestaeQrcodeSceneId);
        model.addAttribute("yestaeQrcodeScene", yestaeQrcodeScene);
        return PREFIX + "yestaeQrcodeScene_edit.html";
    }

    /**
     * 获取二维码场景列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name
    		, @RequestParam(required = false) Integer type
    		, @RequestParam(required = false) Integer applyScope
    		, @RequestParam(required = false) Integer jumpType
    		, @RequestParam(required = false) Integer status) {
    	Page<Map<String, Object>> page = new Page();
    	QueryWrapper<YestaeQrcodeScene> wrapper = new QueryWrapper<>();
//    	wrapper.setSqlSelect(new String[] {"id", "name", "type", "status", "description", "create_time"});
    	wrapper.eq("if_del", SysEnum.NO.getCode());
    	wrapper.orderBy(false,false,"create_time");
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("name", name);
    	}
    	if(status != null){
    		wrapper.eq("status", status);
    	}
    	if(type != null){
    		wrapper.eq("type", type);
    	}
    	if(applyScope != null){
    		wrapper.eq("apply_scope", applyScope);
    	}
    	if(jumpType != null){
    		wrapper.eq("jump_type", jumpType);
    	}
    	page = yestaeQrcodeSceneService.pageMaps(page, wrapper);
    	List<Map<String, Object>> list = page.getRecords();
    	for(Map<String, Object> map: list){
    		
    		map.put("statusName", UserStatusEnum.valueOf(MapUtils.getInteger(map, "status")));
    		map.put("typeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneType:" + MapUtils.getInteger(map, "type")));
    		map.put("applyScopeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneApplyScope:" + MapUtils.getInteger(map, "applyScope")));
    		map.put("jumpTypeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneJumpType:" + MapUtils.getInteger(map, "jumpType")));
    		map.put("createTime", DateUtil.getTime(MapUtils.getLong(map, "createTime")));
    	}
    	return super.packForBT(page);
    }

    /**
     * 新增二维码场景
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeQrcodeScene yestaeQrcodeScene) {
    	yestaeQrcodeScene.setCreateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeQrcodeScene.setCreateTime(new Date().getTime());
    	yestaeQrcodeScene.setIfDel(SysEnum.NO.getCode());
    	yestaeQrcodeScene.setStatus(UserStatusEnum.STATUS_OFF.getCode());
    	yestaeQrcodeSceneService.save(yestaeQrcodeScene);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码场景
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeQrcodeSceneId) {
        yestaeQrcodeSceneService.deleteByYestaeQrcodeSceneId(yestaeQrcodeSceneId);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码场景
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeQrcodeScene yestaeQrcodeScene) {
    	yestaeQrcodeScene.setUpdateTime(new Date().getTime());
    	yestaeQrcodeScene.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
        yestaeQrcodeSceneService.updateYestaeQrcodeScene(yestaeQrcodeScene);
        return SUCCESS_TIP;
    }

    /**
     * 二维码场景详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeQrcodeSceneId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeQrcodeSceneId") String yestaeQrcodeSceneId) {
        return yestaeQrcodeSceneService.getById(yestaeQrcodeSceneId);
    }
    
    /**
     * 启用
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/online")
    @ResponseBody
    public Object online(@RequestParam(required = true) String yestaeQrcodeSceneId) {
    	YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.getById(yestaeQrcodeSceneId);
    	yestaeQrcodeScene.setUpdateTime(new Date().getTime());
    	yestaeQrcodeScene.setStatus(UserStatusEnum.STATUS_ON.getCode());
    	yestaeQrcodeSceneService.updateById(yestaeQrcodeScene);
    	return SUCCESS_TIP;
    }
    
    /**
     * 停用
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/offline")
    @ResponseBody
    public Object offline(@RequestParam(required = true) String yestaeQrcodeSceneId) {
    	YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.getById(yestaeQrcodeSceneId);
    	yestaeQrcodeScene.setUpdateTime(new Date().getTime());
    	yestaeQrcodeScene.setStatus(UserStatusEnum.STATUS_OFF.getCode());
    	yestaeQrcodeSceneService.updateById(yestaeQrcodeScene);
    	return SUCCESS_TIP;
    }
}
