import axios from "axios";

/**
 *  本地ldap用户数据服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function localLdapRequest(config) {
  let axiosInstance = axios.create({
    baseURL: 'http://localhost:8091/',
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
 *  本地车辆数据服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function localCarRequest(config) {
  let axiosInstance = axios.create({
    baseURL: 'http://localhost:8092/',
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
 *  公司ldap用户数据服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function blctekLdapRequest(config) {
  let axiosInstance = axios.create({
    baseURL: '//ldap-server.wqkd.blctek.com/',
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
 *  公司车辆数据服务器
 * @param config
 * @returns {AxiosPromise}
 */
export function blctekCarRequest(config) {
  let axiosInstance = axios.create({
    baseURL: '//car-server.wqkd.blctek.com/',
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
export function localAuthServerRequsest(config) {
  let axiosInstance = axios.create({
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