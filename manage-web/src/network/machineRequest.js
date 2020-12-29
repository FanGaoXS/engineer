import {
  localCarRequest
} from "./request";

/**
 *  获取机械列表（本地）
 */
export function getMachineList(){
  let config={
    url: '/machine/allMachine',
    method: 'GET'
  };
  return localCarRequest(config);
}