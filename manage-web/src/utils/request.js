import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store' //存储在vuex里的token
import { getToken } from '@/utils/auth' //存储在cookies里的token

//axios实例
const axiosInstance = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 5000 // request timeout
})

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

// response拦截器
axiosInstance.interceptors.response.use(
  response => {
    const res = response.data

    if (res.code !== 20000 && res.code!==0 ) {
      Message({
        message: res.message || '错误！',
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
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default axiosInstance
