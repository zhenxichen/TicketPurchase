package com.ruoyi.station.controller;

import java.util.List;

import com.ruoyi.station.domain.dto.StationDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.station.domain.Station;
import com.ruoyi.station.service.IStationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车站前端Controller
 *
 * @author duan
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/station")
public class StationControllerMini
{
    @Autowired
    private IStationService stationService;

    /**
     * 查询车站列表
     */
    //@PreAuthorize("@ss.hasPermi('station:station:list')")
    @GetMapping("/list")
    public AjaxResult list(Station station)
    {
        List<StationDTO> list = stationService.selectStationList(station);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("length",list.size());
        ajax.put("stationList",list);
        return ajax;
    }
}

