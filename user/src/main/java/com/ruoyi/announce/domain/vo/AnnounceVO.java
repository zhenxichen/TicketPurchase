package com.ruoyi.announce.domain.vo;

import java.util.Date;

/**
 * 向小程序端传输公告详情信息的VO
 *
 * @author Zhenxi Chen
 * @date 2021/6/8
 */
public class AnnounceVO {
    private Long annoId;

    private String title;

    private String content;

    private Date time;

    public Long getAnnoId() {
        return annoId;
    }

    public void setAnnoId(Long annoId) {
        this.annoId = annoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
