package com.ruoyi.bus.service.impl;

import com.ruoyi.bus.domain.Bus;
import com.ruoyi.bus.domain.vo.BusManageVO;
import com.ruoyi.bus.mapper.BusManageMapper;
import com.ruoyi.bus.service.IBusManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusManageServiceImpl implements IBusManageService {

    @Autowired
    private BusManageMapper busManageMapper;

    /**
     * 获取车次列表
     *
     * @param bus 筛选条件
     * @return
     */
    @Override
    public List<BusManageVO> selectBusList(Bus bus) {
        return busManageMapper.selectBusList(bus);
    }
}
