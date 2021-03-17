
/**
 * 里程数过滤器（将米->公里并且保留3位有效数字）
 * @param mileage
 * @returns {string}
 */
export function mileageFilter(mileage) {
  return (mileage/1000).toFixed(3)+ '公里';
}

/**
 * 车牌号过滤器，中间加个点'·'
 * @param plateNumber
 * @returns {string}
 */
export function plateNumberFilter(plateNumber) {
  /*console.log(plateNumber);
  console.log(typeof plateNumber);*/
  if (plateNumber!==undefined) { //如果车牌号字符串不为空才执行
    let str1 = plateNumber.slice(0,2);
    let str2 = plateNumber.slice(2);
    return str1 + '·' + str2 ;
  }
}

