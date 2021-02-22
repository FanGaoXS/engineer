import {
  prodServer,
  localServer
} from "./request";

/**
 * 根据所属工程用具得到类型
 * @param config
 */
export function getModelByModelBelong(modelBelong) {
  let config={
    url: '/car/modelListByBelong',
    type: 'GET',
    params: {
      modelBelong: modelBelong
    }
  };
  return prodServer(config);
}