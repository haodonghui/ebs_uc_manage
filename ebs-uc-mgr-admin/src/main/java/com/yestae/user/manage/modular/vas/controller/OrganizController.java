package com.yestae.user.manage.modular.vas.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.IdGenerator;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IPageContentService;
import com.yestae.user.manage.modular.vas.service.IVasImageService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 机构控制器
 *
 * @author fengshuonan
 * @Date 2018-07-10 16:41:46
 */
@Controller
@RequestMapping("/organiz")
public class OrganizController extends BaseController {

    private String PREFIX = "/vas/organiz/";

    @Autowired
    private IOrganizService organizService;
    @Autowired
    private IVasImageService vasImageService;
    @Resource
	IPageContentService pageContentService; 
    @Resource
    UcConstant ucConstant;

    /**
     * 跳转到机构首页
     */
    @RequestMapping("/organiz_select")
    public String organizSelect() {
        return PREFIX + "organiz_select.html";
    }
    /**
     * 跳转到机构首页
     */
    @RequestMapping("")
    public String index() {
    	return PREFIX + "organiz.html";
    }

    /**
     * 跳转到添加机构
     */
    @RequestMapping("/organiz_add")
    public String organizAdd() {
        return PREFIX + "organiz_add.html";
    }

    /**
     * 跳转到修改机构
     */
    @RequestMapping("/organiz_update/{organizId}")
    @DataSource(name="dataSourceUc")
    public String organizUpdate(@PathVariable String organizId, Model model) {
        Organiz organiz = organizService.getById(organizId);
        
        VasImage organizImage = vasImageService.findOneVasImage(organizId, VasConstants.VI_BIZ_TYPE_ORGANIZ);
        if(organizImage != null){
        	organiz.setSurfaceId(organizImage.getId());
        	organiz.setSurfaceUrl(ucConstant.getImageServer() + organizImage.getLarge());
        }
        VasImage organizLogo = vasImageService.findOneVasImage(organizId, VasConstants.VI_BIZ_TYPE_ORGANIZ_VALID_LOGO);
        if(organizLogo != null){
        	organiz.setOrganizLogo(organizLogo.getId());
        	organiz.setOrganizLogoUrl(ucConstant.getImageServer() + organizLogo.getLarge());
        }
        VasImage logo = vasImageService.findOneVasImage(organizId, VasConstants.VI_BIZ_TYPE_ORGANIZ_LOGO);
        if(logo != null){
        	organiz.setLogo(logo.getId());
        	organiz.setLogoUrl(ucConstant.getImageServer() + logo.getLarge());
        }
        VasImage organizLogoInvalid = vasImageService.findOneVasImage(organizId, VasConstants.VI_BIZ_TYPE_ORGANIZ_INVALID_LOGO);
        if(organizLogoInvalid != null){
        	organiz.setOrganizLogoInvalid(organizLogoInvalid.getId());
        	organiz.setOrganizLogoInvalidUrl(ucConstant.getImageServer() + organizLogoInvalid.getLarge());
        }
        PageContent pageContent = pageContentService.findOnePageContent(organizId, VasConstants.PC_BIZ_TYPE_ORGANIZ_STORY);
        if(pageContent != null){
        	organiz.setContent(pageContent.getContent());
        }
        model.addAttribute("organiz",organiz);
        return PREFIX + "organiz_edit.html";
    }

    /**
     * 获取机构列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(@RequestParam(required = false) String code,
    		@RequestParam(required = false) String name, @RequestParam(required = false) Integer status) {
    	Page<Map<String, Object>> page = new Page();
        QueryWrapper<Organiz> wrapper = new QueryWrapper<>();
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("organiz_name", name);
    	}
    	if(StringUtils.isNotEmpty(code)){
    		wrapper.like("organiz_code", code);
    	}
    	if(status != null){
    		wrapper.eq("status", status);
    	}
    	wrapper.eq("del_flag", VasConstants.YES);
    	wrapper.orderBy(false,false,"create_time");
    	page = organizService.pageMaps(page, wrapper);
    	List<Map<String, Object>> list = page.getRecords();
    	for(Map<String, Object> map: list){
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "status:" + MapUtils.getObject(map, "status")));
    	}
        return super.packForBT(page);
    }
    
    /**
     * 获取机构列表
     */
    @RequestMapping(value = "/list/all")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object listAll(@RequestParam(required = true) String organizIds,@RequestParam(required = false) String code,
    		@RequestParam(required = false) String name, @RequestParam(required = false) Integer status) {

        QueryWrapper<Organiz> wrapper = new QueryWrapper<>();
    	if(StringUtils.isEmpty("organizIds")){
    		return new ArrayList<>();
    	}else{
    		wrapper.in("id", organizIds.split(","));
    	}
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("organiz_name", name);
    	}
    	if(StringUtils.isNotEmpty(code)){
    		wrapper.like("organiz_code", code);
    	}
    	if(status != null){
    		wrapper.eq("status", status);
    	}
    	wrapper.eq("del_flag", VasConstants.YES);
    	wrapper.orderBy(false,false,"create_time");
    	List<Map<String, Object>> list = organizService.listMaps(wrapper);
    	for(Map<String, Object> map: list){
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "status:" + MapUtils.getObject(map, "status")));
    	}
    	return list;
    }

    /**
     * 新增机构
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(Organiz organiz) {
    	if(organiz != null){
    		organiz.setCreateBy(ShiroKit.getUser().getId());
    		organiz.setCreateTime(new Date().getTime());
    		organiz.setOrganizCode(IdGenerator.getId());
    		organizService.insertOrganiz(organiz);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 修改机构
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(Organiz organiz) {
    	if(organiz != null && !StringUtils.isEmpty(organiz.getId())){
    		organiz.setUpdateBy(ShiroKit.getUser().getId());
    		organiz.setUpdateTime(new Date().getTime());
    		organizService.updateOrganiz(organiz);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 删除机构
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String organizId) {
    	Organiz organiz = organizService.getById(organizId);
    	if(organiz != null){
    		organiz.setUpdateBy(ShiroKit.getUser().getId());
    		organiz.setUpdateTime(new Date().getTime());
    		organiz.setDelFlag(VasConstants.NO);
    		organizService.updateById(organiz);
    	}
        return SUCCESS_TIP;
    }
    /**
     * 机构上线
     */
    @RequestMapping(value = "/online")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object online(@RequestParam String organizId) {
    	Organiz organiz = organizService.getById(organizId);
    	if(organiz != null){
    		organiz.setUpdateBy(ShiroKit.getUser().getId());
    		organiz.setUpdateTime(new Date().getTime());
    		organiz.setStatus(VasConstants.STATUS_ON);
    		organizService.updateById(organiz);
    	}
    	return SUCCESS_TIP;
    }
    
    /**
     * 机构下线
     */
    @RequestMapping(value = "/offline")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object offline(@RequestParam String organizId) {
    	Organiz organiz = organizService.getById(organizId);
    	if(organiz != null){
    		organiz.setUpdateBy(ShiroKit.getUser().getId());
    		organiz.setUpdateTime(new Date().getTime());
    		organiz.setStatus(VasConstants.STATUS_OFF);
    		organizService.updateById(organiz);
    	}
    	return SUCCESS_TIP;
    }


    /**
     * 机构详情
     */
    @RequestMapping(value = "/detail/{organizId}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object detail(@PathVariable("organizId") String organizId) {
        return organizService.getById(organizId);
    }
}
