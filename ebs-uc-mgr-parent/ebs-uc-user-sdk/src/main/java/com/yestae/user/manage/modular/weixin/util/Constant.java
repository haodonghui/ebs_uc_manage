package com.yestae.user.manage.modular.weixin.util;

public interface Constant {
	//微信数据在EhCache中缓存
	public static String EHCACHE_NAME = "weixin";
	public static String WEIXIN_CODE = "yestae";
	public static String POST = "POST";
	public static String GET = "GET";
	
	/**
	 * token 接口
	 */
	public static final String GET_ACCESSTOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	/**
	 * 上传多媒体资料接口
	 */
	public static final String UPLOAD_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s";
	
	/**
	 * 创建菜单
	 */
	public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	/**
	 * 删除菜单
	 */
	public static final String MENU_DELETE = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=%s";
	
	/**
	 * 获取菜单
	 */
	public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
	
	/**
	 * 获取账号粉丝信息
	 */
	public static final String GET_FANS_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
	
	/**
	 * 获取账号粉丝列表
	 */
	public static final String GET_FANS_LIST = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=%s";
	
	/**
	 * 网页授权OAuth2.0获取code
	 */
	public static final String GET_OAUTH_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=%s#wechat_redirect";
	
	/**
	 * 网页授权OAuth2.0获取token
	 */
	public static final String GET_OAUTH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	
	/**
	 * 网页授权OAuth2.0获取用户信息
	 */
	public static final String GET_OAUTH_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN";
	
	/**
	 * 上传图文消息
	 */
	public static final String UPLOAD_NEWS = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=%s";
	
	
	/**
	 * 上传图文素材
	 */
	public static final String UPLOAD_MATER_NEW = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=%s";
	
	/**
	 * 上传其他永久素材
	 */
	public static final String UPLOAD_MATER = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s";
	
	/**
	 * 群发接口
	 */
	public static final String SEND_ALL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=%s";
	
	/**
	 * 图文素材列表
	 */
	public static final String GET_MATER_LIST = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=%s";
	
	/**
	 * 获取图文素材
	 */
	public static final String GET_MATER_NEW = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=%s";
	
	/**
	 * 修改图文素材
	 */
	public static final String UPDATE_MATER_NEW = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=%s";
	
	/**
	 * 删除图文素材
	 */
	public static final String DEL_MATER_NEW = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=%s";

	/**
	 * 创建微信二维码
	 */
	public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
	
	/**
	 * 获取微信二维码
	 */
	public static final String SHOWQRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

	/**
	 * 获取小程序码（数量限制100,000）
	 */
	public static final String GET_WX_A_CODE = "https://api.weixin.qq.com/wxa/getwxacode?access_token=%s";
	
	/**
	 * 获取小程序码（数量不限）
	 */
	public static final String GET_WX_A_CODE_UNLIMIT = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";
	
	/**
	 * 获取小程序二维码（数量限制100,000）
	 */
	public static final String CREATE_WX_A_QRCODE = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s";


}
