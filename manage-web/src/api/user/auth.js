import request from '@/utils/request'
import { getSalt } from "@/api/user/profile";
import { md5Password } from "@/utils/password";

export async function login(user) {
  const { data } = await getSalt(user.username.trim()) //根据用户名获得该用户的密码盐
  const salt = data.salt
  return request({
    url: 'user-server/auth/login',
    method: 'POST',
    data:{
      username: user.username,
      password: md5Password(user.password,salt), //将密码和盐混淆加密
      validityDay: user.validityDay
    }
  })
}

export function logout() {
  return request({
    url: 'user-server/auth/logout',
    method: 'post'
  })
}
