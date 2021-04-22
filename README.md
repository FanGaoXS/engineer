
# 工程车辆管理系统

## 介绍

本项目主要基于Vue.js和SpringBoot。利用微服务的思想，解决了工程车辆的监理以及附带的相关管理问题。

### 解决问题



### 在线演示

1、项目地址：https://car2.blctek.com:8443/

2、账号密码：ghost/ghost123





## 技术栈

本项目是前后端分离项目，需要用到前端、中端（服务器）、后端需要用到的技术栈。

### 前端

- npm：NPM的全称是Node Package Manager，是一个NodeJS包管理和分发工具，已经成为了非官方的发布Node模块（包）的标准。主要用来下载相关依赖包。
- [Vue.js](https://cn.vuejs.org/index.html)：Vue (读音 /vjuː/，类似于 view) 是一套用于构建[用户界面](https://baike.baidu.com/item/用户界面/6582461)的渐进式[JavaScript](https://baike.baidu.com/item/JavaScript/321142)框架。该项目的所有前端代码都由他构建，所以你必须掌握，并且很熟悉。本项目是基于Vue.js2.x
- [vue-router](https://router.vuejs.org/zh/)：vue官方提供的路由管理器，该项目的所有前端路由都由他控制，你必须掌握，并且熟悉。
- [vuex](https://vuex.vuejs.org/zh/)：vue官方提供的全局状态管理，该项目的全局状态管理由他提供，也必须掌握。
- [vue-cli](https://cli.vuejs.org/zh/)：vue官方提供的vue项目构建脚手架，能够让你够快的构建vue项目，简化了太多webpack的配置，你需要了解。本项目是利用vue-cli3.x构建。
- [axios](http://www.axios-js.com/zh-cn/)：一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中。比ajax更加强大且高效且能够高度定制化，可以将它看作对ajax的进一步封装。该项目的所有HTTP请求均有他构建，所以你必须掌握。
- [element-ui](https://element.eleme.cn/#/zh-CN)：一套为开发者、设计师和产品经理准备的基于 Vue 2.0 的桌面端组件库。本项目的所有组件都有他构建，所以你必须掌握且熟悉。
- [vue-element-admin](https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/)：是一个后台前端解决方案，它基于 [vue](https://github.com/vuejs/vue) 和 [element-ui](https://github.com/ElemeFE/element)实现。它使用了最新的前端技术栈，内置了 i18 国际化解决方案，动态路由，权限验证，提炼了典型的业务模型，提供了丰富的功能组件，它可以帮助你快速搭建企业级中后台产品原型。本项目的后台管理部分是基于它的模板vue-admin-template构建，你起码得需要掌握并且熟悉vue-admin-template
- [vue-amap](https://elemefe.github.io/vue-amap/#/)：基于 Vue 2.x 与高德的地图组件，本项目的路径显示部分由他构建，vue-amap比较简单，了解即可。
- [echarts](https://echarts.apache.org/zh/index.html)：一个基于 JavaScript 的开源可视化图表库，随用随看，了解即可。

### 服务器：

- Nginx：一个高性能服务器，主要解决服务器上的微服务之间的负载均衡问题，以及反向代理微服务。

### 后端：

- Maven：Springboot的所有依赖管理由它完成。

- SpringBoot：本项目后端基于SpringBoot-web实现，所以你必须掌握，并且很熟练。
- MyBatis：本项目的后端的数据的持久层由它完成，所以必须掌握，并且很熟练。
- MySQL：构建数据库。
- JWT：JSON Web Token。主要用来解决微服务之间的令牌共享。主要用于创建和验证token。
- aop：本项目后端日志部分由Spring-aop面向切面编程完成。

## 环境部署

### npm

```bash
npm -v # >=6.14.8
```

#### 1、移动到xxx-web下

```bash
cd xxx-web
```

#### 2、安装依赖包

```bash
npm i  # npm install
```

#### 3、运行开发模式

```bash
npm run dev
```

#### 4、运行生产模式

```bash
npm run build:prod
```

### maven

maven v3.5.0

### Java

JDK1.8

## 项目介绍

## 项目模块

## 前端手册

## 后端手册

