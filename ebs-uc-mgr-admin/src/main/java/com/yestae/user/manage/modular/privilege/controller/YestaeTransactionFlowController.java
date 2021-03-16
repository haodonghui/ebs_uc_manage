package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeTransactionFlow;
import com.yestae.user.manage.modular.privilege.service.IYestaeTransactionFlowService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户消费流水控制器
 *
 * @author fengshuonan
 * @Date 2017-11-16 21:19:52
 */
@Controller
@RequestMapping("/yestaeTransactionFlow")
public class YestaeTransactionFlowController extends BaseController {

    private String PREFIX = "/privilege/yestaeTransactionFlow/";

    @Autowired
    private IYestaeTransactionFlowService yestaeTransactionFlowService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到用户消费流水首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeTransactionFlow.html";
    }

    /**
     * 跳转到添加用户消费流水
     */
    @RequestMapping("/yestaeTransactionFlow_add")
    public String yestaeTransactionFlowAdd() {
        return PREFIX + "yestaeTransactionFlow_add.html";
    }

    /**
     * 跳转到修改用户消费流水
     */
    @RequestMapping("/yestaeTransactionFlow_update/{yestaeTransactionFlowId}")
    @DataSource(name="dataSourceUc")
    public String yestaeTransactionFlowUpdate(@PathVariable String yestaeTransactionFlowId, Model model) {
        YestaeTransactionFlow yestaeTransactionFlow = yestaeTransactionFlowService.getById(yestaeTransactionFlowId);
        model.addAttribute("item",yestaeTransactionFlow);
        LogObjectHolder.me().set(yestaeTransactionFlow);
        return PREFIX + "yestaeTransactionFlow_edit.html";
    }

    /**
     * 获取用户消费流水列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeTransactionFlowService.selectYestaeTransactionFlowList(page, map);
        
        for(Map<String, Object> m: list){
        	m.put("userId", MapUtils.getString(m, "userId"));
        	m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("payMethod", CacheKit.get(Cache.CONSTANT, "payMethod:" + MapUtils.getInteger(m, "payMethod")));
//    		m.put("payMethod", PayMethodEnum.valueOf(MapUtils.getInteger(m, "payMethod")));
    		m.put("flowType", CacheKit.get(Cache.CONSTANT, "flowType:" + MapUtils.getInteger(m, "flowType")));
//    		m.put("flowType", FlowTypeEnum.valueOf(MapUtils.getInteger(m, "flowType")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 导出用户消费流水列表
     */
    @RequestMapping(value = "/export")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public void export() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeTransactionFlowService.selectYestaeTransactionFlowList(null, map);
    	
    	for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("payMethod", CacheKit.get(Cache.CONSTANT, "payMethod:" + MapUtils.getInteger(m, "payMethod")));
//    		m.put("payMethod", PayMethodEnum.valueOf(MapUtils.getInteger(m, "payMethod")));
//    		m.put("flowType", FlowTypeEnum.valueOf(MapUtils.getInteger(m, "flowType")));
    		m.put("flowType", CacheKit.get(Cache.CONSTANT, "flowType:" + MapUtils.getInteger(m, "flowType")));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    	}
    	
    	String[] strArray = {"用户名","手机号","时间","类型","支付方式","金额（元）","关联订单号","关联交易单号","第三方流水号"};
 		Map<Short, String> cellMap = new HashMap<Short, String>();
 		
 		cellMap.put((short)0, "name");
 		cellMap.put((short)1, "mobile");
 		cellMap.put((short)2, "createTime");
 		cellMap.put((short)3, "flowType");
 		cellMap.put((short)4, "payMethod");
 		cellMap.put((short)5, "amountMoney");
 		cellMap.put((short)6, "orderNumber");
 		cellMap.put((short)7, "tradeNumber");
 		cellMap.put((short)8, "flowNumber");
 		String fname = "用户消费流水";
 		String sheetName = "用户消费流水";
 		
 		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }

    /**
     * 新增用户消费流水
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(YestaeTransactionFlow yestaeTransactionFlow) {
        yestaeTransactionFlowService.save(yestaeTransactionFlow);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户消费流水
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String yestaeTransactionFlowId) {
        yestaeTransactionFlowService.removeById(yestaeTransactionFlowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户消费流水
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(YestaeTransactionFlow yestaeTransactionFlow) {
        yestaeTransactionFlowService.updateById(yestaeTransactionFlow);
        return SUCCESS_TIP;
    }

    /**
     * 用户消费流水详情
     */
    @RequestMapping(value = "/detail/{yestaeTransactionFlowId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("yestaeTransactionFlowId") String yestaeTransactionFlowId) {
        return yestaeTransactionFlowService.getById(yestaeTransactionFlowId);
    }
}
