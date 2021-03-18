import request from '../utils/request'

export function insertVehicle(data) {
  // console.log(data)
  return request({
    url: 'engine-server/engineer/insertVehicle',
    method: 'POST',
    data: {
      uuid: data.uuid.trim(),
      deviceId: data.deviceId.trim(),
      vehicleNumber: data.vehicleNumber.trim(),
      plateType: data.plateType.trim(),
      driver: {
        name: data.driverName.trim(),
        phone: data.driverPhone.trim()
      },
      model: {
        id: data.modelId
      },
    }
  })
}

export function insertMachine(data) {
  return request({
    url: 'engine-server/engineer/insertMachine',
    method: 'POST',
    data: {
      uuid: data.uuid.trim(),
      deviceId: data.deviceId.trim(),
      machineNumber: data.machineNumber.trim(),
      engineNumber: data.engineNumber.trim(),
      driver: {
        name: data.driverName.trim(),
        phone: data.driverPhone.trim()
      },
      model: {
        id: data.modelId
      },
    }
  })
}