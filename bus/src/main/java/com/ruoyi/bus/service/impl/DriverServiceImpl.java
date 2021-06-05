package com.ruoyi.bus.service.impl;

import com.ruoyi.bus.domain.dto.VerifyNumberDTO;
import com.ruoyi.bus.mapper.DriverMapper;
import com.ruoyi.bus.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 司机端service业务层处理
 */
@Service
public class DriverServiceImpl implements IDriverService {

    @Autowired
    private DriverMapper driverMapper;

    /**
     * 查看当前车次的已核销人数
     *
     * @param busId 司机ID
     * @return 已核销人数
     */
    @Override
    public int getCurrVerifyCount(String busId) {
        return driverMapper.selectCurrVerifyCount(busId);
    }

    /**
     * 查看当前车次的总乘客数
     *
     * @param busId
     * @return
     */
    @Override
    public int getCurrCount(String busId) {
        return driverMapper.selectCurrCount(busId);
    }

    /**
     * 查看当前车次的总乘客数、已核销人数和未核销人数
     *
     * @param busId 车次
     * @return
     */
    @Override
    public VerifyNumberDTO getVerifyCount(String busId) {
        VerifyNumberDTO ret = new VerifyNumberDTO();
        ret.setBusID(busId);
        int verified = getCurrVerifyCount(busId);
        ret.setVerifyNum(verified);
        int total = getCurrCount(busId);
        ret.setTotalNum(total);
        ret.setUnVerifyNum(total);
        return ret;
    }


}
