package com.yihecloud;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by jyb on 17-8-13.
 */
@Component
@ConfigurationProperties(prefix = "index")
public class PropertSettings {
    private  String hello;

    public String getHello() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
