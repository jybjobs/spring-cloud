package com.yihecloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yihecloud.entity.ParamEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

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


  //  @HystrixCommand(fallbackMethod = "paramsFallback")
    public String params(ParamEntity paramEntity) {
        Long startTime = System.currentTimeMillis();
        System.out.println("startTime = [" + startTime + "]");
        Map params = new HashMap<>();
        for (int i=0;i<10000;i++){
            params.put(i,"aa"+i);
        }
         paramEntity = new ParamEntity("1234", "testinfo", "testinfoinfo", params);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://INDEX-SERVICE/params", paramEntity,String.class);
        Long endTime = System.currentTimeMillis() -startTime;
        System.out.println("endTime = [" + endTime + "]");
        return responseEntity.getBody();
//        return  restTemplate.getForEntity("http://INDEX-SERVICE/params",String.class)
//                .getBody();
    }
    public String  paramsFallback(){
        return "error";
    }
}
