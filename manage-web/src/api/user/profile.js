import request from '@/utils/request'

export function getInfo(token) {
  return request({
    url: 'user-server/profile/info',
    method: 'get',
    params: { token }
  })
}

export function updateInfo(user) {
  return request({
    url: 'user-server/profile/updateInfo',
    method: 'POST',
    data:{
      id: user.id,
      name: user.name.trim(),
      phone: user.phone.trim()
    }
  })
}

export function updatePassword(user) {
  return request({
    url: 'user-server/profile/updatePassword',
    method: 'POST',
    data:{
      id: user.id,
      oldPassword: user.oldPassword.trim(),
      newPassword: user.newPassword.trim()
    }
  })
}
