package com.yestae.user.manage.modular.privilege.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.yestae.user.manage.common.constant.factory.PageFactory;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.modular.privilege.common.enums.InviterIsFCodeEnum;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserRelationService;

/**
 * 用户邀请关系控制器
 *
 * @author fengshuonan
 * @Date 2017-11-12 17:38:07
 */
@Controller
@RequestMapping("/yestaeUserRelation")
public class YestaeUserRelationController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserRelation/";

    @Autowired
    private IYestaeUserRelationService yestaeUserRelationService;

    /**
     * 跳转到用户邀请关系首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserRelation.html";
    }


    /**
     * 获取用户邀请关系列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	
    	Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeUserRelationService.selectYestaeUserRelationList(page, map);
       
    	for(Map<String, Object> m: list){
    		m.put("userId", MapUtils.getString(m, "userId"));
    		m.put("inviterIsFCode", InviterIsFCodeEnum.valueOf(MapUtils.getInteger(map, "inviterIsFCode")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }

}
