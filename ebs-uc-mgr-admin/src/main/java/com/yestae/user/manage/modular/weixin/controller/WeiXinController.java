package com.yestae.user.manage.modular.weixin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.yestae.user.manage.modular.weixin.persistence.model.WeiXin;
import com.yestae.user.manage.modular.weixin.service.IWeiXinService;
import com.yestae.user.manage.modular.weixin.util.EncryptUtil;
import com.yestae.user.manage.modular.weixin.util.WeiUtil;

@Controller
public class WeiXinController {
	
	@Resource
	IWeiXinService weiXinService;
	
	private Log log = LogFactory.getLog(WeiXinController.class);

	/**
	 * GET请求，进行微信公众号验证
	 */
	@RequestMapping(value = "/weixin",  method = RequestMethod.GET)
	@ResponseBody
	public String doGet(HttpServletRequest request) {
		String code = request.getParameter("code");//获取微信标识
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机字符串
		
		log.info("Get from WeiXin:" + signature + " " + timestamp + " " + nonce + " " + echostr);
		
		if(StringUtils.isEmpty(code)){
			return "error";
		}
		WeiXin weiXin = weiXinService.getWeiXinFromCache(code);
		if(weiXin == null){
			return "error";
		}
		// 校验成功返回echostr；否则返回error，接入失败
		if (WeiUtil.valid(signature, EncryptUtil.decodeDes(weiXin.getToken()), timestamp, nonce)) {
			return echostr;
		}

		return "error";
	}
	
}
