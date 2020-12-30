import {
  localLdapRequest,
  blctekLdapRequest
} from "./request";

export function getDevelopList() {
  let config={
    url: '/develop/developList',
    method: 'GET'
  }
  return blctekLdapRequest(config);
}

export function insertDevelop(formData) {
  let config={
    url: '/develop/developInsert',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  };
  return blctekLdapRequest(config);
}

export function deleteDevelop(commonName) {
  let config= {
    url: '/develop/developDelete',
    method: 'GET',
    params: {
      commonName: commonName
    }
  };
  return blctekLdapRequest(config);
}