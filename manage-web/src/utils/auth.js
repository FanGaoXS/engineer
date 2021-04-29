import Cookies from 'js-cookie'

const TokenKey = 'vue_token'

//从cookies里获得key为'vue_admin_template_token'的token
export function getToken() {
  return Cookies.get(TokenKey)
}

//将key为vue_admin_template_token的token放入cookies中（设置cookies过期时间（number类型））
export function setToken(token,expires=1) {
  return Cookies.set(TokenKey, token, { expires })
}

//将cookies中key为vue_admin_template_token的token移除
export function removeToken() {
  return Cookies.remove(TokenKey)
}
