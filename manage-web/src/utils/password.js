import Md5 from 'js-md5'

export function md5Password(password,randomSalt) {
  return Md5(password + '|' + randomSalt)
}



