package com.yihecloud.utils;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.GZIPOutputStream;

/**
 * Description: httpClient工具类
 */
public class HttpClientUtils {

    // 编码格式。发送编码格式统一用UTF-8
    private static final String ENCODING = "UTF-8";

    // 设置连接超时时间，单位毫秒。
    private static final int CONNECT_TIMEOUT = 6000;

    // 请求获取数据的超时时间(即响应时间)，单位毫秒。
    private static final int SOCKET_TIMEOUT = 6000;

    private static final String DEFAULT_URL = "http://127.0.0.1:8080";


    private static CloseableHttpClient httpclient ;

    static {

        httpclient = getHttpClient();
    }

    /**
     * 发送get请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
//	public static HttpClientResult doGet(String url) throws Exception {
//		return doGet(url, null, null);
//	}


    /**
     * 发送post请求；不带请求头和请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
//	public static HttpClientResult doPost(String url) throws Exception {
//		return doPost(url, null, null);
//	}
    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doGet(String url,Map<String,String> headers,JSONObject json) throws Exception{

        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder(url);
        if (json != null) {
            Set<Map.Entry<String, Object>> entrySet = json.entrySet();
            for (Map.Entry<String, Object> entry : entrySet) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }
        HttpGet get = new HttpGet(uriBuilder.build());
        if(headers != null && !headers.isEmpty()) packageHeader(headers,get);
        JSONObject response = null;
        try {
            HttpResponse res = httpclient.execute(get);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }
    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    public static JSONObject doPost(String url,Map<String,String> headers,String json) throws Exception{
//		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        //	CloseableHttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(url);
        if(headers != null && !headers.isEmpty()) packageHeader(headers,post);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json, Charset.forName(ENCODING));
            s.setContentEncoding(ENCODING);
            s.setContentType("application/json");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                response = JSONObject.fromObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    /**
     * post请求
     * @param url
     * @param message
     * @return
     */
    public static JSONObject doPostGzip(String url,Map<String,String> headers,String message) throws Exception{
//		CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        //	CloseableHttpClient httpclient = getHttpClient();
        HttpPost post = new HttpPost(url);
        if(headers == null) headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-Encoding", "gzip");
        headers.put("Content-type","application/json; charset=UTF-8");
        //	headers.put("Transfer-Encoding", "chunked");
        packageHeader(headers,post);
        JSONObject response = null;
        ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
        originalContent.write(message.getBytes(Charset.forName(ENCODING)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
        originalContent.writeTo(gzipOut);
        gzipOut.finish();

        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(baos.toByteArray(), ContentType.APPLICATION_JSON);
//		StringEntity stringEntity = new StringEntity(GzipUtils.compress(message),Charset.forName(ENCODING));
        try {
            post.setEntity(byteArrayEntity);
            HttpResponse res = httpclient.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
//				response = JSONObject.fromObject(result);
                response = JSON.parseObject(result,JSONObject.class);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }


    /**
     * 发送post请求；带请求头和请求参数
     * @param url 请求地址
     * @param headers 请求头集合
     * @param params 请求参数集合
     * @return
     * @throws Exception
     */
//	public static HttpClientResult doPost(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
//		// 创建httpClient对象
//		CloseableHttpClient httpClient = HttpClients.createDefault();
//
//		// 创建http对象
//		HttpPost httpPost = new HttpPost(url);
//		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
//		httpPost.setConfig(requestConfig);
//		packageHeader(headers, httpPost);
//
//		// 封装请求参数
//		packageParam(params, httpPost);
//
//		// 创建httpResponse对象
//		CloseableHttpResponse httpResponse = null;
//
//		try {
//			// 执行请求并获得响应结果
//			return getHttpClientResult(httpResponse, httpClient, httpPost);
//		} finally {
//			// 释放资源
//			release(httpResponse, httpClient);
//		}
//	}

    /**
     * 发送put请求；不带请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static HttpClientResult doPut(String url) throws Exception {
        return doPut(url);
    }

    /**
     * 发送put请求；带请求参数
     *
     * @param url 请求地址
     * @param params 参数集合
     * @return
     * @throws Exception
     */
    public static HttpClientResult doPut(String url, Map<String, String> params) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpPut.setConfig(requestConfig);

        packageParam(params, httpPut);

        CloseableHttpResponse httpResponse = null;

        try {
            return getHttpClientResult(httpResponse, httpClient, httpPut);
        } finally {
            release(httpResponse, httpClient);
        }
    }

    /**
     * 发送delete请求；不带请求参数
     *
     * @param url 请求地址
     * @return
     * @throws Exception
     */
    public static HttpClientResult doDelete(String url) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
        httpDelete.setConfig(requestConfig);

        CloseableHttpResponse httpResponse = null;
        try {
            return getHttpClientResult(httpResponse, httpClient, httpDelete);
        } finally {
            release(httpResponse, httpClient);
        }
    }

    /**
     * Description: 封装请求头
     * @param params
     * @param httpMethod
     */
    public static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
        // 封装请求头
        if (params != null) {
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                // 设置到请求头到HttpRequestBase对象中
                httpMethod.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Description: 封装请求参数
     *
     * @param params
     * @param httpMethod
     * @throws UnsupportedEncodingException
     */
    public static void packageParam(Map<String, String> params, HttpEntityEnclosingRequestBase httpMethod)
            throws UnsupportedEncodingException {
        // 封装请求参数
        if (params != null) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<Map.Entry<String, String>> entrySet = params.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            // 设置到请求的http对象中
            httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
        }
    }

    /**
     * Description: 获得响应结果
     *
     * @param httpResponse
     * @param httpClient
     * @param httpMethod
     * @return
     * @throws Exception
     */
    public static HttpClientResult getHttpClientResult(CloseableHttpResponse httpResponse,
                                                       CloseableHttpClient httpClient, HttpRequestBase httpMethod) throws Exception {
        // 执行请求
        httpResponse = httpClient.execute(httpMethod);

        // 获取返回结果
        if (httpResponse != null && httpResponse.getStatusLine() != null) {
            String content = "";
            if (httpResponse.getEntity() != null) {
                content = EntityUtils.toString(httpResponse.getEntity(), ENCODING);
            }
            return new HttpClientResult(httpResponse.getStatusLine().getStatusCode(), content);
        }
        return new HttpClientResult(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    /**
     * Description: 释放资源
     *
     * @param httpResponse
     * @param httpClient
     * @throws IOException
     */
    public static void release(CloseableHttpResponse httpResponse, CloseableHttpClient httpClient) throws IOException {
        // 释放资源
        if (httpResponse != null) {
            httpResponse.close();
        }
        if (httpClient != null) {
            httpClient.close();
        }
    }


    public static CloseableHttpClient getHttpClient() {
        RequestConfig requestConfig = RequestConfig.custom()//
                .setConnectTimeout(3000)//
                .setSocketTimeout(3000)//
                // 忽略cookie,如果不需要登陆最好去掉,否则修改策略保存cookie即可
                //	.setCookieSpec(CookieSpecs.IGNORE_COOKIES)//
                .setConnectionRequestTimeout(6000)//
                // .setProxy(ip)//设置代理ip,不设置就用本机
                .build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 将最大连接数增加到200
        cm.setMaxTotal(1000);
        // 将每个路由基础的连接增加到20
        cm.setDefaultMaxPerRoute(500);
        // 连接池配置
        CloseableHttpClient httpClient = HttpClients.custom()//
                //	.setSSLSocketFactory(sslConnectionSocketFactory)//
                .setConnectionManager(cm)//
                .setDefaultRequestConfig(requestConfig)//
//				.setRedirectStrategy(redirectStrategy)//
//				.setRetryHandler(myRetryHandler)//
                .build();

        return httpClient;
    }

    public static void main(String[] args) throws Exception {
        HttpClientUtils.doPostGzip("http://localhost:8081/params", null,
                "{\"id\":\"123\",\"name\":\"testinfo\",\"value\":\"testinfoinfo\",\"params\":{\"1\":\"aa\",\"2\":\"bb\"}}");
    }
}