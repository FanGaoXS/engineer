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