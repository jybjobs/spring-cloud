package com.yihecloud.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.logging.Logger;


@Controller
public class IndexController {
private final Logger logger = Logger.getLogger(String.valueOf(getClass()));

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/apps")
    @ResponseBody
    public String eurekaApps() {
        Iterator<Application> iterator = discoveryClient.getApplications().getRegisteredApplications().iterator();
        for (Iterator<Application> it = iterator; it.hasNext(); ) {//查询eureka中所以服务
            Application app = it.next();
            System.out.printf("appName:%s,instance:%s",app.getName(),app.getInstances());
        }
        System.out.println(""+discoveryClient.getApplications().getRegisteredApplications().iterator());
        System.out.println(""+discoveryClient.getApplicationInfoManager().getInfo().getAppName());
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("INDEX-SERVICE", false);
        return instance.getHomePageUrl();
    }

    @RequestMapping("/index")
    @ResponseBody
    String index() {
//        ServiceInstance instances = discoveryClient.getLocalServiceInstance();
//        logger.info(instances.getHost() + " ==> "+instances.getServiceId());
        return "Hello springboot!";
    }

}