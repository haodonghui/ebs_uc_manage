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
import com.yestae.user.center.dubbo.entity.UserResult;
import com.yestae.user.center.dubbo.service.IUserCenterService;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.annotion.BussinessLog;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.AccountStateEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.UserAccount;
import com.yestae.user.manage.modular.privilege.service.IUserAccountService;

/**
 * 用户账户控制器
 *
 * @author fengshuonan
 * @Date 2017-11-20 17:18:49
 */
@Controller
@RequestMapping("/userAccount")
public class UserAccountController extends BaseController {

    private String PREFIX = "/privilege/userAccount/";

    @Autowired
    private IUserAccountService userAccountService;
    
    @Resource
   	private IUserCenterService userCenterService;
    
    @Resource
    ExcelUtil excelUtil;

    /**
     * 跳转到用户账户首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "userAccount.html";
    }

    /**
     * 跳转到添加用户账户
     */
    @RequestMapping("/userAccount_add")
    public String userAccountAdd() {
        return PREFIX + "userAccount_add.html";
    }

    /**
     * 跳转到修改用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/userAccount_update/{userAccountId}")
    public String userAccountUpdate(@PathVariable String userAccountId, Model model) {
        UserAccount userAccount = userAccountService.selectById(userAccountId);
        model.addAttribute("userAccount",userAccount);
        LogObjectHolder.me().set(userAccount);
        return PREFIX + "userAccount_edit.html";
    }

    /**
     * 获取用户账户列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
    	
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userAccountService.selectUserAccountList(page, map);
        
    	for(Map<String, Object> m: list){
    		m.put("userId", MapUtils.getString(m, "userId"));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("stateName", AccountStateEnum.valueOf(MapUtils.getInteger(m, "state")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 导出获取用户账户列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/export")
    @ResponseBody
    public void export(String condition) {
    	
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = userAccountService.selectUserAccountList(null, map);
    	
    	for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("stateName", AccountStateEnum.valueOf(MapUtils.getInteger(m, "state")));
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    	}
    	
    	String[] strArray = {"用户名","手机号","账户编码","账户状态","当前余额（元）","创建时间"};
 		Map<Short, String> cellMap = new HashMap<Short, String>();
 		
 		cellMap.put((short)0, "name");
 		cellMap.put((short)1, "mobile");
 		cellMap.put((short)2, "accountNo");
 		cellMap.put((short)3, "stateName");
 		cellMap.put((short)4, "amount");
 		cellMap.put((short)5, "createTime");
 		String fname = "用户账户";
 		String sheetName = "用户账户";
 		
 		excelUtil.exportExcel(getHttpServletResponse(), list, strArray, cellMap, fname, sheetName);
    }

    /**
     * 新增用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(UserAccount userAccount) {
        userAccountService.insert(userAccount);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String userAccountId) {
        userAccountService.deleteById(userAccountId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update() {
//    	Map<String, String> map = HttpKit.getRequestParameters();
//        userAccountService.updateUserAccount(map);
        return SUCCESS_TIP;
    }
    
    /**
     * 冻结用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/freeze")
    @BussinessLog(value = "冻结用户账户", json = "true")
    @ResponseBody
    public Object freeze(@RequestParam String userAccountId) {
    	
    	UserAccount userAccount = userAccountService.selectById(userAccountId);
    	
    	UserResult<Boolean> flag = userCenterService.modifyAccountState(userAccount.getUserId(), AccountStateEnum.FREEZE.getCode() + "");
    	
    	if(!flag.isSucceed() || !flag.getResult()){
    		return new ErrorTip(0, flag.getRetMsg());
    	}
    	return SUCCESS_TIP;
    }
    
    /**
     * 解冻用户账户
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/unfreeze")
    @BussinessLog(value = "解冻用户账户", json = "true")
    @ResponseBody
    public Object unfreeze(@RequestParam String userAccountId) {
    	
    	UserAccount userAccount = userAccountService.selectById(userAccountId);
    	
    	UserResult<Boolean> flag = userCenterService.modifyAccountState(userAccount.getUserId(), AccountStateEnum.NORMAL.getCode() + "");
    	
    	if(!flag.isSucceed() || !flag.getResult()){
    		return new ErrorTip(0, flag.getRetMsg());
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 用户账户详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{userAccountId}")
    @ResponseBody
    public Object detail(@PathVariable("userAccountId") String userAccountId) {
        return userAccountService.selectById(userAccountId);
    }
}
