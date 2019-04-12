package com.yihecloud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yihecloud.entity.ParamEntity;
import com.yihecloud.utils.SelfHealthIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


@Controller
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private SelfHealthIndicator selfHealthIndicator;

    @RequestMapping("/index")
    @ResponseBody
    String index() {
        ServiceInstance instances = discoveryClient.getLocalServiceInstance();
        logger.info(instances.getHost() + " ==> "+instances.getServiceId());
        return "Hello springboot!";
    }

    @RequestMapping("/params")
    @ResponseBody
    String params(@RequestBody ParamEntity paramEntity) {
   //     String params() {
        long startTime = System.currentTimeMillis();
        ServiceInstance instances = discoveryClient.getLocalServiceInstance();
        logger.info(instances.getHost() + " ==> "+instances.getServiceId());
        Map params = new HashMap<>();
        for (int i=0;i<10000;i++){
            params.put(i,"aa"+i);
        }
        paramEntity = new ParamEntity("1234", "testinfo", "testinfoinfo", params);
       // return  ("".equals(info) ? "Hello springboot!":info);
        ObjectMapper objectMapper=new ObjectMapper();
        // 忽略json字符串中不识别的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 忽略无法转换的对象 “No serializer found for class com.xxx.xxx”
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
        String ret = null;
        try {
            ret = objectMapper.writeValueAsString(paramEntity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("endTime = [" + (System.currentTimeMillis() -startTime) + "]");
        return  ret;
    }

    @RequestMapping("/upstatus")
    @ResponseBody
    public String  upStatus(boolean status){
        selfHealthIndicator.setUP(status);
        return "success!";
    }

}