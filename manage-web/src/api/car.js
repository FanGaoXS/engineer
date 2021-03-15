import request from '@/utils/request'

/**
 *  修改车辆信息
 * @returns {AxiosPromise}
 */
export function updateVehicle(row) {
  console.log('updateVehicle------>row',row);
  console.log(row.id)
  console.log(row.plateType)
  return request({
    url: 'engine-server/engineer/updateVehicle',
    method: 'POST',
    data: {
      id:row.id,
      plateType: row.plateType
    }
  });
}

/**
 * 删除车辆信息
 * @returns {AxiosPromise}
 */
export function deleteVehicle(row) {
  /*console.log('deleteVehicle------>')
  console.log('vehicleId',row.vehicleId);
  console.log('carId',row.car.carId);
  console.log('driverId',row.driver.driverId);*/
  let config = {
    url: '/car/removeVehicle',
    method: 'GET',
    params: {
      vehicleId: row.vehicleId,
      carId: row.car.carId,
      driverId: row.driver.driverId,
    }
  }
  return request(config);
}

/**
 *  获取车辆列表
 * @returns {AxiosPromise}
 */
export function getVehicleList() {
  const type = "车辆";
  let config = {
    url: 'engine-server/engineer/selectListByType',
    method: 'GET',
    params:{
      type
    }
  }
  return request(config);
}

/**
 * 根据车牌号获取工时（有哪些日期）
 * @param vehicleNumber
 * @returns {AxiosPromise}
 */
export function getWorkListByVehicleNumber(vehicleNumber) {
  // console.log(vehicleNumber);
  let config = {
    url: 'polyline-server/'+vehicleNumber,
    method: 'GET'
  }
  return request(config);
}

/**
 * 根据车牌号和日期获得坐标点集合
 * @param vehicleNumber
 * @param Date
 * @returns {AxiosPromise}
 */
export async function getPointListByVehicleNumberAndDate(vehicleNumber,date) {
  /*console.log(vehicleNumber);
  console.log(date);*/
  let config = {
    url: 'polyline-server/'+vehicleNumber+'/'+date,
    method: 'GET',
  }
  return request(config);
}

