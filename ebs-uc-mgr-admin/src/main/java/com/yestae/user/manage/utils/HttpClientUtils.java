package com.yestae.user.manage.utils;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @Package com.yestae.user.center.tools
 * @ClassName
 * @Author h_don
 * @Date 2020/2/20 18:02
 */
public class HttpClientUtils {

    /**
     * post请求，参数为json字符串
     *
     * @param url        请求地址
     * @param jsonString json字符串
     * @return 响应
     */
    public static String sendPost(String url, String jsonString, Charset encoding) {


        String resp = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type" , "application/json");
        CloseableHttpResponse response = null;
        try {
            post.setEntity(new ByteArrayEntity(jsonString.getBytes("UTF-8")));
            response = httpClient.execute(post);
            //            if (response != null && response.getStatusLine().getStatusCode() == 200) {
            resp = EntityUtils.toString(response.getEntity(), encoding);
            //            }
            //            return result;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resp;
    }
}
