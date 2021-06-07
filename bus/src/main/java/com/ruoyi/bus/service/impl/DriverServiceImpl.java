package com.ruoyi.bus.service.impl;

import com.ruoyi.bus.domain.dto.TodayBusDTO;
import com.ruoyi.bus.domain.dto.VerifyDTO;
import com.ruoyi.bus.domain.dto.VerifyNumberDTO;
import com.ruoyi.bus.domain.vo.TodayBusVO;
import com.ruoyi.bus.domain.vo.VerifyRecordVO;
import com.ruoyi.bus.mapper.DriverMapper;
import com.ruoyi.bus.service.IDriverService;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 司机端service业务层处理
 */
@Service
public class DriverServiceImpl implements IDriverService {

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private TokenService tokenService;

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

    /**
     * 校验当前订单的状态是否为待核验且为今日订单
     *
     * @param orderId 订单号
     * @return bool值 true表示状态为待核验 false表示无效或状态错误
     */
    @Override
    public boolean checkOrderStatus(String orderId) {
        int count = driverMapper.checkOrderStatus(orderId);
        if (count == 1) {
            return true;
        }
        return false;
    }

    /**
     * 查询核销记录
     *
     * @param busId 车次
     * @return 核销记录的list
     */
    @Override
    public VerifyRecordVO verifyRecord(String busId) {
        List<VerifyDTO> list = driverMapper.selectVerifyRecord(busId);
        VerifyRecordVO vo = new VerifyRecordVO();
        vo.setVerifyList(list);
        vo.setLength(list.size());
        return vo;
    }

    /**
     * 查询该司机今日的车次
     *
     * @param request HTTP请求信息
     * @return
     */
    @Override
    public List<TodayBusVO> todayBus(HttpServletRequest request) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        String username = loginUser.getUsername();
        List<TodayBusDTO> dtoList = driverMapper.selectTodayBus(username);
        List<TodayBusVO> voList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   /* SimpleDateFormat线程不安全！ */
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        for (TodayBusDTO dto: dtoList) {
            TodayBusVO vo = new TodayBusVO();
            vo.setBusID(dto.getBusID());
            vo.setStart(dto.getStart());
            vo.setDest(dto.getDest());
            vo.setDate(dateFormat.format(dto.getDate()));
            vo.setStartTime(timeFormat.format(dto.getStartTime()));
            vo.setDestTime(timeFormat.format(dto.getDestTime()));
            voList.add(vo);
        }
        return voList;
    }

    /**
     * 将订单状态修改为已核验
     *
     * @param orderId 订单号
     */
    @Override
    public void verifyTicket(String orderId) {
        driverMapper.updateStatusToVerified(orderId);
    }
}
