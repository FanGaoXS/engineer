import {
  localAuthServerRequest,
  blctekAuthServerRequsest
} from "./request";

/**
 *  获取机械列表
 */
export function getMachineList(){
  let config={
    url: '/car/allMachine',
    method: 'GET'
  };
  return blctekAuthServerRequsest(config);
}

/**
 * 删除一行机械记录
 * @param machineId
 * @param carId
 * @param driverId
 */
export function removeMachineRow(machineId,carId,driverId) {
  let config={
    url: '/car/removeMachine',
    method: 'GET',
    params: {
      machineId: machineId,
      carId: carId,
      driverId: driverId
    }
  };
  return blctekAuthServerRequsest(config);
}

export function modifyMachineRow(machineForm) {
  let config={
    url: '/car/modifyMachine',
    method: 'GET',
    params: {
      machineId: machineForm.machineId,
      machineNumber: machineForm.machineNumber,
      machineModel: machineForm.machineModel,
      carId: machineForm.carId,
      driverId: machineForm.driverId,
      driverName: machineForm.driverName,
      driverPhone: machineForm.driverPhone
    }
  };
  return blctekAuthServerRequsest(config);
}