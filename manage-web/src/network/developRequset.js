import {
  localLdapRequest,
  blctekLdapRequest,
  localAuthServerRequsest,
  blctekAuthServerRequsest
} from "./request";

export function getDevelopList() {
  let config={
    url: '/ldap/developList',
    method: 'GET'
  }
  return blctekAuthServerRequsest(config);
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
  return blctekAuthServerRequsest(config);
}

export function deleteDevelop(commonName) {
  let config= {
    url: '/ldap/developDelete',
    method: 'GET',
    params: {
      commonName: commonName
    }
  };
  return blctekAuthServerRequsest(config);
}