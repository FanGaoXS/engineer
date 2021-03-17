/**
 * Created by PanJiaChen on 16/11/18.
 */

/**
 * @param {string} path
 * @returns {Boolean}
 */
export function isExternal(path) {
  return /^(https?:|mailto:|tel:)/.test(path)
}

/**
 * 用户名规则（5到16位（字母，数字，下划线，减号））
 * @param str 用户名
 * @returns {Boolean}
 */
export function validUsername(str) {
  const pattern = /^[a-zA-Z0-9_-]{5,16}$/;
  // console.log('validUsername->',uPattern.test(str));
  return pattern.test(str) //符合返回true，否则返回false
}

/**
 * 密码规则（最少5位）
 * @param str
 * @returns {boolean}
 */
export function validPassword(str) {
  const pattern = /^.*(?=.{5,}).*$/;
  // console.log('validPassword->',pPattern.test(str));
  return pattern.test(str)
}

