package com.ruoyi.tickets.domain.DTO;

/**
 * 存储司机信息，用于返回司机列表
 *
 * @author Zhenxi Chen
 * @date 2021/6/10
 */
public class Driver {
    private Long id;        // 司机ID

    private String name;    // 司机用户名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
