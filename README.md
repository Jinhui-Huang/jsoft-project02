# 吉软教育二期项目

<img alt="Static Badge" src="https://img.shields.io/badge/language-Java-blue">
<img alt="Static Badge" src="https://img.shields.io/badge/language-JavaScript-red">
<img alt="Static Badge" src="https://img.shields.io/badge/language-SQL-SQL">
<br>
<a href="https://github.com/HmEJ"> <img alt="Dynamic JSON Badge" src="https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fapi.spencerwoo.com%2Fsubstats%2F%3Fsource%3Dgithub%26queryKey%3DHmEJ&query=%24.data.totalSubs&suffix=%20followers&logo=github&label=HmEJ" > </a>
<a href="https://github.com/Jinhui-Huang"> <img alt="GitHub followers" src="https://img.shields.io/github/followers/Jinhui-Huang?label=Jinhui-Huang&logo=github">
 </a>
<a href="https://github.com/Yuexinliang"> <img alt="GitHub followers" src="https://img.shields.io/github/followers/Yuexinliang?label=Yuexinliang&logo=github">
 </a>
<a href="https://github.com/Feilixyao"> <img alt="GitHub followers" src="https://img.shields.io/github/followers/Feilixyao?label=Feilixyao&logo=github">
  </a>

欢迎来到我们的项目介绍页面, 我们的组织是
# <p style="color: #ad234d">**M Y H D**</p>

## 项目介绍

前端技术栈：JQuery, CSS(AmazeUI)，ajax

后端技术栈: Mybatis, Druid-JDBC，junit，jwt(令牌验证)，logback+sl4j+lombok(日志记录框架)，servlet+jsp+connector(服务器-数据库连接接口)，maven(项目管理框架)

数据库: MySQL

[Java源码：点击查看](src/main/java)

[前端页面源码：点击查看](src/main/webapp)

## 项目流程

### 1. 登录

输入正确的账号与密码即可进入下一步，若错误则提示

### 2. 跳转至信息认证

先判断是信息是否已认证，未认证则需认证顶面,认证完的信息回显展示

### 3. 添加供应商至白名单

企业可以添加白名单用来和供应商建立联系，可以按条件查询想要的供应商并显示其基本信息，最后也可以解除与选中供应商的白名单关系，被解除的供应商自动进入黑名单

### 4. 添加供应商至黑名单

企业可以拉黑供应商用来终止与该供应商的合作。可以按条件查询想要的供应商并显示其基本信息，最后也可以解除黑名单关系

### 5. 退出系统

退出供应商管理系统


## 项目历程

### 9-22第一天

流程图分析

### 9-23第二天 

写Dao层

### 9-24第三天

写Service层

### 9-25第四天

写Servlet与部分页面

### 9-26第五天

完善页面

### 9-27第六天

BUG调试与解决

## 小组分工

Jinhui-Huang：搭建整体项目框架，配置项目基础过滤器，配置项目所需依赖，负责使用git管理并合并整体项目

yuexinliang：负责用户Dao层，服务层以及登录页面功能实现，企业Dao层，服务层以及认证页面的部分功能，建立数据库

JoneElmo：负责白名单Dao层，服务层以及页面的功能，负责企业服务层以及认证页面的部分功能

DaiYao: 负责黑名单Dao层，服务层以及Servlet

## 附件

