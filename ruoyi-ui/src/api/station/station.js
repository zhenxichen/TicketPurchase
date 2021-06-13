import request from '@/utils/request'

// 查询车站列表
export function listStation(query) {
  return request({
    url: '/station/station/list/table',
    method: 'get',
    params: query
  })
}

// 查询车站详细
export function getStation(stationId) {
  return request({
    url: '/station/station/' + stationId,
    method: 'get'
  })
}

// 新增车站
export function addStation(data) {
  return request({
    url: '/station/station',
    method: 'post',
    data: data
  })
}

// 修改车站
export function updateStation(data) {
  return request({
    url: '/station/station',
    method: 'put',
    data: data
  })
}

// 删除车站
export function delStation(stationId) {
  return request({
    url: '/station/station/' + stationId,
    method: 'delete'
  })
}

// 导出车站
export function exportStation(query) {
  return request({
    url: '/station/station/export',
    method: 'get',
    params: query
  })
}