package com.ruoyi.orders.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 创建订单id类 */
public class OrderIdCreator {
    private Long userId;
    private String bus;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public OrderIdCreator(Long userId, String bus, Date date) {
        this.userId = userId;
        this.bus = bus;
        this.date = date;
    }

    /**
     * 创建订单id
     * 车次+日期+随机数
     * 有可能重复，目前并不完善
     * @return
     */
    public String createOrderId(){
        String id="";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        id+=getBus();
        id+=String.format("%04d",calendar.get(Calendar.YEAR));	//获取年份
        id+=String.format("%02d",calendar.get(Calendar.MONTH)+1);	//获取月份
        id+=String.format("%02d",calendar.get(Calendar.DATE));
        long randomNum = System.currentTimeMillis()%10000;
        Random r = new Random(randomNum);
        int ran1 = r.nextInt(100000);
        id+=String.format("%05d",ran1);
        return id;

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
