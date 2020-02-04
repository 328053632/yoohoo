<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--首页--%>
    <title>雅英</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/login.css?version=${_dt}">
    <link href="${pageContext.request.contextPath }/src/student/css/style.css" rel="stylesheet" type="text/css" />
    <%@ include file="../student/import.jsp" %>
    <style>
        .selectType{
            width: 70px;
            margin-top: 1px;
            margin-right: 250px;
        }
        .SelectTip{
            width: 43%;
            float: left;
            margin-top: 11px;
        }
    </style>
</head>
<body>
<div style="float:left">
    <div>
       	<div>
	        <span><a class="buy-btn" style="background: #fff;color:#16dabe;text-decoration:none;" 
	        		href="http://www.yoohooabc.com/student/views/login">学生登陆</a></span>
        </div>
        <div class="bn_img wow" data-wow-delay="0.3s" style="visibility: visible; animation-delay: 0.3s; animation-name: fadeInUp;"></div>
    </div>
</div>
    <div id="app">
        <div class="login-frame">
            <h2 class="title">欢迎来到YoohooABC</h2>
            <div class="input-item"><input type="text" placeholder="输入手机号码" v-model="accountInfo.account"/></div>
            <div class="input-item password">
                <i :class="showPassword ? 'visible' : 'invisible'" @click="showPassword = !showPassword"></i>
                <input :type="showPassword ? 'text' : 'password'" placeholder="输入密码" v-model="accountInfo.password"/>
            </div>
           <%-- <div class="forget-pwd"></div>--%>
            <div  class="SelectTip">请选择员工类型：
            </div>
            <div class="SelectTip" >
                <select  id="SelectteacherType"  class="selectType" >
                <option value="1">老师</option>
                <option value="2">课长</option>
                <option value="3">电教</option>
                </select>
            </div>
            <div class="login"><button class="login-btn" :class="loginCanDo ? '' : 'login-brn-not-click'" @click="doLogin">登录</button></div>
           <%-- <div style="margin-top: 60px; text-align: center; font-size: 12px; color: #999999;">
                <span>如有疑问请联系: 18212345678</span>
            </div>--%>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath }/dep/jquery/jquery.md5.js"></script>
<script src="${pageContext.request.contextPath }/src/teacher/js/login.js?version=${_dt}"></script>
<script type="text/javascript">
	function showLogin(){
		$("#app").show();
	}
</script>
</html>
