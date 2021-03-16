package com.yestae.user.manage.modular.vas.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroKit;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.DateUtils;
import com.yestae.user.manage.core.util.IdGenerator;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.Organiz;
import com.yestae.user.manage.modular.vas.persistence.model.Store;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IOrganizService;
import com.yestae.user.manage.modular.vas.service.IStoreService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 门店控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:07:32
 */
@Controller
@RequestMapping("/store")
public class StoreController extends BaseController {

    private String PREFIX = "/vas/store/";

    @Autowired
    private IStoreService storeService;
    @Autowired
    private IOrganizService organizService;
    @Autowired
    private IVasImageService vasImageService;
    @Resource
    UcConstant ucConstant;

    /**
     * 跳转到门店首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "store.html";
    }

    /**
     * 跳转到添加门店
     */
    @RequestMapping("/store_add")
    public String storeAdd() {
        return PREFIX + "store_add.html";
    }

    /**
     * 跳转到修改门店
     */
    @RequestMapping("/store_update/{storeId}")
    @DataSource(name="dataSourceUc")
    public String storeUpdate(@PathVariable String storeId, Model model) {
        Store store = storeService.getById(storeId);
        if(store != null){
        	Organiz organiz = organizService.getById(store.getOrganizId());
        	if(organiz != null){
        		model.addAttribute("organizName", organiz.getOrganizName());
        	}
        	
        	VasImage vasImage = vasImageService.findOneVasImage(storeId, VasConstants.VI_BIZ_TYPE_STORE);
            if(vasImage != null){
            	store.setSurfaceId(vasImage.getId());
            	store.setSurfaceUrl(ucConstant.getImageServer() + vasImage.getLarge());
            }
        	
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            if(store.getStartTime() != null){
            	model.addAttribute("startTime", timeFormat.format(new Date(store.getStartTime())));
            }
            if(store.getEndTime() != null){
            	model.addAttribute("endTime", timeFormat.format(new Date(store.getEndTime())));
            }
        }
        
        model.addAttribute("store",store);
        LogObjectHolder.me().set(store);
        return PREFIX + "store_edit.html";
    }

    /**
     * 获取门店列表
     */
    @RequestMapping(value = "/list")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object list(String condition) {
    	Page<Map<String, Object>> page = new Page();
    	Map<String, Object> paramMap = HttpKit.getRequestParametersMap();
    	List<Map<String, Object>> storeList = storeService.selectList(page, paramMap);
    	
    	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    	for(Map<String, Object> map: storeList){
    		map.put("createTime", DateUtils.toDatetimeString(MapUtils.getLong(map, "createTime")));
    		map.put("statusName", CacheKit.get(Cache.CONSTANT, "status:" + MapUtils.getObject(map, "status")));
    		map.put("recommendName", CacheKit.get(Cache.CONSTANT, "recommendFlag:" + MapUtils.getObject(map, "recommendFlag")));
            map.put("delFlagName", CacheKit.get(Cache.CONSTANT, "delFlag:" + MapUtils.getObject(map, "felFlag")));
    		Long startTime = MapUtils.getLong(map, "startTime");
    		Long endTime = MapUtils.getLong(map, "endTime");
    		
    		map.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(map, "mobile")));
    		if(startTime != null){
    			map.put("startTime", timeFormat.format(new Date(startTime)));
            }
            if(endTime != null){
            	map.put("endTime", timeFormat.format(new Date(endTime)));
            }
    	}
    	page.setRecords(storeList);
    	return super.packForBT(page);
    }
    
    public static void main(String[] args) {
    	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    	System.out.println(timeFormat.format(new Date(4323300000l)));
	}

    /**
     * 新增门店
     */
    @RequestMapping(value = "/add")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object add(Store store) {
    	
    	if(store != null){
    		store.setCreateBy(ShiroKit.getUser().getId());
    		store.setCreateTime(new Date().getTime());
    		store.setStoreCode(IdGenerator.getId());
    		storeService.insertStore(store);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 删除门店
     */
    @RequestMapping(value = "/delete")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object delete(@RequestParam String storeId) {
    	Store store = storeService.getById(storeId);
        if(store != null){
        	store.setUpdateBy(ShiroKit.getUser().getId());
        	store.setUpdateTime(new Date().getTime());
        	store.setDelFlag(VasConstants.NO);
    		storeService.updateById(store);
    	}
        return SUCCESS_TIP;
    }

    /**
     * 修改门店
     */
    @RequestMapping(value = "/update")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object update(Store store) {
    	if(store != null && !StringUtils.isEmpty(store.getId())){
    		store.setUpdateBy(ShiroKit.getUser().getId());
    		store.setUpdateTime(new Date().getTime());
    		storeService.updateStore(store);
    	}
        return SUCCESS_TIP;
    }
    
    /**
     * 门店启用
     */
    @RequestMapping(value = "/online")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object online(@RequestParam String storeId) {
    	Store store = storeService.getById(storeId);
    	if(store != null){
    		store.setUpdateBy(ShiroKit.getUser().getId());
    		store.setUpdateTime(new Date().getTime());
    		store.setStatus(VasConstants.STATUS_ON);
    		storeService.updateById(store);
    	}
    	return SUCCESS_TIP;
    }
    /**
     * 门店停用
     */
    @RequestMapping(value = "/offline")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public Object offline(@RequestParam String storeId) {
    	Store store = storeService.getById(storeId);
    	if(store != null){
    		store.setUpdateBy(ShiroKit.getUser().getId());
    		store.setUpdateTime(new Date().getTime());
    		store.setStatus(VasConstants.STATUS_OFF);
    		storeService.updateById(store);
    	}
    	return SUCCESS_TIP;
    }

    /**
     * 门店详情
     */
    @RequestMapping(value = "/detail/{storeId}")
    @ResponseBody
    public Object detail(@PathVariable("storeId") String storeId) {
        return storeService.getById(storeId);
    }
}
