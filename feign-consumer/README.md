#springcloud-feign

> springcloud feign 基于Netflix Feign 实现，整合了Ribbon 和 Hystrix ，
此外还提供了一种声明式的web服务客户端定义方式。

 
## 配置

  #### 1. 引入 starter
````
  <dependency>
  		<groupId>org.springframework.cloud</groupId>
  		<artifactId>spring-cloud-starter-eureka</artifactId>
  </dependency>
  <dependency>
  		<groupId>org.springframework.cloud</groupId>
  		<artifactId>spring-cloud-starter-feign</artifactId>
  </dependency>
  
````
  #### 2. 开启Feign支持
  
  > @EnableFeignClients
  
  #### 3. 创建client对应service接口，使用 String mvc 注解绑定api接口
 
  #### 4. 创建feign 客户端 controller
  
  #### 5. 指定服务注册中心
  
## ribbon配置
  > Feign默认使用了Ribbon实现客户端负载均衡，可直接使用ribbon.<key>=<value> 的方式进行配置.
  
## Hystrix 配置

  #### 服务降级
  ````
   1. 创建降级类重写接口方法
   
   2. 通过 @FeignClient 的fallback绑定降级实现类
  ````
  
  
