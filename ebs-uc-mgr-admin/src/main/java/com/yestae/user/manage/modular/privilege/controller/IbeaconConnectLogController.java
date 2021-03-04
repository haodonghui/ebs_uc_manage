package com.yestae.user.manage.modular.privilege.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.privilege.persistence.model.IbeaconConnectLog;
import com.yestae.user.manage.modular.privilege.service.IIbeaconConnectLogService;

/**
 * ibeacon连接记录控制器
 *
 * @author fengshuonan
 * @Date 2018-05-24 11:37:38
 */
@Controller
@RequestMapping("/ibeaconConnectLog")
public class IbeaconConnectLogController extends BaseController {

    private String PREFIX = "/privilege/ibeaconConnectLog/";

    @Autowired
    private IIbeaconConnectLogService ibeaconConnectLogService;

    /**
     * 跳转到ibeacon连接记录首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ibeaconConnectLog.html";
    }

    /**
     * 跳转到添加ibeacon连接记录
     */
    @RequestMapping("/ibeaconConnectLog_add")
    public String ibeaconConnectLogAdd() {
        return PREFIX + "ibeaconConnectLog_add.html";
    }

    /**
     * 跳转到修改ibeacon连接记录
     */
    @RequestMapping("/ibeaconConnectLog_update/{ibeaconConnectLogId}")
    @DataSource(name="dataSourceUc")
    public String ibeaconConnectLogUpdate(@PathVariable String ibeaconConnectLogId, Model model) {
        IbeaconConnectLog ibeaconConnectLog = ibeaconConnectLogService.selectById(ibeaconConnectLogId);
        model.addAttribute("ibeaconConnectLog",ibeaconConnectLog);
        LogObjectHolder.me().set(ibeaconConnectLog);
        return PREFIX + "ibeaconConnectLog_edit.html";
    }

    /**
     * 获取ibeacon连接记录列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(String condition) {
        return ibeaconConnectLogService.selectList(null);
    }

    /**
     * 新增ibeacon连接记录
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(IbeaconConnectLog ibeaconConnectLog) {
        ibeaconConnectLogService.insert(ibeaconConnectLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除ibeacon连接记录
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String ibeaconConnectLogId) {
        ibeaconConnectLogService.deleteById(ibeaconConnectLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改ibeacon连接记录
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(IbeaconConnectLog ibeaconConnectLog) {
        ibeaconConnectLogService.updateById(ibeaconConnectLog);
        return SUCCESS_TIP;
    }
    
    /**
     * 修改ibeacon连接记录
     */
    @RequestMapping(value = "/update/uuid")
    @ResponseBody
    public Object updateUuid(@RequestParam String uuid) {
    	CacheKit.put("CONSTANT", "UUID", uuid);
    	return SUCCESS_TIP;
    }
    
    /**
     * 
    * 校验uuid
    *
    * @param uuid ibeacon设备UUID
    * @param mac 设备名/Mac地址
    * @return
    * @throws
     */
    @RequestMapping(value = "/check/uuid")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object checkUuid(@RequestParam String uuid, @RequestParam String mac) {
    	String cacheUuid = (String)CacheKit.get("CONSTANT", "UUID");
    	int status = 0;
    	String msg = "校验失败";
    	if(StringUtils.isEmpty(mac)){
    		msg = "缺少mac参数";
    	}else if(StringUtils.isEmpty(uuid)){
    		msg = "缺少uuid参数";
    	}else{
    		if(uuid.equals(cacheUuid)){
    			status = 1;
    			msg = "校验成功";
    		}
    	}
    	IbeaconConnectLog ibeaconConnectLog = new IbeaconConnectLog();
    	ibeaconConnectLog.setCreateTime(new Date().getTime());
    	ibeaconConnectLog.setMac(mac);
    	ibeaconConnectLog.setStatus(status);
    	ibeaconConnectLog.setUuid(uuid);
		ibeaconConnectLogService.insert(ibeaconConnectLog);
		
		Map<String, Object> map = new HashMap<>();
		map.put("returnCode", status);
		map.put("returnMsg", msg);
    	return map;
    }
    
    /**
     * 获取uuid
     */
    @RequestMapping(value = "/get/uuid")
    @ResponseBody
    public Object getUuid() {
    	String uuid = (String)CacheKit.get("CONSTANT", "UUID");
    	Map<String, Object> map = new HashMap<>();
    	map.put("uuid", uuid == null? "": uuid);
    	return map;
    }

    /**
     * ibeacon连接记录详情
     */
    @RequestMapping(value = "/detail/{ibeaconConnectLogId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("ibeaconConnectLogId") String ibeaconConnectLogId) {
        return ibeaconConnectLogService.selectById(ibeaconConnectLogId);
    }
}
