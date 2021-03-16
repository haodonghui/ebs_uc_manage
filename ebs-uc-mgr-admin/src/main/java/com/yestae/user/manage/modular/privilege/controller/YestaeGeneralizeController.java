package com.yestae.user.manage.modular.privilege.controller;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 推广明细控制器
 *
 * @author fengshuonan
 * @Date 2017-12-06 20:02:33
 */
@Controller
@RequestMapping("/yestaeGeneralize")
public class YestaeGeneralizeController extends BaseController {

    private String PREFIX = "/privilege/yestaeGeneralize/";

    @Autowired
    private IYestaeGeneralizeService yestaeGeneralizeService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到推广明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeGeneralize.html";
    }
    
    /**
     * 跳转到推广明细首页
     */
    @RequestMapping("/sale")
    public String sale() {
    	return PREFIX + "sale.html";
    }
    /**
     * 跳转到推广明细首页
     */
    @RequestMapping("/sale/SHCC")
    public String saleSHCC() {
    	return PREFIX + "sale_SHCC.html";
    }
    /**
     * 跳转到推广明细首页
     */
    @RequestMapping("/limit")
    public String saleLimit() {
    	return PREFIX + "yestaeGeneralize_limit.html";
    }

    /**
     * 获取推广明细列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectYestaeGeneralizeList(page, map);
        
        for(Map<String, Object> m: list){
        	m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 门店注册会员商城销售统计
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/sale/list")
    @ResponseBody
    public Object listSale() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	if(!StringUtils.isEmpty(map.get("startRegistTime"))){
    		List<Map<String, Object>> list = yestaeGeneralizeService.selectSaleList(page, map);
    		
    		page.setRecords(list);
    	}
    	return super.packForBT(page);
    }
    
    /**
     * 门店注册会员商城销售统计导出
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/sale/export")
    public void exportSale() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectSaleList(null, map);
    	
    	String registTime = DateUtil.getTime(MapUtils.getLong(HttpKit.getRequestParametersMap(), "startRegistTime"));
    	String consumeTime = DateUtil.getTime(MapUtils.getLong(HttpKit.getRequestParametersMap(), "startTime"));
    	if(!StringUtils.isEmpty(registTime)){
    		Long endRegistTime = MapUtils.getLong(HttpKit.getRequestParametersMap(), "endRegistTime");
    		registTime += " 至 " + DateUtil.getTime(endRegistTime == null ?(new Date()).getTime() : endRegistTime);
    	}
    	if(!StringUtils.isEmpty(consumeTime)){
    		Long endTime = MapUtils.getLong(HttpKit.getRequestParametersMap(), "endTime");
    		consumeTime += " 至 " + DateUtil.getTime(endTime == null ?(new Date()).getTime() : endTime);
    	}
    	for(Map<String, Object> m: list){
    		m.put("registTime", registTime);
    		m.put("consumeTime", consumeTime);
    	}
    	
    	String[] strArray = {"门店名称","渠道","推广注册人数","商城消费金额","注册时间","消费时间"};
		Map<Short, String> cellMap = new HashMap<Short, String>();
		
		cellMap.put((short)0, "name");
		cellMap.put((short)1, "secondChannelName");
		cellMap.put((short)2, "registNum");
		cellMap.put((short)3, "money");
		cellMap.put((short)4, "registTime");
		cellMap.put((short)5, "consumeTime");
		String fname = "销售统计";
		String sheetName = "销售统计";
		
		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }
    
    /**
     * 门店注册会员商城销售统计详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/sale/list/detail")
    @ResponseBody
    public Object listSaleDeatil() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectSaleDetailList(page, map);
    	
    	for(Map<String, Object> m: list){
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 门店注册会员商城销售统计详情导出
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/sale/export/detail")
    public void exportSaleDeatil() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectSaleDetailList(null, map);
    	
    	for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    	}
    	
    	String[] strArray = {"用户ID","用户名","手机号","金额（元）","消费时间","关联订单号","门店名称","渠道"};
		Map<Short, String> cellMap = new HashMap<Short, String>();
		
		cellMap.put((short)0, "userId");
		cellMap.put((short)1, "userName");
		cellMap.put((short)2, "mobile");
		cellMap.put((short)3, "amountMoney");
		cellMap.put((short)4, "createTime");
		cellMap.put((short)5, "orderNumber");
		cellMap.put((short)6, "name");
		cellMap.put((short)7, "secondChannelName");
		String fname = "销售统计详情";
		String sheetName = "销售统计详情";
		
		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }
    
    /**
     * 获取用户邀请关系列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/relationList")
    @ResponseBody
    public Object relationList() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectYestaeUserRelationList(page, map);
    	for(Map<String, Object> m: list){
    		m.put("userId", MapUtils.getString(m, "userId"));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("inviterMobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "inviterMobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    	}
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 导出推广明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/export")
    public void export() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeService.selectYestaeGeneralizeList(null, map);
        
        for(Map<String, Object> m: list){
        	m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
        	m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    	}
        
        String[] strArray = {"推广人","推荐码","推广渠道一级","推广渠道二级","用户业务类型","场景名称","推广注册人数","推广金卡人数"};
		Map<Short, String> cellMap = new HashMap<Short, String>();
		
		cellMap.put((short)0, "name");
		cellMap.put((short)1, "recommendCode");
		cellMap.put((short)2, "firstChannelName");
		cellMap.put((short)3, "secondChannelName");
		cellMap.put((short)4, "userTypeName");
		cellMap.put((short)5, "sceneName");
		cellMap.put((short)6, "registNum");
		cellMap.put((short)7, "upgradeNum");
		String fname = "推广明细";
		String sheetName = "推广明细";
		
		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }


}
