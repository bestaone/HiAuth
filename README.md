<h1 align="center">Hi Auth</h1>

<div align="center">

HiAuth是一个开源的基于OAuth2.0协议的认证、授权系统，除了标准的OAuth2.0授权流程功能外，还提供了应用管理、用户管理、权限管理等相关功能。

[![Star](https://img.shields.io/github/stars/bestaone/HiAuth?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/HiAuth/stargazers)
[![Fork](https://img.shields.io/github/forks/bestaone/HiAuth?color=42b883&logo=github&style=flat-square)](https://github.com/bestaone/HiAuth/network/members)
[![Language](https://img.shields.io/badge/%E8%AF%AD%E8%A8%80-Java%20%7C%20Springboot%20%7C%20Vue3-red?style=flat-square&color=42b883)](https://github.com/bestaone/HiAuth)
[![License](https://img.shields.io/github/license/bestaone/HiAuth?color=42b883&style=flat-square)](https://github.com/bestaone/HiAuth/blob/master/LICENSE)
[![Author](https://img.shields.io/badge/作者-码道功臣-orange.svg)](https://github.com/bestaone)

</div>

## 介绍
除了认证相关功能外，还提供了`/example/demo`、`/example/himall`项目，供用户参考如何集成。

- 参考`demo`实例，你可以几分钟之内快速验证如何集成HiAuth；
- 参考`himall`实例，你可以快速的启动一个带页面的实例；

### LIVE
- HiAuth Docs：http://docs.hiauth.cn
- HiAuth Admin：http://auth.hiauth.cn/admin
- HiAuth 授权页：http://auth.hiauth.cn

### 目录结构
```
├─cicd                              持续集成
├─docs                              开发文档
├─example                           实例
│ ├─demo                            基础实例
│ ├─himall                          带有页面的实力
├─hiauth-client-spring-boot-starter 客户端SDK，如果深度的集成，可以参考此SDK，或者直接使用；
├─hiauth-front                      管理端前端项目
├─hiauth-server                     HiAuth服务端
├─hiauth-resource                   HiAuth资源服务端
├─other                             其他内容，数据库脚本等
```

## 快速尝试

### 环境需求
- Git
- JDK17+
- Maven 3.8+

### 下载源码
```sh
$ git clone https://github.com/bestaone/HiAuth.git
```
### 构建、启动
```sh
# 启动himall实例
$ cd HiAuth/example/himall
$ mvn clean install
$ mvn spring-boot:run
```

### 验证
- 访问HiMall：http://127.0.0.1:9000 点击`Login`按钮，登录账号：`corpadmin/123456`

> 注意：`127.0.0.1`不能使用`localhost`代替，因为数据库中配置了回调地址为`http://127.0.0.1:9000`。

### 其他集成方式
- 直接使用SaaS版集成，[参考文档](http://docs.hiauth.cn/guide/saas)；
- 安装Docker版并集成，[参考文档](http://docs.hiauth.cn/guide/docker)；
- 源码编译安装并集成，[参考文档](http://docs.hiauth.cn/guide/sourcecode)；

## 效果图
- 认证中心登录页
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/github/%E8%AE%A4%E8%AF%81%E7%99%BB%E5%BD%95%E9%A1%B5.jpg">
</p>

- 管理后台登录页
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/github/%E7%AE%A1%E7%90%86%E5%90%8E%E5%8F%B0%E7%99%BB%E5%BD%95%E9%A1%B5.jpg">
</p>

- 超级管理员-用户管理页
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/github/%E8%B6%85%E7%BA%A7%E7%AE%A1%E7%90%86%E5%91%98-%E7%94%A8%E6%88%B7%E7%AE%A1%E7%90%86%E9%A1%B5.jpg">
</p>

- 企业管理员-部门列表页
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/github/%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%91%98-%E9%83%A8%E9%97%A8%E5%88%97%E8%A1%A8%E9%A1%B5.jpg">
</p>

- 企业管理员-员工列表页
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/github/%E4%BC%81%E4%B8%9A%E7%AE%A1%E7%90%86%E5%91%98-%E5%91%98%E5%B7%A5%E5%88%97%E8%A1%A8%E9%A1%B5.jpg">
</p>

**如果你觉得此项目对你有帮助，请给我点个star，谢谢！**

## 授权协议
本项目执行 [MIT](https://github.com/bestaone/HiAuth/blob/master/LICENSE) 协议

## 社区与作者
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/community_wechat.jpg">
</p>

>如果群二维码失效了，请先添加我的微信，然我我拉你入群。