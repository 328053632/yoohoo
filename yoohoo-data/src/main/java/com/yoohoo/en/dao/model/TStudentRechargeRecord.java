package com.yoohoo.en.dao.model;

import java.util.Date;

public class TStudentRechargeRecord {
    private String orderId;

    private Integer userId;

    private Double payMoney;

    private Date orderTime;

    private Date payTime;

    private Integer status;

    private Integer payType;

    private String aliPayNo;

    private String wxPayNo;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getAliPayNo() {
        return aliPayNo;
    }

    public void setAliPayNo(String aliPayNo) {
        this.aliPayNo = aliPayNo == null ? null : aliPayNo.trim();
    }

    public String getWxPayNo() {
        return wxPayNo;
    }

    public void setWxPayNo(String wxPayNo) {
        this.wxPayNo = wxPayNo == null ? null : wxPayNo.trim();
    }

    @Override
    public String toString() {
        return "TStudentRechargeRecord{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", payMoney=" + payMoney +
                ", orderTime=" + orderTime +
                ", payTime=" + payTime +
                ", status=" + status +
                ", payType=" + payType +
                ", aliPayNo='" + aliPayNo + '\'' +
                ", wxPayNo='" + wxPayNo + '\'' +
                '}';
    }
}