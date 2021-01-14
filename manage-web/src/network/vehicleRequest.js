import {
  localAuthServerRequest,
  blctekAuthServerRequsest
} from "./request";

/**
 *  获取车辆列表
 */
export function getVehicleList(){
  let config={
    url: '/car/allVehicle',
    method: 'GET'
  };
  return localAuthServerRequest(config);
}

/**
 * 删除一行车辆记录
 * @param vehicleId
 * @param carId
 * @param driverId
 */
export function removeVehicleRow(vehicleId,carId,driverId) {
  let config={
    url: '/car/removeVehicle',
    method: 'GET',
    params: {
      vehicleId: vehicleId,
      carId: carId,
      driverId: driverId
    }
  }
  return localAuthServerRequest(config);
}

/**
 * 修改车辆信息
 * @param vehicleForm
 * @returns {AxiosPromise}
 */
export function modifyVehicleRow(vehicleForm) {
  let config={
    url: '/car/modifyVehicle',
    method: 'GET',
    params: {
      vehicleId: vehicleForm.vehicleId,
      plateNumber: vehicleForm.plateNumber,
      plateType: vehicleForm.plateType,
      vehicleModel: vehicleForm.vehicleModel,
      carId: vehicleForm.carId,
      driverId: vehicleForm.driverId,
      driverName: vehicleForm.driverName,
      driverPhone: vehicleForm.driverPhone
    }
  };
  return localAuthServerRequest(config);
}