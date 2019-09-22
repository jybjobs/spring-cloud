package com.rkhd.sre.actuator.health;


import org.springframework.stereotype.Component;

//@Component
public class SelfHealthIndicator implements HealthIndicator {

    private  boolean UP = true;

  //  @RequestMapping("/health")
    public Health health() { // 字定義的health接口
        if(UP) {
            return new Health.Builder().withDetail("description","Demo service").withDetail("service status","UP").up().build();
        }else {
            return new Health.Builder().withDetail("eureka client status ","down").down().build();
        }
}

    public boolean isUP() {
        return UP;
    }

    public void setUP(boolean UP) {
        this.UP = UP;
    }
}