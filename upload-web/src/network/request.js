// 引入axios
import Axios from "axios";

// 本地request
export function localRequest(config) {
  // 创建axios实例，配置baseURL
  let axiosInstance = Axios.create({
    baseURL: 'http://localhost:8092/',
    timeout: 5000
  });
  // response拦截器，过滤data
  axiosInstance.interceptors.response.use(res => {
    console.log('interceptors.response.onFulfilled->',res);
    return res.data;
  },error => {
    console.log('interceptors.response.onRejected->',error);
    // 抛出异常
    throw error;
  });
  return axiosInstance(config);
}

// 远程wqk服务器
export function wqkRequest(config) {
  // 创建axios实例，配置baseURL
  let axiosInstance = Axios.create({
    baseURL: '//upload-server.yueke.cloud/',
    timeout: 5000
  });
  // response拦截器，过滤data
  axiosInstance.interceptors.response.use(res => {
    console.log('interceptors.response.onFulfilled->',res);
    return res.data;
  },error => {
    console.log('interceptors.response.onRejected->',error);
    // 抛出异常
    throw error;
  });
  return axiosInstance(config);
}

// 远程blctek服务器
export function blctekRequest(config) {
  // 创建axios实例，配置baseURL
  let axiosInstance = Axios.create({
    baseURL: '//car-server.wqkd.blctek.com/',
    timeout: 5000
  });
  // response拦截器，过滤data
  axiosInstance.interceptors.response.use(res => {
    console.log('interceptors.response.onFulfilled->',res);
    return res.data;
  },error => {
    console.log('interceptors.response.onRejected->',error);
    // 抛出异常
    throw error;
  });
  return axiosInstance(config);
}
