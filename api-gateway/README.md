# springcloud-zuul

## 代理配置

#### 1. 引入starter
````
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-eureka</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-zuul</artifactId>
		</dependency>
````
#### 2. 开启网关
> @EnableZuulProxy

#### 3. 配置路由规则

````
 zuul.routes.[api-path].path=/[api-path]/**
 zuul.routes.[api-path].serviceId= [app-name]
 
 注意：
   zuul默认提供了 zuul.routes.\[app-name\].path=/\[app-name\]/**
   可以通过 zuul.ignored-services 设置不需要路由的规则
   
 路径通配符：
    ? 匹配任意单个字符
    * 匹配任意数量字符
    ** 匹配任意数量字符，支持多级目录
````

#### 4. 测试
> http://localhost:8888/feign-consumer/hello?info=&accessToken=aaa

## api网关请求校验
> Zuul 提供通过过滤器来实现对请求的拦截和过滤（继承ZuulFilter 实现4个抽象函数）

1. filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型:
   * pre：可以在请求被路由之前调用
   * route：在路由请求时候被调用
   * post：在route和error过滤器之后被调用
   * error：处理请求时发生错误时被调用
   
## 修改服务路由规则
````
    @Bean
	public PatternServiceRouteMapper serviceRouteMapper(){
		return new PatternServiceRouteMapper(
				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}"
		);// eg: feign-consumer-v1.0 --> /v1.0/feign-consumer
	}
````   
 > 测试：http://localhost:8888/v1.0/feign-consumer/hello?info=&accessToken=aaa 
  
## 本地跳转
````
zuul.routes.proxy.path=/proxy/**
zuul.routes.proxy.url=http://www.keepalived.org/

#zuul.routes.fw.path=/fw/**
#zuul.routes.fw.url=forward:/local
````

