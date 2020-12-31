import {
  localCarRequest,
  blctekCarRequest
} from "./request";

/**
 *  获取机械列表
 */
export function getMachineList(){
  let config={
    url: '/machine/allMachine',
    method: 'GET'
  };
  return blctekCarRequest(config);
}

/**
 * 删除一行机械记录
 * @param machineId
 * @param carId
 * @param driverId
 */
export function removeMachineRow(machineId,carId,driverId) {
  let config={
    url: '/machine/removeMachine',
    method: 'GET',
    params: {
      machineId: machineId,
      carId: carId,
      driverId: driverId
    }
  };
  return blctekCarRequest(config);
}