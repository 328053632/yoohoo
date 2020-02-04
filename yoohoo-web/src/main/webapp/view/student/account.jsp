<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--学生个人中心--%>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/account.css?version=${_dt}">
    <%@ include file="import.jsp" %>
</head>
<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<div id="wrap" class="main account" style="display: none;" v-show="loadFinish">
    <div class="title">个人信息</div>
    <div class="info">
        <div class="line">
            <span class="zlabel">英文名+学号:</span>
            <span class="content" v-if="!canSave"><input type="text" v-model="name" readonly/></span>
            <span class="content" v-if="canSave"><input type="text" v-model="name"/></span>
        </div>
        <div class="line">
            <span class="zlabel">中文名:</span>
            <span class="content" v-if="!canSave"><input type="text" v-model="enName" readonly/></span>
            <span class="content" v-if="canSave"><input type="text" v-model="enName"/></span>
        </div>
        <div class="line">
            <span class="zlabel">出生日期:</span>
            <span class="content">
                <input type="text" v-if="!canSave" v-model="birthday" readonly/>
                <input type="text" v-if="canSave" v-model="birthday"/>
                <i class="calendar"></i>
            </span>
        </div>
        <div class="line">
            <span class="save-btn" v-show="canSave" @click="saveChildInfo">保存信息</span>
        </div>
    </div>
</div>
<%@include file="tool-service.jsp" %>
</body>
<script>
    new Vue({
        el: '#wrap',
        data: {
            name: '${sessionScope.LOGIN_STUDENT.name}',
            enName: '${sessionScope.LOGIN_STUDENT.enName}',
            birthday: '${sessionScope.LOGIN_STUDENT.birthday}',
            msisdn: '${sessionScope.LOGIN_STUDENT.msisdn}',
            loadFinish: false,
            canSave: false
        },
        mounted: function () {
            this.loadFinish = true;
            this.canSave = (!this.name || this.name == this.msisdn);
        },
        methods: {
            saveChildInfo: function () {
                var info = {
                    name: this.name,
                    enName: this.enName,
                    birthday: this.birthday
                }

                var me = this;
                AjaxClient.post({
                    url: contextPath + '/stu/user/addChild',
                    data: info,
                    success: function (resp) {
                        if (resp.code == '0') {
                            message.info("保存信息成功, 孩子信息可联系我们修改.");
                            setTimeout(function () {
                                location.reload();
                            }, 3 * 1000)
                        } else {
                            message.error("系统异常, 请稍后重试!")
                        }
                    }
                });
            }
        }
    });
</script>
</html>
