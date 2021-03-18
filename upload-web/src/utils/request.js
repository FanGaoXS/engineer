import axios from 'axios'
import { Notification } from 'element-ui'

//创建axios实例，配置baseURL和timeout
const axiosInstance = axios.create({
  baseURL: '/',
  timeout: 1000*5
})

//request拦截器
axiosInstance.interceptors.request.use(
    config => {//request请求发出前的操作
      //通常做一些对request发出前的处理
      config.headers['X-Client'] = 'upload-web' //添加自定义请求头便于后端识别来自哪一个客户端
      return config;
    },
    error => {//request请求发出错误时的操作
      console.log('request拦截器错误->',error)
      throw error
    }
)

//response拦截器
axiosInstance.interceptors.response.use(
    response => {//response响应成功时的操作
      //通常做一些全局的code判断
      const res = response.data;
      // console.log('request.js 28->',res.data)
      /*if (res.data === false){//失败
        Notification.error({
          title: '失败',
          message: res.message + '失败'
        })
      } else {//成功
        Notification.success({
          title: '成功',
          message: res.message + '成功'
        })
        return res
      }*/
      return res;
    },
    error => {//response响应失败时的操作
      console.log('response拦截器错误->',error);
      throw error;
    }
)

export default axiosInstance