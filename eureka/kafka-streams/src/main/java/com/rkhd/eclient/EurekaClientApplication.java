package com.rkhd.eclient;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.rkhd.eclient.entity.CatServiceProvider;
import com.rkhd.eclient.utils.FilterGzip;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.binder.kafka.streams.annotations.KafkaStreamsProcessor;
import org.springframework.messaging.handler.annotation.SendTo;

//@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.rkhd")
//@EnableBinding(Sink.class)
public class EurekaClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}




}
