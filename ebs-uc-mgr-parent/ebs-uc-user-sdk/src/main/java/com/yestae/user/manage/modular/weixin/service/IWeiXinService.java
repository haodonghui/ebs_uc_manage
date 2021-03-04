package com.yestae.user.manage.modular.weixin.service;

import com.yestae.user.manage.modular.weixin.persistence.model.WeiXin;

public interface IWeiXinService {

	/**
	 * 从缓存中拿到微信信息
	 * @param code
	 * @return
	 */
	WeiXin getWeiXinFromCache(String parameter);
	
	/**
	 * 从缓存中拿到微信信息
	 * @param code
	 * @return
	 */
	void delAccessTokenFromCache(String parameter);
	
	/**
	 * 获取微信小程序码（数量不限）
	 * @param filePath
	 *         文件路径
	 * @param fileName
	 *         文件名
	 * @param json
	 *        接口请求参数
	 * @return
	 */
	String getWxACodeUnlimit(String filePath, String fileName, String json);
	
	/**
	 * 获取微信小程序码（数量不超过100,000）
	 * @param filePath
	 *         文件路径
	 * @param fileName
	 *         文件名
	 * @param json
	 *        接口请求参数
	 * @return
	 */
	String getWxACode(String filePath, String fileName, String json);
	
	/**
	 * 获取小程序二维码（数量不超过100,000）
	 * @param filePath
	 *         文件路径
	 * @param fileName
	 *         文件名
	 * @param json
	 *        接口请求参数
	 * @return
	 */
	String createWxAQrCode(String filePath, String fileName, String json);

}
