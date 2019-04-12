package com.yihecloud.utils;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthCheckHandler {

    @Autowired
    private  SelfHealthIndicator selfHealthIndicator;
    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        System.out.println("instanceStatus = [" + instanceStatus + "]");
        Status status = selfHealthIndicator.health().getStatus();
        if(status == Status.UP)
            return InstanceInfo.InstanceStatus.UP;
        else
            return InstanceInfo.InstanceStatus.OUT_OF_SERVICE; // 该状态表示服務在 eurekaserver 中状态
    }
}
