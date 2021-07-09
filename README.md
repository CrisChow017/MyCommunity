## 一个缓慢完工的社区项目
## 资源网站
[BootStrap](https://v3.bootcss.com/components/#navbar)

[github app](https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app)

[OKHttp](https://square.github.io/okhttp/#mockwebserver) 用于发送post请求

[maven repository](https://mvnrepository.com/) Maven的仓库

[Flyway](https://flywaydb.org/) flyway数据库管理工具

[Lombok](https://projectlombok.org/) getter and setter 生成器

[Springboot](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html) 官方文档

[Mybatis-Generator](http://mybatis.org/generator/) 官方文档
## 项目工具

## 技术栈
java, jsp, spring boot

## 脚本
创建USER表
```sql
create table USER
(
ID INT auto_increment,
ACCOUNT_ID VARCHAR(100),
NAME VARCHAR(50),
TOKEN CHAR(36),
GMT_CREATE BIGINT,
GMT_MODIFIED BIGINT,
constraint USER_PK
primary key (ID)
);
```

##常用命令
```flyway更新sql
mvn flyway:migrate
```
```mybatis-generator
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```