import {
  localAuthServerRequest,
  blctekAuthServerRequsest
} from "./request";

export function getDevelopList() {
  let config={
    url: '/ldap/developList',
    method: 'GET'
  }
  return localAuthServerRequest(config);
}

export function insertDevelop(formData) {
  let config={
    url: '/ldap/developInsert',
    method: 'POST',
    headers: {
      'Content-Type': 'multipart/form-data'
    },
    data: formData
  };
  return localAuthServerRequest(config);
}

export function deleteDevelop(commonName) {
  let config= {
    url: '/ldap/developDelete',
    method: 'GET',
    params: {
      commonName: commonName
    }
  };
  return localAuthServerRequest(config);
}