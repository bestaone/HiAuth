<h1 align="center">Hi Auth</h1>

<div align="center">

HiAuth是一个开源的基于Oauth2协议的认证、授权系统，除了标准的Oauth2授权流程功能外，还提供了应用管理、用户管理、权限管理等相关功能。

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

### 目录结构
```
├─cicd                              持续集成
├─docs                              开发文档
├─example                           实例
│ ├─demo                            基础实例
│ ├─himall                          带有页面的实力
├─hiauth-client-spring-boot-starter 客户端SDK
├─hiauth-front                      管理端前端项目
├─hiauth-server                     HiAuth服务端
├─hiauth-resource                   HiAuth资源服务端
├─other                             其他内容，数据库脚本等
```

## 快速集成

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
$ cd example\himall
$ mvn clean install
$ mvn spring-boot:run
```

### 验证
- 访问HiMall：http://127.0.0.1:9000

> 注意：`127.0.0.1`不能使用`localhost`代替，因为数据库中配置了回调地址为`http://127.0.0.1:9000`

### LIVE
- HiAuth Docs：http://hiauth.cn/docs
- HiAuth Admin：http://auth.hiauth.cn/admin
- HiAuth 授权页：http://auth.hiauth.cn

### 集成方式
- SAAS版集成，[参考文档](http://hiauth.cn/docs)；
- Docker版集成，[参考文档](http://hiauth.cn/docs)；
- 源码编译安装集成，[参考文档](http://hiauth.cn/docs)；

**如果你觉得此项目对你有帮助，请给我点个star，谢谢！**

## 授权协议
本项目执行 [MIT](https://github.com/bestaone/HiAuth/blob/master/LICENSE) 协议

## 社区与作者
<p align="center">
  <img width="900" src="https://hiauth.oss-cn-zhangjiakou.aliyuncs.com/community_wechat.jpg">
</p>

>如果群二维码失效了，请先添加我的微信，然我我拉你入群。