package com.yestae.user.manage.modular.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yestae.user.manage.modular.weixin.persistence.dao.WeiXinMapper;
import com.yestae.user.manage.modular.weixin.persistence.model.WeiXin;
import com.yestae.user.manage.modular.weixin.service.IWeiXinService;
import com.yestae.user.manage.modular.weixin.util.Constant;
import com.yestae.user.manage.modular.weixin.util.EncryptUtil;
import com.yestae.user.manage.modular.weixin.util.WeiUtil;
import com.yestae.user.manage.modular.weixin.util.WrapCache;

@Service
public class WeiXinServiceImpl implements IWeiXinService {
	
	@Resource
	private WeiXinMapper weiXinMapper;
	@Resource
	private WrapCache cache;
	
	private Logger log = LoggerFactory.getLogger(WeiXinServiceImpl.class);

	/**
	 * 从缓存中拿到微信信息
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public WeiXin getWeiXinFromCache(String code){
		
		//单服务器可以使用内存缓存
		WeiXin weiXin = (WeiXin)cache.getCacheObj(code);
		if (weiXin==null){
			//查找weixin对象
			weiXin = weiXinMapper.selectByCode(code);
			if(weiXin == null ){
				return null;
			}
			cache.addCacheObj(code, weiXin);
		}
		
		//多服务器时每次去数据库中取值
		//WeiXin weiXin = weiXinMapper.selectByCode(code);
		
		boolean accessToken = true;
		if (weiXin.getAccessToken() != null && !"".equals(weiXin.getAccessToken())){
			Long accessTime = weiXin.getAccessTokenTime();
			Long accessExp = weiXin.getAccessTokenExpiresIn();
			Long now = new Date().getTime();
			if (accessTime == null || accessExp == null || now - accessTime < (accessExp-200)*1000){
				accessToken = false;
			}
			log.info("accessTime->毫秒:"+accessTime);
			log.info("now->毫秒:"+now);
			log.info("时间差:"+(now-accessTime));
			log.info("accessExp->令牌有效时间:"+accessExp);
		}
		
		//从微信服务器拿到accessToken
		if (accessToken){
			String url = WeiUtil.getAccessTokenUrl(EncryptUtil.decodeDes(weiXin.getAppid()), EncryptUtil.decodeDes(weiXin.getSecret()));
			log.info("微信获得令牌的URL:"+url);
			String json = WeiUtil.httpsRequest(url, Constant.GET, null);
			log.info("access_token json:"+json);
			
			if (json != null){
				Map<String, Object> pa = JSONObject.parseObject(json, HashMap.class);
				
				//更新微信数据表
				if (pa.containsKey("access_token")){
					Map<String, Object> params = new HashMap<String, Object>();
					weiXin.setAccessToken(MapUtils.getString(pa, "access_token"));
					weiXin.setAccessTokenExpiresIn(MapUtils.getLong(pa, "expires_in"));
					weiXin.setAccessTokenTime(new Date().getTime());
					params.put("id", weiXin.getId());
					params.put("accessToken", weiXin.getAccessToken());
					params.put("accessTokenExpiresIn", weiXin.getAccessTokenExpiresIn());
					params.put("accessTokenTime", weiXin.getAccessTokenTime());
					weiXinMapper.updateByMap(params);
					cache.addCacheObj(code, weiXin);
				}else{
					return null;
				}
			}
		}
		
		return weiXin;
	}

	@Override
	public void delAccessTokenFromCache(String code) {
		//单服务器可以使用内存缓存
		WeiXin weiXin = (WeiXin)cache.getCacheObj(code);
		if (weiXin!=null){
			weiXin.setAccessToken(null);
			cache.addCacheObj(code, weiXin);
		}
	}

	public static void main(String[] args) {
//		String url = WeiUtil.getAccessTokenUrl("wxf1cb37f6a9831e9f", "7ed98716c1431ea15b943aebc3ac97ee");
//		System.out.println("微信获得令牌的URL:"+url);
//		String json = WeiUtil.httpsRequest(url, Constant.GET, null);
//		System.out.println("access_token json:"+json);
		
		
		
	}

	@Override
	public String getWxACodeUnlimit(String filePath, String fileName, String json) {
		WeiXin weiXin = this.getWeiXinFromCache(Constant.WEIXIN_CODE);
		if(weiXin != null){
			return WeiUtil.httpsRequest(filePath, WeiUtil.getWxACodeUnlimit(weiXin.getAccessToken()), Constant.POST, json, fileName);
		}
		return null;
	}

	@Override
	public String getWxACode(String filePath, String fileName, String json) {
		WeiXin weiXin = this.getWeiXinFromCache(Constant.WEIXIN_CODE);
		if(weiXin != null){
			return WeiUtil.httpsRequest(filePath, WeiUtil.getWxACode(weiXin.getAccessToken()), Constant.POST, json, fileName);
		}
		return null;
	}
	
	@Override
	public String createWxAQrCode(String filePath, String fileName, String json) {
		WeiXin weiXin = this.getWeiXinFromCache(Constant.WEIXIN_CODE);
		if(weiXin != null){
			return WeiUtil.httpsRequest(filePath, WeiUtil.createWxAQrCode(weiXin.getAccessToken()), Constant.POST, json, fileName);
		}
		return null;
	}

}
