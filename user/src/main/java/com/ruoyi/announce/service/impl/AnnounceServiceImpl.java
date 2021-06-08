package com.ruoyi.announce.service.impl;

import com.ruoyi.announce.domain.vo.AnnounceListVO;
import com.ruoyi.announce.service.IAnnounceService;
import com.ruoyi.announce.util.AnnounceFormatUtil;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 小程序端公告 服务层
 *
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
@Service
public class AnnounceServiceImpl implements IAnnounceService {

    @Autowired
    ISysNoticeService noticeService;

    /**
     * 获取公告标题列表
     */
    @Override
    public List<AnnounceListVO> selectAnnounceTitleList() {
        SysNotice notice = new SysNotice();
        List<AnnounceListVO> list = new ArrayList<>();
        List<SysNotice> noticeList = noticeService.selectNoticeList(notice);
        for (SysNotice sysNotice: noticeList) {
            list.add(AnnounceFormatUtil.sysNoticeToAnnounceListVO(sysNotice));
        }
        return list;
    }
}
