import Cookies from 'js-cookie'

const TokenKey = 'vue_admin_template_token'

//从cookies里获得key为'vue_admin_template_token'的token
export function getToken() {
  return Cookies.get(TokenKey)
}

//将key为vue_admin_template_token的token放入cookies中
export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

//将cookies中key为vue_admin_template_token的token移除
export function removeToken() {
  return Cookies.remove(TokenKey)
}
