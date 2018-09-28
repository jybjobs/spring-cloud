package com.yihecloud.controller;


import com.yihecloud.entity.ParamEntity;
import com.yihecloud.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;


@RestController
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

   @Autowired
    IndexService indexService;
   @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/consumer")
    String index() {
        return  indexService.indexService();
    }

    @RequestMapping("/params")
    @ResponseBody
    String params(@RequestBody ParamEntity paramEntity) {
        //     String params() {
        long startTime = System.currentTimeMillis();
       String ret = indexService.params(paramEntity);
        System.out.println("endTime = [" + (System.currentTimeMillis() -startTime) + "]");
        return  ret;
    }
    @RequestMapping(value="/ptest")
    public String receive(HttpServletRequest req){
    Long startTime = System.currentTimeMillis();
        System.out.println("startTime = [" + startTime + "]");
        StringBuilder sb = null;
        String json = null;
        try {
            ServletInputStream inputStream = req.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            json = URLEncoder.encode(sb.toString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Long connectionTime = System.currentTimeMillis()- startTime;
        System.out.println("connectionTime = [" + connectionTime + "]");
        System.out.println("sb ----> " + sb.toString());
        if(sb != null){
            logger.info("**** 接收成功1条 ****");
        }
        MultiValueMap<String, String> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("jsonStr", json);
        Long reqBeforeTime = System.currentTimeMillis()- startTime;
        System.out.println("reqBeforeTime = [" + reqBeforeTime + "]");

        String str = restTemplate.postForEntity("http://INDEX-SERVICE/params", sb.toString(),String.class).getBody();
        logger.info("#### 传输 成功！####");
        Long afterTime = System.currentTimeMillis()- startTime;
        System.out.println("afterTime = [" + afterTime + "]");
        return str;
    }

    private byte[] getGzip(byte[] body) throws IOException {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {
            GZIPOutputStream zipStream = new GZIPOutputStream(byteStream);
            try {
                zipStream.write(body);
            } finally {
                zipStream.close();
            }
        } finally {
            byteStream.close();
        }

        byte[] compressedData = byteStream.toByteArray();
        return compressedData;

    }
}