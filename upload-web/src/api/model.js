import request from '../utils/request'

export function getVehicleModel() {
  return request({
    url: 'engine-server/model/selectListByVehicle',
    method: 'GET'
  })
}

export function getMachineModel() {
  return request({
    url: 'engine-server/model/selectListByMachine',
    method: 'GET'
  })
}