/*
正则表达式
 */

export function validatePhone(phone) {
  const pattern = /^(?:(?:\+|00)86)?1[3-9]\d{9}$/
  return pattern.test(phone)
}

export function validateName(name) {
  const pattern = /^(?:[\u4e00-\u9fa5·]{2,16})$/
  return pattern.test(name)
}

export function validateVehicleNumber(vehicleNumber) {
  const pattern = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领][A-HJ-NP-Z][A-HJ-NP-Z0-9]{4,5}[A-HJ-NP-Z0-9挂学警港澳]$/
  return pattern.test(vehicleNumber)
}