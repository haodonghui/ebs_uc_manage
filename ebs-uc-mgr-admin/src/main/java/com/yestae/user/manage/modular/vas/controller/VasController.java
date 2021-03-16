package com.yestae.user.manage.modular.vas.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.IdGenerator;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.persistence.model.Vas;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IPageContentService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
import com.yestae.user.manage.modular.vas.service.IVasService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 增值服务控制器
 *
 * @author fengshuonan
 * @Date 2018-07-10 15:41:01
 */
@Controller
@RequestMapping("/vas")
public class VasController extends BaseController {

    private String PREFIX = "/vas/vas/";

    @Autowired
    private IVasService vasService;
    @Autowired
    private IOrganizService organizService;
    @Resource
	IPageContentService pageContentService; 
    @Autowired
    private IVasImageService vasImageService;
    @Resource
    UcConstant ucConstant;

    /**
     * 跳转到增值服务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vas.html";
    }

    /**
     * 跳转到添加增值服务
     */
    @RequestMapping("/vas_add")
    public String vasAdd() {
        return PREFIX + "vas_add.html";
    }

    /**
     * 跳转到修改增值服务
     */
    @RequestMapping("/vas_update/{vasId}")
    @DataSource(name="dataSourceUc")
    public String vasUpdate(@PathVariable String vasId, Model model) {
        Vas vas = vasService.getById(vasId);
        
        if(vas != null){
        	
        	Organiz organiz = organizService.getById(vas.getOrganizId());
        	if(organiz != null){
        		model.addAttribute("organizName", organiz.getOrganizName());
        	}
        	
        	PageContent pageContent1 = pageContentService.findOnePageContent(vasId, VasConstants.PC_BIZ_TYPE_EQUITY_DETAIL);
        	if(pageContent1 != null){
        		vas.setEquityDetail(pageContent1.getContent());
        	}
        	
        	PageContent pageContent2 = pageContentService.findOnePageContent(vasId, VasConstants.PC_BIZ_TYPE_FAQ);
        	if(pageContent2 != null){
        		vas.setFaq(pageContent2.getContent());
        	}
        	
        	PageContent pageContent3 = pageContentService.findOnePageContent(vasId, VasConstants.PC_BIZ_TYPE_SERVICE_TERMS);
        	if(pageContent3 != null){
        		vas.setServiceTerms(pageContent3.getContent());
        	}
        	
        	VasImage vasImage = vasImageService.findOneVasImage(vasId, VasConstants.VI_BIZ_TYPE_VAS);
            if(vasImage != null){
            	vas.setSurfaceId(vasImage.getId());
            	vas.setSurfaceUrl(ucConstant.getImageServer() + vasImage.getLarge());
            }
        }
        
        
        model.addAttribute("vas", vas);
        return PREFIX + "vas_edit.html";
    }

    /**
     * 获取增值服务列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(String condition) {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
    	List<Map<String, Object>> vasList = vasService.selectVasList(page, paramMap);
    	for(Map<String, Object> map: vasList){
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    		map.put("validTypeName", CacheKit.get(Cache.CONSTANT, "vasValidType:" + MapUtils.getObject(map, "validType")));
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "vasStatus:" + MapUtils.getObject(map, "status")));
    	}
    	page.setRecords(vasList);
    	return super.packForBT(page);
    }

    /**
     * 新增增值服务
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(Vas vas) {
    	if(vas != null){
    		vas.setCreateBy(ShiroKit.getUser().getId());
    		vas.setCreateTime(new Date().getTime());
    		vas.setVasCode(IdGenerator.getId());
    		vasService.insertVas(vas);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 删除增值服务
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String vasId) {
    	Vas vas = vasService.getById(vasId);
    	if(vas != null){
    		if(VasConstants.VAS_STATUS_NO_PUBLISH != vas.getStatus()){
    			return new ErrorTip(0, "增值服务不是待发布状态，不能删除");
    		}
    		vas.setUpdateBy(ShiroKit.getUser().getId());
    		vas.setUpdateTime(new Date().getTime());
    		vas.setDelFlag(VasConstants.NO);
    		vasService.updateById(vas);
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 发布增值服务
     */
    @RequestMapping(value = "/publish")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object publish(@RequestParam String vasId) {
    	Vas vas = vasService.getById(vasId);
    	if(vas != null){
    		vas.setUpdateBy(ShiroKit.getUser().getId());
    		vas.setUpdateTime(new Date().getTime());
    		vas.setStatus(VasConstants.VAS_STATUS_ONLINE);
    		vasService.updateById(vas);
    	}
    	return SUCCESS_TIP;
    }
    
    /**
     * 停用增值服务
     */
    @RequestMapping(value = "/offline")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object offline(@RequestParam String vasId) {
    	Vas vas = vasService.getById(vasId);
    	if(vas != null){
    		vas.setUpdateBy(ShiroKit.getUser().getId());
    		vas.setUpdateTime(new Date().getTime());
    		vas.setStatus(VasConstants.VAS_STATUS_OFFLINE);
    		vasService.updateById(vas);
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 修改增值服务
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(Vas vas) {
    	if(vas != null && !StringUtils.isEmpty(vas.getId())){
    		vas.setUpdateBy(ShiroKit.getUser().getId());
    		vas.setUpdateTime(new Date().getTime());
    		vasService.updateVas(vas);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 增值服务详情
     */
    @RequestMapping(value = "/detail/{vasId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("vasId") String vasId) {
        return vasService.getById(vasId);
    }
}
