package com.ruoyi.tickets.controller;

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
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.service.ITicketsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车票管理Controller
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/tickets/tickets")
public class TicketsController extends BaseController
{
    @Autowired
    private ITicketsService ticketsService;

    /**
     * 查询车票管理列表
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tickets tickets)
    {
        startPage();
        List<Tickets> list = ticketsService.selectTicketsList(tickets);
        return getDataTable(list);
    }

    /**
     * 导出车票管理列表
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:export')")
    @Log(title = "车票管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Tickets tickets)
    {
        List<Tickets> list = ticketsService.selectTicketsList(tickets);
        ExcelUtil<Tickets> util = new ExcelUtil<Tickets>(Tickets.class);
        return util.exportExcel(list, "车票管理数据");
    }

    /**
     * 获取车票管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:query')")
    @GetMapping(value = "/{busId}")
    public AjaxResult getInfo(@PathVariable("busId") String busId)
    {
        return AjaxResult.success(ticketsService.selectTicketsById(busId));
    }

    /**
     * 新增车票管理
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:add')")
    @Log(title = "车票管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tickets tickets)
    {
        return toAjax(ticketsService.insertTickets(tickets));
    }

    /**
     * 修改车票管理
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:edit')")
    @Log(title = "车票管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tickets tickets)
    {
        return toAjax(ticketsService.updateTickets(tickets));
    }

    /**
     * 删除车票管理
     */
    @PreAuthorize("@ss.hasPermi('tickets:tickets:remove')")
    @Log(title = "车票管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{busIds}")
    public AjaxResult remove(@PathVariable String[] busIds)
    {
        return toAjax(ticketsService.deleteTicketsByIds(busIds));
    }
}
