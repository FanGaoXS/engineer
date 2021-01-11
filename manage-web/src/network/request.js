import axios from "axios";

/**
 *  局域网获取地图polyline
 * @param config
 * @returns {AxiosPromise}
 */
export function lanPolylineRequest(config) {
  let axiosInstance = axios.create({
    baseURL: '//172.16.0.95:8880/',
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
 * 本地鉴权服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function localAuthServerRequest(config) {
  let axiosInstance = axios.create({
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
export function blctekAuthServerRequsest(config) {
  let axiosInstance = axios.create({
    baseURL: '//auth-server.wqkd.blctek.com/',
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