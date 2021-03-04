package com.yestae.user.manage.modular.privilege.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.util.ImageUtil;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeUserImage;
import com.yestae.user.manage.modular.privilege.service.IYestaeUserImageService;

/**
 * 用户图片控制器
 *
 * @author fengshuonan
 * @Date 2017-11-17 13:28:04
 */
@Controller
@RequestMapping("/yestaeUserImage")
public class YestaeUserImageController extends BaseController {

    private String PREFIX = "/privilege/yestaeUserImage/";
    
    @Resource
    UcConstant ucConstant;

    @Autowired
    private IYestaeUserImageService yestaeUserImageService;
    
    @Resource
    private ImageUtil imageUtil;

    /**
     * 跳转到用户图片首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "yestaeUserImage.html";
    }

    /**
     * 跳转到添加用户图片
     */
    @RequestMapping("/yestaeUserImage_add")
    public String yestaeUserImageAdd() {
        return PREFIX + "yestaeUserImage_add.html";
    }

    /**
     * 跳转到修改用户图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping("/yestaeUserImage_update/{yestaeUserImageId}")
    public String yestaeUserImageUpdate(@PathVariable String yestaeUserImageId, Model model) {
        YestaeUserImage yestaeUserImage = yestaeUserImageService.selectById(yestaeUserImageId);
        model.addAttribute("item",yestaeUserImage);
        LogObjectHolder.me().set(yestaeUserImage);
        return PREFIX + "yestaeUserImage_edit.html";
    }

    /**
     * 获取用户图片列表
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return yestaeUserImageService.selectList(null);
    }

    /**
     * 新增用户图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(YestaeUserImage yestaeUserImage) {
        yestaeUserImageService.insert(yestaeUserImage);
        return SUCCESS_TIP;
    }

    /**
     * 删除用户图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String yestaeUserImageId) {
        yestaeUserImageService.deleteById(yestaeUserImageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(YestaeUserImage yestaeUserImage) {
        yestaeUserImageService.updateById(yestaeUserImage);
        return SUCCESS_TIP;
    }

    /**
     * 用户图片详情
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(value = "/detail/{yestaeUserImageId}")
    @ResponseBody
    public Object detail(@PathVariable("yestaeUserImageId") String yestaeUserImageId) {
        return yestaeUserImageService.selectById(yestaeUserImageId);
    }
    
    /**
     * 上传图片
     */
    @DataSource(name="dataSourceUc")
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture) {
    	YestaeUserImage yestaeUserImage = new YestaeUserImage();
    	
    	String originalFileName = picture.getOriginalFilename();
    	
    	//校验图片类型
    	String rex = ".*\\.(?i)(jpg|png|bmp|gif)";
    	if(!originalFileName.matches(rex)){
    		throw new BussinessException(BizExceptionEnum.UPLOAD_TYPE_ERROR);
    	}
        try {
        	//图片类型
        	String type = String.valueOf(originalFileName.substring(originalFileName.lastIndexOf(".")));
        	//图片名称
        	String fName = new Date().getTime() + type;
        	//上传原图
        	String path = imageUtil.uploadFile(picture, ucConstant.getImageDir() + ucConstant.getUserImageDir(), fName);
        	String large = ucConstant.getUserImageDir() + path + "/" + fName;
        	//创建缩略图 200px * 200px
        	String thumb = ucConstant.getUserImageDir() + imageUtil.scaleImageWithParams(ucConstant.getImageDir() + large, 
        			ucConstant.getImageDir() + ucConstant.getUserImageDir() + path + "/thumb", null, 200, 200, true);
        	yestaeUserImage.setLarge(large);
        	yestaeUserImage.setThumb(thumb);
        	String size = getPara("size");
        	yestaeUserImage.setSize(Integer.parseInt(size));
        	yestaeUserImage.setCreateTime(new Date().getTime());
        	yestaeUserImageService.insert(yestaeUserImage);
        } catch (Exception e) {
        	e.printStackTrace();
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return yestaeUserImage.getId();
    }
}
