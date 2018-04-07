package com.plut.demo.service;

import org.springframework.stereotype.Component;

/**
 * Created by jyb on 18-4-7.
 */
@Component
public class IndexServiceFallback implements IndexService{

    @Override
    public String index() {
        return "error info.";
    }

    @Override
    public String hello(String info) {
        return "default msg.";
    }
}
