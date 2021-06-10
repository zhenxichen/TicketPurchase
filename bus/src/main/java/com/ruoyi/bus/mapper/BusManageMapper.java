package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.Bus;
import com.ruoyi.bus.domain.vo.BusManageVO;

import java.util.List;

public interface BusManageMapper {
    /**
     * 查询车次列表
     *
     * @param bus 筛选条件
     * @return
     */
    List<BusManageVO> selectBusList(Bus bus);
}
