import request from '../utils/request'

export function uploadImage(data) {
  // console.log(data)
  return request({
    url: 'assets-server/img/insertEngineer',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: data
  })
}