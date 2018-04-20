package com.yihecloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;


@Controller
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));


    @Autowired
    private DiscoveryClient discoveryClient;


    @RequestMapping("/index")
    @ResponseBody
    String index() {
        ServiceInstance instances = discoveryClient.getLocalServiceInstance();
        logger.info(instances.getHost() + " ==> "+instances.getServiceId());
        return "Hello springboot!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    String hello(@RequestParam String info) {
        ServiceInstance instances = discoveryClient.getLocalServiceInstance();
        logger.info(instances.getHost() + " ==> "+instances.getServiceId());
        return  ("".equals(info) ? "Hello springboot!":info);
    }


}