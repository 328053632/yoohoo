package com.yoohoo.en.bean.recharge;

/**
 * Created By LiWenLong On 2018/8/17 16:50
 * E-Mail:it_lwl@163.com
 * 支付宝支付实体
 */
public class AliPayBean {

    //订单编号
    private String trade_no;
    //付款金额 元为单位
    private String total_amount;
    //订单名称
    private String subject;
    //商品描述
    private String body;

    //支付方式
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AliPayBean{" +
                "trade_no='" + trade_no + '\'' +
                ", total_amount='" + total_amount + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
