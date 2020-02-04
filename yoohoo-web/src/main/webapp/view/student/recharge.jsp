<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>充值页面</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/recharge/css/amazeui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/recharge/css/main.css" />
<%@ include file="import.jsp" %>
</head>
<body  onload="init()">
<div class="pay">
	<!--主内容开始编辑-->
	<div class="tr_recharge">
		<div class="tr_rechtext">
			<p class="te_retit"><img src="${pageContext.request.contextPath}/src/student/img/sunflower.png" alt="" />充值中心</p>
		</div>
		<%--<div class="tr_rechhead">
				<p class="te_retit_text_title" >限时特惠：</p>
				<p class="te_retit_text">单词课25分钟,1元/3个太阳花；单次公开课最低仅须12个太阳花，外教还是中教；是否固定教师；还是1对1，1对2，1对3，1对4，1对5，1对6 ...,依内容与个性需求安排，灵活消费！</p>
			</div>--%>
		<form   method="post" class="am-form" id="doc-vld-msg"   >
            <input type="hidden"   name="subject" value="商品充值" >
            <div class="tr_rechbox">
				<div class="tr_rechhead">
					<img src="${pageContext.request.contextPath}/src/student/img/boy.png" />
					<p>充值帐号：
						<a  id="user_name"></a>
					</p>
					<div class="tr_rechheadcion">
						<img src="${pageContext.request.contextPath}/src/student/img/sunflower.png" alt="" />
						<span>当前太阳花：<span  id="account_balance"></span></span>
					</div>
				</div>
				<div class="tr_rechli am-form-group">
					<ul class="ui-choose am-form-group" id="uc_01">
						<li>
							<label class="am-radio-inline">
									<input  id="iput_01" type="radio"   name="total_amount" required data-validation-message="请选择一项充值额度"> ￥100/兑换300太阳花
                            </label>
                            <span  id="span_01"   class="reward_span" style="display: none"></span>
						</li>
						<li>
							<label class="am-radio-inline">
									<input  id="iput_02" type="radio" name="total_amount" data-validation-message="请选择一项充值额度"> ￥2880/兑换8640太阳花
                            </label>
                            <span  id="span_02" class="reward_span" style="display: none">(赠送200太阳花)</span>
						</li>
						<li>
							<label class="am-radio-inline">
									<input  id="iput_03"  type="radio"  name="total_amount" data-validation-message="请选择一项充值额度"> ￥5000/兑换15000太阳花
                            </label>
                            <span  id="span_03" class="reward_span" style="display: none">(赠送500太阳花)</span>
						</li>
					</ul>
				</div>
				<div class="tr_rechcho am-form-group">
					<span>充值方式：</span>
                    <label class="am-radio" style="margin-right:30px;">
                        <input type="radio" name="type"    value="1" data-am-ucheck required data-validation-message="请选择一种充值方式"><img src="${pageContext.request.contextPath}/src/recharge/images/alipay_icon.png">
                    </label>
					<label class="am-radio">
                        <input type="radio" name="type"  value="2" data-am-ucheck  data-validation-message="请选择一种充值方式"><img src="${pageContext.request.contextPath}/src/recharge/images/weixin_icon.png">
                    </label>
					<label class="am-radio">
					<img   onclick="pay_bank()"      src="${pageContext.request.contextPath}/src/recharge/images/duigong_icon.png">
					</label>
				</div>
				<div class="tr_rechnum"> 
					<span>应付金额：</span>
					<p class="rechnum">0.00元</p>
				</div>
			</div>
			<div class="tr_paybox">
				<input type="button" onclick="pay_button()" value="确认支付" class="tr_pay am-btn" />
				<span>充值成功后,请保凭据,联系客服！</span>
				<span  style="color: red">${sessionScope.warning}</span>
			</div>
			<div class="clearfix beizu">
				<div class="piblicp">
					<p><span class="spandbeizu">备注</span>在线充值为最优惠交费，5000元以上充值请联系客服</p>
				</div>
				<div class="piblicvip">
					<p class="p1">VIP班:</p>
					<ul class="ull" style="margin-top: 0.5rem;">
						<li>外教（正常/测试）课=360朵太阳花/次</li>
						<li>中教（正常/测试）课=300朵太阳花/次</li>
						<li>电教课=30朵太阳花/次</li>
					</ul>
				</div>
				<div style="width: 60%;height: 90%;float: left;">
					<div style="width: 100%;height: 12%;float:left;">
						<span class="p1">多人班:</span>
						<%--<span style="padding-left:10px">按实际人数对应太阳花数扣费，如果全部缺课各人扣除平台费（全体提前请假除外！）</span>--%>
					</div>
					<div style="width: 50%;height: 60%;float: left;padding-top: 5px">
						<p class="p1">外教</p>
						<ul class="ull" style="margin-top: 0.5rem;">
							<li>1人（366太阳花/次）</li>
							<li>2人（240太阳花/次）</li>
							<li>3人（210太阳花/次）</li>
							<li>4人（180太阳花/次）</li>
						</ul>
					</div>
					<div style="width: 50%;height: 60%;float: left;padding-top: 5px">
						<p class="p1">中教</p>
						<ul class="ull" style="margin-top: 0.5rem;">
							<li>1人（306太阳花/次）</li>
							<li>2人（210太阳花/次）</li>
							<li>3人（180太阳花/次）</li>
							<li>4人（150太阳花/次）</li>
						</ul>
					</div>
					<div style="width: 100%;height: 28%;float:left;">
						<p style="padding-left:10px;padding-right: 10px;">按实际人数对应太阳花数扣费，如果全部缺课各人扣除平台费（全体提前请假除外！）</p>
					</div>
				</div>

			</div>
		</form>
	</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/src/recharge/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/src/recharge/js/amazeui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/src/recharge/js/ui-choose.js"></script>
<script type="text/javascript">
	function init(){
        $.get(contextPath+"/stu/user/loginStudentInfo",
			function(resp){
			if(JSON.parse(resp).code=="0"){
			    $('#user_name').text(JSON.parse(resp).data.studentInfo.name)
			    $('#account_balance').text(JSON.parse(resp).data.studentInfo.balance)
			}
        })
	}
	// 将所有.ui-choose实例化
	$('.ui-choose').ui_choose();
	// uc_01 ul 单选
	var uc_01 = $('#uc_01').data('ui-choose'); // 取回已实例化的对象
	uc_01.click = function(index, item) {
		console.log('click', index, item.text())
	}
	uc_01.change = function(index, item) {
		console.log('change', index, item.text())
	}
	$(function() {
		$('#uc_01 li:eq(0)').click(function() {
            $('#span_01').show();
            $('#iput_01').val("a")
            $('#span_02').hide();
            $('#span_03').hide();
			$('.rechnum').text('100.00元');
		})
		$('#uc_01 li:eq(1)').click(function() {
            $('#span_01').hide();
            $('#span_02').show();
            $('#iput_02').val("b")
            $('#span_03').hide();
			$('.rechnum').text('2880.00元');
		})
		$('#uc_01 li:eq(2)').click(function() {
            $('#span_01').hide();
            $('#span_02').hide();
            $('#iput_03').val("c")
            $('#span_03').show();
			$('.rechnum').text('5000.00元');
		})
	})
	$(function() {
		$('#doc-vld-msg').validator({
			onValid: function(validity) {
				$(validity.field).closest('.am-form-group').find('.am-alert').hide();
			},
			onInValid: function(validity) {
				var $field = $(validity.field);
				var $group = $field.closest('.am-form-group');
				var $alert = $group.find('.am-alert');
				// 使用自定义的提示信息 或 插件内置的提示信息
				var msg = $field.data('validationMessage') || this.getValidationMessage(validity);
				if(!$alert.length) {
					$alert = $('<div class="am-alert am-alert-danger"></div>').hide().
					appendTo($group);
				}
				$alert.html(msg).show();
			}
		});
	});
	function pay_bank() {
        layer.open({
            type: 2,
            title: '对公账号',
            maxmin: false,
            shadeClose: true, //点击遮罩关闭层
            area: ['400px', '300px'],
            content: '/view/student/bank-message.jsp'
        });
    }

    function pay_button() {
        var total=$("input[name='total_amount']:checked").val();
		if($("input[name='type']:checked").val()==1 ){
            $("#doc-vld-msg").attr("action","${pageContext.request.contextPath}/UserRecharge/aliPay")
		    $("#doc-vld-msg").submit();
		  //  alert("吊起支付宝支付")
		}else if($("input[name='type']:checked").val()==2 && total!=undefined){
            layer.open({
                type: 2,
                title: '充值金额',
                maxmin: false,
                shadeClose: false, //点击遮罩关闭层
                area: ['400px', '400px'],
                content: '${pageContext.request.contextPath}/view/student/wxPay.jsp?total_amount='+total
            });
		}else{
		    alert("请选择支付方式！")
        }
    }
</script>
</div>
</body>
</html>