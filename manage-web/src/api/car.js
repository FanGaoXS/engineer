import {
  localRequest,
  blctekRequest
} from "@/utils/myRequest";

/**
 *  修改车辆信息
 * @returns {AxiosPromise}
 */
export function updateVehicle(row) {
  /*console.log('updateVehicle------>')
  console.log('row',row);*/
  let config = {
    url: '/car/modifyVehicle',
    type: 'GET',
    params: {
      vehicleId: row.vehicleId,
      plateNumber: row.plateNumber,
      plateType: row.plateType,
      vehicleModel: row.vehicleModel,
      carId: row.car.carId,
      chipId: row.car.chipId,
      driverId: row.driver.driverId,
      driverName: row.driver.driverName,
      driverPhone: row.driver.driverPhone
    }
  }
  return blctekRequest(config);
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
    type: 'GET',
    params: {
      vehicleId: row.vehicleId,
      carId: row.car.carId,
      driverId: row.driver.driverId,
    }
  }
  return blctekRequest(config);
}

/**
 *  获取车辆列表
 * @returns {AxiosPromise}
 */
export function getVehicleList() {
  let config = {
    url: '/car/allVehicle',
    type: 'GET'
  }
  return blctekRequest(config);
}

/**
 * 根据车牌号获取工时（有哪些日期）
 * @param plateNumber
 * @returns {AxiosPromise}
 */
export function getWorkListByPlateNumber(plateNumber) {
  // console.log(plateNumber);
  let config = {
    url: '/v0/loc/'+plateNumber,
    type: 'GET'
  }
  return blctekRequest(config);
}

/**
 * 根据车牌号和日期获得坐标点集合
 * @param plateNumber
 * @param Date
 * @returns {AxiosPromise}
 */
export async function getPointListByPlateNumberAndDate(plateNumber,date) {
  /*console.log(plateNumber);
  console.log(date);*/
  let config = {
    url: '/v0/loc/'+plateNumber+'/'+date,
    type: 'GET',
  }
  return blctekRequest(config);
}

