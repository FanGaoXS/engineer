import {
  blctekRequest
} from "@/utils/myRequest";

/**
 * 根据所属工程用具类型获取对应的类型（车辆的或者机械的）
 * @param modelBelong
 */
export function getModelByBelong(modelBelong) {
  let config = {
    url: '/car/modelListByBelong',
    method: 'GET',
    params: {
      modelBelong: modelBelong
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
    url: '/car/insertModel',
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
    url: '/car/deleteModel',
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
