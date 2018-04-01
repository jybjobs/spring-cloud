package com.yihecloud.controller;


import com.yihecloud.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@RestController
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

   @Autowired
    IndexService indexService;

    @RequestMapping("/consumer")
    String index() {
        return  indexService.indexService();
    }



}