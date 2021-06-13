package com.ruoyi.station.controller;

import java.util.List;
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
 * 车站Controller
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/station/station")
public class StationController extends BaseController
{
    @Autowired
    private IStationService stationService;

    @GetMapping("/list")
    public AjaxResult list(Station station)
    {
        List<Station> list = stationService.selectStationList(station);
        return AjaxResult.success(list);
    }

    /**
     * 查询车站列表
     */
    @PreAuthorize("@ss.hasPermi('station:station:list')")
    @GetMapping("/list/table")
    public TableDataInfo listTable(Station station)
    {
        startPage();
        List<Station> list = stationService.selectStationList(station);
        return getDataTable(list);
    }

    /**
     * 获取车站详细信息
     */
    @PreAuthorize("@ss.hasPermi('station:station:query')")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") Long stationId)
    {
        return AjaxResult.success(stationService.selectStationById(stationId));
    }

    /**
     * 新增车站
     */
    @PreAuthorize("@ss.hasPermi('station:station:add')")
    @Log(title = "车站", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Station station)
    {
        return toAjax(stationService.insertStation(station));
    }

    /**
     * 修改车站
     */
    @PreAuthorize("@ss.hasPermi('station:station:edit')")
    @Log(title = "车站", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Station station)
    {
        return toAjax(stationService.updateStation(station));
    }

    /**
     * 删除车站
     */
    @PreAuthorize("@ss.hasPermi('station:station:remove')")
    @Log(title = "车站", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable Long[] stationIds)
    {
        return toAjax(stationService.deleteStationByIds(stationIds));
    }
}
