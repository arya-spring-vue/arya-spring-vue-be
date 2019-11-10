![](https://github.com/arya-spring-vue/arya-spring-vue-be/blob/master/screenshot/arya-spring-vue-be.png)

# arya-spring-vue-be
这是一个后台管理系统的服务端，后端技术架构spring-boot, spring ,mysql

### 后端技术架构
spring-boot, spring ,mysql

### 项目构建过程
#### 1.初始化项目

[Spring Initialzr](https://start.spring.io/) 

- 基本：Maven，Java8，Spring Boot 2.1.3
- 依赖：Web，MySql8.0.15, Lombok

#### 2.Spring与MySql的ORM映射
- 创建数据库，设置用户账号密码并赋权
- 创建application.properties，配置Spring JPA和Spring DataSource，建立Spring与Mysql之间的连接

#### 3.Spring编写Controller提供接口
- 创建实体类，其实也就是通过Spring创建user表，指定pk，并创建name，email字段及其getter和setter，通过lombok的@Data注解优化实体类
- 创建用于CRUD的repository Bean，继承spring CrudRepository类，用于实例的增删改查
- 创建Spring的Controller，并且通过RequestMapping添加路由，RequestParam,RequestBody抽取请求中数据，在不同路由进行不同的业务处理

#### 4.打包运行本地启动服务供前端调用
- 创建基于Spring的内嵌Tomcat容器运行时，这是通过spring boot的SpringApplication
- 通过mvn生成可执行的jar包并运行:`./mvnw clean package` `java -jar target/foo-bar-baz.jar`或者`./mvnw spring-boot:run`
- 服务运行在localhost:8080

#### 5.配置项目全局配置支持CORS
- 通过CorsRegistry.allowedOrigins("http://localhost:3000"),指定 Access-Control-Allow-Origin:http://localhost:3000，限制指定源发送跨域请求，提升安全性
- 通过CorsRegistry.allowedMethods("GET", "POST", "PUT", "DELETE"),指定 Access-Control-Allow-Methods: GET, POST, PUT", DELETE，支持OPTIONS跨域预检


#### 6.提供符合Restful规范的CRUD接口
- 增 POST `/spring/vue/createUser` reqBody
- 删 DELETE `/spring/vue/deleteUser/{id}` pathVar
- 改 PUT `/spring/vue/updateUser/{id}` pathVar,reqBody
- 查 GET `/spring/vue/readUsers`

#### 7.注册登录
- 注册 仅支持输入手机号进行注册，需要输入密码
- 登录 支持手机号密码登录、手机号验证码登录、微信二维码登录


### 参考资料
- https://spring.io/guides/gs/accessing-data-mysql/
- https://spring.io/guides/gs/rest-service-cors/
- https://www.baeldung.com/spring-cors
