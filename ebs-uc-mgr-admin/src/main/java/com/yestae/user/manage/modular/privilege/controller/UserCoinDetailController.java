package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.UserCoinDetailTypeEnum;
import com.yestae.user.manage.modular.privilege.service.IUserCoinDetailService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 受益券明细控制器
 *
 * @author fengshuonan
 * @Date 2017-11-20 17:21:07
 */
@Component
@Controller
@RequestMapping("/userCoinDetail")
public class UserCoinDetailController extends BaseController {

    private String PREFIX = "/privilege/userCoinDetai/";

    @Autowired
    private IUserCoinDetailService userCoinDetailService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到受益券明细查询页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userCoinDetail.html";
    }

    /**
     * 获取受益券明细
     */
    @DataSource(name="dataSourceCoin")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userCoinDetailService.selectCoinDetail(page, map);
        
        for(Map<String, Object> m: list){
			m.put("userId", MapUtils.getString(m, "userId"));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("direction", UserCoinDetailTypeEnum.valueOf(MapUtils.getInteger(m, "direction")));
    		m.put("product", CacheKit.get(Cache.CONSTANT, "product:" + MapUtils.getString(m, "product")));
    		m.put("type", CacheKit.get(Cache.CONSTANT, "coinType:" + MapUtils.getString(m, "type")));
    	}

    	page.setRecords(list);
    	return super.packForBT(page);
    }

    /**
     * 导出受益券明细
     */
    @DataSource(name="dataSourceCoin")
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userCoinDetailService.selectCoinDetail(null, map);

    	for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
            m.put("direction", UserCoinDetailTypeEnum.valueOf(MapUtils.getInteger(m, "direction")));
            m.put("product", CacheKit.get(Cache.CONSTANT, "product:" + MapUtils.getString(m, "product")));
            m.put("type", CacheKit.get(Cache.CONSTANT, "coinType:" + MapUtils.getString(m, "type")));
    	}

    	String[] strArray = {"用户ID","变动渠道","订单编号","变动方向","受益券金额","交易前余额","交易后余额","交易类型","时间","备注"};
 		Map<Short, String> cellMap = new HashMap<Short, String>();

 		cellMap.put((short)0, "userId");
 		cellMap.put((short)1, "product");
 		cellMap.put((short)2, "orderNo");
 		cellMap.put((short)3, "direction");
 		cellMap.put((short)4, "amount");
 		cellMap.put((short)5, "preAmount");
 		cellMap.put((short)6, "laterAmount");
 		cellMap.put((short)7, "type");
 		cellMap.put((short)8, "createTime");
 		cellMap.put((short)9, "remark");
 		String fname = "受益券";
 		String sheetName = "明细";

 		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }
//
//    /**
//     * 新增用户账户流水
//     */
//    @DataSource(name="dataSourceUc")
//    @RequestMapping(value = "/add")
//    @ResponseBody
//    public Object add(UserAccountFlow userAccountFlow) {
//        userAccountFlowService.insert(userAccountFlow);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 删除用户账户流水
//     */
//    @DataSource(name="dataSourceUc")
//    @RequestMapping(value = "/delete")
//    @ResponseBody
//    public Object delete(@RequestParam String userAccountFlowId) {
//        userAccountFlowService.deleteById(userAccountFlowId);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 修改用户账户流水
//     */
//    @DataSource(name="dataSourceUc")
//    @RequestMapping(value = "/update")
//    @ResponseBody
//    public Object update(UserAccountFlow userAccountFlow) {
//        userAccountFlowService.updateById(userAccountFlow);
//        return SUCCESS_TIP;
//    }
//
//    /**
//     * 用户账户流水详情
//     */
//    @DataSource(name="dataSourceUc")
//    @RequestMapping(value = "/detail/{userAccountFlowId}")
//    @ResponseBody
//    public Object detail(@PathVariable("userAccountFlowId") String userAccountFlowId) {
//        return userAccountFlowService.selectById(userAccountFlowId);
//    }
}
