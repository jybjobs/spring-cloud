
# spring cloud demo
## Eureka-server
1.  引入pom
2.  配置@EnableEurekaServer
3.  添加配置

## 增加 springcloud-dashboard
1.  引入 pom
````
		<dependency>
			<groupId>com.github.vanroy</groupId>
			<artifactId>spring-cloud-dashboard</artifactId>
			<version>1.2.0.RELEASE</version>
		</dependency>
````
2. 开启 Cloud Dashboard
> @EnableCloudDashboard 

3. 客户端配置pom
````
<!-- jmx-bean management -->
		<dependency>
			<groupId>org.jolokia</groupId>
			<artifactId>jolokia-core</artifactId>
		</dependency>
        <!-- 监控-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        
````

4. 客户端增加 endpoints

spring.boot.admin.routes.endpoints:  env,metrics,dump,jolokia,info,configprops,trace,logfile,refresh,flyway,liquibase,heapdump,loggers,auditevents,hystrix.stream


