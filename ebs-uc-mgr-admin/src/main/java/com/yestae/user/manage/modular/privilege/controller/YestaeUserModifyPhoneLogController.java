package com.yestae.user.manage.modular.privilege.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyPhoneLog;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserModifyPhoneLogService;
import com.yestae.user.manage.modular.system.persistence.model.User;
import com.yestae.user.manage.modular.system.service.IUserService;

/**
 * 用户手机号变更日志控制器
 *
 * @author fengshuonan
 * @Date 2017-11-12 17:47:54
 */
@Controller
@RequestMapping("/yestaeUserModifyPhoneLog")
public class YestaeUserModifyPhoneLogController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserModifyPhoneLog/";
    
	@Resource
	IUserService userService;

    @Autowired
    private IYestaeUserModifyPhoneLogService yestaeUserModifyPhoneLogService;

    /**
     * 跳转到用户手机号变更日志首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserModifyPhoneLog.html";
    }

    /**
     * 跳转到添加用户手机号变更日志
     */
    @RequestMapping("/yestaeUserModifyPhoneLog_add")
    public String yestaeUserModifyPhoneLogAdd() {
        return PREFIX + "yestaeUserModifyPhoneLog_add.html";
    }

    /**
     * 跳转到修改用户手机号变更日志
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserModifyPhoneLog_update/{yestaeUserModifyPhoneLogId}")
    public String yestaeUserModifyPhoneLogUpdate(@PathVariable String yestaeUserModifyPhoneLogId, Model model) {
        YestaeUserModifyPhoneLog yestaeUserModifyPhoneLog = yestaeUserModifyPhoneLogService.selectById(yestaeUserModifyPhoneLogId);
        model.addAttribute("item",yestaeUserModifyPhoneLog);
        return PREFIX + "yestaeUserModifyPhoneLog_edit.html";
    }

    /**
     * 获取用户手机号变更日志列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeUserModifyPhoneLogService.selectYestaeUserModifyPhoneLogList(page, map);
    	this.dealList(list);
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    
	public void dealList(List<Map<String, Object>> list){
		for(Map<String, Object> m: list){
			
			m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
			m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
			m.put("sourceMobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "sourceMobile")));
			m.put("targetMobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "targetMobile")));
			String id = MapUtils.getString(m, "operatorId");
			if(!StringUtils.isEmpty(id)){
				if(id.equals(MapUtils.getString(m, "userId"))){
					m.put("operatorName", MapUtils.getString(m, "name"));
				}else{
					User user = userService.selectUserById(id);
					if(user != null){
						m.put("operatorName", user.getName());
					}
				}
			}
    	}
	}

}
