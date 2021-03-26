import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'user-server/auth/login',
    method: 'POST',
    data:{
      username: data.username,
      password: data.password,
      validityDay: data.validityDay
    }
  })
}

export function logout() {
  return request({
    url: 'user-server/auth/logout',
    method: 'post'
  })
}
