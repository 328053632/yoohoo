<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--添加孩子--%>
    <title>雅英</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/add-child.css?version=${_dt}">
    <%@ include file="import.jsp" %>
</head>
<body>
<div id="addChildApp">
    <div class="login-frame">
        <h2 class="title">添加孩子信息</h2>
        <div class="info">
            <div class="line">
                <span class="label">孩子姓名:</span>
                <span class="content"><input id="name" type="text" v-model="childInfo.name" class="verify"
                                             verify-data="请输入"/></span>
            </div>
            <div class="line">
                <span class="label">英文名:</span>
                <span class="content"><input id="enname" type="text" v-model="childInfo.enName" class="verify"
                                             verify-data="请输入"/></span>
            </div>
            <div class="line">
                <span class="label">出生日期:</span>
                <span class="content">
                        <input id="birthday" type="text" readonly v-model="childInfo.birthday" class="verify"
                               verify-data="请输入"/>
                        <i class="calendar"></i>
                    </span>
            </div>
        </div>
        <span class="btn" @click="addChildInfo">确认</span>
        <p class="link"><a href="#" @click="toHome">跳过</a></p>
    </div>
</div>
</body>
<%@ include file="tpl-apply-audition.jspf" %>
<script>
    var addChildApp = new Vue({
        el: '#addChildApp',
        data: function () {
            return {
                childInfo: {}
            }
        },
        created: function () {
            this.loadChildInfo();
        },
        mounted: function () {
            this.initLayDate();
        },
        methods: {
            loadChildInfo: function () {
                var me = this;
                AjaxClient.get({
                    url: contextPath + "/stu/user/loadChildInfo",
                    success: function (resp) {
                        var info = resp.data.childInfo;
                        if (info) {
                            me.childInfo = {
                                name: info.name,
                                enName: info.enName,
                                birthday: info.birthday
                            }
                        }
                    }
                });
            },
            initLayDate: function () {
                var me = this;
                laydate.render({
                    elem: '#birthday',
                    done: function (value, date) {
                        Vue.set(me.childInfo, 'birthday', value);
                    }
                });
            },
            examineApplyInfo: function () {
                var $verify = $('.verify');
                for (var i = 0, len = $verify.length; i < len; i++) {
                    if (!$($verify[i]).val()) {
                        var message = $($verify[i]).attr('verify-data');
                        var id = '#' + $($verify[i]).attr('id');
                        layer.tips(message, id);
                        return false;
                    }
                }
                return true;
            },
            addChildInfo: function () {
                if (!this.examineApplyInfo()) {
                    return;
                }
                var me = this;
                AjaxClient.post({
                    url: contextPath + '/stu/user/addChild',
                    data: this.childInfo,
                    success: function (resp) {
                        if (resp.code == '0') {
                            message.info("添加信息成功, 孩子信息可联系我们修改.")
                            setTimeout(function () {
                                window.location.href = '${pageContext.request.contextPath}/student/views/lesson/library';
                            }, 3 * 1000)
                        } else {
                            message.error("系统异常, 请稍后重试!")
                        }
                    }
                });
            },
            toHome: function () {
                AjaxClient.post({
                    url: contextPath + '/stu/user/addChild',
                    data: this.childInfo,
                    success: function (resp) {
                        window.location.href = '${pageContext.request.contextPath}/student/views/lesson/library';
                    }
                });
            }
        }
    });
</script>
</html>
