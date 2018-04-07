package com.plut.demo.controller;

import com.plut.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyb on 18-4-7.
 */

@RestController
public class ConsumerController {
    @Autowired
    IndexService indexService;

    /**
     * 无参demo
     * @return
     */
    @RequestMapping(value = "feign-index",method = RequestMethod.GET)
    public String index (){
        return indexService.index();
    }

    /**
     * 带参demo
     * @param info
     * @return
     */
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello (@RequestParam String info){
        return indexService.hello(info);
    }
}
