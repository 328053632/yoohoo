<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/21
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/recharge/css/main.css" />
<script src="${pageContext.request.contextPath }/src/student/js/qrcode.min.js"></script>
<%@ include file="import.jsp" %>
<html>
<head>
    <style>
       #qrcode img{
          width: 68%;
          height: 70%;
          position: absolute;
          left: 0;
          top: 20px;
          bottom: 0;
          right: 0;
          margin: auto;
       }
       #recharge_messgae {
          width: 96%;
          background: rgba(39, 176, 214, .15);
          padding: 0px;
          display: inline-block;
          float: left;
          margin-top: 10px;
          padding-left: 10px;
       }
    </style>
</head>
<body  onload="init()">
<div >
      <div  id="recharge_messgae">
         <span>充值金额：</span><span id="money"></span>元<br>
         <span>订单号:</span><span id="no"></span>
      </div>
</div>
  <div  id="qrcode"></div>
</body>
<script type="text/javascript">
    var trade_no="";
    var qrcode = new QRCode('qrcode', {
        width: 250,
        height: 250,
        colorDark : '#000000',
        colorLight : '#ffffff',
        correctLevel : QRCode.CorrectLevel.H
    });


    function init() {
      var total_amount=  getQueryString("total_amount");
      $.post(
          "${pageContext.request.contextPath}/UserRecharge/wxPay",
          {total_amount:total_amount},
          function(data) {
            if(data.code=="0"){
                console.log(data.data.qrcode_url)
                qrcode.makeCode( data.data.qrcode_url);
                $("#money").text(data.data.total_amount)
                $("#no").text(data.data.out_trade_no)
                trade_no=data.data.out_trade_no;
            }
        },
          "json"
      )

        //重复执行某个方法
        var t1 = window.setInterval("getPayState()",5000);
    }


    function getQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]); return null;
    }


    function getPayState(){
        console.log(trade_no)
        var url = '${pageContext.request.contextPath}/callback/isFinishPay';
        //轮询是否已经付费
        $.ajax({
            type:'post',
            url:url,
            data:{out_trade_no:trade_no},
            cache:false,
            async:true,
            success:function(json){
                if(JSON.parse(json).code === "0"){
                    layer.msg("支付成功，请尽快与管理员联系!")
                    var index=parent.layer.getFrameIndex(window.name);//获取当前弹出层的层级
                    setTimeout(function () {
                        window.parent.location.reload();//刷新父页面
                    },3000);
                    setTimeout(function () {
                        parent.layer.close(index);//关闭弹出层
                    },3000);
                }else{
                }
            },
            error:function(){
                layer.msg("执行错误！", 8);
            }
        });
    }
</script>
</html>
