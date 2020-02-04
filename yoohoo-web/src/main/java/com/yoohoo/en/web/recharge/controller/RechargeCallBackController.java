package com.yoohoo.en.web.recharge.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.yoohoo.en.bean.response.BaseResp;
import com.yoohoo.en.dao.model.TStudentRechargeRecord;
import com.yoohoo.en.web.recharge.config.AliPayConfig;
import com.yoohoo.en.web.recharge.config.WxPayConfig;
import com.yoohoo.en.web.recharge.service.RechargeCallBackService;
import com.yoohoo.en.web.recharge.wxutil.AmounUtils;
import com.yoohoo.en.web.recharge.wxutil.PayToolUtil;
import com.yoohoo.en.web.recharge.wxutil.XMLUtil4jdom;

/**
 * Created By LiWenLong On 2018/8/18 16:22
 * E-Mail:it_lwl@163.com
 *
 * 支付宝支付和微信支付的回调函数
 */


@Controller
@RequestMapping("/callback")
public class RechargeCallBackController {


    @Autowired
    private RechargeCallBackService rechargeCallBackService;

    /**
     * 支付宝异步回调函数
     * @param response
     * @param request
     */
    @RequestMapping("alipay_notify_url")
    public void NotifyUrl(HttpServletResponse response, HttpServletRequest request,HttpSession session) throws IOException, AlipayApiException, ParseException {
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("utf-8"), "utf-8");
            System.out.println(name+":"+valueStr);
            params.put(name, valueStr);
        }
        PrintWriter out=response.getWriter();
        boolean signVerified = AlipaySignature.rsaCheckV1(params,AliPayConfig.alipay_public_key, "utf-8", AliPayConfig.sign_type); //调用SDK验证签名
        if(signVerified) {
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("UTF-8"),"UTF-8");
            String trade_no = new String(request.getParameter("trade_no").getBytes("UTF-8"),"UTF-8");
            String total_amount = new String(request.getParameter("total_amount").getBytes("UTF-8"),"UTF-8");
            String gmt_create=new String(request.getParameter("gmt_create").getBytes("UTF-8"),"UTF-8");
            String gmt_payment=new String(request.getParameter("gmt_payment").getBytes("UTF-8"),"UTF-8");
            TStudentRechargeRecord tStudentRechargeRecord = setRechargeBean(out_trade_no, trade_no, total_amount, gmt_create, gmt_payment);
            rechargeCallBackService.inserTransactionRecord(tStudentRechargeRecord);
            out.println("success");
        }else {
            out.println("fail");
            String sWord = AlipaySignature.getSignCheckContentV1(params);
            AliPayConfig.logResult(sWord);
        }
    }
    /**封装支付宝交易实体属性**/
    private TStudentRechargeRecord setRechargeBean(String out_trade_no, String trade_no, String total_amount, String gmt_create, String gmt_payment) throws ParseException {
        TStudentRechargeRecord tStudentRechargeRecord=new TStudentRechargeRecord();
        //2018-08-20 10:00:03
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tStudentRechargeRecord.setOrderTime(format.parse(gmt_create));
        tStudentRechargeRecord.setPayTime(format.parse(gmt_payment));
        tStudentRechargeRecord.setOrderId(out_trade_no);
        tStudentRechargeRecord.setAliPayNo(trade_no);
        tStudentRechargeRecord.setStatus(0);
        tStudentRechargeRecord.setUserId(Integer.valueOf(out_trade_no.substring(17)));
        tStudentRechargeRecord.setPayType(1);
        double money=Double.parseDouble(total_amount);
        tStudentRechargeRecord.setPayMoney(money);
        return  tStudentRechargeRecord;
    }


    /**
     * 支付宝同步回调页面
    */
    @RequestMapping("alipay_return_url")
    public String ReturnUrl(){
        return "student/my-study";
    }


    /**
     *  微信回调页面
     */
    @RequestMapping("/wxpay_notify_url")
    public void WxNotifyUrl(HttpServletRequest request,HttpServletResponse response) throws Exception {
        InputStream inputStream ;
        StringBuffer sb = new StringBuffer();
        inputStream = request.getInputStream();
        String s ;
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        while ((s = in.readLine()) != null){
            sb.append(s);
        }
        in.close();
        inputStream.close();

        Map<String, String> m = new HashMap<String, String>();
        m = XMLUtil4jdom.doXMLParse(sb.toString());
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();
        Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            String parameter = (String) it.next();
            String parameterValue = m.get(parameter);

            String v = "";
            if(null != parameterValue) {
                v = parameterValue.trim();
            }
            packageParams.put(parameter, v);
        }
        String key = WxPayConfig.API_KEY;
        if(PayToolUtil.isTenpaySign("UTF-8", packageParams,key)) {
            String resXml = "";
            if("SUCCESS".equals((String)packageParams.get("result_code"))){
                String mch_id = (String)packageParams.get("mch_id");
                String openid = (String)packageParams.get("openid");
                String is_subscribe = (String)packageParams.get("is_subscribe");
                String out_trade_no = (String)packageParams.get("out_trade_no");
                String total_amount = (String)packageParams.get("total_fee");
                String trade_no = (String)packageParams.get("transaction_id");
                String gmt_payment = (String)packageParams.get("time_end");
                TStudentRechargeRecord tStudentRechargeRecord = setRechargeBean(out_trade_no, trade_no, total_amount, gmt_payment);
                System.out.println(tStudentRechargeRecord);
                rechargeCallBackService.inserTransactionRecord(tStudentRechargeRecord);
                System.out.println("支付成功");
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";

            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
            }
            BufferedOutputStream out = new BufferedOutputStream(
                    response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } else{
            System.out.println("通知签名验证失败");
        }
    }

    /**
     *
     * 微信交易实体封装
     * @param out_trade_no
     * @param trade_no
     * @param total_amount
     * @param gmt_payment
     * @return
     * @throws ParseException
     */
    private TStudentRechargeRecord setRechargeBean(String out_trade_no, String trade_no, String total_amount, String gmt_payment) throws Exception {
        TStudentRechargeRecord tStudentRechargeRecord=new TStudentRechargeRecord();
        //2018-08-20 10:00:03
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        Date format = simpleDateFormat.parse(gmt_payment);
        SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format2 = simpleDateFormat2.format(format);
        tStudentRechargeRecord.setPayTime(simpleDateFormat2.parse(format2));
        tStudentRechargeRecord.setOrderId(out_trade_no);
        tStudentRechargeRecord.setWxPayNo(trade_no);
        tStudentRechargeRecord.setStatus(0);
        tStudentRechargeRecord.setUserId(Integer.valueOf(out_trade_no.substring(17)));
        tStudentRechargeRecord.setPayType(2);
        String money = AmounUtils.changeF2Y(total_amount);
        tStudentRechargeRecord.setPayMoney(Double.valueOf(money));
        return  tStudentRechargeRecord;
    }

    /**
     *
     * 微信支付后的轮询，确定微信支付成功
     *
     */
    @ResponseBody
    @RequestMapping("/isFinishPay")
    public BaseResp isFinishPay(HttpServletRequest request, HttpServletResponse response,String out_trade_no) {
        BaseResp baseResp=new BaseResp();
        TStudentRechargeRecord tStudentRechargeRecord=    rechargeCallBackService.findOrderByOrderId(out_trade_no);
        //简单的业务逻辑：在微信的回调接口里面，已经定义了，回调返回成功的话，那么 _PAY_RESULT 不为空
        if(tStudentRechargeRecord != null ){
            baseResp.put("SUCCESS","支付成功");
            return baseResp;
        }
        baseResp.put("ERROR","还未支付");
        baseResp.setCode("1");
        return baseResp;
    }
}
