package com.ruoyi.bus.controller;

import java.util.List;

import com.ruoyi.bus.domain.vo.BusManageVO;
import com.ruoyi.bus.service.IBusManageService;
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
import com.ruoyi.bus.domain.Bus;
import com.ruoyi.bus.service.IBusService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车次管理Controller
 * 
 * @author Zhenxi Chen
 * @date 2021-06-05
 */
@RestController
@RequestMapping("/bus/bus")
public class BusController extends BaseController
{
    @Autowired
    private IBusService busService;

    @Autowired
    private IBusManageService busManageService;

    /**
     * 查询车次管理列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Bus bus)
    {
        startPage();
        List<BusManageVO> list = busManageService.selectBusList(bus);
        return getDataTable(list);
    }

    /**
     * 导出车次管理列表
     */
    @PreAuthorize("@ss.hasPermi('bus:bus:export')")
    @Log(title = "车次管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Bus bus)
    {
        List<Bus> list = busService.selectBusList(bus);
        ExcelUtil<Bus> util = new ExcelUtil<Bus>(Bus.class);
        return util.exportExcel(list, "车次管理数据");
    }

    /**
     * 获取车次管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('bus:bus:query')")
    @GetMapping(value = "/{busId}")
    public AjaxResult getInfo(@PathVariable("busId") String busId)
    {
        return AjaxResult.success(busService.selectBusById(busId));
    }

    /**
     * 新增车次管理
     */
    @PreAuthorize("@ss.hasPermi('bus:bus:add')")
    @Log(title = "车次管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Bus bus)
    {
        return toAjax(busService.insertBus(bus));
    }

    /**
     * 修改车次管理
     */
    @PreAuthorize("@ss.hasPermi('bus:bus:edit')")
    @Log(title = "车次管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Bus bus)
    {
        return toAjax(busService.updateBus(bus));
    }

    /**
     * 删除车次管理
     */
    @PreAuthorize("@ss.hasPermi('bus:bus:remove')")
    @Log(title = "车次管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busIds}")
    public AjaxResult remove(@PathVariable String[] busIds)
    {
        return toAjax(busService.deleteBusByIds(busIds));
    }
}
