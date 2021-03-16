package com.yestae.user.manage.modular.vas.controller;

import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.modular.vas.persistence.model.PageContent;
import com.yestae.user.manage.modular.vas.service.IPageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面内容控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:05:56
 */
@Controller
@RequestMapping("/pageContent")
public class PageContentController extends BaseController {

    private String PREFIX = "/vas/pageContent/";

    @Autowired
    private IPageContentService pageContentService;

    /**
     * 跳转到页面内容首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pageContent.html";
    }

    /**
     * 跳转到添加页面内容
     */
    @RequestMapping("/pageContent_add")
    public String pageContentAdd() {
        return PREFIX + "pageContent_add.html";
    }

    /**
     * 跳转到修改页面内容
     */
    @RequestMapping("/pageContent_update/{pageContentId}")
    public String pageContentUpdate(@PathVariable String pageContentId, Model model) {
        PageContent pageContent = pageContentService.getById(pageContentId);
        model.addAttribute("pageContent",pageContent);
        LogObjectHolder.me().set(pageContent);
        return PREFIX + "pageContent_edit.html";
    }

    /**
     * 获取页面内容列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return pageContentService.list(null);
    }

    /**
     * 新增页面内容
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PageContent pageContent) {
        pageContentService.save(pageContent);
        return SUCCESS_TIP;
    }

    /**
     * 删除页面内容
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String pageContentId) {
        pageContentService.removeById(pageContentId);
        return SUCCESS_TIP;
    }

    /**
     * 修改页面内容
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PageContent pageContent) {
        pageContentService.updateById(pageContent);
        return SUCCESS_TIP;
    }

    /**
     * 页面内容详情
     */
    @RequestMapping(value = "/detail/{pageContentId}")
    @ResponseBody
    public Object detail(@PathVariable("pageContentId") String pageContentId) {
        return pageContentService.getById(pageContentId);
    }
}
