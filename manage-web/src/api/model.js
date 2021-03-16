import request from "@/utils/request";

export function getModelByVehicle(currentPage,pageSize) {
  return request({
    url: 'engine-server/model/selectListByVehicle',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  });
}

export function getModelByMachine(currentPage,pageSize) {
  return request({
    url: 'engine-server/model/selectListByMachine',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  });
}

export function insertModel(model) {
  return request({
    url: 'engine-server/model/insertModel',
    method: 'POST',
    data: {
      name: model.name,
      type: model.type,
      description: model.description
    }
  });
}

export function deleteModel(id) {
  return request({
    url: 'engine-server/model/deleteModel',
    method: 'GET',
    params: {
      id:id
    }
  });
}

export function updateModel(model) {
  return request({
    url: 'engine-server/model/updateModel',
    method: 'POST',
    data: {
      id:model.id,
      name: model.name,
      type: model.type,
      description: model.description
    }
  });
}
