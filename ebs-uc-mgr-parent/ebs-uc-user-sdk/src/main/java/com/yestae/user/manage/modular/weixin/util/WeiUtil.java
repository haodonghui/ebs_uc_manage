package com.yestae.user.manage.modular.weixin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class WeiUtil {
	
	/**
	 * 字符串编码成UTF-8
	 * @param str
	 * @return
	 */
	public static String urlToUTF8(String str){
        String result = str;
        try {
            result = URLEncoder.encode(str,"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
		
	/**
	 * 微信签名验证
	 * @param signature 微信加密签名
	 * @param tocken 微信令牌
	 * @param timestamp 时间戳
	 * @param nonce 随机数
	 * @return
	 */
	public static boolean valid(String signature, String tocken, String timestamp, String nonce){
		String[] arr = new String[] { tocken, timestamp, nonce };
		//对token、timestamp、nonce 进行字典排序，并拼接字符串
		Arrays.sort(arr);		
		StringBuilder sb = new StringBuilder(arr[0]);
		sb.append(arr[1]).append(arr[2]);
		
		String encrypt = EncryptUtil.encodeSha1(sb.toString());
		//将加密后的字符串与  signature 进行比较
		return encrypt != null ? encrypt.equals(signature.toUpperCase()) : false;
	}
	
	/**
	 * 获取token接口
	 * @param appid
	 * @param secret
	 * @return
	 */
	public static String getAccessTokenUrl(String appid,String secret){
		return String.format(Constant.GET_ACCESSTOKEN, appid, secret);
	}
	
	/**
	 * 获取上传Media接口
	 * @param token
	 * @param type
	 * @return
	 */
	public static String getUploadMediaUrl(String token,String type){
		return String.format(Constant.UPLOAD_MEDIA, token, type);
	}
	
	/**
	 * 获取菜单创建接口
	 * @param accessToken
	 * @return
	 */
	public static String getMenuCreateUrl(String accessToken){
		return String.format(Constant.MENU_CREATE, accessToken);
	}
	
	/**
	 * 获取菜单删除接口
	 * @param accessToken
	 * @return
	 */
	public static String getMenuDeleteUrl(String accessToken){
		return String.format(Constant.MENU_DELETE, accessToken);
	}
	
	/**
	 * 获取菜单接口
	 * @param accessToken
	 * @return
	 */
	public static String getMenuUrl(String accessToken){
		return String.format(Constant.MENU_GET, accessToken);
	}
	
	/**
	 * 获取粉丝信息接口
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static String getFansInfoUrl(String accessToken,String openid){
		return String.format(Constant.GET_FANS_INFO, accessToken, openid);
	}
	
	/**
	 * 获取粉丝列表接口
	 * @param accessToken
	 * @param nextOpenId
	 * @return
	 */
	public static String getFansListUrl(String accessToken,String nextOpenId){
		if(nextOpenId == null){
			return String.format(Constant.GET_FANS_LIST, accessToken);
		}else{
			return String.format(Constant.GET_FANS_LIST + "&next_openid=%s", accessToken, nextOpenId);
		}
	}
	
	/**
	 * 网页授权OAuth2.0获取code
	 * @param appid
	 * @param redirectUrl
	 * @param scope
	 * @param state
	 * @return
	 */
	public static String getOAuthCodeUrl(String appid ,String redirectUrl ,String scope ,String state){
		return String.format(Constant.GET_OAUTH_CODE, appid, urlToUTF8(redirectUrl), "code", scope, state);
	}
	
	/**
	 * 网页授权OAuth2.0获取token
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static String getOAuthTokenUrl(String appid ,String secret ,String code ){
		return String.format(Constant.GET_OAUTH_TOKEN, appid, secret, code);
	}
	
	/**
	 * 网页授权OAuth2.0获取用户信息
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static String getOAuthUserinfoUrl(String accessToken ,String openid){
		return String.format(Constant.GET_OAUTH_USERINFO, accessToken, openid);
	}
	
	/**
	 * 获取上传图文消息接口
	 * @param accessToken
	 * @return
	 */
	public static String getUploadMaterUrl(String accessToken){
		return String.format(Constant.UPLOAD_MATER_NEW, accessToken);
	}
	
	/**
	 * 获取上传图文素材接口
	 * @param accessToken
	 * @return
	 */
	public static String getUploadNewsUrl(String accessToken){
		return String.format(Constant.UPLOAD_NEWS, accessToken);
	}
	
	/**
	 * 群发接口
	 * @param accessToken
	 * @return
	 */
	public static String getSendAllUrl(String accessToken){
		return String.format(Constant.SEND_ALL, accessToken);
	}
	
	/**
	 * 获取素材列表接口
	 * @param accessToken
	 * @return
	 */
	public static String getMaterListUrl(String accessToken){
		return String.format(Constant.GET_MATER_LIST, accessToken);
	}
	/**
	 * 获取上传其他永久素材接口
	 * @param accessToken
	 * @return
	 */
	public static String uploadMaterUrl(String accessToken){
		return String.format(Constant.UPLOAD_MATER, accessToken);
	}
	
	/**
	 * 获取图文素材接口
	 * @param accessToken
	 * @return
	 */
	public static String getMaterNewUrl(String accessToken){
		return String.format(Constant.GET_MATER_NEW, accessToken);
	}
	
	/**
	 * 更新图文素材接口
	 * @param accessToken
	 * @return
	 */
	public static String getUpdateMaterNewUrl(String accessToken){
		return String.format(Constant.UPDATE_MATER_NEW, accessToken);
	}
	
	/**
	 * 删除图文素材接口
	 * @param accessToken
	 * @return
	 */
	public static String getDelMaterNewUrl(String accessToken){
		return String.format(Constant.DEL_MATER_NEW, accessToken);
	}
	
	/**
	 * 创建微信二维码接口
	 * @param accessToken
	 * @return
	 */
	public static String getQrcodeCreateUrl(String accessToken){
		return String.format(Constant.QRCODE_CREATE, accessToken);
	}
	
	/**
	 * 获取微信二维码
	 * @param ticket
	 * @return
	 */
	public static String getShowQrcodeUrl(String ticket){
		return String.format(Constant.SHOWQRCODE, ticket);
	}
	
	/**
	 * 获取小程序码（数量限制100,000）
	 * @param accessToken
	 * @return
	 */
	public static String getWxACode(String accessToken){
		return String.format(Constant.GET_WX_A_CODE, accessToken);
	}
	
	/**
	 * 获取小程序码（数量不限）
	 * @param accessToken
	 * @return
	 */
	public static String getWxACodeUnlimit(String accessToken){
		return String.format(Constant.GET_WX_A_CODE_UNLIMIT, accessToken);
	}
	
	/**
	 * 获取小程序二维码（数量限制100,000）
	 * @param accessToken
	 * @return
	 */
	public static String createWxAQrCode(String accessToken){
		return String.format(Constant.CREATE_WX_A_QRCODE, accessToken);
	}
	
	/**
	 * 发送请求
	 * @param requestUrl
	 * @param requestMethod
	 * @param json
	 *        请求参数
	 * @return
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String json) {			
		
		HttpsURLConnection conn = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader =  null;
		
		try {
			
			//createConnection(requestUrl, requestMethod, json, conn, outputStream, inputStream);
			TrustManager[] tm = { new WeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//ssl3有安全漏洞, 使用tlsv1或tlsv1.1或tlsv1.2
			System.setProperty("https.protocols", "TLSv1");  
			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != json) {
				
				outputStream = conn.getOutputStream();
				outputStream.write(json.getBytes("UTF-8"));
				outputStream.close();
				outputStream = null;
			}
			inputStream = conn.getInputStream();
			
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			bufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			
			//返回json串
			//后续可以在调用方法中使用org.codehaus.jackson.map.ObjectMapper类处理
			return buffer.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStreamReader != null){
				try {
					inputStreamReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(conn != null){
				conn.disconnect();
			}
		}
		return null;
	}
	
	/**
	 * 通过https获取图片
	 * @param filePath 存储地址"/ssss/ssss"
	 * @param requestUrl 请求的地址
	 * @param requestMethod POST,GET
	 * @param json 请求的json字符串
	 * @param fileName 请求的json字符串
	 * @return
	 */
	public static String httpsRequest(String filePath, String requestUrl, String requestMethod, String json, String fileName) {			
		
		HttpsURLConnection conn = null;
		OutputStream outputStream = null;
		InputStream inputStream = null;
		FileOutputStream o = null;
		
		try {
			
			//createConnection(requestUrl, requestMethod, json, conn, outputStream, inputStream);
			TrustManager[] tm = { new WeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			//ssl3有安全漏洞, 使用tlsv1或tlsv1.1或tlsv1.2
			System.setProperty("https.protocols", "TLSv1");  
			URL url = new URL(requestUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != json) {
				
				outputStream = conn.getOutputStream();
				outputStream.write(json.getBytes("UTF-8"));
				outputStream.close();
				outputStream = null;
			}
			inputStream = conn.getInputStream();
			String contentType = conn.getHeaderField("Content-Type");
			if(!"image/jpeg".equals(contentType)){
				if(inputStream != null){
					StringBuffer sb = new StringBuffer("");
					byte[] bufByte  = new byte[1024];
					while((inputStream.read(bufByte)) != -1){
						sb.append(new String(bufByte));
					}
					return sb.toString();
				}
			}
			if(fileName == null){
				fileName = new Date().getTime()+"IMG.jpg";
			}
			
			// 如果文件夹不存在则创建
			File tempFile = new File(filePath, fileName);
			if (!tempFile.getParentFile().exists()) {
				tempFile.getParentFile().mkdirs();
			}
			tempFile = null;
			
			o = new FileOutputStream(filePath + File.separator + fileName);
			byte[] buf  = new byte[1024];
			int len = 0;
			
			if(inputStream != null){
				while((len = inputStream.read(buf)) != -1){
					o.write(buf, 0 , len);
				}
			}
			
			o.flush();
			o.close();
			o = null;
			return fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(outputStream != null){
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(o != null){
				try {
					o.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(inputStream != null){
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				conn.disconnect();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		File file = new File("D:/upload/app/qrcode/2017/12/12/1513078286464IMG.jpg");
		try {
			InputStream inputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			bufferedReader = null;
			inputStreamReader.close();
			inputStreamReader = null;
			
			inputStream.close();
			
			System.out.println(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建https连接
	 * @param requestUrl
	 * @param requestMethod
	 * @param json
	 * @param conn
	 * @param outputStream
	 * @param inputStream
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static void createConnection(String requestUrl, String requestMethod, String json, 
			HttpsURLConnection conn, OutputStream outputStream, InputStream inputStream)throws Exception{
		TrustManager[] tm = { new WeiXinX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		
		//ssl3有安全漏洞, 使用tlsv1或tlsv1.1或tlsv1.2
		System.setProperty("https.protocols", "TLSv1");  
		URL url = new URL(requestUrl);
		conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(ssf);
		
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod(requestMethod);
		if (null != json) {
			
			outputStream = conn.getOutputStream();
			outputStream.write(json.getBytes("UTF-8"));
			outputStream.close();
			outputStream = null;
		}
		inputStream = conn.getInputStream();
	}
	
	/**
	 * 获取随机字符串
	 * @return
	 */
	public static String getNonceStr() {
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		// 随机数
		String currTime = outFormat.format(new Date());
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		Random random = new Random();
		String strRandom = String.valueOf(random.nextInt(10000));
		// 10位序列号,可以自行调整。
		return strTime + strRandom;
	}
	
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}
	
	//创建签名SHA1
	@SuppressWarnings("rawtypes")
	public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
		StringBuffer sb = new StringBuffer();
		Set es = signParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + v + "&");
			//要采用URLENCODER的原始值！
		}
		String params = sb.substring(0, sb.lastIndexOf("&"));
			System.out.println("sha1之前:" + params);
//				System.out.println("SHA1签名为："+getSha1(params));
		return getSha1(params);
	}
	//Sha1签名
	public static String getSha1(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		try {
			MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(str.getBytes("GBK"));

			byte[] md = mdTemp.digest();
			int j = md.length;
			char buf[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
				buf[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(buf);
		} catch (Exception e) {
			return null;
		}
	}
}

class WeiXinX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}