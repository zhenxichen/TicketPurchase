package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.Bus;
import com.ruoyi.bus.domain.vo.BusManageVO;
import com.ruoyi.station.domain.Station;

import java.util.List;

/**
 * 车次管理 服务层接口
 *
 * @author Zhenxi Chen
 * @date 2021/6/10
 */
public interface IBusManageService {
    /**
     * 获取车次列表
     *
     * @param bus 筛选条件
     * @return
     */
    List<BusManageVO> selectBusList(Bus bus);
}
