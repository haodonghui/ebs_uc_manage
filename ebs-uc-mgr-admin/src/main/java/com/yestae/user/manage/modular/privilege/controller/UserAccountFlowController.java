package com.yestae.user.manage.modular.privilege.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.AccountFlowTypeEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccountFlow;
import com.yestae.user.manage.modular.privilege.service.IUserAccountFlowService;

/**
 * 用户账户流水控制器
 *
 * @author fengshuonan
 * @Date 2017-11-20 17:21:07
 */
@Controller
@RequestMapping("/userAccountFlow")
public class UserAccountFlowController extends BaseController {

    private String PREFIX = "/privilege/userAccountFlow/";

    @Autowired
    private IUserAccountFlowService userAccountFlowService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到用户账户流水首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userAccountFlow.html";
    }

    /**
     * 跳转到添加用户账户流水
     */
    @RequestMapping("/userAccountFlow_add")
    public String userAccountFlowAdd() {
        return PREFIX + "userAccountFlow_add.html";
    }

    /**
     * 跳转到修改用户账户流水
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/userAccountFlow_update/{userAccountFlowId}")
    public String userAccountFlowUpdate(@PathVariable String userAccountFlowId, Model model) {
        UserAccountFlow userAccountFlow = userAccountFlowService.selectById(userAccountFlowId);
        model.addAttribute("item",userAccountFlow);
        LogObjectHolder.me().set(userAccountFlow);
        return PREFIX + "userAccountFlow_edit.html";
    }

    /**
     * 获取用户账户流水列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userAccountFlowService.selectUserAccountFlowList(page, map);
        
        for(Map<String, Object> m: list){
        	m.put("userId", MapUtils.getString(m, "userId"));
        	m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("type", AccountFlowTypeEnum.valueOf(MapUtils.getInteger(m, "type")));
    		m.put("tradeType", CacheKit.get(Cache.CONSTANT, "tradeType:" + MapUtils.getString(m, "tradeType")));
    		m.put("payType", CacheKit.get(Cache.CONSTANT, "payType:" + MapUtils.getString(m, "payType")));
//    		m.put("tradeType", AccountTradeTypeEnum.valueOf(MapUtils.getInteger(m, "tradeType")));
//    		m.put("payType", AccountPayTypeEnum.valueOf(MapUtils.getInteger(m, "payType")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 导出用户账户流水列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userAccountFlowService.selectUserAccountFlowList(null, map);
    	
    	for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("type", AccountFlowTypeEnum.valueOf(MapUtils.getInteger(m, "type")));
    		m.put("tradeType", CacheKit.get(Cache.CONSTANT, "tradeType:" + MapUtils.getString(m, "tradeType")));
    		m.put("payType", CacheKit.get(Cache.CONSTANT, "payType:" + MapUtils.getString(m, "payType")));
//    		m.put("tradeType", AccountTradeTypeEnum.valueOf(MapUtils.getInteger(m, "tradeType")));
//    		m.put("payType", AccountPayTypeEnum.valueOf(MapUtils.getInteger(m, "payType")));
    	}
    	
    	String[] strArray = {"用户名","手机号","账户编码","方向","发生金额","当期余额","交易类型","支付类型","时间","关联交易单号","第三方交易单号","备注"};
 		Map<Short, String> cellMap = new HashMap<Short, String>();
 		
 		cellMap.put((short)0, "name");
 		cellMap.put((short)1, "mobile");
 		cellMap.put((short)2, "accountNo");
 		cellMap.put((short)3, "type");
 		cellMap.put((short)4, "amount");
 		cellMap.put((short)5, "laterAmount");
 		cellMap.put((short)6, "tradeType");
 		cellMap.put((short)7, "payType");
 		cellMap.put((short)8, "createTime");
 		cellMap.put((short)9, "tradeNo");
 		cellMap.put((short)10, "thirdpartyTradeNo");
 		cellMap.put((short)11, "remark");
 		String fname = "余额流水";
 		String sheetName = "预存款";
 		
 		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }

    /**
     * 新增用户账户流水
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserAccountFlow userAccountFlow) {
        userAccountFlowService.insert(userAccountFlow);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户账户流水
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String userAccountFlowId) {
        userAccountFlowService.deleteById(userAccountFlowId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户账户流水
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(UserAccountFlow userAccountFlow) {
        userAccountFlowService.updateById(userAccountFlow);
        return SUCCESS_TIP;
    }

    /**
     * 用户账户流水详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{userAccountFlowId}")
    @ResponseBody
    public Object detail(@PathVariable("userAccountFlowId") String userAccountFlowId) {
        return userAccountFlowService.selectById(userAccountFlowId);
    }
}
