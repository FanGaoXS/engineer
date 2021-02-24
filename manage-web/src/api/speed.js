import {
  blctekRequest
} from "@/utils/myRequest";

export function uploadSpeedTest(formData) {
  // console.log(formData.get('text'));
  let config = {
    url: '/car/uploadSpeedTest',
    method: 'POST',
    timeout: 600000,
    data: formData
  }
  return blctekRequest(config);
}
