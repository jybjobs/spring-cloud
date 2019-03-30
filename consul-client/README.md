
# spring cloud demo

## Consul-client

#### 基本步骤
1.  引入pom
````xml
<dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
````
2.  配置
> @EnableDiscoveryClient
3.  添加配置信息
````

````
#### 健康检查

> 实际使用中可以通过spring-boot-actuator 端的health来实现：
1.  引入pom
 ````
 <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-actuator</artifactId>
 </dependency>
 ````
2.  开启检测
