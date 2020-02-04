<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--退出登录--%>
    <title>雅英</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/login.css?version=${_dt}">
    <link href="${pageContext.request.contextPath }/src/student/css/style.css" rel="stylesheet" type="text/css" />
    <%@ include file="import.jsp" %>
</head>
<body  >
<div style="float:left">
    <div>
       	<div>
	        <span><a class="buy-btn" style="background: #fff;color:#16dabe;text-decoration:none;" 
	        		href="http://tykj.nat100.top/yoohoo-web/index/class.html">如何使用</a></span>
	        <span><a class="buy-btn" style="background: #fff;color:#16dabe;text-decoration:none;" 
	        		href="javascript://" onclick="showLogin()">学生登陆</a></span>
            <span><a class="buy-btn" style="background: #fff;color:#16dabe;text-decoration:none;" 
            		href="http://tykj.nat100.top/yoohoo-web/student/views/lesson/library">课程库</a></span>
	        <span><a class="buy-btn" style="background: #fff;color:#16dabe;text-decoration:none;" 
	        		href="http://tykj.nat100.top/yoohoo-web/teacher/views/login">老师登陆</a></span>
        </div>
        <div class="bn_img wow" data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: fadeInUp;"></div>
    </div>
</div>
    <div id="app" style="display:none">
        <div class="login-frame">
            <h2 class="title">欢迎来到YoohooABC</h2>
            <div class="input-item"><input type="text" placeholder="输入手机号码" v-model="accountInfo.account"/></div>
            <div class="input-item password">
                <i :class="showPassword ? 'visible' : 'invisible'" @click="showPassword = !showPassword"></i>
                <input :type="showPassword ? 'text' : 'password'" placeholder="输入密码" v-model="accountInfo.password"  @keyup.enter="doLogin"/>
            </div>
            <div class="forget-pwd"><a href="${pageContext.request.contextPath }/student/views/resetpwd">忘记密码?</a></div>
            <div class="login"><button class="login-btn"  @click="doLogin">登录</button></div>
            <div class="register"><a href="${pageContext.request.contextPath}/student/views/register">还没有账号? 立即注册</a></div>
            <div class="bottom">
                <span><button class="btn-ting" @click="applyAudition(4)" >免费试听</button></span>
                <span><button class="btn-ce" @click="applyAudition(5)" >免费测评</button></span>
            </div>
        </div>
        <poohoo-apply-audition :apply-type="applyType" ref="apply_Audition"></poohoo-apply-audition>
    </div>
</body>
<%@ include file="tpl-apply-audition.jspf" %>
<script src="${pageContext.request.contextPath }/dep/jquery/jquery.md5.js"></script>
<script src="${pageContext.request.contextPath }/src/student/js/login.js?version=${_dt}"></script>
<script type="text/javascript">
	function showLogin(){
		$("#app").show();
	}
</script>
</html>
