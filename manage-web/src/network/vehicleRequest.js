import {
  localCarRequest,
  blctekCarRequest
} from "./request";

/**
 *  获取车辆列表
 */
export function getVehicleList(){
  let config={
    url: '/vehicle/allVehicle',
    method: 'GET'
  };
  return blctekCarRequest(config);
}

/**
 * 删除一行车辆记录
 * @param vehicleId
 * @param carId
 * @param driverId
 */
export function removeVehicleRow(vehicleId,carId,driverId) {
  let config={
    url: 'vehicle/removeVehicle',
    method: 'GET',
    params: {
      vehicleId: vehicleId,
      carId: carId,
      driverId: driverId
    }
  }
  return blctekCarRequest(config);
}