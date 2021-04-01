import request from '@/utils/request'

/**
 * 删除车辆信息
 * @returns {AxiosPromise}
 */
export function deleteVehicle(row) {
  /*console.log('deleteVehicle------>row',row)*/
  return request({
    url: 'engine-server/engineer/deleteVehicle',
    method: 'GET',
    params: {
      id: row.id
    }
  });
}

/**
 *  修改车辆信息
 * @returns {AxiosPromise}
 */
export function updateVehicle(row) {
  // console.log('updateVehicle------>row',row);
  return request({
    url: 'engine-server/engineer/updateVehicle',
    method: 'POST',
    data: {
      id: row.id,
      vehicleNumber: row.vehicleNumber,
      plateType: row.plateType,
      driver:{
        id: row.driver.id,
        name: row.driver.name,
        phone: row.driver.phone
      },
      model:{
        id: row.model.id
      }
    }
  });
}

/**
 *  获取车辆列表
 * @returns {AxiosPromise}
 */
export function getVehicleList(currentPage,pageSize) {
  const type = "车辆";
  return request({
    url: 'engine-server/engineer/selectListByType',
    method: 'GET',
    params:{
      type,
      currentPage,
      pageSize
    }
  });
}

/**
 * 根据车牌号获取工时（有哪些日期）
 * @param vehicleNumber
 * @returns {AxiosPromise}
 */
export function getWorkListByVehicleNumber(vehicleNumber) {
  // console.log(vehicleNumber);
  return request({
    url: 'polyline-server/'+vehicleNumber,
    method: 'GET'
  });
}

/**
 * 根据车牌号和日期获得坐标点集合
 * @param vehicleNumber
 * @param Date
 * @returns {AxiosPromise}
 */
export async function getPointListByVehicleNumberAndDate(vehicleNumber,date) {
  /*console.log(vehicleNumber);
  console.log(date);*/
  return request({
    url: 'polyline-server/'+vehicleNumber+'/'+date,
    method: 'GET',
  });
}

