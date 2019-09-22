
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
#### eureka健康检查

> 默认情况下eureka的健康检查是依赖client端的心跳实现的;
> 实际使用中可以通过spring-boot-actuator 端的health来实现;
> 由于actuator对 spring-boot 的依赖较大,涉及额外jar较多,此处自己实现了一个基本的健康检查

1.  关闭默认健康检测(会依赖actuator)
> eureka.client.healthcheck.enabled=false 
