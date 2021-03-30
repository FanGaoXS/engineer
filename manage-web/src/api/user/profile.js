import request from '@/utils/request'
import {md5Password} from "@/utils/password";

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

export function getSalt(username) {
  return request({
    url: 'user-server/profile/salt',
    method: 'GET',
    params: {
      username
    }
  })
}

export async function updatePassword(user) {
  const { data } = await getSalt(user.username.trim())
  const salt = data.salt
  return request({
    url: 'user-server/profile/updatePassword',
    method: 'POST',
    data:{
      id: user.id,
      oldPassword: md5Password(user.oldPassword,salt),
      newPassword: md5Password(user.newPassword,salt)
    }
  })
}
