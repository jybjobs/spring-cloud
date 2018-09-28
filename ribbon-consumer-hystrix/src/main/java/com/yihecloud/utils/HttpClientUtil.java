package com.yihecloud.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * Created by JYB on 2018/9/27.
 */
public class HttpClientUtil {

    static Log LOGGER = LogFactory.getLog(HttpClientUtil.class);
    public static void sendHttp(String url, String message) {
        if (StringUtils.isBlank(message)) {
            LOGGER.info("a blank message, return.");
            return;
        }
        PostMethod postMethod = new PostMethod(url);
        postMethod.setContentChunked(true);
        postMethod.addRequestHeader("Accept", "application/json");
        postMethod.setRequestHeader("Content-Encoding", "gzip");
        postMethod.setRequestHeader("Transfer-Encoding", "chunked");

        try {
            ByteArrayOutputStream originalContent = new ByteArrayOutputStream();
            originalContent
                    .write(message.getBytes(Charset.forName("UTF-8")));

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
            originalContent.writeTo(gzipOut);
            gzipOut.finish();

            postMethod.setRequestEntity(new ByteArrayRequestEntity(baos
                    .toByteArray(), "application/json; charset=utf-8"));
        } catch (IOException e) {
            LOGGER.error("write message fail.", e);
            return;
        }

        int retry = 0;
        do {
            try {
                HttpClient httpClient = new HttpClient();
                int status = httpClient.executeMethod(postMethod);
                if (HttpStatus.SC_OK == status) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("send http success, url=" + url
                                + ", content=" + postMethod.getResponseBodyAsString());
                    }
                    return;
                } else {
                    String rsp = postMethod.getResponseBodyAsString();
                    LOGGER.error("send http fail, status is: " + status
                            + ", response is: " + rsp);
                }
            }  catch (IOException e) {
                LOGGER.info("io exception when send http.", e);
            } finally {
                postMethod.releaseConnection();
            }
            LOGGER.info("this is "+ retry + " time, try next");
        } while (retry++ < 3);
    }

    public static void main(String[] args) {

        HttpClientUtil.sendHttp("http://localhost:8000/params",
                "{\"id\":\"123\",\"name\":\"testinfo\",\"value\":\"testinfoinfo\",\"params\":{\"1\":\"aa\",\"2\":\"bb\"}}");
    }
}
