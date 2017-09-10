package com.yihecloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@RestController
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

   @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/consumer")
    String index() {
        return  restTemplate.getForEntity("http://INDEX-SERVICE/index",String.class)
                .getBody();
    }



}