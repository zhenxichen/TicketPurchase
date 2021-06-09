package com.ruoyi.announce.domain.vo;

import java.util.Date;

public class AnnounceListVO {

    private Long annoId;        // 公告ID

    private String title;       // 公告标题

    private Date time;          // 发布时间

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
