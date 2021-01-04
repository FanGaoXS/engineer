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

export function modifyMachineRow(machineForm) {
  let config={
    url: '/machine/modifyMachine',
    method: 'GET',
    params: {
      machineId: machineForm.machineId,
      machineNumber: machineForm.machineNumber,
      machineModel: machineForm.machineModel,
      driverId: machineForm.driverId,
      driverName: machineForm.driverName,
      driverPhone: machineForm.driverPhone
    }
  };
  return blctekCarRequest(config);
}