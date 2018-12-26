package com.yihecloud;

import com.yihecloud.utils.FilterGzip;
import org.apache.commons.codec.Charsets;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.consul.serviceregistry.ConsulServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;


@EnableDiscoveryClient
@SpringBootApplication
public class ConsulClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(ConsulClientApplication.class, args);
	}
	ThreadPoolExecutor
//	@Bean
//	public RestTemplate restTemplate() {
//		//使用HttpClient替换默认实现
//		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//		ClientHttpRequestFactory requestFactory =
//				new HttpComponentsClientHttpRequestFactory(httpClient);
//		RestTemplate restTemplate = new RestTemplate(requestFactory);
//		//解决中文乱码
//		restTemplate.getMessageConverters()
//				.set(1, new StringHttpMessageConverter(Charsets.UTF_8));
//		return restTemplate;
//	}
//@Bean
//public FilterRegistrationBean filterRegistrationBean() {
//	FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//	FilterGzip httpBasicFilter = new FilterGzip();
//	registrationBean.setFilter(httpBasicFilter);
//	List<String> urlPatterns = new ArrayList<String>();
//	urlPatterns.add("/*");
//	registrationBean.setUrlPatterns(urlPatterns);
//	return registrationBean;
//}
}
