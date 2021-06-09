package com.ruoyi.announce.service;

import com.ruoyi.announce.domain.vo.AnnounceListVO;
import com.ruoyi.announce.domain.vo.AnnounceVO;

import java.util.List;

/**
 * 小程序端公告 服务层接口
 *
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
public interface IAnnounceService {
    /**
     * 获取公告标题列表
     */
    List<AnnounceListVO> selectAnnounceTitleList();

    /**
     * 根据公告ID获取公告详情
     * @param id 公告ID
     * @return
     */
    AnnounceVO selectAnnounceById(Long id);
}
