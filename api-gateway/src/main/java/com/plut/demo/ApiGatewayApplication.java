package com.plut.demo;

import com.plut.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy//开启网关服务功能
@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	/**
	 * 增加过滤器
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter(){
		return  new AccessFilter();
	}

	/**
	 * 修改 服务路由匹配规则
	 * @return
	 */
	@Bean
	public PatternServiceRouteMapper serviceRouteMapper(){
		return new PatternServiceRouteMapper(
				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}"
		);// eg: feign-consumer-v1 --> /v1/feign-consumer
	}
}

