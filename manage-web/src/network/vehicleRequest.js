import {
  localCarRequest
} from "./request";

/**
 *  获取车辆列表（本地）
 */
export function getVehicleList(){
  let config={
    url: '/vehicle/allVehicle',
    method: 'GET'
  };
  return localCarRequest(config);
}