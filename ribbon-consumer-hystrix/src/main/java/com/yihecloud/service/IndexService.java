package com.yihecloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by jyb on 18-4-1.
 */
@Service
public class IndexService {

    @Autowired
    RestTemplate restTemplate ;


    @HystrixCommand(fallbackMethod = "indexFallback")
    public String indexService() {
        return  restTemplate.getForEntity("http://INDEX-SERVICE/index",String.class)
                .getBody();

    }

    public String  indexFallback(){
        return "error";
    }
}
