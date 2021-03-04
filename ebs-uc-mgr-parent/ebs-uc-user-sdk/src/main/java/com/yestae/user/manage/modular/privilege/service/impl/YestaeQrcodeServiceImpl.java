package com.yestae.user.manage.modular.privilege.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.yestae.user.common.exception.BizExceptionEnum;
import com.yestae.user.common.exception.BussinessException;
import com.yestae.user.common.qr.ImgQrTool;
import com.yestae.user.common.qr.MyQRCodeImage;
import com.yestae.user.common.util.DateUtil;
import com.yestae.user.manage.modular.privilege.common.Constants.UserConstant;
import com.yestae.user.manage.modular.privilege.common.enums.QrcodeSceneTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.QrcodeTypeEnum;
import com.yestae.user.manage.modular.privilege.common.enums.SysEnum;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeMapper;
import com.yestae.user.manage.modular.privilege.persistence.dao.YestaeQrcodeSceneMapper;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcode;
import com.yestae.user.manage.modular.privilege.persistence.model.YestaeQrcodeScene;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeSceneService;
import com.yestae.user.manage.modular.privilege.service.IYestaeQrcodeService;
import com.yestae.user.manage.modular.weixin.service.IWeiXinService;
import com.yestae.user.manage.modular.weixin.util.Constant;

import jp.sourceforge.qrcode.QRCodeDecoder;

/**
 * <p>
 * 二维码表 服务实现类
 * </p>
 *
 * @author chenfeida
 * @since 2017-12-05
 */
@Service
public class YestaeQrcodeServiceImpl extends ServiceImpl<YestaeQrcodeMapper, YestaeQrcode> implements IYestaeQrcodeService {

	@Resource
	YestaeQrcodeMapper yestaeQrcodeMapper;
	
	@Resource
	YestaeQrcodeSceneMapper yestaeQrcodeSceneMapper;
	
	@Resource
	IWeiXinService weiXinService;
	
	@Resource
	IYestaeQrcodeSceneService yestaeQrcodeSceneService;
	
	
	private final static Logger log = LoggerFactory.getLogger(YestaeQrcodeServiceImpl.class);
	
	@Override
	public List<Map<String, Object>> selectYestaeQrcodeList(Page<Map<String, Object>> page, Map<String, String> map) {
		map.put("ifDel", SysEnum.NO.getCode() + "");
		return yestaeQrcodeMapper.selectYestaeQrcodeList(page, map);
	}

	@Override
	@Transactional
	public int insertYestaeQrcode(YestaeQrcode yestaeQrcode, String imageDir, String weixinQrCodeDir, String page) {
		if(yestaeQrcode == null){
			return -1;
		}
		
		if(!this.checkYestaeQrcode(yestaeQrcode)){
			throw new BussinessException(BizExceptionEnum.QRCODE_EXISTED);
		}
		
		yestaeQrcodeMapper.insert(yestaeQrcode);
		
		int i = this.generateQrcode(yestaeQrcode, imageDir, weixinQrCodeDir, page);
		if(i < 0){
			throw new BussinessException(BizExceptionEnum.QRCODE_GET_ERROR);
		}
		return yestaeQrcodeMapper.updateById(yestaeQrcode);
	}
	
	@Override
	public int insertYestaeQrcodeAuto(YestaeQrcode yestaeQrcode, String imageDir, String weixinQrCodeDir) {
		yestaeQrcode.setCreateTime(new Date().getTime());
    	yestaeQrcode.setIfDel(SysEnum.NO.getCode());
    	yestaeQrcode.setType(QrcodeTypeEnum.DCARD.getCode());
    	
    	YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneService.selectDcardYestaeQrcodeScene();
    	yestaeQrcode.setSceneId(yestaeQrcodeScene.getId());
    	String page = QrcodeSceneTypeEnum.valueOf(yestaeQrcodeScene.getType() + QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode());
		return insertYestaeQrcode(yestaeQrcode, imageDir, weixinQrCodeDir, page);
	}

	@Override
	@Transactional
	public int updateYestaeQrcode(YestaeQrcode yestaeQrcode) {
		if(yestaeQrcode == null){
			return -1;
		}
		
		if(!this.checkYestaeQrcode(yestaeQrcode)){
			throw new BussinessException(BizExceptionEnum.QRCODE_EXISTED);
		}
		
		YestaeQrcode yestaeQrcodeDb = yestaeQrcodeMapper.selectById(yestaeQrcode.getId());
		if(yestaeQrcodeDb == null){
			throw new BussinessException(BizExceptionEnum.DB_RESOURCE_NULL);
		}
		
		BeanUtils.copyProperties(yestaeQrcode, yestaeQrcodeDb, new String[]{"createBy", "createTime", "ifDel", "codeUrl", "qrcodeId"} );
		
		return yestaeQrcodeMapper.updateAllColumnById(yestaeQrcodeDb);
	}
	
	/**
	 * 检验数据库中是否存在与当前二维码关联的推广人和场景都相同的二维码
	 * @param yestaeQrcode
	 * @return
	 */
	private boolean checkYestaeQrcode(YestaeQrcode yestaeQrcode){
		
		EntityWrapper<YestaeQrcode> wrapper = new EntityWrapper<YestaeQrcode>();
		wrapper.eq("if_del", SysEnum.NO.getCode());
		wrapper.eq("generalize_user_id", yestaeQrcode.getGeneralizeUserId());
		wrapper.eq("scene_id", yestaeQrcode.getSceneId());
		if(!StringUtils.isEmpty(yestaeQrcode.getId())){
			wrapper.ne("id", yestaeQrcode.getId());
		}
		int i = yestaeQrcodeMapper.selectCount(wrapper);
		if(i > 0){
			return false;
		}
		return true;
	}
	
	private int generateQrcode(YestaeQrcode yestaeQrcode, String imageDir, String weixinQrCodeDir, String page){
		
		YestaeQrcodeScene yestaeQrcodeScene = yestaeQrcodeSceneMapper.selectById(yestaeQrcode.getSceneId());
		
		if(yestaeQrcodeScene == null){
			return -1;
		}
		
		String path = DateFormatUtils.format(new Date(), "/YYYY/MM/dd");
		String scene = yestaeQrcode.getId();
		String fileName = "";
		if(UserConstant.APPLY_SCOPE_APP == yestaeQrcodeScene.getApplyScope()){
			try {
				fileName = new Date().getTime() + ".jpg";
				String qrcodeUrl = weixinQrCodeDir
						+ DateUtil.format(new Date(), "/YYYY/MM/dd")
						+ "/" + fileName;
				String destImagePath = imageDir + qrcodeUrl;
				File tempFile = new File(destImagePath);
				// 如果文件夹不存在则创建
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				
//				String srcImagePath = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"static/img/yestae.jpg").getPath();
				ImgQrTool.encode("yestae:qrcodeId=~~" + scene + "~~", 1280, 1280, "/static/img/yestae_new.png", destImagePath);
				yestaeQrcode.setQrcodeId(scene);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		
		}else{
			String json = "";
			String filePath = imageDir + weixinQrCodeDir + path;
			if(UserConstant.APPLY_SCOPE_WEIXIN == yestaeQrcodeScene.getApplyScope()){
				
				//小程序码数量不限接口参数-上线使用
				json = "{"
				+ "\"scene\":\"" + scene + "\","
				+ "\"page\":\"" + page + "\","
				+ "\"width\":1280,"
				+ "\"auto_color\":true"
				+ "}";
				fileName = weiXinService.getWxACodeUnlimit(filePath, null, json);
			}else{
				
				//小程序二维码数量限制接口参数-调接口测试
				json = "{"
						+ "\"path\":\"" + page + "?scene=" +scene + "\","
						+ "\"width\":1280,"
						+ "}";
				fileName = weiXinService.createWxAQrCode(filePath, null, json);
			}
			
			//小程序码数量限制接口参数-调接口临时使用
//		String json = "{"
//				+ "\"path\":\"" + page + "?scene=" +scene + codeInfo + "\","
//				+ "\"width\":430,"
//				+ "\"auto_color\":true"
//				+ "}";
			log.info("YestaeQrcodeServiceImpl->generateQrcode->json:" + json);
			//生成无数量限制的小程序码
			
			//生成有数量限制的小程序码
//		String fileName = weiXinService.getWxACode(filePath, null, json);
			
			if(fileName.indexOf(".jpg") < 0){
				log.info("YestaeQrcodeServiceImpl->generateQrcode->fileName:" + fileName);
				Boolean getQrcodeError = true;
				JSONObject jsonObject = JSONObject.parseObject(fileName);
				
				if(jsonObject != null && 40001 == (Integer)jsonObject.get("errcode")){
					weiXinService.delAccessTokenFromCache(Constant.WEIXIN_CODE);
					fileName = weiXinService.createWxAQrCode(filePath, null, json);
					getQrcodeError = false;
				}
				if(getQrcodeError){
					return -1;
				}
			}
			
			yestaeQrcode.setQrcodeId(findQrcodeId(filePath + "/" + fileName));
		}
		yestaeQrcode.setCodeUrl(weixinQrCodeDir + path + "/" + fileName);
		
		return 1;
	}
	
	private String findQrcodeId(String path){
		try {
			//图片路径
			File file = new File(path);
			//读取图片到缓冲区
			BufferedImage bufferedImage = ImageIO.read(file);
			//QRCode解码器
			QRCodeDecoder codeDecoder = new QRCodeDecoder();
			/**
			 *codeDecoder.decode(new MyQRCodeImage())
			 *这里需要实现QRCodeImage接口，移步最后一段代码
			 */
			//通过解析二维码获得信息
			String result = new String(codeDecoder.decode(new MyQRCodeImage(bufferedImage)), "utf-8");
		
			String results[] = result.split("~~");
			if(results.length >= 2){
				return results[1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	public static void main(String[] args) {
		/*String page = QrcodeSceneTypeEnum.valueOf(1 + QrcodeSceneTypeEnum.DIFFERENCE_VALUE.getCode());
		
		String json = "{"
				+ "\"path\":\"" + page + "?scene=" +123 + "\","
				+ "\"width\":430,"
				+ "\"auto_color\":true"
				+ "}";
		System.out.println(json);*/
		
		try {
			File fOldFile = new File("D:/work/invite_manage/yestae-uc-manager/uc-mgr-admin/target/uc-mgr-admin-v2.0.0.jar/BOOT-INF/classes/static/img/yestae.jpg");
			if (fOldFile.exists()) 
			{
				@SuppressWarnings("unused")
				int bytesum = 0; 
				int byteread = 0;
				InputStream inputStream = new FileInputStream(fOldFile);
				FileOutputStream fileOutputStream = new FileOutputStream("D:/data");
				byte[] buffer = new byte[1444]; 
				while ( (byteread = inputStream.read(buffer)) != -1) 
				{ 
					bytesum += byteread; //这一行是记录文件大小的，可以删去
					fileOutputStream.write(buffer, 0, byteread);//三个参数，第一个参数是写的内容，
					//第二个参数是从什么地方开始写，第三个参数是需要写的大小
				} 
				inputStream.close();
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
