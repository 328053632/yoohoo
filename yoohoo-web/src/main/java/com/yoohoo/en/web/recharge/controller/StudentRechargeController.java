package com.yoohoo.en.web.recharge.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.yoohoo.en.bean.recharge.AliPayBean;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TStudentInfo;
import com.yoohoo.en.utils.SessionUtil;
import com.yoohoo.en.web.recharge.config.AliPayConfig;
import com.yoohoo.en.web.recharge.service.StudentRechargeService;

/**
 * Created By LiWenLong On 2018/8/17 16:09
 * E-Mail:it_lwl@163.com
 *
 * 用户充值
 */

@Controller
@RequestMapping("/UserRecharge")
public class StudentRechargeController {

    @Autowired
    StudentRechargeService studentRechargeService;


    @RequestMapping(value="/payType",method = RequestMethod.POST)
    public String payType(AliPayBean aliPayBean,HttpServletRequest request){
        if(aliPayBean.getType().equals("1")){
            return "forward:/UserRecharge/aliPay";
        }else if(aliPayBean.getType().equals("2")){

            HttpSession session = request.getSession();
            session.setAttribute("aliPayBean",aliPayBean);
            return "forward:/view/student/wxPay.jsp";
        }else{
            return "forward:/UserRecharge/aliPay";
        }
    }

    @RequestMapping("/aliPay")
    public Object  aliPay(HttpServletRequest request, HttpServletResponse response, AliPayBean aliPayBean,HttpSession session) throws AlipayApiException, IOException {
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        Integer userId = loginStudent.getUserId().intValue();
        String total_amount =CheckMoney(aliPayBean);
        String out_trade_no = getTradeNo(userId);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AliPayConfig.gatewayUrl,AliPayConfig.app_id, AliPayConfig.merchant_private_key, "json", AliPayConfig.charset,AliPayConfig.alipay_public_key,AliPayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AliPayConfig.return_url);
        alipayRequest.setNotifyUrl(AliPayConfig.notify_url);
        //订单名称，必填
        String subject = new String(request.getParameter("subject").getBytes("UTF-8"),"UTF-8");
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        //输出
        //AlipayConfig.logResult(result);// 记录支付日志
        PrintWriter out =response.getWriter();
        response.setContentType("text/html; charset=utf-8");
        out.println(result);
        return  null;
    }


    @RequestMapping("/wxPay")
    @ResponseBody
    public BaseResp wxPay(HttpServletRequest request, HttpServletResponse response, String total_amount, HttpSession session) throws JDOMException, IOException {
        BaseResp baseRese=new BaseResp();
        TStudentInfo loginStudent = SessionUtil.INSTANCE.getLoginStudent(session);
        Integer userId = loginStudent.getUserId().intValue();
        String out_trade_no = getTradeNo(userId);
        AliPayBean aliPayBean=new AliPayBean();
        aliPayBean.setTotal_amount(total_amount);
        String amount = CheckMoney(aliPayBean);
        String qrcode_url = studentRechargeService.weixinPay(out_trade_no, amount);
        baseRese.put("qrcode_url",qrcode_url);
        baseRese.put("out_trade_no",out_trade_no);
        baseRese.put("total_amount",amount);
        return baseRese;
    }


    /**
     *  金额检验
     * @param aliPayBean
     * @return
     */
    private String CheckMoney(AliPayBean aliPayBean) {
        if(aliPayBean.getTotal_amount().equals("a")){
            aliPayBean.setTotal_amount("100");
        }else if(aliPayBean.getTotal_amount().equals("b")){
            aliPayBean.setTotal_amount("2880");
        }else if(aliPayBean.getTotal_amount().equals("c")){
            aliPayBean.setTotal_amount("5000");
        }else{
            aliPayBean.setTotal_amount("100000");
        }
        return aliPayBean.getTotal_amount();
    }

    /**
     * 生成商品订单
     * @return
     * @param userId
     */
    private String getTradeNo(Integer userId) {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = dateFormat.format(new Date());
        StringBuffer stringBuffer=new StringBuffer(format);
        stringBuffer.append(userId);
        return String.valueOf(stringBuffer);
    }

}
