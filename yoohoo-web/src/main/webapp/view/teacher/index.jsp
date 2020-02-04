<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%----%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/teacher/css/index.css?version=${_dt}">
    <%@ include file="../student/import.jsp" %>
</head>
<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<input id="type" type="hidden" value="${sessionScope.LOGIN_TEACHER.type}">
<div id="wrap" class="main clearfix">
    <div style="width: 90%;height: 620px;margin-left: 20px;margin-top: 50px;">
        <div style="width: 50%;height: 600px;float: left">
            <poohoo-calendar @change-date="changeDate"></poohoo-calendar>
        </div>
        <div style="width: 45%;height: 600px;margin-left:20px;float: left" v-if="type=='老师端'">
            <lesson-time :date="date"></lesson-time>

        </div>

    </div>
    <div style="width: 90%;margin-left: 20px;margin-top: 20px">
        <lesson-schedule :date="date"></lesson-schedule>
    </div>
</div>
<%@include file="tpl-lesson-time.jspf"%>
<%@include file="tpl-lesson-schedule.jspf"%>
<%@include file="tpl-calendar.jspf"%>
<%--<%@include file="tpl-lesson-time.jspf"%>--%>
<script type="text/javascript">
var app=new Vue({
    el: '#wrap',
    data: {
    	date:null,
        type:""
    },
    created: function () {
        this.type=$("#type").val();
    },
    mounted: function () {
        this.type=$("#type").val();
    },
    updated: function(){
    },
    methods: {
    	changeDate: function(date){
    		this.date=date;
    	}
    }
});
</script>
</body>
</html>
