import request from '@/utils/request'

export function insertRole(role) {
  return request({
    url: 'user-server/role/insertRole',
    method: 'POST',
    data: {
      name: role.name.trim(),
      chineseName: role.chineseName.trim(),
      description: role.description.trim()
    }
  })
}

export function deleteRole(id) {
  return request({
    url: 'user-server/role/deleteRole',
    method: 'GET',
    params: {
      id
    }
  })
}

export function updateRole(role) {
  return request({
    url: 'user-server/role/updateRole',
    method: 'POST',
    data: {
      id: role.id,
      name: role.name.trim(),
      chineseName: role.chineseName.trim(),
      description: role.description.trim()
    }
  })
}

export function getRoleList(currentPage=1,pageSize=10) {
  return request({
    url: 'user-server/role/selectList',
    method: 'GET',
    params: {
      currentPage,
      pageSize
    }
  })
}
