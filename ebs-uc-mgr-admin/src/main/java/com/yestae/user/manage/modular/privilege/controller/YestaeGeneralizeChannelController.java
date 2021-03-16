package com.yestae.user.manage.modular.privilege.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yestae.user.common.node.ZTreeNode;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.support.HttpKit;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeGeneralizeChannel;
import com.yestae.user.manage.modular.privilege.service.IYestaeGeneralizeChannelService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 推广渠道控制器
 *
 * @author fengshuonan
 * @Date 2017-11-28 10:54:23
 */
@Controller
@RequestMapping("/yestaeGeneralizeChannel")
public class YestaeGeneralizeChannelController extends BaseController {

    private String PREFIX = "/privilege/yestaeGeneralizeChannel/";

    @Autowired
    private IYestaeGeneralizeChannelService yestaeGeneralizeChannelService;

    /**
     * 跳转到推广渠道首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeGeneralizeChannel.html";
    }

    /**
     * 跳转到添加推广渠道
     */
    @RequestMapping("/yestaeGeneralizeChannel_add")
    public String yestaeGeneralizeChannelAdd() {
        return PREFIX + "yestaeGeneralizeChannel_add.html";
    }

    /**
     * 跳转到修改推广渠道
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeGeneralizeChannel_update/{yestaeGeneralizeChannelId}")
    public String yestaeGeneralizeChannelUpdate(@PathVariable String yestaeGeneralizeChannelId, Model model) {
        
    	YestaeGeneralizeChannel yestaeGeneralizeChannel = yestaeGeneralizeChannelService.getById(yestaeGeneralizeChannelId);
    	model.addAttribute("pname", "0".equals(yestaeGeneralizeChannel.getPid())? "顶级": "");
        YestaeGeneralizeChannel parentChannel = yestaeGeneralizeChannelService.getById(yestaeGeneralizeChannel.getPid());
        if(parentChannel != null){
        	//父级渠道名称
        	model.addAttribute("pname",parentChannel.getName());
        }
        model.addAttribute("yestaeGeneralizeChannel",yestaeGeneralizeChannel);
        LogObjectHolder.me().set(yestaeGeneralizeChannel);
        return PREFIX + "yestaeGeneralizeChannel_edit.html";
    }

    /**
     * 获取推广渠道列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
    	Page<Map<String, Object>> page = new Page<>();
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeChannelService.selectYestaeGeneralizeChannelList(page, map);
        
        for(Map<String, Object> m: list){
    		m.put("createTime", DateUtil.getTime(MapUtils.getLong(m, "createTime")));
    		m.put("ifEnd", SysEnum.valueOf(MapUtils.getInteger(m, "ifEnd")));
    	}
    	
    	page.setRecords(list);
    	return super.packForBT(page);
    }
    
    /**
     * 获取大益茶庭子推广渠道列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list/DYCT")
    @ResponseBody
    public Object listDYCT() {
    	Map<String, String> map = HttpKit.getRequestParameters();
    	List<Map<String, Object>> list = yestaeGeneralizeChannelService.selectYestaeGeneralizeChannelListDYCT(map);
    	return list;
    }
    
    /**
     * 获取推广渠道tree列表 
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree(@RequestParam(required = false) String pid, @RequestParam(required = false) String code) {
        List<ZTreeNode> tree = this.yestaeGeneralizeChannelService.tree(pid);
        if(tree != null && tree.size() > 0){
        	
        	if(StringUtils.isEmpty(code)){
        		tree.add(ZTreeNode.createParent());
        	}else{
                QueryWrapper<YestaeGeneralizeChannel> wrapper = new QueryWrapper<>();
        		wrapper.eq("channel_code", code);
        		wrapper.eq("if_del", SysEnum.NO.getCode());
        		YestaeGeneralizeChannel yestaeGeneralizeChannel =this.yestaeGeneralizeChannelService.getOne(wrapper);
        		if(yestaeGeneralizeChannel != null){
        			tree = this.dealYestaeGeneralizeChannelList(tree, yestaeGeneralizeChannel.getId());
        		}else{
        			tree.clear();
        		}
        	}
        }
        return tree;
    }

    private List<ZTreeNode> dealYestaeGeneralizeChannelList(List<ZTreeNode> tree, String id) {
    	List<ZTreeNode> newTree = new ArrayList<>();
    	if(tree != null && tree.size() > 0){
    		for(ZTreeNode zTreeNode: tree){
    			if(id.equals(zTreeNode.getId())){
    				newTree.add(zTreeNode);
    			}
    			if(id.equals(zTreeNode.getpId())){
    				newTree.addAll(dealYestaeGeneralizeChannelList(tree, zTreeNode.getId()));
    			}
    		}
    	}
		return newTree;
	}

	/**
     * 新增推广渠道
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeGeneralizeChannel yestaeGeneralizeChannel) {
        yestaeGeneralizeChannelService.insertYestaeGeneralizeChannel(yestaeGeneralizeChannel);
        return SUCCESS_TIP;
    }

    /**
     * 删除推广渠道
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeGeneralizeChannelId) {
        yestaeGeneralizeChannelService.deleteByYestaeGeneralizeChannelId(yestaeGeneralizeChannelId);
        return SUCCESS_TIP;
    }

    /**
     * 修改推广渠道
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeGeneralizeChannel yestaeGeneralizeChannel) {
    	yestaeGeneralizeChannelService.updateYestaeGeneralizeChannel(yestaeGeneralizeChannel);
        return SUCCESS_TIP;
    }

    /**
     * 推广渠道详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeGeneralizeChannelId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeGeneralizeChannelId") String yestaeGeneralizeChannelId) {
        return yestaeGeneralizeChannelService.getById(yestaeGeneralizeChannelId);
    }
}
