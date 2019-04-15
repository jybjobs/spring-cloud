package com.rkhd.eclient.utils;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.rkhd.sre.actuator.health.SelfHealthIndicator;
import com.rkhd.sre.actuator.health.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthCheckHandler {

    @Autowired
    private SelfHealthIndicator selfHealthIndicator;
    @Override
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        System.out.println("instanceStatus = [" + instanceStatus + "]");
//        EurekaDiscoveryClientConfiguration
        Status status = selfHealthIndicator.health().getStatus();
        if(status == Status.UP)
            return InstanceInfo.InstanceStatus.UP;
        else
            return InstanceInfo.InstanceStatus.OUT_OF_SERVICE; // 该状态表示服務在 eurekaserver 中状态
    }
}
