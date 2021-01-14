// 引入axios
import Axios from "axios";

/**
 * 本地鉴权服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function localAuthServerRequest(config) {
  let axiosInstance = Axios.create({
    baseURL: '//localhost:8090/',
    timeout: 5000
  });
  axiosInstance.interceptors.request.use(config=>{
    return config;
  },error => {
    throw error;
  });
  axiosInstance.interceptors.response.use(res=>{
    return res.data;
  },error => {
    throw error;
  });
  return axiosInstance(config);
}

/**
 * 远程鉴权服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function blctekAuthServerRequest(config) {
  let axiosInstance = Axios.create({
    // baseURL: '//auth-server.wqkd.blctek.com/',
    timeout: 5000
  });
  axiosInstance.interceptors.request.use(config=>{
    return config;
  },error => {
    throw error;
  });
  axiosInstance.interceptors.response.use(res=>{
    return res.data;
  },error => {
    throw error;
  });
  return axiosInstance(config);
}


export function localImageServerRequest(config) {
  let axiosInstance = Axios.create({
    baseURL: '//localhost:8093/',
    timeout: 5000
  });
  axiosInstance.interceptors.request.use(config=>{
    return config;
  },error => {
    throw error;
  });
  axiosInstance.interceptors.response.use(res=>{
    return res.data;
  },error => {
    throw error;
  });
  return axiosInstance(config);
}

export function blctekImageServerRequest(config) {
  let axiosInstance = Axios.create({
    // baseURL: '//upload.wqkd.blctek.com/',
    timeout: 5000
  });
  axiosInstance.interceptors.request.use(config=>{
    return config;
  },error => {
    throw error;
  });
  axiosInstance.interceptors.response.use(res=>{
    return res.data;
  },error => {
    throw error;
  });
  return axiosInstance(config);
}
