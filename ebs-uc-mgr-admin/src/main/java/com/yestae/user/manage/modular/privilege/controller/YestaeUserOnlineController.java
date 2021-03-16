package com.yestae.user.manage.modular.privilege.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.center.dubbo.entity.UserQuitSystemDubbo;
import com.yestae.user.center.dubbo.entity.UserResult;
import com.yestae.user.center.dubbo.service.IUserCenterService;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.annotion.BussinessLog;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserOnlineService;
import org.apache.commons.collections.MapUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 登录用户控制器
 *
 * @author fengshuonan
 * @Date 2017-12-07 14:34:27
 */
@Controller
@RequestMapping("/yestaeUserOnline")
public class YestaeUserOnlineController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserOnline/";

    @Autowired
    private IYestaeUserOnlineService yestaeUserOnlineService;

    @DubboReference
   	private IUserCenterService userCenterService;
    
    /**
     * 跳转到登录用户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserOnline.html";
    }

    /**
     * 获取登录用户列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeUserOnlineService.selectYestaeUserOnlineList(page, map);
        
        for(Map<String, Object> m: list){
        	if(MapUtils.getLong(m, "onlineTime") != null){
        		m.put("onlineTime", DateUtil.getTime(MapUtils.getLong(m, "onlineTime")));
        		m.put("userId", MapUtils.getString(m, "userId"));
        	}
        	m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("sourceName", CacheKit.get(Cache.CONSTANT, "source:" + MapUtils.getObject(m, "source")));
//    		m.put("sourceName", SourceEnum.valueOf((Integer) m.get("source")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }

    /**
     * 批量退出系统
     */
	@RequestMapping(value = "/quit/batch")
	@BussinessLog(value = "批量踢出用户", json = "true")
    @ResponseBody
    public Object quitSystemBatch() {
    	String json = getHttpServletRequest().getParameter("json");
    	if(!StringUtils.isEmpty(json)){
    		List<UserQuitSystemDubbo> userQuitSystemDubbos = JSONObject.parseArray(json, UserQuitSystemDubbo.class);
    		UserResult<Boolean> flag = userCenterService.quitSystem(userQuitSystemDubbos);
    		if(!flag.isSucceed() || !flag.getResult()){
    			return new ErrorTip(0, flag.getRetMsg());
    		}
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 按来源退出系统
     */
    @RequestMapping(value = "/quit/source")
    @BussinessLog(value = "按来源踢出用户", json = "true")
    @ResponseBody
    public Object QuitSystemBySource() {
    	String sourceStr = getHttpServletRequest().getParameter("source");
    	if(!StringUtils.isEmpty(sourceStr)){
    		
    		Integer source = Integer.parseInt(sourceStr);
    		UserResult<Boolean> flag = userCenterService.quitSystem(source);
    		if(!flag.isSucceed() || !flag.getResult()){
    			return new ErrorTip(0, flag.getRetMsg());
    		}
    	}
    	return SUCCESS_TIP;
    }

   
}
