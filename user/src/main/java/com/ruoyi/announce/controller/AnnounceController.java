package com.ruoyi.announce.controller;

import com.ruoyi.announce.domain.vo.AnnounceListVO;
import com.ruoyi.announce.service.IAnnounceService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 小程序端公告功能 Controller
 *
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
@RestController
@RequestMapping("/announce")
public class AnnounceController {

    @Autowired
    private IAnnounceService announceService;

    /**
     * 获取公告列表接口
     */
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermi('system:notice:list')")
    public AjaxResult announceList() {
        AjaxResult ajax = AjaxResult.success();
        List<AnnounceListVO> list = announceService.selectAnnounceTitleList();
        ajax.put("length", list.size());
        ajax.put("annoList", list);
        return ajax;
    }

    /**
     * 获取公告详情接口
     * @param id 公告ID
     * @return
     */
    @GetMapping("/query")
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    public AjaxResult announceDetail(@RequestParam("id") String id) {
        Long announceId = Long.valueOf(id);
        AjaxResult ajax = AjaxResult.success();
        return ajax;
    }
}
