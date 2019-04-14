package com.yihecloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientApplication {


	public static void main(String[] args) {

//		SpringApplication.run(EurekaClientApplication.class, args);
		new SpringApplicationBuilder(EurekaClientApplication.class).web(true).run(args);
	}

}
