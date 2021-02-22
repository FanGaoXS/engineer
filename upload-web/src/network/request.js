// 引入axios
import Axios from "axios";

/**
 * 本地服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function localServer(config) {
  //基础配置
  let axiosInstance = Axios.create({
    baseURL: '//localhost:8090',
    timeout: 5000
  })
  //request拦截器
  axiosInstance.interceptors.request.use(config=>{
    // console.log(config)
    return config
  },error => {
    console.log('axios的request拦截器错误->',error)
    throw error
  })
  //response拦截器
  axiosInstance.interceptors.response.use(res=>{
    // console.log(res)
    return res.data
  },error => {
    console.log('axios的response拦截器错误->',error)
    throw error
  })
  return axiosInstance(config)
}

/**
 * 远程服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function blctekServer(config) {
  //基础配置
  let axiosInstance = Axios.create({
    baseURL: '//upload.wqkd.blctek.com',
    timeout: 5000
  })
  //request拦截器
  axiosInstance.interceptors.request.use(config=>{
    // console.log(config)
    return config
  },error => {
    console.log('axios的request拦截器错误->',error)
    throw error
  })
  //response拦截器
  axiosInstance.interceptors.response.use(res=>{
    // console.log(res)
    return res.data
  },error => {
    console.log('axios的response拦截器错误->',error)
    throw error
  })
  return axiosInstance(config)
}

/**
 * 生产服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function prodServer(config) {
  //基础配置
  let axiosInstance = Axios.create({
    baseURL: '',
    timeout: 5000
  })
  //request拦截器
  axiosInstance.interceptors.request.use(config=>{
    // console.log(config)
    return config
  },error => {
    console.log('axios的request拦截器错误->',error)
    throw error
  })
  //response拦截器
  axiosInstance.interceptors.response.use(res=>{
    // console.log(res)
    return res.data
  },error => {
    console.log('axios的response拦截器错误->',error)
    throw error
  })
  return axiosInstance(config)
}

