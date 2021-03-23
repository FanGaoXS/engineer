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

/**
 * 是否全英文
 * @param str
 * @returns {boolean}
 */
export function validIsEnglish(str) {
  const pattern = /^[a-zA-Z]+$/;
  return pattern.test(str)
}

export function validIsChinese(str) {
  const pattern = /^(?:[\u3400-\u4DB5\u4E00-\u9FEA\uFA0E\uFA0F\uFA11\uFA13\uFA14\uFA1F\uFA21\uFA23\uFA24\uFA27-\uFA29]|[\uD840-\uD868\uD86A-\uD86C\uD86F-\uD872\uD874-\uD879][\uDC00-\uDFFF]|\uD869[\uDC00-\uDED6\uDF00-\uDFFF]|\uD86D[\uDC00-\uDF34\uDF40-\uDFFF]|\uD86E[\uDC00-\uDC1D\uDC20-\uDFFF]|\uD873[\uDC00-\uDEA1\uDEB0-\uDFFF]|\uD87A[\uDC00-\uDFE0])+$/;
  return pattern.test(str)
}

export function validatePhone(phone) {
  const pattern = /^(?:(?:\+|00)86)?1[3-9]\d{9}$/
  return pattern.test(phone)
}

export function validateName(name) {
  const pattern = /^(?:[\u4e00-\u9fa5·]{2,16})$/
  return pattern.test(name)
}

