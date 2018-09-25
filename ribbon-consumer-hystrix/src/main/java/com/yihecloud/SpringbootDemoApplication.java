package com.yihecloud;

import com.yihecloud.utils.FilterGzip;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@EnableHystrixDashboard//开启 Dashboard
@EnableCircuitBreaker//开启断路保护
@EnableDiscoveryClient//服务发现
@SpringBootApplication
public class SpringbootDemoApplication {


	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
	}

	@Bean
	@LoadBalanced
//	RestTemplate restTemplate() {
//		return  new RestTemplate();
//	}
	public RestTemplate restTemplate() {

		//使用HttpClient替换默认实现
		HttpClient httpClient = HttpClientBuilder.create().build();
		ClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		//解决中文乱码
		restTemplate.getMessageConverters()
				.set(1, new StringHttpMessageConverter(Charsets.UTF_8));
		return restTemplate;
	}

	@Bean
	public FilterRegistrationBean  filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		FilterGzip httpBasicFilter = new FilterGzip();
		registrationBean.setFilter(httpBasicFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

}
