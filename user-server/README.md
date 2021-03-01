用户的登录退出注册等操作

## 1、用户登录login

- request payload：

  ```js
  {
      username: "admin",
      password: "111111"
  }
  ```

- response：

  ```json
  {
      "code":20000,
      "data":{
          "token":"admin-token"
      }
  }
  ```

  

## 2、用户信息info

- request query：

  ```js
  token: admin-token
  ```

- request header：

  ```js
  X-Token: admin-token
  ```

- response：

  ```json
  {
      "code":20000,
      "data":{
          "roles":["admin"],
          "introduction":"I am a super administrator",
          "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",
          "name":"Super Admin"
      }
  }
  ```

## 3、用户退出logout