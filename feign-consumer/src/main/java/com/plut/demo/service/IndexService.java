package com.plut.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jyb on 18-4-7.
 */

@FeignClient(name="index-service",fallback = IndexServiceFallback.class)
public interface IndexService {

    @RequestMapping("/index")
    String index();

    /**
     * 注意： 此处RequestParam 必须通过value指定，否则会报错：
     * （nested exception is java.lang.IllegalStateException:
     *  RequestParam.value() was empty on parameter 0）
     * @param info
     * @return
     */
    @RequestMapping("/hello")
    String hello(@RequestParam("info")  String info);
}
