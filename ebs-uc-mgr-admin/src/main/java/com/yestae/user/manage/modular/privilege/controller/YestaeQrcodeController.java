package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.FileUtil;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeUser;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcode;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcodeScene;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeUserService;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeSceneService;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 二维码控制器
 *
 * @author fengshuonan
 * @Date 2017-12-05 11:45:52
 */
@Controller
@RequestMapping("/yestaeQrcode")
public class YestaeQrcodeController extends BaseController {

    private String PREFIX = "/privilege/yestaeQrcode/";
    
    @Resource
	UcConstant ucConstant;

    @Autowired
    private IYestaeQrcodeService yestaeQrcodeService;
    
    @Autowired
    private IYestaeGeneralizeUserService yestaeGeneralizeUserService;
    
    @Autowired
    private IYestaeQrcodeSceneService yestaeQrcodeSceneService;

    /**
     * 跳转到二维码首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeQrcode.html";
    }

    /**
     * 跳转到添加二维码
     */
    @RequestMapping("/yestaeQrcode_add")
    public String yestaeQrcodeAdd() {
        return PREFIX + "yestaeQrcode_add.html";
    }

    /**
     * 跳转到修改二维码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeQrcode_update/{yestaeQrcodeId}")
    public String yestaeQrcodeUpdate(@PathVariable String yestaeQrcodeId, Model model) {
        YestaeQrcode yestaeQrcode = yestaeQrcodeService.getById(yestaeQrcodeId);
        model.addAttribute("yestaeQrcode",yestaeQrcode);
        model.addAttribute("generalizeUserName","");
        model.addAttribute("sceneName","");
        if(yestaeQrcode != null){
        	YestaeGeneralizeUser yestaeGeneralizeUser = yestaeGeneralizeUserService.getById(yestaeQrcode.getGeneralizeUserId());
        	if(yestaeGeneralizeUser != null){
        		model.addAttribute("generalizeUserName", yestaeGeneralizeUser.getName());
        	}
        	YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.getById(yestaeQrcode.getSceneId());
        	if(yestaeQrcodeScene != null){
        		model.addAttribute("sceneName", yestaeQrcodeScene.getName());
        	}
        	YestaeQrcodeScene yestaeQrcodeSceneNext = yestaeQrcodeSceneService.getById(yestaeQrcode.getNextSceneId());
        	if(yestaeQrcodeSceneNext != null){
        		model.addAttribute("nextSceneName", yestaeQrcodeSceneNext.getName());
        	}
        }
        LogObjectHolder.me().set(yestaeQrcode);
        return PREFIX + "yestaeQrcode_edit.html";
    }

    /**
     * 获取二维码列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeQrcodeService.selectYestaeQrcodeList(page, map);
        
        for(Map<String, Object> m: list){
        	m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("userTypeName", CacheKit.get(Cache.CONSTANT, "platformUserType:" + MapUtils.getObject(m, "userType")));
    		m.put("typeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneType:" + MapUtils.getInteger(m, "type")));
    		m.put("applyScopeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneApplyScope:" + MapUtils.getInteger(m, "applyScope")));
    		m.put("jumpTypeName", CacheKit.get(Cache.CONSTANT, "qrcodeSceneJumpType:" + MapUtils.getInteger(m, "jumpType")));
    		m.put("codeUrl", ucConstant.getImageServer() + MapUtils.getString(m, "codeUrl"));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }

    /**
     * 新增二维码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeQrcode yestaeQrcode) {
    	yestaeQrcode.setCreateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeQrcode.setCreateTime(new Date().getTime());
    	yestaeQrcode.setIfDel(SysEnum.NO.getCode());
    	YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.getById(yestaeQrcode.getSceneId());
    	String page = CacheKit.get(Cache.CONSTANT, "qrcodeSceneTypePage:" + yestaeQrcodeScene.getType());
        yestaeQrcodeService.insertYestaeQrcode(yestaeQrcode, ucConstant.getImageDir(), ucConstant.getWeixinQrCodeDir(), page);
        return SUCCESS_TIP;
    }

    /**
     * 删除二维码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeQrcodeId) {
    	YestaeQrcode yestaeQrcode = yestaeQrcodeService.getById(yestaeQrcodeId);
    	yestaeQrcode.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeQrcode.setUpdateTime(new Date().getTime());
    	yestaeQrcode.setIfDel(SysEnum.YES.getCode());
    	yestaeQrcodeService.updateById(yestaeQrcode);
        return SUCCESS_TIP;
    }

    /**
     * 修改二维码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeQrcode yestaeQrcode) {
    	yestaeQrcode.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeQrcode.setUpdateTime(new Date().getTime());
        yestaeQrcodeService.updateYestaeQrcode(yestaeQrcode);
        return SUCCESS_TIP;
    }
    
    /**
     * 下载二维码
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/download")
    public void download(@RequestParam String yestaeQrcodeId) {
    	YestaeQrcode yestaeQrcode = yestaeQrcodeService.getById(yestaeQrcodeId);
    	if(yestaeQrcode != null){
    		String path = yestaeQrcode.getCodeUrl();
    		if(!StringUtils.isEmpty(path)){
    			byte[] bytes = FileUtil.toByteArray(ucConstant.getImageDir() + path);
    			OutputStream out = null;
    			try {
    				out = getHttpServletResponse().getOutputStream();
    				out.write(bytes);
    				out.flush();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}finally{
    				if(out != null){
    					try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
    				}
    			}
    		}
    	}
    }

    /**
     * 二维码详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeQrcodeId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeQrcodeId") String yestaeQrcodeId) {
        return yestaeQrcodeService.getById(yestaeQrcodeId);
    }
}
