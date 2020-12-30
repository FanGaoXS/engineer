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