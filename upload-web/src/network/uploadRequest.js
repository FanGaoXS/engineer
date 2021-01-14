import {
  localAuthServerRequest,
  blctekAuthServerRequest,
  localImageServerRequest
} from "./request";

// 上传车辆信息
export function uploadVehicle(formData) {
  console.log('准备上传的图片文件->',formData.get('file'));
  console.log('准备上传的芯片编号->',formData.get('chipId'));
  console.log('准备上传的车牌类型->',formData.get('plateType'));
  console.log('准备上传的车牌号->',formData.get('plateNumber'));
  console.log('准备上传的车辆类型->',formData.get('vehicleModel'));
  console.log('准备上传的驾驶员姓名->',formData.get('driverName'));
  console.log('准备上传的驾驶员手机号码->',formData.get('driverPhone'));
  let config={
    url: '/car/addVehicle',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data:formData
  }
  return localAuthServerRequest(config);
}

// 上传机械
export function uploadMachine(formData) {
  console.log('准备上传的图片文件->',formData.get('file'));
  console.log('准备上传的芯片编号->',formData.get('chipId'));
  console.log('准备上传的机械类型->',formData.get('machineModel'));
  console.log('准备上传的机械型号->',formData.get('machineNumber'));
  console.log('准备上传的机械的发动机编号->',formData.get('engineNumber'));
  console.log('准备上传的驾驶员姓名->',formData.get('driverName'));
  console.log('准备上传的驾驶员手机号码->',formData.get('driverPhone'));
  let config={
    url: '/car/addMachine',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data:formData
  }
  return localAuthServerRequest(config);
}

//上传图片
export function uploadImage(formData) {
  console.log('准备上传的图片->',formData.get('file'));
  let uploadFormData = new FormData();
  uploadFormData.append('file',formData.get('file'));
  let config= {
    url: 'img/imgInsert',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: uploadFormData
  }
  return localImageServerRequest(config);
}