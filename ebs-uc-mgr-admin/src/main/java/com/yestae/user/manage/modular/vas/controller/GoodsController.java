package com.yestae.user.manage.modular.vas.controller;

import com.yestae.user.manage.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.yestae.user.manage.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.yestae.user.manage.modular.vas.persistence.model.Goods;
import com.yestae.user.manage.modular.vas.service.IGoodsService;

/**
 * 商品控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:15:41
 */
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {

    private String PREFIX = "/vas/goods/";

    @Autowired
    private IGoodsService goodsService;

    /**
     * 跳转到商品首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "goods.html";
    }

    /**
     * 跳转到添加商品
     */
    @RequestMapping("/goods_add")
    public String goodsAdd() {
        return PREFIX + "goods_add.html";
    }

    /**
     * 跳转到修改商品
     */
    @RequestMapping("/goods_update/{goodsId}")
    public String goodsUpdate(@PathVariable String goodsId, Model model) {
        Goods goods = goodsService.getById(goodsId);
        model.addAttribute("goods",goods);
        LogObjectHolder.me().set(goods);
        return PREFIX + "goods_edit.html";
    }

    /**
     * 获取商品列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return goodsService.list(null);
    }

    /**
     * 新增商品
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Goods goods) {
        goodsService.save(goods);
        return SUCCESS_TIP;
    }

    /**
     * 删除商品
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String goodsId) {
        goodsService.removeById(goodsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改商品
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Goods goods) {
        goodsService.updateById(goods);
        return SUCCESS_TIP;
    }

    /**
     * 商品详情
     */
    @RequestMapping(value = "/detail/{goodsId}")
    @ResponseBody
    public Object detail(@PathVariable("goodsId") String goodsId) {
        return goodsService.getById(goodsId);
    }
}
