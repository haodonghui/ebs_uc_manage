package com.yestae.user.manage.modular.vas.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.google.common.collect.Maps;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUser;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserService;
import com.yestae.user.manage.modular.vas.persistence.model.Order;
import com.yestae.user.manage.modular.vas.service.IOrderService;

/**
 * 订单控制器
 *
 * @author fengshuonan
 * @Date 2018-07-24 14:53:20
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController {

    private String PREFIX = "/vas/order/";

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IYestaeUserService yestaeUserService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到订单首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "order.html";
    }

    /**
     * 跳转到添加订单
     */
    @RequestMapping("/order_add")
    public String orderAdd() {
        return PREFIX + "order_add.html";
    }

    /**
     * 跳转到修改订单
     */
    @RequestMapping("/order_update/{orderId}")
    @DataSource(name="dataSourceUc")
    public String orderUpdate(@PathVariable String orderId, Model model) {
        Order order = orderService.selectById(orderId);
        model.addAttribute("order",order);
        LogObjectHolder.me().set(order);
        return PREFIX + "order_edit.html";
    }

    /**
     * 获取订单列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>> ().defaultPage();
    	page.setRecords(this.findOrderList(page));
    	return super.packForBT(page);
    }
    
    
    /**
     * 导出推广明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/export")
    public void export() {
    	List<Map<String, Object>> list = this.findOrderList(null);
		BigDecimal payAmount = new BigDecimal(0.00);;
		for(Map<String, Object> m: list){
//        	m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
			payAmount = payAmount.add((BigDecimal)m.get("payAmount"));
		}
		Map<String, Object> mapTem = Maps.newHashMap();
		mapTem.put("payAmount","总支付金额："+payAmount);
		list.add(mapTem);
		for(Map<String, Object> m: list){
			m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
//			m.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(m, "createTime")));
		}
		System.out.println(payAmount);
        
       /* String[] strArray = {"订单编号","用户ID","会员名称","会员手机号","增值服务名称","增值服务编码","支付单号","订单支付总金额"
        		,"支付平台","支付平台订单号","支付方式","支付状态","年度","月份","创建时间"};
		Map<Short, String> cellMap = new HashMap<>();
		
		cellMap.put((short)0, "orderNo");
		cellMap.put((short)1, "userId");
		cellMap.put((short)2, "name");
		cellMap.put((short)3, "mobile");
		cellMap.put((short)4, "vasName");
		cellMap.put((short)5, "vasCode");
		cellMap.put((short)6, "payNo");
		cellMap.put((short)7, "payAmount");
		cellMap.put((short)8, "payPlatformName");
		cellMap.put((short)9, "payOrderNo");
		cellMap.put((short)10, "payTypeName");
		cellMap.put((short)11, "payStateName");
		cellMap.put((short)12, "createYear");
		cellMap.put((short)13, "createMonth");
		cellMap.put((short)14, "createTime");
		String fname = "增值服务订单";
		String sheetName = "增值服务订单";*/

		String[] strArray = {"增值服务名称","订单支付总金额","支付方式","支付单号","支付平台订单号","创建时间"};
		Map<Short, String> cellMap = new HashMap<>();


		cellMap.put((short)0, "vasName");
		cellMap.put((short)1, "payAmount");
		cellMap.put((short)2, "payTypeName");
		cellMap.put((short)3, "payNo");
		cellMap.put((short)4, "payOrderNo");
		cellMap.put((short)5, "createTime");
		String fname = "增值服务订单";
		String sheetName = "增值服务订单";
		
		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }

	/**
     * 新增订单
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Order order) {
        orderService.insert(order);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String orderId) {
        orderService.deleteById(orderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(Order order) {
        orderService.updateById(order);
        return SUCCESS_TIP;
    }

    /**
     * 订单详情
     */
    @RequestMapping(value = "/detail/{orderId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("orderId") String orderId) {
        return orderService.selectById(orderId);
    }
    
    private List<Map<String, Object>> findOrderList(Page<Map<String, Object>> page){
    	List<Map<String, Object>> orderList = new ArrayList<>();
    	Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
    	
    	String name = MapUtils.getString(paramMap, "name");
    	String mobile = MapUtils.getString(paramMap, "mobile");
    	
    	List<YestaeUser> userList = null;
    	
    	if(!StringUtils.isEmpty(name) || !StringUtils.isEmpty(mobile)){
    		
    		EntityWrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();
    		if(!StringUtils.isEmpty(name)){
    			wrapper.eq("name", name);
    		}
    		if(!StringUtils.isEmpty(mobile)){
    			wrapper.eq("mobile", mobile);
    		}
    		userList = yestaeUserService.selectList(wrapper);
    		if(userList != null && userList.size() > 0){
    			List<Long> userIdList = new ArrayList<>();
        		for(YestaeUser user: userList){ 
        			userIdList.add(new Long(user.getUserId()));
        		}
        		paramMap.put("userIds", userIdList);
        		orderList = orderService.selectList(page, paramMap);
    		}
    	}else{
    		
    		orderList = orderService.selectList(page, paramMap);
    		
    		if(orderList != null && orderList.size() > 0){
    			
    			List<Long> userIdList = new ArrayList<>();
    			for(Map<String, Object> map: orderList){ 
    				Long userId =  MapUtils.getLong(map, "userId");
    				if(userId != null){
    					userIdList.add(userId);
    				}
    			}
    			if(userIdList.size() > 0){
    				
    				//获取会员名称、id
    				EntityWrapper<YestaeUser> wrapper = new EntityWrapper<YestaeUser>();
    				wrapper.in("user_id", userIdList);
    				userList = yestaeUserService.selectList(wrapper);
    			}
    		}
    			
    	}
    	if(orderList != null && orderList.size()> 0
    			&& userList != null && userList.size() > 0){
    		
    		for(Map<String, Object> map: orderList){ 
    			for(YestaeUser user: userList){
    				String userId =  MapUtils.getString(map, "userId");
    				if(user.getUserId().equals(userId)){
    					map.put("userId", userId);
    					map.put("name", user.getName());
    					map.put("mobile", PrivacyHideUtil.mobileHide(user.getMobile()));
    					map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    					map.put("updateTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "updateTime")));
    					map.put("payStateName", CacheKit.get(Cache.CONSTANT, "payStatus:" + MapUtils.getObject(map, "payState")));//支付状态;//("自定义Name","码表名称","数据库字段")
    					map.put("payTypeName", CacheKit.get(Cache.CONSTANT, "payMethod:" + MapUtils.getObject(map, "payType")));//支付方式
    					map.put("payPlatformName", CacheKit.get(Cache.CONSTANT, "payPlatform:" + MapUtils.getObject(map, "payPt")));//支付平台
    					map.put("orderTypeName", CacheKit.get(Cache.CONSTANT, "orderType:" + MapUtils.getObject(map, "orderType")));//订单类型
    					break;
    				}
    			}
    		}
    	}
    	return orderList;
    }
}
