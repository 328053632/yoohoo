<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--注册页--%>
    <title>雅英</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/register.css?version=${_dt}">
    <%@ include file="import.jsp" %>
</head>
<body>
<div id="app">
    <div class="register-frame">
        <h2 class="title">欢迎来到YoohooABC</h2>
        <div class="input-item"><input id="msisdn" type="text" placeholder="输入手机号码" v-model="accountInfo.msisdn"/></div>
        <div class="send-code">
            <div><input id="verifyCode" type="text" placeholder="输入手机验证码" v-model="accountInfo.code"/></div>
            <div><span :class="sendCodeWait ? 'wait':''" @click="getCode()">{{codeMessage}}</span></div>
        </div>
        <div class="input-item password">
            <i :class="showPassword ? 'visible' : 'invisible'" @click="showPassword = !showPassword"></i>
            <input :type="showPassword ? 'text' : 'password'" placeholder="输入密码" v-model="accountInfo.password"/>
        </div>
        <div class="login">
            <button class="register-btn" :class="registerCanDo ? '' : 'register-brn-not-click'" @click="doRegister">立即注册</button>
        </div>
        <div class="register"><a href="${pageContext.request.contextPath}/student/views/login">已有账号，直接登录</a></div>
        <div class="bottom">
            <span><button class="btn-ting" @click="applyAudition(4)">免费试听</button></span>
            <span><button class="btn-ce" @click="applyAudition(5)" >免费测评</button></span>
        </div>
    </div>
    <poohoo-apply-audition :apply-type="applyType" ref="applyAudition"></poohoo-apply-audition>
</div>
</body>
<%@ include file="tpl-apply-audition.jspf" %>
<script src="${pageContext.request.contextPath }/dep/jquery/jquery.md5.js"></script>
<script src="${pageContext.request.contextPath }/src/student/js/register.js?version=${_dt}"></script>
</html>
