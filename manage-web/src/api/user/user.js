import request from '@/utils/request'

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

export function getUser(id) {
  return request({
    url: 'user-server/user/selectUserById',
    method: 'GET',
    params: {
      id
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
