import request from '@/utils/request'

export function getLoggerList(executor,client,currentPage=1,pageSize=10) {
  return request({
    url: 'logger-server/logger/selectListByCondition',
    method: 'GET',
    params: {
      executor,
      client,
      currentPage,
      pageSize
    }
  })
}
