import request from '@/utils/request'

export function uploadSpeedTest(formData) {
  // console.log(formData.get('text'));
  return request({
    url: 'speed-server/speed/uploadTest',
    method: 'POST',
    timeout: 600000,
    data: formData
  });
}
