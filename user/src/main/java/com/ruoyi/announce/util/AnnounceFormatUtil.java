package com.ruoyi.announce.util;

import com.ruoyi.announce.domain.vo.AnnounceListVO;
import com.ruoyi.system.domain.SysNotice;

/**
 * 对公告进行转换的工具类
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
public class AnnounceFormatUtil {
    /**
     * 将SysNotice对象转为AnnounceListVO对象
     * @param notice SysNotice对象
     * @return AnnounceListVO对象
     */
    public static AnnounceListVO sysNoticeToAnnounceListVO(SysNotice notice) {
        AnnounceListVO vo = new AnnounceListVO();
        vo.setAnnoId(notice.getNoticeId());
        vo.setTitle(notice.getNoticeTitle());
        vo.setTime(notice.getCreateTime());
        return vo;
    }
}
