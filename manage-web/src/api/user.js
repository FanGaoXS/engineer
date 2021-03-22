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

export function getInfo(token) {
  return request({
    url: 'user-server/auth/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: 'user-server/auth/logout',
    method: 'post'
  })
}

export function getUserList(currentPage,pageSize) {
  return request({
    url: 'user-server/user/userList',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  })
}
