package com.yestae.user.manage.modular.system.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.yestae.user.common.cache.CacheKit;
import com.yestae.user.manage.common.constant.cache.Cache;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.base.tips.ErrorTip;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.modular.system.common.constant.SysConstant;
import com.yestae.user.manage.modular.system.persistence.model.Dict;
import com.yestae.user.manage.modular.system.service.IDictService;

/**
 * 数据字典控制器
 *
 * @author fengshuonan
 * @Date 2018-05-23 17:47:23
 */
@Controller
@RequestMapping("/dict")
public class DictController extends BaseController {


    @Autowired
    private IDictService dictService;
    
    @Resource
    private SysConstant sysConstant;

    private String PREFIX = "/system/dict/";
    /**
     * 跳转到数据字典首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "dict.html";
    }

    /**
     * 跳转到添加数据字典
     */
    @RequestMapping("/dict_add")
    public String dictAdd() {
        return PREFIX + "dict_add.html";
    }

    /**
     * 跳转到修改数据字典
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping("/dict_update/{dictId}")
    public String dictUpdate(@PathVariable String dictId, Model model) {
        Dict dict = dictService.selectById(dictId);
        model.addAttribute("dict",dict);
        return PREFIX + "dict_edit.html";
    }

    /**
     * 获取数据字典列表
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name
    		, @RequestParam(required = false) String code
    		, @RequestParam(required = false) String pcode) {
        Wrapper<Dict> wrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(name)){
        	wrapper.like("name", name);
        }
        if(!StringUtils.isEmpty(code)){
        	wrapper.eq("code", code);
        }
        if(!StringUtils.isEmpty(pcode)){
    		wrapper.eq("pcode", pcode);
        }
        wrapper.orderBy("code");
		return dictService.selectList(wrapper);
    }

    /**
     * 新增数据字典
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Dict dict) {
    	if(null == dict || StringUtils.isEmpty(dict.getCode())|| StringUtils.isEmpty(dict.getName())){
    		return new ErrorTip(0, "缺少参数");
    	}
    	Wrapper<Dict> wrapper = getWrapper(dict);
    	int i = dictService.selectCount(wrapper );
    	if(i > 0){
    		return new ErrorTip(0, "编码重复");
    	}
        boolean flag = dictService.insert(dict);
        if(flag){
        	if(sysConstant.getDictRoot().equals(dict.getPcode())){
        		CacheKit.put(Cache.CONSTANT, dict.getCode(), dict.getName());
        	}else{
        		CacheKit.put(Cache.CONSTANT, dict.getPcode() + ":" + dict.getCode(), dict.getName());
        	}
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除数据字典
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String dictId) {
    	Dict dict = dictService.selectById(dictId);
    	if(null != dict){
    		Wrapper<Dict> wrapper = new EntityWrapper<>();
    		wrapper.eq("pcode", dict.getCode());
    		int i = dictService.selectCount(wrapper );
    		if(i > 0){
    			return new ErrorTip(0, "请先删除下级编码");
    		}
    		boolean flag = dictService.deleteById(dictId);
    		if(flag){
            	if(sysConstant.getDictRoot().equals(dict.getPcode())){
            		CacheKit.remove(Cache.CONSTANT, dict.getCode());
            	}else{
            		CacheKit.remove(Cache.CONSTANT, dict.getPcode() + ":" + dict.getCode());
            	}
            }
    	}
        return SUCCESS_TIP;
    }

    /**
     * 修改数据字典
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Dict dict) {
    	if(null == dict){
    		return new ErrorTip(0, "缺少参数");
    	}
    	Wrapper<Dict> wrapper = getWrapper(dict);
    	wrapper.ne("id", dict.getId());
    	int i = dictService.selectCount(wrapper );
    	if(i > 0){
    		return new ErrorTip(0, "编码重复");
    	}
        boolean flag = dictService.updateAllColumnById(dict);
        if(flag){
        	if(sysConstant.getDictRoot().equals(dict.getPcode())){
        		CacheKit.put(Cache.CONSTANT, dict.getCode(), dict.getName());
        	}else{
        		CacheKit.put(Cache.CONSTANT, dict.getPcode() + ":" + dict.getCode(), dict.getName());
        	}
        }
        return SUCCESS_TIP;
    }
    
    /**
     * 修改数据字典
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/refresh")
    @ResponseBody
    public Object refresh() {
    	
    	List<Dict> list = dictService.selectList(null);
		for(Dict d: list){
			if(!StringUtils.isEmpty(d.getPcode()) && !StringUtils.isEmpty(d.getCode())){
				
				if("0".equals(d.getPcode())){
					CacheKit.put(Cache.CONSTANT, d.getCode(), d.getName());
				}else{
					CacheKit.put(Cache.CONSTANT, d.getPcode() + ":" + d.getCode(), d.getName());
				}
			}
		}
    	return SUCCESS_TIP;
    }

    /**
     * 数据字典详情
     */
    @DataSource(name="dataSourceUcMgr")
    @RequestMapping(value = "/detail/{dictId}")
    @ResponseBody
    public Object detail(@PathVariable("dictId") String dictId) {
        return dictService.selectById(dictId);
    }
    
    private Wrapper<Dict> getWrapper(Dict dict){
    	
    	Wrapper<Dict> wrapper = new EntityWrapper<>();
    	if(StringUtils.isEmpty(dict.getPcode())){
    		dict.setPcode(sysConstant.getDictRoot());
    	}
    	wrapper.in("pcode", dict.getPcode() + "," + sysConstant.getDictRoot());
    	wrapper.eq("code", dict.getCode());
    	return wrapper;
    }
}
