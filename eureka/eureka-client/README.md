
#spring cloud demo
##Eureka-client
#### 基本步骤
1.  引入pom
````
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-eureka</artifactId>
</dependency>
````
2.  配置
> @EnableDiscoveryClient
3.  添加配置信息
````
eureka:
  client:
    serviceUrl:
          defaultZone: http://${eureka-ip:port}/eureka/
````
#### 健康检查

> 默认情况下eureka的健康检查是依赖client端的心跳实现的;
> 实际使用中可以通过spring-boot-actuator 端的health来实现：
1.  引入pom
 ````
 <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-actuator</artifactId>
 </dependency>
 ````
2.  开启检测
> eureka.client.healthcheck.enabled=true #通过 /health 检测client的可用性
