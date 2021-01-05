import {
  lanPolylineRequest
} from "./request";

export function getPolylineArray(plateNumber,time) {
  let config = {
    url: 'v0/loc/'+plateNumber+'/'+time,
  };
  return lanPolylineRequest(config);
}