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

export function getUserList(roleId,currentPage,pageSize) {
  return request({
    url: 'user-server/user/selectList',
    method: 'GET',
    params: {
      roleId,
      currentPage,
      pageSize
    }
  })
}

export function insertUser(user) {
  return request({
    url: 'user-server/user/insertUser',
    method: 'POST',
    data: {
      username: user.username.trim(),
      name: user.name.trim(),
      phone: user.phone.trim(),
      role: {
        id: user.roleId
      }
    }
  })
}

export function deleteUser(id) {
  return request({
    url: 'user-server/user/deleteUser',
    method: 'GET',
    params: {
      id
    }
  })
}

export function updateUser(user) {
  return request({
    url: 'user-server/user/updateUser',
    method: 'POST',
    data: {
      id: user.id,
      username: user.username.trim(),
      name: user.name.trim(),
      phone: user.phone.trim(),
      role: {
        id: user.roleId
      }
    }
  })
}
