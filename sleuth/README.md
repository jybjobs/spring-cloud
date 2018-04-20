
# springcloud-sleuth

## 基本配置
#### 1. 引入pom
````
	<dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-sleuth</artifactId>
    </dependency>
````

#### 2. 测试

````
输出说明：[应用名,TraceID,SpanID,是否输出到zipkin等服务展示] 
 [ribbon-consumber,cded0000ef60d98a,ca4508f0f4d369e7,false]  
````

## 与zipkin整合

#### 1. 搭建zipkin server
* 添加pom
* 启动zipkin server
#### 2. 引入和配置zipkin服务
* 引入pom
* 配置zipkin server url
