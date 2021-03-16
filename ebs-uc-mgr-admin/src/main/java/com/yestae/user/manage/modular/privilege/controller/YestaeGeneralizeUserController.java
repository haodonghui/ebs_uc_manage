package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.common.util.ToolUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.ExcelUtil;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.InviterIsFCodeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.PlatformUser;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeChannel;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;
import com.yestae.user.manage.modular.privilege.service.IPlatformUserService;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeChannelService;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeUserService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 推广人控制器
 *
 * @author fengshuonan
 * @Date 2017-11-28 20:59:21
 */
@Controller
@RequestMapping("/yestaeGeneralizeUser")
public class YestaeGeneralizeUserController extends BaseController {

    private String PREFIX = "/privilege/yestaeGeneralizeUser/";

    @Autowired
    private IPlatformUserService platformUserService;
    
    @Autowired
    private IYestaeGeneralizeUserService yestaeGeneralizeUserService;

    @Autowired
    private IYestaeGeneralizeChannelService yestaeGeneralizeChannelService;
    
    @Resource
    private ExcelUtil excelUtil; 
    
    private Logger logger = LoggerFactory.getLogger(YestaeGeneralizeUserController.class);

    /**
     * 跳转到推广人首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeGeneralizeUser.html";
    }
    
    /**
     * 跳转到推广人选择列表
     */
    @RequestMapping("yestaeGeneralizeUser_select")
    public String yestaeGeneralizeUserSelect() {
    	return PREFIX + "yestaeGeneralizeUser_select.html";
    }

    /**
     * 跳转到添加推广人
     */
    @RequestMapping("/yestaeGeneralizeUser_add")
    public String yestaeGeneralizeUserAdd() {
        return PREFIX + "yestaeGeneralizeUser_add.html";
    }

    /**
     * 跳转到修改推广人
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeGeneralizeUser_update/{yestaeGeneralizeUserId}")
    public String yestaeGeneralizeUserUpdate(@PathVariable String yestaeGeneralizeUserId, Model model) {
        YestaeGeneralizeUser yestaeGeneralizeUser = yestaeGeneralizeUserService.getById(yestaeGeneralizeUserId);
        YestaeGeneralizeChannel channel = yestaeGeneralizeChannelService.getById(yestaeGeneralizeUser.getChannelId());
        if(channel != null){
        	//渠道名称
        	model.addAttribute("channelName", channel.getName());
        }
        if(yestaeGeneralizeUser.getUserId() != null){
            QueryWrapper<PlatformUser> wrapper = new QueryWrapper<>();
			List<PlatformUser> platformUserList = platformUserService.list(wrapper);
			if(platformUserList != null && platformUserList.size() > 0){
				PlatformUser platformUser = platformUserList.get(0);
				if(platformUser != null){
					model.addAttribute("platformUserName", platformUser.getName());
				}
			}
        }
        model.addAttribute("yestaeGeneralizeUser",yestaeGeneralizeUser);
        LogObjectHolder.me().set(yestaeGeneralizeUser);
        return PREFIX + "yestaeGeneralizeUser_edit.html";
    }

    /**
     * 获取推广人列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, Object> map = HttpKit.getRequestParametersMap();
    	List<Map<String, Object>> list = yestaeGeneralizeUserService.selectYestaeGeneralizeUserList(page, map);
        
        for(Map<String, Object> m: list){
        	m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
        	m.put("userMobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "userMobile")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    /**
     * 获取推广人列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list/DYCT")
    @ResponseBody
    public Object listDYCT() {
    	Map<String, Object> map = HttpKit.getRequestParametersMap();
    	List<Map<String, Object>> list = yestaeGeneralizeUserService.selectYestaeGeneralizeUserListDYCT(map);
    	return list;
    }

    /**
     * 新增推广人
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeGeneralizeUser yestaeGeneralizeUser) {
    	yestaeGeneralizeUser.setCreateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeGeneralizeUser.setCreateTime(new Date().getTime());
    	yestaeGeneralizeUser.setIfDel(SysEnum.NO.getCode());
    	yestaeGeneralizeUser.setInviterIsFCode(InviterIsFCodeEnum.F_CODE_NONE.getCode());
    	yestaeGeneralizeUser.setRecommendCode(ToolUtil.getRandomString(6));
    	if(StringUtils.isEmpty(yestaeGeneralizeUser.getUserId())){
    		yestaeGeneralizeUser.setUserId(null);
    	}
        yestaeGeneralizeUserService.insertYestaeGeneralizeUser(yestaeGeneralizeUser);
        return SUCCESS_TIP;
    }
    
    /**
     * 新增推广人
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(@RequestParam("file") MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response) {
    	
    	try {
			InputStream in = file.getInputStream();
			
			List<List<String>> list =  excelUtil.getListByExcel(in, file.getOriginalFilename());
			
			for(List<String> l: list){
				if(l.size() < 3 || StringUtils.isEmpty(l.get(0)) 
						|| StringUtils.isEmpty(l.get(1)) || StringUtils.isEmpty(l.get(2))
						|| StringUtils.isEmpty(l.get(3))){
					logger.error("推广人导入: " + l.toString() + "未成功, 原因：缺少必要字段");
					continue;
				}
				String mobile = l.get(2);
				mobile = mobile.substring(0, mobile.indexOf(".") > 0? mobile.indexOf("."): mobile.length());
				
				String id = l.get(0);
				if(id.startsWith("ObjectId")){
					id = id.replace("ObjectId(", "").replace(")", "");
				}
				String name = l.get(1);
				String channelId = l.get(3);
				
				YestaeGeneralizeUser yestaeGeneralizeUser = yestaeGeneralizeUserService.getById(id);
				
				if(yestaeGeneralizeUser == null){
					
					yestaeGeneralizeUser = new YestaeGeneralizeUser();
					yestaeGeneralizeUser.setId(id);
					yestaeGeneralizeUser.setCreateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
					yestaeGeneralizeUser.setCreateTime(new Date().getTime());
					yestaeGeneralizeUser.setIfDel(SysEnum.NO.getCode());
					yestaeGeneralizeUser.setInviterIsFCode(InviterIsFCodeEnum.F_CODE_EXIST.getCode());
					yestaeGeneralizeUser.setRecommendCode(ToolUtil.getRandomString(6));
					yestaeGeneralizeUser.setMobile(mobile);
					yestaeGeneralizeUser.setName(name);
					yestaeGeneralizeUser.setChannelId(channelId);
					yestaeGeneralizeUserService.insertYestaeGeneralizeUser(yestaeGeneralizeUser);
				}else{
					yestaeGeneralizeUser.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
					yestaeGeneralizeUser.setUpdateTime(new Date().getTime());
					yestaeGeneralizeUser.setMobile(mobile);
					yestaeGeneralizeUser.setName(name);
					yestaeGeneralizeUser.setChannelId(channelId);
					yestaeGeneralizeUserService.updateById(yestaeGeneralizeUser);
				}
			}
    	}catch (Exception e) {
    		throw new BussinessException(BizExceptionEnum.IMPORT_ERROE);
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 删除推广人
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeGeneralizeUserId) {
        yestaeGeneralizeUserService.deleteByYestaeGeneralizeUserId(yestaeGeneralizeUserId);
        return SUCCESS_TIP;
    }

    /**
     * 修改推广人
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeGeneralizeUser yestaeGeneralizeUser) {
    	yestaeGeneralizeUser.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeGeneralizeUser.setUpdateTime(new Date().getTime());
    	if(StringUtils.isEmpty(yestaeGeneralizeUser.getUserId())){
    		yestaeGeneralizeUser.setUserId(null);
    	}
        yestaeGeneralizeUserService.updateYestaeGeneralizeUser(yestaeGeneralizeUser);
        return SUCCESS_TIP;
    }

    /**
     * 推广人详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeGeneralizeUserId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeGeneralizeUserId") String yestaeGeneralizeUserId) {
        return yestaeGeneralizeUserService.getById(yestaeGeneralizeUserId);
    }
    
}
