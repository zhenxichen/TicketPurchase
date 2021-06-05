package com.ruoyi.bus.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bus.mapper.BusMapper;
import com.ruoyi.bus.domain.Bus;
import com.ruoyi.bus.service.IBusService;

/**
 * 车次管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
@Service
public class BusServiceImpl implements IBusService 
{
    @Autowired
    private BusMapper busMapper;

    /**
     * 查询车次管理
     * 
     * @param busId 车次管理ID
     * @return 车次管理
     */
    @Override
    public Bus selectBusById(String busId)
    {
        return busMapper.selectBusById(busId);
    }

    /**
     * 查询车次管理列表
     * 
     * @param bus 车次管理
     * @return 车次管理
     */
    @Override
    public List<Bus> selectBusList(Bus bus)
    {
        return busMapper.selectBusList(bus);
    }

    /**
     * 新增车次管理
     * 
     * @param bus 车次管理
     * @return 结果
     */
    @Override
    public int insertBus(Bus bus)
    {
        return busMapper.insertBus(bus);
    }

    /**
     * 修改车次管理
     * 
     * @param bus 车次管理
     * @return 结果
     */
    @Override
    public int updateBus(Bus bus)
    {
        return busMapper.updateBus(bus);
    }

    /**
     * 批量删除车次管理
     * 
     * @param busIds 需要删除的车次管理ID
     * @return 结果
     */
    @Override
    public int deleteBusByIds(String[] busIds)
    {
        return busMapper.deleteBusByIds(busIds);
    }

    /**
     * 删除车次管理信息
     * 
     * @param busId 车次管理ID
     * @return 结果
     */
    @Override
    public int deleteBusById(String busId)
    {
        return busMapper.deleteBusById(busId);
    }
}
