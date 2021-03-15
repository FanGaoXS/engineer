import {
  blctekRequest
} from "@/utils/myRequest";

/**
 * 查询车辆的类型
 * @param modelBelong
 */
export function getModelByVehicle() {
  let currentPage;
  let pageSize;
  let config = {
    url: 'engine-server/model/selectListByVehicle',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  }
  return blctekRequest(config);
}

/**
 * 查询车辆的类型
 * @param modelBelong
 */
export function getModelByMachine() {
  let currentPage;
  let pageSize;
  let config = {
    url: 'engine-server/model/selectListByMachine',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  }
  return blctekRequest(config);
}

/**
 * 插入类型
 * @param model
 * @returns {AxiosPromise}
 */
export function insertModel(model) {
  let config = {
    url: 'car/insertModel',
    method: 'GET',
    params: {
      id: model.id,
      modelName: model.modelName,
      modelDescription: model.modelDescription,
      modelBelong: model.modelBelong
    }
  }
  return blctekRequest(config);
}

/**
 * 删除类型（根据id）
 * @param id
 * @returns {AxiosPromise}
 */
export function deleteModel(id) {
  let config = {
    url: 'car/deleteModel',
    method: 'GET',
    params: {
      id:id
    }
  }
  return blctekRequest(config);
}

/**
 * 修改类型
 * @param model
 * @returns {AxiosPromise}
 */
export function updateModel(model) {
  let config = {
    url: '/car/updateModel',
    method: 'GET',
    params: {
      id: model.id,
      modelName: model.modelName,
      modelDescription: model.modelDescription,
      modelBelong: model.modelBelong
    }
  }
  return blctekRequest(config);
}
