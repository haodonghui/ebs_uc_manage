package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.shiro.ShiroUser;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserGrade;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserGradeService;
import com.yestae.user.manage.modular.privilege.vo.YestaeUserGradeVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户会员等级控制器
 *
 * @author fengshuonan
 * @Date 2017-11-12 17:39:51
 */
@Controller
@RequestMapping("/yestaeUserGrade")
public class YestaeUserGradeController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserGrade/";

    @Autowired
    private IYestaeUserGradeService yestaeUserGradeService;

    /**
     * 跳转到用户会员等级首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserGrade.html";
    }

    /**
     * 跳转到添加用户会员等级
     */
    @RequestMapping("/yestaeUserGrade_add")
    public String yestaeUserGradeAdd() {
        return PREFIX + "yestaeUserGrade_add.html";
    }

    /**
     * 跳转到修改用户会员等级
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserGrade_update/{yestaeUserGradeId}")
    public String yestaeUserGradeUpdate(@PathVariable String yestaeUserGradeId, Model model) {
        YestaeUserGrade yestaeUserGrade = yestaeUserGradeService.getById(yestaeUserGradeId);
        model.addAttribute("yestaeUserGrade",yestaeUserGrade);
        LogObjectHolder.me().set(yestaeUserGrade);
        return PREFIX + "yestaeUserGrade_edit.html";
    }

    /**
     * 获取用户会员等级列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
    	
    	QueryWrapper<YestaeUserGrade> wrapper = new QueryWrapper<>();
//    	wrapper.setSqlSelect(new String[] {"id", "name", "type", "money", "discount", "if_default"});
    	wrapper.eq("if_del", SysEnum.NO.getCode());
    	wrapper.orderBy(false,false,"create_time");
    	if(StringUtils.isNotEmpty(name)){
    		wrapper.like("name", name);
    	}
    	List<YestaeUserGrade> list = yestaeUserGradeService.list(wrapper);
    	List<YestaeUserGradeVo> voList = new ArrayList<>();
    	for(YestaeUserGrade g: list){
    		YestaeUserGradeVo yestaeUserGradeVo = new YestaeUserGradeVo();
    		BeanUtils.copyProperties(g, yestaeUserGradeVo, 
    				new String[] {"ifDefault", "createBy", "createTime", "updateBy", "updateTime"});
    		yestaeUserGradeVo.setIfDefault(SysEnum.valueOf(g.getIfDefault()));
    		voList.add(yestaeUserGradeVo);
    	}
        return voList;
    }

    /**
     * 新增用户会员等级
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeUserGrade yestaeUserGrade) {
    	yestaeUserGrade.setCreateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeUserGrade.setCreateTime(new Date().getTime());
    	yestaeUserGrade.setIfDel(SysEnum.NO.getCode());
    	yestaeUserGradeService.save(yestaeUserGrade);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户会员等级
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeUserGradeId) {
    	YestaeUserGrade yestaeUserGrade = yestaeUserGradeService.getById(yestaeUserGradeId);
    	yestaeUserGrade.setUpdateTime(new Date().getTime());
    	yestaeUserGrade.setIfDel(SysEnum.YES.getCode());
        yestaeUserGradeService.updateById(yestaeUserGrade);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户会员等级
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeUserGrade yestaeUserGrade) {
    	yestaeUserGrade.setUpdateTime(new Date().getTime());
    	yestaeUserGrade.setUpdateBy(((ShiroUser)getSession().getAttribute("shiroUser")).getId());
    	yestaeUserGradeService.updateYestaeUserGrade(yestaeUserGrade);
        return SUCCESS_TIP;
    }

    /**
     * 用户会员等级详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeUserGradeId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeUserGradeId") String yestaeUserGradeId) {
        return yestaeUserGradeService.getById(yestaeUserGradeId);
    }
}
