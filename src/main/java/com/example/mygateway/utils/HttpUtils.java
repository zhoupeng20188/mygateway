/**
 * Title: HttpUtils.java
 * Description: 
 * Company: 长江数字
 * @author JIMO
 * @date 2019年7月31日
 * @version 1.0
 */
package com.example.mygateway.utils;

import com.cjsz.iopcommon.utils.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Title: HttpUtils
 * Description: 
 * @author JIMO
 * @date 2019年7月31日
 */
public class HttpUtils {
    public static String get(String url) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(request);
        try {
            HttpEntity entity = response.getEntity();
            String ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return ret;
        } finally {
            response.close();
            httpclient.close();
        }
    }

    /**
     * 
     * POST的参数设定例子(以form的形式进行参数传递)
     * List <NameValuePair> nvps = new ArrayList <NameValuePair>();
     * nvps.add(new BasicNameValuePair("password", "secret"));
     * nvps.add(new BasicNameValuePair("username", "vip"));
     * 
     * @param url
     * @param nvps
     * @return
     * @throws IOException
     */
    public static String post(String url, List<NameValuePair> nvps) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            String ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return ret;
        } finally {
            response.close();
            httpclient.close();
        }
    }

//    // TODO需要在代理环境调试
//    public static String getByProxy(String url) throws ClientProtocolException, IOException {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpHost proxy = new HttpHost("10.12.01.44", 8080, "http"); // TODO
//            CredentialsProvider credsProvider = new BasicCredentialsProvider();
//            credsProvider.setCredentials(new AuthScope(proxy), new UsernamePasswordCredentials("user", "password"));//TODO
//
//            HttpClientContext context = HttpClientContext.create();
//            context.setCredentialsProvider(credsProvider);
//
//            RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//            HttpGet request = new HttpGet(url);
//            request.setConfig(config);
//
//            CloseableHttpResponse response = httpclient.execute(request);
//            return EntityUtils.toString(response.getEntity());
//        } finally {
//            httpclient.close();
//        }
//    }

    /**
     * 
     * POST的参数设定例子(以Json的形式进行参数传递)
     * 
     * @param url
     * @param Param
     * @return
     * @throws IOException
     */
    public static String post(String url, Object obj) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setEntity(new StringEntity(JsonUtils.toJson(obj), "utf-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            String ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return ret;
        } finally {
            response.close();
            httpclient.close();
        }
    }

    public static String post(HttpPost httpPost) throws ClientProtocolException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = httpclient.execute(httpPost);
        
        try {
            HttpEntity entity = response.getEntity();
            String ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return ret;
        } finally {
            response.close();
            httpclient.close();
        }
    }
    
    /**
    * 
    * POST的参数设定例子
    * 
    * @param url
    * @return
    * @throws IOException
    */
    public static String post(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try {
            HttpEntity entity = response.getEntity();
            String ret = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            return ret;
        } finally {
            response.close();
            httpclient.close();
        }
    }
}
