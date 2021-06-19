# 车票内购系统-TicketPurchase
本项目是实训项目企业员工车票内购系统的后端以及后台管理系统。<br>
基于 [RuoYi](https://gitee.com/y_project/RuoYi-Vue) 框架进行扩展，实现业务功能。<br>
## 项目部署
+ 项目的后端接口部署于http://49.232.26.148:8080/
+ 项目的管理后台部署于：[车票内购系统管理后台](http://49.232.26.148:2000/)
+ 项目的通过[腾讯云](https://cloud.tencent.com/)进行部署
## 技术栈
该项目的主要技术栈如下：<br>
### 后端
+ [Spring Boot](https://spring.io/projects/spring-boot)
+ [Spring Security](https://spring.io/projects/spring-security)
+ [MySQL](https://www.mysql.com/)
+ [MyBatis](https://mybatis.org/mybatis-3/zh/index.html)
+ [Redis](https://redis.io/)
+ [RocketMQ](https://rocketmq.apache.org/)
+ WebSocket
<br>
### 前端（管理端）
+ [Vue.js](https://vuejs.org/)
+ [element-ui](https://element.eleme.cn/)
### 前端（客户端）
+ [uni-app](https://uniapp.dcloud.io/api/README)
## 其他说明
+ 客户端通过小程序实现，并未部署到服务端，且源代码并非本组开发，因此代码并不在本项目中。
+ 如需访问后端接口，需要先访问登录接口获取token，并添加到header中