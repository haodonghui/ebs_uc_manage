package com.yestae.user.manage.modular.vas.controller;

import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.manage.core.base.controller.BaseController;
import com.yestae.user.manage.core.constant.UcConstant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.yestae.user.manage.core.log.LogObjectHolder;
import com.yestae.user.manage.core.mutidatasource.annotion.DataSource;
import com.yestae.user.manage.core.util.ImageUtil;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.yestae.user.manage.modular.vas.common.constant.VasConstants;
import com.yestae.user.manage.modular.vas.persistence.model.VasImage;
import com.yestae.user.manage.modular.vas.service.IVasImageService;

/**
 * 图片表控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:08:52
 */
@Controller
@RequestMapping("/vasImage")
public class VasImageController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(VasImageController.class);

    private String PREFIX = "/vas/vasImage/";

    @Autowired
    private IVasImageService vasImageService;
    
    @Resource
    UcConstant ucConstant;
    
    @Resource
    private ImageUtil imageUtil;
    /**
     * 跳转到图片表首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "vasImage.html";
    }

    /**
     * 跳转到添加图片表
     */
    @RequestMapping("/vasImage_add")
    public String vasImageAdd() {
        return PREFIX + "vasImage_add.html";
    }

    /**
     * 跳转到修改图片表
     */
    @RequestMapping("/vasImage_update/{vasImageId}")
    public String vasImageUpdate(@PathVariable String vasImageId, Model model) {
        VasImage vasImage = vasImageService.selectById(vasImageId);
        model.addAttribute("vasImage",vasImage);
        LogObjectHolder.me().set(vasImage);
        return PREFIX + "vasImage_edit.html";
    }

    /**
     * 获取图片表列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return vasImageService.selectList(null);
    }

    /**
     * 新增图片表
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(VasImage vasImage) {
        vasImageService.insert(vasImage);
        return SUCCESS_TIP;
    }

    /**
     * 删除图片表
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String vasImageId) {
        vasImageService.deleteById(vasImageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改图片表
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(VasImage vasImage) {
        vasImageService.updateById(vasImage);
        return SUCCESS_TIP;
    }

    /**
     * 图片表详情
     */
    @RequestMapping(value = "/detail/{vasImageId}")
    @ResponseBody
    public Object detail(@PathVariable("vasImageId") String vasImageId) {
        return vasImageService.selectById(vasImageId);
    }
    
    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload/{bizName}")
    @DataSource(name="dataSourceUc")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile picture, @PathVariable("bizName") String bizName) {
    	VasImage vasImage = new VasImage();
    	
    	String originalFileName = picture.getOriginalFilename();
    	
    	//校验图片类型
    	String rex = ".*\\.(?i)(jpg|png|bmp|gif)";
    	if(!originalFileName.matches(rex)){
    		throw new BussinessException(BizExceptionEnum.UPLOAD_TYPE_ERROR);
    	}
        try {
        	
        	String bizDir = null;
        	switch(bizName){
	        	case VasConstants.VI_BIZ_NAME_VAS :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_VAS);
	        		bizDir = ucConstant.getVasImageDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_EQUITY :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_EQUITY);
	        		bizDir = ucConstant.getEquityImageDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_ORGANIZ :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_ORGANIZ);
	        		bizDir = ucConstant.getOrganizImageDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_ORGANIZ_VALID_LOGO :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_ORGANIZ_VALID_LOGO);
	        		bizDir = ucConstant.getOrganizValidLogoDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_ORGANIZ_INVALID_LOGO :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_ORGANIZ_INVALID_LOGO);
	        		bizDir = ucConstant.getOrganizInvalidLogoDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_ORGANIZ_LOGO :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_ORGANIZ_LOGO);
	        		bizDir = ucConstant.getOrganizLogoDir();
	        		break;
	        	case VasConstants.VI_BIZ_NAME_STORE :
	        		vasImage.setBizType(VasConstants.VI_BIZ_TYPE_STORE);
	        		bizDir = ucConstant.getStoreImageDir();
	        		break;
        	}
        	if(StringUtils.isEmpty(bizDir)){
        		return "";
        	}
        	
        	//图片类型
        	String type = String.valueOf(originalFileName.substring(originalFileName.lastIndexOf(".")));
        	//图片名称
        	String fName = new Date().getTime() + type;
        	//上传原图
        	String path = imageUtil.uploadFile(picture, ucConstant.getImageDir() + bizDir, fName);
        	String large = bizDir + path + "/" + fName;
        	//创建缩略图 200px * 200px
        	String thumb = bizDir + imageUtil.scaleImageWithParams(ucConstant.getImageDir() + large, 
        			ucConstant.getImageDir() + bizDir + path + "/thumb", null, 200, 200, true);
        	vasImage.setLarge(large);
        	vasImage.setThumb(thumb);
        	String size = getPara("size");
        	if(StringUtils.isEmpty(size)){
        		vasImage.setSize(picture.getSize());
        	}else{
        		vasImage.setSize(Long.parseLong(size));
        	}
        	vasImage.setCreateTime(new Date().getTime());
        	vasImageService.insert(vasImage);
        } catch (Exception e) {
        	logger.error("VasImageController->upload", e);
            throw new BussinessException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return vasImage.getId();
    }
}
