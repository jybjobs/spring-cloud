package com.yihecloud;

import org.apache.commons.codec.Charsets;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		//使用HttpClient替换默认实现
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		ClientHttpRequestFactory requestFactory =
				new HttpComponentsClientHttpRequestFactory(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		//解决中文乱码
		restTemplate.getMessageConverters()
				.set(1, new StringHttpMessageConverter(Charsets.UTF_8));
		return restTemplate;
	}

}
