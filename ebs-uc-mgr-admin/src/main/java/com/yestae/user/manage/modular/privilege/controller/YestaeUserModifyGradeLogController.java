package com.yestae.user.manage.modular.privilege.controller;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.core.util.PrivacyHideUtil;
import com.yestae.user.manage.modular.privilege.common.enums.UserTypeEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserModifyGradeLog;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserModifyGradeLogService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 用户等级变动明细控制器
 *
 * @author fengshuonan
 * @Date 2017-11-12 17:41:58
 */
@Controller
@RequestMapping("/yestaeUserModifyGradeLog")
public class YestaeUserModifyGradeLogController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserModifyGradeLog/";

    @Autowired
    private IYestaeUserModifyGradeLogService yestaeUserModifyGradeLogService;

    /**
     * 跳转到用户等级变动明细首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserModifyGradeLog.html";
    }

    /**
     * 跳转到添加用户等级变动明细
     */
    @RequestMapping("/yestaeUserModifyGradeLog_add")
    public String yestaeUserModifyGradeLogAdd() {
        return PREFIX + "yestaeUserModifyGradeLog_add.html";
    }

    /**
     * 跳转到修改用户等级变动明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserModifyGradeLog_update/{yestaeUserModifyGradeLogId}")
    public String yestaeUserModifyGradeLogUpdate(@PathVariable String yestaeUserModifyGradeLogId, Model model) {
        YestaeUserModifyGradeLog yestaeUserModifyGradeLog = yestaeUserModifyGradeLogService.getById(yestaeUserModifyGradeLogId);
        model.addAttribute("yestaeUserModifyGradeLog",yestaeUserModifyGradeLog);
        LogObjectHolder.me().set(yestaeUserModifyGradeLog);
        return PREFIX + "yestaeUserModifyGradeLog_edit.html";
    }

    /**
     * 获取用户等级变动明细列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	
    	Page<Map<String, Object>> page = new Page();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeUserModifyGradeLogService.selectYestaeUserModifyGradeLogList(page, map);
        
        for(Map<String, Object> m: list){
        	Integer sourceCard = MapUtils.getInteger(m, "sourceCard");
        	Integer targetCard = MapUtils.getInteger(m, "targetCard");
        	String type = null;
        	if(sourceCard != null && targetCard != null && sourceCard != targetCard){
        		type = sourceCard < targetCard ? "升级" : "降级";
        	}
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("sourceCard", UserTypeEnum.valueOf(sourceCard));
    		m.put("targetCard", UserTypeEnum.valueOf(targetCard));
    		m.put("type", type);
    		m.put("mobile", PrivacyHideUtil.mobileHide(MapUtils.getString(m, "mobile")));
    		if(StringUtils.isEmpty(MapUtils.getString(m, "operatorName"))){
    			m.put("operatorName", "系统自动");
    		}
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }

    /**
     * 新增用户等级变动明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeUserModifyGradeLog yestaeUserModifyGradeLog) {
        yestaeUserModifyGradeLogService.save(yestaeUserModifyGradeLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户等级变动明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeUserModifyGradeLogId) {
        yestaeUserModifyGradeLogService.removeById(yestaeUserModifyGradeLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户等级变动明细
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeUserModifyGradeLog yestaeUserModifyGradeLog) {
        yestaeUserModifyGradeLogService.updateById(yestaeUserModifyGradeLog);
        return SUCCESS_TIP;
    }

    /**
     * 用户等级变动明细详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeUserModifyGradeLogId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeUserModifyGradeLogId") String yestaeUserModifyGradeLogId) {
        return yestaeUserModifyGradeLogService.getById(yestaeUserModifyGradeLogId);
    }
}
