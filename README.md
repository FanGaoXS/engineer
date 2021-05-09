# 微服务架构的工程车辆管理系统

## 介绍

本项目是利用了微服务架构的思想，提出一种解决方案，解决了工程车辆监理的难题，以及附带的工程车辆管理。微服务是一种架构。顾名思义就是将一个项目拆分成多个小的服务，这样易于扩展也易于维护，只要把负载均衡的问题解决好了，性能就不是大问题。所以我才会在服务端选择使用Nginx高性能服务器。只是我这种解决方案弊端会比较多，但是会比较适合熟悉SpringBoot并且比较小一点项目。后面会升级到SpringCloud也不一定。SpringCloud是一种比较好的微服务解决方案，生态也很完备，并且提供了较好的负载均衡、服务容灾、注册中心等组件。

### 解决问题

在过去，传统路桥施工行业的工程用具管理问题一直是个大难题。对于管理方面来说， 属于工程用具的工程车辆管理是比较难的一个问题，会涉及到工程车辆的申报、工程车辆 的检修、工程车辆的维护、工程车辆的归属、工程车辆的基本信息以及工程车辆的每日使 用情况等问题。对于监理来说，工程车辆的每日使用情况跟踪也是一个问题。 传统的解决工程车辆管理的方法就是采用纸质档记录车辆信息并归档，虽然这样比较 方便并且数据有更久的保存性，但是对于大型项目来说，纸质档文件的传递性就会比较低 下，并且使用大量的纸张不环保，造成资源的浪费。但这些问题都不是核心问题，我认为 对于传统项目来说，纸质档记录车辆信息存在最大的问题就是可欺骗性和篡改性，由其体 现在工程车辆的每日使用情况的监理上。 传统的解决工程车辆的每日使用情况的监理则是采用请监理公司的人员对工程车辆 进行监理，从而达到实现对工程车辆的使用情况认定的问题。但是这样可能会有一个问题 就是有的司机可能会偷懒，并且向监理人员多报自己的工作量等，还有可能监理人员与司机串通，这样就没有很好的达到管理的目的。 一般在工程上的工程车辆会涉及到几个方面需要进行监理：车辆的油耗、车辆的每日 使用情况、车辆的行驶路径等核心。所以如何高效且准确地记录他们并且结合计算机地方 式对它们进行监理，是我想要解决的关键性问题。

### 在线演示

1、上传演示地址：http://upload.yueke.cloud/TESTTEST

2、后台地址地址：http://engineer.yueke.cloud/

3、后台演示账号：ghost/ghost123

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

### 服务器

- Nginx：一个高性能服务器，主要解决服务器上的微服务之间的负载均衡问题，以及反向代理微服务。

### 后端

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

1、移动到xxx-web下

```bash
cd xxx-web
```

2、安装依赖包

```bash
npm i  # npm install
```

3、运行开发模式

```bash
npm run dev
```

4、运行生产模式

```bash
npm run build:prod
```

### maven

maven v3.5.0

### Java

JDK1.8

## 项目介绍

本项目的前端都是基于Vue.js、后端都是基于SpringBoot，服务端采用的是Nginx高性能服务器。

### 项目架构

![image-20210422124412749](img/image-20210422124412749.png)

采用微服务的好处就是易于扩展，前后端不会太耦合。

## 版本控制

版本控制采用的是git。

有两条分支（branch）：dev（开发分支）、master（主分支）

标签版本（tag）：每发布一个版本或者有重大更新就会打上tag

## 环境变量

开发的时候肯定会遇到本地开发环境（以下简称dev）和服务器线上生产环境（以下简称prod）等，几套数据库，几套接口地址，如果每一次都需要修改对应的数据库的连接地址或者接口地址，我相信这肯定会是一场灾难。所以你需要保证你的dev和prod的环境起码是一样的（或者差别不要太大），比如prod上起码得有MySQL数据库、Nginx服务器、JRE（Java运行环境），其实OpenJDK也行，运行时占用的内存比较小，但是性能可能就会差些。你本地dev就也必须得有上述东西才行。

### 前端

采用.env.production和.env.development文件来配置对应的接口地址的，具体可以参看：[vue-element-admin的环境变量](https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/essentials/env.html#%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F)

比如我的dev环境需要的接口地址：

```properties
# 开发环境
# just a flag
ENV = 'development'
# base url
VUE_APP_BASE_URL = 'http://localhost:8080/'
```

具体到时候在引用的时候就采用`process.env.VUE_APP_BASE_URL`来获取：

```js
//axios实例
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_BASE_URL, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})
```

记住：`process.env.VUE_APP_BASE_URL`和`process.env['VUE_APP_BASE_URL']`是等价的。那么接下来每次运行的时候就会根据你配置的环境变量来自动获取VUE_APP_BASE_URL了。这点简直不要太方便。

### 后端

后端主要就是采用SpringBoot的application.yaml配置文件的方式来实现：

```
application.yaml				#公共配置文件（同时会表明要启用哪个配置文件）
application-dev.yaml			#开发环境配置文件
application-prod.yaml			#生产环境配置文件
```

application.yaml：

```yaml
#当前启用哪个配置文件
spring:
  profiles:
    active: dev
#项目名称
  application:
    name: user-server
#数据源
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      stat-view-servlet:
        enabled: true
        login-username: mysql
        login-password: mysql
#端口号
server:
  port: 8097
#mybatis相关
mybatis:
  mapper-locations: classpath*:mybatis/mapper/*Mapper.xml
```

这里配置了全局配置文件，同时在spring.profiles.active里根据名字启用对应的配置文件

application-dev.yaml：

```yaml
#当前配置文件名称
spring:
  config:
    activate:
      on-profile: dev

  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/xxx?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password:

```

application.prod.yaml：

```yaml
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://xxx.xx.xx.xx:3306/xxx?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8
    username: root
    password: root
```

局部配置如果有同名属性会覆盖全局配置，类似局部变量和全局变量。

## 服务端部署

服务端部署采用的Nginx，所以只需要修改Nginx的配置文件就行。最好是一个前端就当作是一个项目，然后再根据对应的端口号代理对应的后端：

```nginx
	# localhost:8081上传车辆信息
	server	{
		listen 8081;
		server_name localhost;
		location / {
			root E:/project/upload-web/dist/;
			index index.html;
            try_files $uri $uri/ /index.html;
		}
		#静态资源服务器（用于上传图片）
		location /assets-server/ {
			client_max_body_size  15M;
			proxy_pass	http://localhost:8095/;
		}
		#工程用具信息服务器（用户上传车辆信息和获取类型数据）
		location /engine-server/ {
			proxy_pass http://localhost:8096/;
		}
	}
```

前端的location就直接绝对定位到对应的项目`dist/`下，后端就直接根据端口号代理即可。

> **注意**
>
> Nginx的代理是需要在`proxy_pass http://localhost:8095/`后面这个地址的最后加上`/`，这样就是代理。如果`proxy_pass	http://localhost:8095`没有加上`/`，就是转发。
>
> **区别**
>
> 比如我从浏览器客户端发起了一个`localhost:8095/assets-server/image/test.jpg`的请求：
>
> 如果是代理的话，Nginx会先匹配到`location /assets-server/`然后就会直接将`/assets-server/`用这个`localhost:8095`的服务（以Jar包形式启动的SpringBoot项目）替代，即用户实际上会访问的是`localhost:8095`这个服务，或者说是`8095`端口下的`/`服务。
>
> 如果是转发，Nginx同样会匹配到`location /assets-server/`然后直接将`8095`这个端口号下的`/assets-server/`服务转发给用户，即用户实际上访问到的会是`8095`端口下的`/assets-server/`服务。

## <span id="auth">权限验证</span>

### 后端

JWT

关于JWT可以参看：[JWT认证原理，并整合SpringBoot](https://blog.csdn.net/weixin_45747080/article/details/111684442)

利用JWT来贯穿全后端，然后利用每个SpringBoot-web的HandlerInterceptor（拦截器），在拦截器中验证是否存在token，并且验证token的有效性、token中存的用户的角色等来判断是否允许请求，并且返回相应的response。同时在WebMvcConfigurer里注册该拦截器，并且配置好哪些请求需要拦截，哪些请求不需要拦截。

```java
@Component
public class CrudInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String token = request.getHeader("X-Token");//从header中获取token
        // 对token进行相应判断
        if (true) {
            return true; //放行
        } else {
            return false; //不放行
        }
    }
}
```

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CrudInterceptor crudInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathList = new ArrayList<>();          //要拦截的请求集合
        pathList.add("/role/**");                       //要拦截/role/下的所有请求
        pathList.add("/user/**");                         //要拦截/user/下的所有请求
        List<String> excludePathList = new ArrayList<>();   //不过滤的请求集合
        excludePathList.add("/role/select*");           //不过滤/role/下的查询的请求
        excludePathList.add("/user/select*");             //不过滤/user/下的所有查询的请求
        registry.addInterceptor(crudInterceptor)
                .addPathPatterns(pathList)
                .excludePathPatterns(excludePathList);
    }
}
```

PathPatterns是配置哪些来自浏览器的请求需要被拦截，ExcludePathPatterns则是不需要被拦截。（我一般会拦截除了查询的请求）。

所以后端的权限验证逻辑就是，在用户登录成功的时候会向用户下发一个token（token中包含用户的角色，JWT特性）。然后每一次发起请求时如果被拦截，就会进行对token进行相应的判断（比如该角色是否拥有该操作的权限），然后放行或者不放行。所以token就是令牌，就是通行证，每一次的发起请求都会根据你携带的token进行验证身份。

### 前端

主要采用cookies和vuex和vue-router结合使用的方式来达到存储用户token，验证用户token，限制用户访问和操作

cookies

`@/utils/auth.js`（token在cookies中的存取）：

```js
import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

//从cookies里获得key为'vue_admin_template_token'的token
export function getToken() {
  return Cookies.get(TokenKey)
}

//将key为vue_admin_template_token的token放入cookies中（设置cookies过期时间（number类型））
export function setToken(token,expires=7) {
  return Cookies.set(TokenKey, token, { expires })
}

//将cookies中key为vue_admin_template_token的token移除
export function removeToken() {
  return Cookies.remove(TokenKey)
}

```

vuex

`@/store/modules/user.js`（将token赋值给vuex）

```js

import { getToken, setToken, removeToken } from '@/utils/auth'
import router , { resetRouter } from '@/router/index'

//默认的state
const getDefaultState = () => {
  return {
    token: getToken(), //token（存在cookies中）
    id: undefined,
    roles: [],
  }
}

const state = getDefaultState()

//同步方法
const mutations = {
  // 重置STATE
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  // 设置id
  SET_ID: (state, id) => {
    state.id = id
  },
  // 设置角色
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  // 设置token
  SET_TOKEN: (state, token) => {
    state.token = token
  }
}

//异步方法
const actions = {
  // 用户登录
  login({ commit }, loginInfo) {
    //登录成功后获得token、用户的相关信息roles（角色）
    //将token存入cookies
  },

  getInfo({ commit, state } ){
      
  },

  // 用户退出
  logout({ commit, state }) {
    //退出时清空cookies中的token并且将state还原
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

```

由于vuex的特性，vuex的全局变量是存储在内存中的，浏览器刷新了以后就没有了，所以如果想要vuex全局变量能够持久化就可以考虑使用localStorage或者sessionStorage存储到本地，而该项目使用的是每一次请求路由（刷新页面也会重新请求路由）的时候使用`getInfo()`方法利用token向服务端请求一次用户个人信息，从而每次都会获得用户的个人信息，如id、roles。这样就不会每次都从客户端本地取，而是从服务端中取。

无论哪种方式都是可行的，你只需要注意的就是vuex的全局变量是存储在内存中的，刷新就没有了，你需要在客户端持久化。在用户登录的时候就将用户个人信息，如id、roles赋值给vuex和本地存储，并且将token存入cookies。用户退出时必须要先清空cookies中的token和客户端本地的个人信息，最后再将state利用`getDefaultState()`还原。因为`getDefaultState()`里是取的cookies和本地存储的个人信息，每次在用户进入在项目的时候都会赋值（直接从cookies和本地取值），如果你没有先清空cookies和本地存储就进行还原state，那么这次用户即使退出，但是vuex里存储的仍然有cookies中的token和本地存储的个人信息。具体可以参看vue-admin-template的store中user用户信息的设计[@/store/mudules/user.js](https://github.com/PanJiaChen/vue-admin-template/blob/master/src/store/modules/user.js)，写得特别好。

vue-router

另外还通过路由来控制用户哪些页面能访问哪些页面不能访问：在访问路由前通过路由守卫获得要访问的页面的权限（哪些角色能够访问），再通过vuex中存储的用户的角色进行比对，如果匹配则允许访问，否则不允许访问。关于如果利用vue-router来控制用户能够访问的页面，vue-element-admin的作者PanJiaChen的这篇文章讲得特别好 [手摸手，带你用 vue 撸后台 系列二(登录权限篇)](https://juejin.im/post/591aa14f570c35006961acac)。

v-permission

通过你还可以通过指令来达到按钮级别的权限控制：控制该操作该用户是否能够执行，具体可以参看：[vue-admin-element的权限验证](https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/essentials/permission.html)

## 密码加密

用户登录的用户名和密码验证以及存储是一个老生常谈的问题，你需要考虑以下问题：

1. 密码在http请求中不能明文传输。
2. 密码在数据库中存储时开发者不能知道。

本项目采用的方式是：用户不能自行注册，只能管理员新增用户，管理员新增用户的时候会给用户生成一个只属于该用户的**salt（盐）**，并且该盐存在另外一个表内，同时该盐与用户的用户名按照一定规则再经过md5加密算法生成一个默认的密码。所以用户默认密码是随机盐+用户名+规则。所以这就解决了第二个问题，用户的密码开发者是完全不知道的。同时在浏览器客户端，用户在请求登录的时候会先根据用户名获得该用户名的salt，如果salt存在说明存在该用户，此时用户输入密码后，再根据密码+盐+规则的方式混淆该密码，然后通过post的方式提交给后端，后端直接将用户名和密码进行验证，所以此时就解决了第一个问题，密码在http请求中是密文传输。

需要注意的是，前后端的密码混淆规则应该一致才行。

- 为什么要用md5算法？因为md5是不可逆的。
- 为什么要使用随机salt？避免撞库。

关于该项目前端如何实现密码加密请参看：[前端手册之密码加密](#front-end-secret)

关于该项目后端如何实现密码加密请参看：[后端手册之密码加密](#back-end-secret)

## <span id='resource'>静态资源</span>

利用SpringBoot-web启动了一个只存放静态资源的服务。

### 文件上传

比如我需要上传图片或者文件，只需要利用MultipartFile类接收文件，然后存储到服务器本地就可以了。关于该项目的图片上传请参看：[前端手册之图片上传](#upload-image)

### 文件下载（访问）

文件下载到服务器本地了没有用，还得需要利用该服务打开本地资源映射才行。

配置WebMvcConfigurer：

```java
@Configuration
public class AssetsConfigurer implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取文件的真实路径
        String winPath = System.getProperty("user.dir")+"\\assets-server";
        String linuxPath = System.getProperty("user.dir")+"/assets-server";
        //获取操作系统的名字
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {//windows系统
            registry.addResourceHandler("/image/**","/zip/**")
                .addResourceLocations("file:"+winPath+"/image/","file:"+winPath+"/zip/");
        }else{//linux和mac系统 可以根据逻辑再做处理
            /*handlers表示会拦截的请求，比如会处理/image*//**这种样式URL的请求
            locations表示会映射到的本地路径
            将resourceHandler拦截到的URL请求映射到resourceLocations上的本地路径
             比如以下语句就会拦截/image/**和/zip/**的url请求，然后映射到服务器本地的
             /image/或/zip/文件路径下 */
            registry.addResourceHandler("/image/**", "/zip/**")
                .addResourceLocations("file:"+linuxPath+"/image/","file:"+linuxPath+"/zip/");
        }

    }
}
```

主要的逻辑就是拦截对应的请求，然后将该对应的请求映射到服务器上的本地目录，这样就可以达到访问（下载）的目的了。就相当于将本地目录映射到某某请求上，那么浏览器客户端在访问该请求的时候就会自动映射到该文件上，这样就可以下载或者浏览了。

## 工程用具上传

手机扫描二维码或者访问http://upload.yueke.cloud/+deviceId，需要注意的是，需要在`/`后面加上设备编号，然后就会直接重定向到填写工程用具信息页面，自动将URL中`/`后面的设备编号填入表单中：

![IMG_1009(20210422-160959)](img/IMG_1009(20210422-160959).JPEG)

需要注意的是，工程用具信息以及上传图片大小都会有验证，必须正确如实的输入。同时图片上传成功后会返回该图片的uuid作为该图片的唯一识别码，然后绑定到工程用具的信息中，这也访问该工程用具就能访问到该uuid，从而访问到该图片。

关于页面重定向：

使用vue-router实现的，利用vue-router的动态参数，获取URL上的参数。

`@/router/index.js`

```js
let routes=[
  {
    path: '/:deviceId',
    redirect: '/vehicle/:deviceId' 					//默认会重定向到Vehicle页面并且携带参数
  },
  {
    name: 'Vehicle',
    path: '/vehicle/:deviceId',
    component: () => import('../views/upload/vehicle')	//携带参数
  },
  {
    name: 'Machine',
    path: '/machine/:deviceId',
    component: () => import('../views/upload/machine')	//携带参数
  }
];
```

然后通过`this.$route.params.deviceId`来获得URL上的参数

## 工程监理

该项目的核心功能，通过定位来达到对工程用具进行监理的目的。获取来自硬件后端的GPS坐标中的经纬度来进行在地图上定位（以及一些GPS常用的其他信息，比如风速、海拔、时间）。`vue-amap`将高德地图JS API进行了封装，能够快速绘制折线和图标以及一些常用的插件，比如：比例尺、鹰眼、卫星图等。

### 效果图

![image-20210422163614558](img/image-20210422163614558.png)

### 原理

GPS定位通常返回的是当前瞬时时间下当前位置的经度、纬度、高度、海拔等信息。两点就会连成一条直线，n点依次连接就会形成折线，然后再绘制出起点和终点。

GPS坐标点原数据

```json
{
    "data": [
        {
            "vehicle_num": "川ABP369",
            "record_time": "2021-02-04T09:39:12.000+0800",
            "latitude": 29.66222,
            "longitude": 91.0528,
            "latitude_amap": 29.65952,
            "longitude_amap": 91.05436,
            "altitude": 3612.7,
            "pdop": 3.3,
            "hdop": 1.8,
            "vdop": 2.8,
            "cogt": 0,
            "sogknot": 0,
            "sogkph": 0
        },
        {
            "vehicle_num": "川ABP369",
            "record_time": "2021-02-04T09:39:52.000+0800",
            "latitude": 29.66223,
            "longitude": 91.05278,
            "latitude_amap": 29.65953,
            "longitude_amap": 91.05434,
            "altitude": 3612.2,
            "pdop": 3.1,
            "hdop": 1.7,
            "vdop": 2.6,
            "cogt": 0,
            "sogknot": 0,
            "sogkph": 0
        },
        ...
}
```

都是类似这样的坐标点数组，然后再将这些的形成坐标数组传入给`vue-amap`绑定到polyline上就能绘制出来了：

```vue
<el-amap-polyline
          :path="path"
          :lineJoin="'round'"
          strokeColor="#409EFF"
          strokeOpacity="0.8"
          strokeWeight="5"
          strokeStyle= "solid"
        /><!--轨迹折线-->
<el-amap-marker
          vid="startMarker"
          :position="startMarker.position"
          title="起点"
        /><!--起点标记-->
<el-amap-marker
          vid="endMarker"
          :position="endMarker.position"
          title="终点"
        /><!--终点标记-->
```

逻辑就是通过网络请求得到一整条坐标点对象数组，然后将该坐标点对象的经纬度赋值给path（因为高德地图折线所需要的数组只有经纬度），然后再将path绑定到`el-amap-polyline`上就可以了，同理可得坐标点对象的时间数组、海拔数组等，然后即可计算出平均海拔、起点坐标和终点坐标。

### 总结

GPS定位仪器放置在车辆上，设置一定时间间隔获取来自GPS的信号，GPS会返回此时该点的经纬度、海拔、时间等信息，将信息上传到服务器中，然后前端利用http请求获得某一辆车、某个时间段的坐标点对象数组，然后遍历该数组，存放成高德地图适用的格式（高德地图适用的格式）。

### 文档

[高德地图JS API](https://lbs.amap.com/api/javascript-api/guide/overlays/vector-overlay#polyline)：高德地图原生官方JS API。

[vue-amap](https://elemefe.github.io/vue-amap/#/)：基于Vue.js和高德原生JS 封装。

## 工程用具

方便对录入该系统的工程用具进行信息上的管理，比如：车辆归属、车牌、驾驶员等相关信息。

### 效果图

展示

![image-20210422181951580](img/image-20210422181951580.png)

直接通过http请求获得工程用具对象数组，然后遍历数组，得到对应的信息，其中图片需要注意的是：直接利用该工程用具uuid然后访问到静态资源服务上的该图片就能访问了。

修改

![image-20210422182451364](img/image-20210422182451364.png)

## 工程用具类型

方便自定义工程用具的类型，比如车辆会有越野车、公路车、洒水车等等类型，方便自定义修改删除增加

### 效果图

展示

![image-20210422182925465](img/image-20210422182925465.png)

新增

![image-20210422183016757](img/image-20210422183016757.png)

修改

![image-20210422183032592](img/image-20210422183032592.png)

因为工程用具的类型是直接绑定的类型编号，所以即使类型修改后，工程用具的类型也会跟着修改。同时在扫码录入工程用具信息的时候也会随之更新。

## 系统权限

本项目权限划分主要用角色和用户来划分。

### 角色

角色类似于用户组的概念，就是某个角色能够访问哪些页面，某个角色能够进行哪些操作。目前角色只细分到了用户能够进行具体哪一个操作上（比如：增删改用户），同时还能够新增角色（v1.6）。目前暂不支持新增角色后然后给角色指定哪些页面能够访问哪些操作能够进行。

展示

![image-20210422202723968](img/image-20210422202723968.png)

修改

![image-20210422202738878](img/image-20210422202738878.png)

新增

![image-20210422202833620](img/image-20210422202833620.png)

### 用户

用户就是能够登录系统的最小单元。通过指定给用户分配角色来指定用户能够访问什么页面，进行什么操作。同时在前面的<a href="#auth">权限验证</a>有讲过我是怎样做到前后端互相联调实现权限验证的。

展示

![image-20210422202804804](img/image-20210422202804804.png)

条件查询

同时还可以按角色直接快速查询用户

![image-20210422203010083](img/image-20210422203010083.png)

修改

![image-20210422202856227](img/image-20210422202856227.png)

新增

![image-20210422202912609](img/image-20210422202912609.png)

## <span id='logger'>日志管理</span>

由SpringBoot-AOP实现，细分到controller上的方法，获得该方法的切面（只为增删改定义了切点），同时采用注解的方式给方法定义切点：方法上有注解的才会执行aop。同时在该切面的正常返回通知里来判断该方法是否执行成功、执行者、执行的客户端来源、执行者的IP地址、执行该操作的时间等。关于SpringBoot-AOP的切点、切面以及返回通知的解释请参看：

展示

![image-20210422203949714](img/image-20210422203949714.png)

条件查询

同时能够利用执行者或者来源客户端进行快速查询日志

![image-20210422204018170](img/image-20210422204018170.png)

> **提示**
>
> 估计是Nginx正向代理的问题，直接将用户访问的真实IP经过正向代理后然后再经过反向代理到具体日志微服务中的时候直接将Nginx服务器的本机地址代理到服务当中去了。下个版本预计修复。
>
> **解决方案**
>
> 不用Nginx反向代理，使用Nginx直接转发到服务。

### 注解

定义注解，用于定义切点表明加了该注解的就是切面

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CrudLog {
    String value() default "";
}
```

### AOP

关于SpringBoot的AOP可以参看：[一张图搞懂SpringBoot AOP的5个通知的交织顺序](https://blog.csdn.net/weixin_45747080/article/details/114421918)，[Spring的AOP面向切面编程](https://blog.csdn.net/weixin_45747080/article/details/106364788)

**使用方法**

在需要进行日志AOP的直接加上注解：`@CrudLog`

```java
@CrudLog("新增用户")
@PostMapping("/insertUser")
public ResultResponse insertUser(@RequestBody VoUser voUser){
    //对获取到的参数进行处理
    return new ResulteResponse()
        ,setData(true)
        .setMessage('')
        .setCode(2000)
}
```

加上了该注解的方法就能够直接作为切面，从而转交给AOP。

**具体逻辑**

1、定义切点为`@CrudLog`注解：加上该注解的就是切面

2、在正常返回通知中获得如下参数：（注：正常返回通知是方法正常执行没有异常时才会进行）

- 操作名：该切点的注解值
- 操作时间：当前时间（`new Date()`）
- 执行者：从request的header中获得自定义的X-Token，利用JWT解开这个token获得其中的用户姓名
- 执行者IP：从servlet的http处理器中获得request，然后从request中获得IP
- 执行者客户端：从request的header中获得自定义的X-Client

```java
@Component
@Slf4j
@Aspect
public class CrudAspect {
    @Autowired
    private LoggerService loggerService;

    /**
     * 定义切点为：CrudLog这个注释
     */
    @Pointcut("@annotation(com.engineer.userserver.anno.CrudLog)")
    public void crudPointCut() { }//切入点签名


    /**
     * 正常返回通知（方法有正常返回值时）：（一般用这个）
     * 在整个后置通知前，在整个前置通知后，与异常返回并列。
     * 将返回值强转为ResultResponse，获得其中的data，如果data为true则证明操作成功，
     * 再从切点里获得注解名称（也就是操作名），从handler中获得request再从request中获得ip，
     * 再从header中根据X-Client和X-Token获得客户端名称和token，再从token中获得name（executor操作者）
     *
     * @param joinPoint   切点
     * @param returnValue 正常返回值
     */
    @AfterReturning(pointcut = "crudPointCut()", returning = "returnValue")
    public void crudAfterReturning(JoinPoint joinPoint, Object returnValue) {
        ResultResponse resultResponse = (ResultResponse) returnValue; //将返回值强转为ResultResponse
        Boolean data = (Boolean)resultResponse.getData();             //获取ResultResponse里的data
        if (data){ //如果操作成功执行（操作成功会返回true）
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            CrudLog annotation = signature.getMethod().getAnnotation(CrudLog.class);
            String methodName = annotation.value();//将注解上的值赋值为操作名
//            System.out.println("methodName = " + methodName);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();//从解析器里获得request请求
            String ip = request.getRemoteAddr();//从request请求中获得ip地址
            String client = request.getHeader("X-Client");//从headers中取出客户端名称
            String token = request.getHeader("X-Token");//从headers中取出token
            Date time = new Date();//时间
            String executor = "未知";//执行者
            try {
                executor = JWTUtils.getTokenInfo(token).getClaim("name").asString();
            } catch (RuntimeException e){
                executor = "未知";
            }
            insertLogger(methodName,time,executor,ip,client);
        }

    }

    //操作名、操作时间、执行人、ip、来源客户端
    private void insertLogger(String name, Date time, String executor, String ip, String client){
        log.info("用户[{}]从[{}]客户端成功[{}]，时间是[{}]，ip地址是[{}]",
                executor,client,name,time.toLocaleString(),ip);
        Logger logger = new Logger();
        logger.setName(name);
        logger.setTime(time);
        logger.setExecutor(executor);
        logger.setIp(ip);
        logger.setClient(client==null?"未知":client);
        loggerService.insertLogger(logger);
    }
}
```

### 缺点

采用的方法是直接将该条日志记录插入进MySQL的数据库中，这样对数据库访问资源造成了很大的浪费和占用，这样的效率不高，有可以改进的方案，比如放在内存中或者缓存中。

## 拓展功能

自己写的可能跟项目本身没有太大联系的小组件。

### 网络测速

纯前后端分离的，利用Ajax的网络测速小组件。关于该组件的完整前后端可以参看：[speed-test测速组件（基于Vue、elementUI、Springboot）](https://github.com/FanGaoXS/speed-test)

**测速原理**

**下载**

1. 访问位于服务端的静态资源（切记该资源不能太大，因为网速限制，如果太大会造成该次请求超时）
2. 发起访问的时候获得此时的时间戳作为开始时间
3. 请求资源结束的时候获得此时的时间戳作为结束时间
4. 利用该静态资源的大小除以时间差，即可算出单位时间的资源下载量，即可算出下载的网速。

**上传**

1. 利用POST向服务端发起一个请求（POST可以发送大请求，请求不需要太大，也不能太小，因为网速限制）
2. 发起请求的时候记录下该时的时间戳作为开始时间
3. 请求从服务端返回的时间作为结束时间
4. 利用该上传请求的大小除以时间差，即可算出单位时间的资源上传量，即可算出上传的网速。

**代码实现**

**下载**

后端

直接将静态资源利用放置上面所提到的<a href='#resources'>静态资源</a>服务器上即可

前端

```js
download(){
    let image = new Image(); //定义Image对象
    let imageSrc=IMAGE_PREFIX_URL+'speed/test.JPG';
    let imageSize=7984555;
    image.src = imageSrc + '?n=' +Math.random(); //随机访问该图片资源
    let startTime = new Date().getTime(); //开始下载时的时间戳
    image.onload = () => { //图片加载完时会执行的回调函数
        let endTime = new Date().getTime(); //完成下载的时的时间
        /*console.log('startTime',startTime);
          console.log('endTime',endTime);*/
        // console.log('延迟',endTime-startTime,'ms'); //误差为6ms
        let diffSeconds = (endTime - startTime)/1000; //差时间转为秒
        let speedBps = (imageSize/diffSeconds)*8; //每秒下载多少B的资源
        let speedKBps = speedBps / 1024;  //每秒下载多少KB（千B）的资源
        let speedMbps = speedKBps / 1024; //每秒下载多少MB（兆B）的资源
        console.log('['+this.count/10+']'+'下载速率',speedMbps,'Mbps');
        //将该次测速得到的速率追加到速率速组里
        this.speedArray.push(speedMbps);
        // delete image; //下载完成后删除该图片资源
        if (this.count<this.maxCount){//如果没有到达最大次数，则依然执行
            this.startDownload();
        } else {
            this.flag = false;
        }
    };
},
```

**上传**

 后端

```java
@RestController
@CrossOrigin("*")
@RequestMapping("/speed")
@Slf4j
public class SpeedController {
    @PostMapping("/uploadTest")
    public ResultResponse uploadSpeedTest(HttpServletRequest request){
        long contentLength = request.getContentLengthLong();//该请求的contentLength
        log.info("contentLength->[{} Byte]",contentLength);
        log.info("contentLength->[{} Kb]",contentLength/1024);
        log.info("contentLength->[{} Mb]",contentLength/1024/1024);
        Content content = new Content();
        content.setContentLength(contentLength);
        return new ResultResponse()
                .setData(content)
                .setMessage("测试客户端到服务端的上传速率");
    }
}
```

 前端

```js
upload(){
    let startTime = new Date().getTime();
    // console.log('startTime->',startTime);
    let text =`A`;   //一个字母大小为1字节Byte
    let totalText ;
    for (let i = 0; i < 1024 * 1024 * 2; i++) {
        totalText+=text; //post大小为2M的请求
    }
    let formData = new FormData();
    formData.append('text',totalText);
    uploadSpeedTest(formData).then(res=>{
        let endTime = new Date().getTime()
        // let endTime = res.data.endTime;
        let contentLength = res.data.contentLength;
        let diffTime = endTime-startTime;
        let speedBps = (contentLength*8)/(diffTime/1000);
        let speedKbps = speedBps / 1024 ;
        let speedMbps = speedKbps / 1024 ;
        console.log('['+this.count/20+']'+'上行速率',speedMbps,'Mbps（仅供参考）');
        //将该次测速得到的速率追加到速率速组里
        this.speedArray.push(speedMbps);
        if (this.count<this.maxCount){//如果没有到达最大次数，则依然执行
            this.startUpload();
        } else {
            this.flag = false;
        }
    }).catch(err=>{
        this.isError=true;
        this.flag = false;
        console.log(err);
    })
},
```

> **单位换算**
>
> 上传和下载的资源大小单位是Byte字节，以网速换算单位就是需要乘8然后除以单位时间：
>
> bps  		#字节/每秒
> Kbps		#K/每秒 		Kbps = bps/1024
> Mbps		#兆/每秒	   Mbps = Kbps/1024



## <span id='pagination'>分页实现</span>

分页一直以来都是前后端开发中比较有趣且难得一个点。本项目采用的是前后端联合实现的分页查询。

### 逻辑

**后端**持久层在查询列表数据的时候就利用MyBatis定义好分页的SQL预处理语句，查询的时候如果传递再传递当前页和每页多少记录就可以了，为了避免两个都为空而查询所有表耗费大量查询资源，所以默认情况是当前页为1，每页记录数为10。同时利用SQL语句查询总记录数，就能很好地协调前端根据当前页、每页记录数、总记录数获得总共有多少页。[后端手册之分页实现](#back-end-pagination)

**前端**得益于Vue.js的动态绑定的特性，所以每次修改**当前页**和**记录数**就可以重新发起请求然后再次填充页面数据。

前端访问后端的时候只需要得到**当前页、每页记录数、总记录数**就可以了。[前端手册之分页实现](#front-end-pagination)

## 前端手册

前端主要用到的技术栈：Vue.js、vuex、vue-router、axios、element-ui

### 项目结构

具体可以参看：[vue-element-admin的目录结构](https://panjiachen.gitee.io/vue-element-admin-site/zh/guide/#%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84)

```sh
├── build                      # 构建相关
├── mock                       # 项目mock 模拟数据
├── plop-templates             # 基本模板
├── public                     # 静态资源
│   │── favicon.ico            # favicon图标
│   └── index.html             # html模板
├── src                        # 源代码
│   ├── api                    # 所有请求
│   ├── assets                 # 主题 字体等静态资源
│   ├── components             # 全局公用组件
│   ├── directive              # 全局指令
│   ├── filters                # 全局 filter
│   ├── icons                  # 项目所有 svg icons
│   ├── lang                   # 国际化 language
│   ├── layout                 # 全局 layout
│   ├── router                 # 路由
│   ├── store                  # 全局 store管理
│   ├── styles                 # 全局样式
│   ├── utils                  # 全局公用方法
│   ├── vendor                 # 公用vendor
│   ├── views                  # views 所有页面
│   ├── App.vue                # 入口页面
│   ├── main.js                # 入口文件 加载组件 初始化等
│   └── permission.js          # 权限管理
├── tests                      # 测试
├── .env.xxx                   # 环境变量配置
├── .eslintrc.js               # eslint 配置项
├── .babelrc                   # babel-loader 配置
├── .travis.yml                # 自动化CI配置
├── vue.config.js              # vue-cli 配置
├── postcss.config.js          # postcss 配置
└── package.json               # package.json
```



### 网络请求

前端的网络请求主要采用的是axios，然后本项目再将axios进行了二次封装，主要封装了request拦截器和response拦截器，能够对全局的request和response做出响应。关于axios可以参看[Vue.js之axios的使用](https://blog.csdn.net/weixin_45747080/article/details/109817118#t95)

对axios的封装位于`@/utils/request.js`，具体的网络请求位于`@/api/xxx.js`，建议api下最后按照model来分。

**全局配置**

主要配置axios的全局参数，如baseURL和timeout等

```js
//axios实例
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_BASE_URL, // url = base url + request url
  timeout: 5000 // request timeout
})
```

**request拦截器**

request拦截器就是在每一个http请求发出的时候都会对config进行操作，如：在config的headers中添加自定义的客户端标识X-Client以及携带自定义的X-Token

```js
/**
 * request拦截器：在发送每一个请求前都在headers里加上Manage-Token
 */
axiosInstance.interceptors.request.use(
  config => {
    config.headers['X-Client'] = 'manage-web' //在headers中添加设备标识
    if (store.getters.token) { //已经登录成功才会执行该if
      //让每一个request的headers里携带一个token（其中token从本地的cookies中获取）
      config.headers['X-Token'] = getToken()
    }
    // console.log(config)
    return config
  },
  error => {
    console.log('request interceptors error',error) // for debug
    return Promise.reject(error)
  }
)
```

**response拦截器**

由于axios对于http请求再加了一层，所以其实我们从后端接收到的真正数据应该是`response.data`，response拦截器可以在数据返回的时候判断数据中的状态码（自定义异常），然后对自定义异常进行一些相应的判断并且利用element-ui的message进行弹窗提示。

```js
// response拦截器
axiosInstance.interceptors.response.use(
  response => {
    const res = response.data

    if (res.code !== 20000 && res.code!==0 ) {
      Message({
        message: res.message || '错误！请联系管理员',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: 非法的token; 50012: 其他客户端已经登录; 50014: token过期;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // 重新登录
        MessageBox.confirm('你已经退出，你可以选择取消留在此页面或者重新登录', '确认退出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消登录',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || '错误！'))
    } else {
      return res
    }
  },
  error => {
    console.log('response interceptors error->' + error) // for debug
    Message({
      message: '网络连接错误：'+error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)
```

**请求**

`@/utils/user.js`

```js
import request from '@/utils/request'

export function getUser(id) {
  return request({
    url: 'user-server/user/selectUserById',
    method: 'GET',
    params: {
      id
    }
  })
}
```

直接传入对应的url、method和params就可以了。

**使用**

先利用import导入该请求，然后直接利用Promise就可以使用了。

```vue
<template>
  <div>
    <el-button @click="buttonClick"></el-button>
  </div>
</template>

<script>
  import {
    getUser
  } from "@/api/user/user";

  export default {
    name: "index2",
    methods: {
      buttonClick() {
        const id = 10
        getUser(id).then(res=>{
          console.log(res)
        }).catch(error=>{
          console.log(error)
        })
      }
    },
  }
</script>

```

**全局状态管理**

主要是利用Vue.js的一个核心组件vuex实现，存储一些凌驾于页面之上的，存在于整个应用的变量或者方法，比如：用户的编号、用户的姓名、用户的token、用户的一些偏好设置等。关于vuex请参看：[Vue.js之vuex](https://blog.csdn.net/weixin_45747080/article/details/109817118#t74)

```
│  getters.js
│  index.js						#主入口
│
└─modules
        app.js					#应用的相关设置
        permission.js			#权限相关
        settings.js				#偏好
        user.js					#用户信息相关

```

### 工具类

工具方法都位于`@/utils/`下

```sh
    AMap.js						#异步加载高德地图原生JS API
    auth.js						#token相关
    get-page-title.js			#获得页面标题
    global-filters.js			#全局filter
    global-variable.js			#全局常量
    index.js					
    password.js					#密码（md5混淆）
    permission.js				#权限相关
    request.js					#axios封装的网络请求
    validate.js					#相关内容的验证
```

### <span id='front-end-secret'>密码加密</span>

和后端一致的密码混淆规则：

`@/utils/password.js`

```js
import Md5 from 'js-md5'

//正常密码+规则+盐，混淆
export function md5Password(password,randomSalt) {
  return Md5(password + 'dsxssx' + randomSalt)
}
```

根据用户名获得密码盐的网络请求：

`@/api/profile.js`

```js
export function getSalt(username) {
  return request({
    url: '/salt',
    method: 'GET',
    params: {
      username
    }
  })
}
```

在用户登录或者修改密码的时候先根据用户名获得盐，再混淆然后将用户名和密码传递给后端进行比对用户名和密码是否正确：

`@/api/user/auth.js`

```js
export async function login(user) {
  const { data } = await getSalt(user.username.trim()) //根据用户名获得该用户的密码盐
  const salt = data.salt
  return request({
    url: '/login',
    method: 'POST',
    data:{
      username: user.username,
      password: md5Password(user.password,salt), //将密码和盐混淆加密
      validityDay: user.validityDay
    }
  })
}
```

> **切记**
>
> 一定要用==同步==方法先获得密码盐，然后根据MD5混淆规则混淆密码，利用POST传递给后端

### <span id='front-end-pagination'>分页实现</span>

得益于Vue.js的动态绑定以及双向绑定的特性，该项目前端分页使用element-ui的`<el-pagination>`组件，具体可以参看：[element-ui的分页组件](https://element.eleme.cn/#/zh-CN/component/pagination)

我的处理思路是：后端做好根据每页记录数、当前页以及条件查询的接口，像这样：

```json
url: 'user-server/user/selectList',
method: 'GET',
params: {
    roleId,
    currentPage,
    pageSize
}
```

表明`url请求地址`、`method请求方式`、`params请求参数（其中需要包含当前页、每页记录数）`，后端就会返回这样的数据结构：

```json
{
    "code": 20000,
    "message": "查询用户",
    "timestamp": 1541512312312,
    "data": {
        "currentPage": 1,
        "pageSize": 10,
        "totalSize": 20,
        "items": [
            {
                "id": 1,
                "name": "测试1"
            },
            {
               "id": 2,
                "name": "测试2"
            },
            ...
        ]
    }
}
```

在向后端发起请求后直接赋值`currentPage`、`pageSize`、`totalSize`到页面数据上就可以了

```js
//拉取表格数据
async fetchList() {
    const { data:userList } = await getUserList(this.listQuery.roleId,this.listQuery.currentPage,this.listQuery.pageSize)
    this.list = userList.items
    this.listQuery.currentPage = userList.currentPage
    this.listQuery.pageSize = userList.pageSize
    this.listQuery.totalSize = userList.totalSize
},
```

同时在页面的分页组件就直接绑定`currentPage`、`pageSize`、`totalSize`就能得到非常好的分页体验了：

```vue
<el-pagination
        :total="listQuery.totalSize"
        :page-size="listQuery.pageSize"
        :current-page.sync="listQuery.currentPage"
        @current-change="handleListQueryChange"
        layout="total, prev, pager, next, jumper">
</el-pagination>
```

### <span id='upload-image'>图片上传</span>

图片上传使用的是element-ui的上传组件，具体可以参看：[element-ui的上传组件](https://element.eleme.cn/#/zh-CN/component/upload)

```vue
<el-upload
           ref="upload"
           action="/upload"
           show-file-list
           list-type="picture"
           name="image"
           :limit="1"
           :accept="'image/png, image/jpeg'"
           :before-upload="beforeUpload"
           :on-success="successUpload"
           :on-error="errorUpload"
           >
    <el-button size="medium" type="primary">上传<i class="el-icon-upload el-icon--right"></i></el-button>
</el-upload>
resource
```

直接使用`el-upload`组件中的action属性指定上传地址,上传到后端的[静态资源服务器](#resource)，或者各大图床。其中name是后端接收该文件的键名），最后处理上传各个阶段的回调函数就可以了。

## 后端手册

后端主要基于SpringBoot、MyBatis

### 项目结构

项目直接利用SpringBoot Initializr构建

```java
├─main
│  ├─java
│  │  └─com
│  │      └─xxx
│  │          └─userserver
│  │              ├─anno				#注解类
│  │              ├─aop					#aspect切面
│  │              ├─config				#配置类
│  │              ├─controller			#控制器类
│  │              ├─interceptor			#拦截器类
│  │              ├─mapper				#对数据库的增删改查
│  │              ├─pojo				#数据库实体类
│  │              ├─service				#业务接口
│  │              │  └─impl			   #业务接口实现
│  │              ├─utils				#工具类
│  │              └─vo					#视图对象
│  └─resources							 #资源文件夹（会存放application配置文件）
│      └─mybatis						
│          └─mapper						 #MyBatis的XxxMapper.xml
```

`pojo`：主要存放与数据库表字段一一对应的实体类（重点是一一对应）。

`mapper`：存放对数据库的增删改查的接口（基于MyBatis的规范）

`service`：对mapper增删改查出来的数据进行进一步封装和处理。**处理数据**

`controller`：控制网络请求的流程，然后返回处理后的数据。**控制流程**

`aop`：基于spring-aop的实现，本项目主要用于处理日志。

`interceptor`：Web的拦截器，主要处理请求，哪些请求允许通过，哪些请求不允许通过

`config`：SpringBoot的配置，主要是手动启用SpringBoot的相关配置，该项目主要用于启用SpringMVC的Web拦截器。

### <span id='public'>公共组件</span>

依旧是采用SpringBoot Initializr构建。

```sh
└─main
    ├─java
    │  └─com
    │      └─xxx
    │          └─commonserver
    │              ├─mapper
    │              ├─pojo
    │              ├─response
    │              ├─utils
    │              └─vo
    └─resources
        └─mybatis
            └─mapper
```

**其他项目引入**

需要将公共组件的构建方式改为利用maven打包成依赖，并且生成不可执行的jar包，需要在`pom.xml`中将构建方式改为：

`common-server/pom.xml`

```xml
<build>
    <plugins>
        <!--<plugin>&lt;!&ndash;将该项目打包成可执行的jar包，能够放在jre环境中运行&ndash;&gt;
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>-->
        <plugin><!--将该项目打包成不可执行的jar依赖，这样就可以被其他项目所引入-->
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

然后其他项目直接在`pom.xml`中将该项目作为依赖引入：

`user-server/pom.xml`

```xml
<dependencies>

    <dependency><!--公用模块-->
        <groupId>com.xxx</groupId>
        <artifactId>common-server</artifactId>
        <version>1.2.5</version>
    </dependency>

</dependencies>
```

> **注意**
>
> 每次修改了公共组件（common-server）切记都需要利用maven打包成依赖，然后再在其他项目中引入。强烈建议修改了公共组件后修改pom文件里的版本号再打包，然后在引入的时候也修改为打包后的版本！！！

**日志**

由于大多数后端项目中都需要对日志表进行增删改查，所以我就选择将日志实体类和日志DAO放置到公共组件当中。

**BasePojo**

上文的[分页实现](#pagination)中讲过，基本上所有的数据库实体都可能用到分页查询，所以将实现分页的基础pojo放置到公共组件的BasePojo中。由于Pojo继承了BasePojo，那么就可以直接使用set或者get来设置或者得到currentPage和pageSize，从而与该Pojo对象一起传入进Mapper中，然后进行符合条件的分页查询。（所以该BasePojo基本只在查询的时候使用）

**使用方法**

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void selectList() {
        String username = "admin";
        Integer currentPage = 1;
        Integer pageSize = 2;
        User user = new User();
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        List<User> userList = userMapper.selectList(user);
        for (User user1 : userList) {
            System.out.println("user1 = " + user1);
        }
    }
    @Test
    public void selectList2() {
        String username = "admin";
        User user = new User();
        List<User> userList = userMapper.selectList(user);
        for (User user1 : userList) {
            System.out.println("user1 = " + user1);
        }
    }
}
```

只需要在new该对象的时候，传入currentPage和pageSize就能自动进行分页查询了，同时返回查询到的列表。

如果你没有传入currentPage和pageSize，没关系，得益于MyBatis的特性：`<trim>`标签结合`<if>`标签的使用，如果没有传入currentPage和pageSize，那么该`<trim>`标签就意味着不会生效，也就是不会进行分页查询， 而是查询全表，但是我建议你不要这么做！！！可以参看[分页实现](#pagination)来查看`<trim>`标签和`<if>`标签是怎么进行运作的。

**自定义请求返回体**

由于前端向后端发起请求，后端必须使用SpringBoot-web，所以后端制定一个统一的自定义返回体便于管理和扩展，同时也利于前端统一处理。本项目自定义得请求返回体包含**自定义状态码**`code`、**自定义消息体**`message`、**自定义数据对象**`data`、**自定义时间戳**`timestamp`

`src/response/ResultResponse.java`：

```java
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class ResultResponse implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 自定义响应状态码
     */
    private Integer code;

    /**
     * 响应的消息
     */
    private String message;

    /**
     * 响应的具体数据对象
     */
    private Object data;

    /**
     * 响应的时间戳
     */
    private Long timestamp;

    public ResultResponse() {
        //status默认为2000
        this.code = 20000;
        //data默认为null
        this.data = null;
        //timestamp
        this.timestamp = System.currentTimeMillis();
    }

}
```

**使用**

返回的时候直接返回一个ResultResponse对象就可以了，然后使用链式编程的方法`setMessage()`、`setData()`和`setCode()`就可以了。

```java
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/selectUserById")
    public ResultResponse selectUserById(@RequestParam("id")Integer id){
        User dbUser = userService.selectUserById(id);
        return new ResultResponse()
                .setMessage("根据编号查询用户")
                .setData(new VoUser(dbUser));
    }
}
```

那么在前端，你就会接收到类似这样的json

```json
{
    "data": {
        
    },
    "code": 20000,
    "message": "根据编号查询用户",
    "timestamp": 151543131335
}
```

不出意外的，每个请求正常返回都是这样，看起来是不是很整齐，并且使得前端更好处理，只需要处理自定义状态码就可以了。

**公共工具类**

因为所有后端项目几乎都会使用到基于JWT的token，所以本项目将JWT的生成和解码以及验证放置到公共组件。

`src/utils/JWTUtils.java`

```java
public class JWTUtils {

    /*
    *   secret密钥
    * */
    private static final String SECRET = "XXXXXX";

    /**
     *  生成JWT对象（重载，可以指定有效期（单位天））
     * @param payloadMap    有效负载集合
     * @param validityDay   有效时间
     * @return token    JWT令牌
     */
    public static String createToken(Map<String,String> payloadMap,Integer validityDay){
        if (validityDay == null){
            validityDay = 7; //如果用户没有自定义有效期，则指定有效期为7
        }
        //1、创建JWT的builder构造器
        JWTCreator.Builder jwtBuilder = JWT.create();
        //2、遍历payloadMap然后利用withClaim添加到JWT中
        payloadMap.forEach((key,value)->{ //lambda表达式遍历map
            jwtBuilder.withClaim(key,value);
        });
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,validityDay);//单位天
        String token = jwtBuilder
                .withExpiresAt(instance.getTime())  //3、添加JWT失效时间
                .sign(Algorithm.HMAC256(SECRET));   //4、利用算法结合SECRET给JWT签名
        return token;
    }

    /**
     *  获取成功解密后的token对象（然后获取其中的payload）
     * @param token JWT令牌
     * @return decodedJWT   解密后的JWT对象
     */
    public static DecodedJWT getTokenInfo(String token){
        try {
            //1、结合算法生成准备验证token的验证器
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            //2、解密JWT对象
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        } catch (SignatureVerificationException e) { //签名不一致
            throw new RuntimeException("签名不一致");
        } catch (TokenExpiredException e) {  //token已过期
            throw new RuntimeException("token已过期");
        } catch (AlgorithmMismatchException e) { //算法不匹配
            throw new RuntimeException("算法不匹配");
        } catch (InvalidClaimException e) { // payload已失效
            throw new RuntimeException("payload已失效");
        }
    }

}
```

**使用**

在任何地方使用`JWTUtils.creatToken()`来创建token

```java
String token = JWTUtils.createToken(payloadMap, voUserLogin.getValidityDay()); //利用payload和有效期生成token
```

同样的，使用`JWTUtils.getTokenInfo()`来验证token，并且解析

```java
String role = JWTUtils.getTokenInfo(token).getClaim("name").asString();//经过JWT取出role
```

根据`key=name`来取value，同样的存入的时候也需要`key,value`的方式存入。

**公共视图对象**

同样的，因为绝大多数数据实体都需要用到分页查询，然后用到分页查询就离不开返回对象数组以及分页的相关参数。所以我选择将分页需要用到的视图对象放置到公共组件当中，然后塞入自定义请求返回体的data中，就能很好的与前端实现分页功能了。

`src/vo/List.java`

```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoList<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer currentPage;

    private Integer pageSize;

    private Long totalSize;

    private List<T> items;

}
```

利用到了Java的泛型。

**使用**

根据传入的currentPage和pageSize进行查询，返回集合对象，然后将该集合对象利用泛型创建的voList的`setItems()`方法塞进去，同样的，利用`setCurrentPage()`和`setPageSize()`和`setTotalSize()`都塞进去。最后将该voList对象作为自定义请求返回体的data返回。

```java
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/selectList")
    public ResultResponse selectList(@RequestParam(required = false)Integer currentPage,
                                     @RequestParam(required = false)Integer pageSize){
        List<VoUser> voUserList = userService.selectUserList(currentPage, pageSize);
        User user = new User();
        Long totalSize = userService.selectTotalSize(user);
        VoList<VoUser> voList = new VoList<>();
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        voList.setTotalSize(totalSize);
        voList.setItems(voUserList);
        return new ResultResponse()
                .setMessage("查询用户列表")
                .setData(voList);
    }

}
```

### 数据库设计

**用户表（user）**

存储用户的相关信息

| 字段名   | 数据类型 | 是否主键 | 注释         |
| -------- | -------- | -------- | ------------ |
| id       | int      | 是       | 用户自增主键 |
| uuid     | varchar  |          | 用户唯一标识 |
| username | varchar  |          | 用户名       |
| password | varchar  |          | 密码         |
| name     | varchar  |          | 姓名         |
| avatar   | varchar  |          | 头像地址     |
| phone    | varchar  |          | 电话号码     |
| roleId   | int      |          | 所属权限编号 |

其中用户名不能重复，所以`username`有唯一性索引

**密码盐（password_salt）**

存储用户创建时生成的随机密码盐

| 字段名   | 数据类型 | 是否主键 | 注释     |
| -------- | -------- | -------- | -------- |
| id       | int      | 是       | 用户编号 |
| username | varchar  |          | 用户名   |
| value    | int      |          | 随机盐   |

**角色表（role）**

存储该系统的角色（用户组）

| 字段名      | 数据类型 | 是否主键 | 注释         |
| ----------- | -------- | -------- | ------------ |
| id          | int      | 是       | 角色自增主键 |
| name        | varchar  |          | 角色名       |
| chineseName | varchar  |          | 角色中文名   |
| description | varchar  |          | 角色描述     |

**日志表（logger）**

存储操作的日志

| 字段名   | 数据类型 | 是否主键 | 注释             |
| -------- | -------- | -------- | ---------------- |
| id       | int      | 是       | 日志自增主键     |
| name     | varchar  |          | 操作名           |
| time     | datetime |          | 操作发生的时间   |
| executor | varchar  |          | 操作执行者       |
| ip       | varchar  |          | 操作ip地址       |
| client   | varchar  |          | 操作执行的客户端 |

**工程用具表（engineer）**

存储工程用具的相关信息

| 字段名    | 数据类型 | 是否主键 | 注释                       |
| --------- | -------- | -------- | -------------------------- |
| id        | int      | 是       | 自增主键                   |
| uuid      | varchar  |          | 随机唯一标识               |
| type      | varchar  |          | 所属工程用具（机械or车辆） |
| deviceId  | varchar  |          | 设备编号                   |
| driverId  | int      |          | 所属驾驶员编号             |
| inputTime | datetime |          | 录入时间                   |
| modelId   | int      |          | 所属的类型编号             |

**工程用具类型表（model）**

存储工程用具类型的相关信息

| 字段名      | 数据类型 | 是否主键 | 注释                           |
| ----------- | -------- | -------- | ------------------------------ |
| id          | int      | 是       | 自增主键                       |
| uuid        | varchar  |          | 随机唯一标识                   |
| type        | varchar  |          | 所属工程用具类型（车辆或机械） |
| name        | varchar  |          | 类型名                         |
| description | varchar  |          | 类型描述                       |

**驾驶员表（driver）**

存储驾驶员的相关信息

| 字段名 | 数据类型 | 是否主键 | 注释           |
| ------ | -------- | -------- | -------------- |
| id     | int      | 是       | 自增主键       |
| uuid   | varchar  |          | 随机唯一标识   |
| name   | varchar  |          | 驾驶员姓名     |
| phone  | varchar  |          | 驾驶员联系方式 |

### POJO

是跟数据库一一对应的数据库实体，如用户表`src/pojo/user.java`

```java
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BasePojo {
    /**
     * 用户自增主键
     */
    private Integer id;

    /**
     * 用户唯一标识
     */
    private String uuid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 所属角色编号
     */
    private Integer roleId;

    private Role role;

}
```

至于为什么最后会加上`private Role role;`是根据MyBatis的联表查询的规范，请参看：[MyBatis实现多表之间联系](https://blog.csdn.net/weixin_45747080/article/details/105252194)

### DAO

数据访问层是使用的MyBatis，所以我们应该遵循MyBatis的设计规范。

创建`XxxMapper.java`接口

```java
@Mapper
@Repository
public interface UserMapper {

    /**
     * 增加一条记录
     * @param user 用户对象
     * @return 增加成功的记录数
     */
    Integer insertOne(User user);

    /**
     * 删除一条记录
     * @param user 用户对象
     * @return 删除成功的记录数
     */
    Integer deleteOne(User user);

    /**
     * 修改用户
     * @param user 用户对象
     * @return 修改成功的记录数
     */
    Integer updateOne(User user);

    /**
     * 查询所有user集合（可分页，可多条件，可单条件）
     * @param user 用户对象
     * @return user集合
     */
    List<User> selectList(User user);

    /**
     * 查询一条user记录（可多条件，可单条件）
     * @param user
     * @return  一条用户对象
     */
    User selectOne(User user);

    /**
     * 查询记录数（可条件查询）
     * @param user  用户对象
     * @return  记录数
     */
    Long count(User user);

}
```

记得加上`@Mapper`和`@Repository`注解，启用MyBatis和SpringBoot的注解。

然后创建`XxxMapper.xml`文件，进行增删改查

```xml
<mapper namespace="UserMapper">
    
</mapper>
```

### Service

将DAO层增删改查出来的数据进行进一步处理和封装成我们期望的数据。

### Vo

视图对象我认为是很有必要加上的，因为传统查询出来的数据使用Pojo对象接管后会出现一些不希望返回给前端的数据，比如：user表的password字段，就不需要返回给前端，不安全也没有必要。并且在添加了Vo层后，Pojo只要跟数据库字段一一对应后就不需要改动了，只需要创建新的Vo对象来匹配查询出来的Pojo对象就可以了，这样既增加了可复用性也增加了可维护性。

关于Vo对象的设计，`src/vo/VoUser.java`

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String uuid;

    private String username;

    private String name;

    private String avatar;

    private String phone;

    private VoRole role;

    public VoUser(User user) {
        this.id = user.getId();
        this.uuid = user.getUuid();
        this.username = user.getUsername();
        this.name = user.getName();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
        this.role = new VoRole(user.getRole());
    }
}
```

我只需要返回给前端需要的数据就可以了，其余的就可以直接不管，建议直接在Vo对象的构造函数中传入Pojo对象，然后再一一对应就可以了。

**Service层中的List\<Vo对象\>**

我在上面讲过，service层最好是处理数据，controller转发请求，所以我选择在该项目的service层中返回查询的VO对象集合。先是查询到POJO对象的集合，然后遍历该POJO集合，将遍历到的对象依次塞进VO对象集合中去。

```java
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<VoUser> selectUserListCondition(Integer roleId,
                                                Integer currentPage,
                                                Integer pageSize) {
        User user = new User();
        user.setRoleId(roleId);
        user.setCurrentPage(currentPage);
        user.setPageSize(pageSize);
        List<User> userList = userMapper.selectList(user);
        ArrayList<VoUser> voUserList = new ArrayList<>();
        //直接采用VoUser构造方法
        userList.forEach(user1 -> voUserList.add(new VoUser(user1)));
        return voUserList;
    }

    @Override
    public Long selectTotalSize(User user) {
        return userMapper.count(user);
    }

}
```



**POJO——DAO——VO关系**

经过DAO层的查询出来的结果是POJO对象，可能包含我们不期望返回给前端的属性，比如：password。那么在service层我们就可以将POJO对象转为VO对象，最后再经由controller转发给前端的时候就是我们期望返回的数据结构类型了。

![image-20210426180756228](img/image-20210426180756228.png)

### <span id='back-end-secret'>密码加密</span>

需要使用到MD5的工具类`src/utils/Md5Utils.java`，其中有两个方法：1、生成随机盐。2、将规则和原密码和盐利用MD5进行混淆。

```java
public class Md5Utils {

    /**
     * 获得随机盐值
     * @return                  随机盐
     */
    public static Integer randomSalt(){
        return (int)(Math.random()*Integer.MAX_VALUE);
    }

    /**
     * 生成md5：
     *  规则：密码+'dsxssx'+随机盐
     * @param password
     * @param randSalt
     * @return                  经过md5算法后的password
     */
    public static String md5Password(String password,Integer randSalt){
        byte[] passwordByte = (password +"dsxssx"+ randSalt).getBytes();
        return DigestUtils.md5DigestAsHex(passwordByte);
    }
}
```

在新增用户的时候就进行先生成一个随机盐，然后利用随机盐混淆密码后再直接将混淆后的密码进行插入：`src/service/UserService.java`

```java
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SaltMapper saltMapper;

    @Override
    public Boolean insertUser(VoUser voUser) {
        try {
            User user = new User();
            user.setUuid(UUID.randomUUID().toString());
            user.setUsername(voUser.getUsername());
            Integer randomSalt = Md5Utils.randomSalt(); 				//生成随机盐
            String password = voUser.getUsername()+"123";                   //初始密码为：用户名+123
            String md5Password = Md5Utils.md5Password(password,randomSalt); //密码+随机盐生成md5
            user.setPassword(md5Password);
            user.setName(voUser.getName());
            user.setPhone(voUser.getPhone());
            user.setRoleId(voUser.getRole().getId());
            userMapper.insertOne(user);

            Salt salt = new Salt();
            salt.setId(user.getId()); //将成功插入的用户的id作为盐的id存入
            salt.setUsername(voUser.getUsername());
            salt.setValue(randomSalt);
            saltMapper.insertOne(salt);

            return true;
        } catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
        }
        return false;
    }


}
```

其中，用到了Spring的事务，try代码块中如果发生异常，则整个事务发生回滚。具体参看：[事务处理](#transactional)

### <span id='transactional'>事务处理</span>

有的时候会出现同时插入很多表，并且可能需要用到插入到第一次表中的返回数据或者必须保证两个表同时插入成功。但是有的时候可能发生不可抗力或者出现异常，第一个表插入成功了，而第二个表却没有插入成功，这是我们不期望看到的，所以本项目就引入了spring的事务处理。

期望的业务利用try代码块包裹起来

```java
try{
    insert1();
    insert2();
}
```

然后利用catch捕获异常，只要发生异常事务就回滚

```java
catch (Exception e){
    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //事务回滚
}
```

### <span id='back-end-pagination'>分页实现</span>

因为基本上的所有数据库实体都可能需要使用到分页功能，所以考虑到代码复用性和可维护性，我便定义了公共的POJO（[后端的公共组件](#public)），需要用到分页功能的实体对象只需要继承他就可以了。

**Pojo**

User.java：

```java
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User extends BasePojo {
    /**
     * 用户自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

}
```

需要分页的实体对象只需要继承BasePojo就好。

MyBatis预处理语句：

**Mapper**

UserMapper.java:

```java
@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询所有user集合（可分页，可多条件，可单条件）
     * @param user 用户对象
     * @return user集合
     */
    List<User> selectList(User user);

    /**
     * 查询记录数（可条件查询）
     * @param user  用户对象
     * @return  记录数
     */
    Long count(User user);

}

```

UserMapper.xml：

```xml
<mapper namespace="UserMapper">
    <!--分页的条件sql（当前页，每页记录数）-->
    <sql id="limitCondition">
        <if test=" currentPage!=null and currentPage!='' and pageSize!=null and pageSize!='' ">
            <bind name="offset" value="pageSize*(currentPage-1)"/>
            <bind name="rows" value="pageSize"/>
            #{offset},#{rows}
        </if>
    </sql>
    <!--查询所有（可分页，可单条件，可多条件）-->
    <select id="selectList"
            parameterType="User"
            resultMap="userMap">
        SELECT
        	#查询列
        FROM
        	user u
            <trim prefix="WHERE" prefixOverrides="AND">
                #查询条件
        </trim>
        ORDER BY
        	id
        <trim prefix="LIMIT">
            <include refid="limitCondition"></include>
        </trim>
    </select>
    <!--查询记录数（可单条件，可多条件）-->
    <select id="count"
            parameterType="User"
            resultType="long">
        SELECT
        	count(id)
        FROM
        	user
        <trim prefix="WHERE" prefixOverrides="AND">
        	#查询条件
        </trim>
    </select>
</mapper>
```

先利用`<sql>`标签定义好了分页的语句，需要注意的是，MySQL的分页语句是`LIMIT offset,rows`从哪条记录开始，多少条记录，由于我为了方便理解，我就利用`<bind>`标签将offset和rows将我传入的pageSize和currentPage进行换算，就能得到offset和rows：

```
offset = pageSize * (currentPage - 1)  		#起始下标=每页记录数x（当前页-1）
rows = pageSize								#记录数
```

**Service**

在Service中传入分页的条件就可以了

```java
@Service
public interface UserService {

    List<VoUser> selectUserListCondition(Integer roleId,
                                         Integer currentPage,
                                         Integer pageSize);

    /**
     * 查询记录数
     * @param user
     * @return              记录数
     */
    Long selectTotalSize(User user);
}
```

最后经过controller的处理

```java
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/selectList")
    public ResultResponse selectList(@RequestParam(required = false)Integer currentPage,
                                     @RequestParam(required = false)Integer pageSize){
        List<VoUser> voUserList = userService.selectUserList(currentPage, pageSize);
        User user = new User();
        Long totalSize = userService.selectTotalSize(user);
        VoList<VoUser> voList = new VoList<>();
        voList.setCurrentPage(currentPage);
        voList.setPageSize(pageSize);
        voList.setTotalSize(totalSize);
        voList.setItems(voUserList);
        return new ResultResponse()
                .setMessage("查询用户列表")
                .setData(voList);
    }

}
```

返回这样的数据结构：

```json
{
    "code": 20000,
    "message": "查询用户",
    "timestamp": 1541512312312,
    "data": {
        "currentPage": 1,
        "pageSize": 10,
        "totalSize": 20,
        "items": [
            {
                "id": 1,
                "name": "测试1"
            },
            {
               "id": 2,
                "name": "测试2"
            },
            ...
        ]
    }
}
```

## 缺陷（待更新）

### 全局异常处理

异常处理在前后端开发中应该是需要非常重视的一个模块，但是在该项目中并没有很好地预定义一些异常，也没有预定义一些异常状态码

> **解决方案**
>
> 在后端的自定义请求返回体`ResultResponse`中预定义一些自定义异常状态码，然后前端根据返回的data中的code判断自定义异常码，再根据统一定义的状态码的意义返回给用户进行提示。

### 获取操作IP地址

在上述[日志管理](#logger)中有提到，在通过Nginx反向代理服务的时候出现了问题，直接将用户访问的真实IP经过正向代理后然后再经过反向代理到具体日志微服务中的时候直接将Nginx服务器的本机地址代理到服务当中去了。下个版本预计修复。

> **解决方案**
>
> 不用Nginx反向代理，使用Nginx直接转发到服务。

### 权限控制

在上述[权限验证](#auth)中你可以察觉到，其实目前该项目的后端在拦截器中允许通过的权限是写死了的，前端的路由加载也是，在开发过程中就写死了哪些角色能够允许访问路由，实际开发中肯定哪些角色对应能够访问哪些路由应该是可以配置的（可持久化的）。

> **解决方案**
>
> 在后端添加权限表，对应的权限能够访问哪一些页面或者进行哪一些操作都是可以任意增删改。

### 密码加密

在上述[密码加密](#secret)中你可以看到，该项目前端往后端提交密码的方式是前后端利用同样的规则，同样的密码盐然后经过MD5混淆。其实这是完全不安全的，在客户端浏览器存储任何密钥、任何加密规则都是不安全的，即使你利用babel混淆js，黑客利用抓包和反编译是完全可以拿到密钥、密码盐和混淆规则的。所以在前端加密其实没多大意义。

> **解决方案**
>
> 将密码加密放到后端执行，前端只做简单的hash处理