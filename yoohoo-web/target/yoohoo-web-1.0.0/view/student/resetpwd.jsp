<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--重置密码--%>
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
            <button class="register-btn" :class="registerCanDo ? '' : 'register-brn-not-click'" @click="doRegister">重置密码</button>
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
<script>
    var app = new Vue({
        el: '#app',
        data: {
            accountInfo: {
                msisdn: '',
                password: '',
                code: ''
            },
            showPassword: false,
            codeMessage: '获取验证码',
            sendCodeWait: false,
            tipMessage:{
                yan_zheng_ma_wu_xiao: '验证码无效, 请重新发送',
                fu_wu_qi_error: '服务器异常, 稍后重试!'
            },
            // 4试听 5测试
            applyType:4
        },
        computed: {
            registerCanDo: function () {
                if (!this.accountInfo.msisdn || !this.accountInfo.password || !this.accountInfo.code) {
                    return false;
                }
                return true;
            }
        },
        created: function () {

        },
        mounted: function () {

        },
        methods: {
            doRegister: function () {
                if (!this.registerCanDo) {
                    return;
                }
                var me = this;
                AjaxClient.post({
                    url: contextPath + '/stu/user/register',
                    data: {
                        msisdn: me.accountInfo.msisdn,
                        password: $.md5(me.accountInfo.password),
                        code: me.accountInfo.code
                    },
                    success: function (resp) {
                        if (resp.code == '0') {
                            location.href = contextPath + "/student/views/study";
                        } else if (resp.code == '20003') {
                            // 验证码无效
                            message.warn(me.tipMessage.yan_zheng_ma_wu_xiao);
                        } else {
                            // 注册失败
                            message.error(me.tipMessage.fu_wu_qi_error);
                        }
                    }
                });
            },
            applyAudition: function (applyType) {
                this.applyType=applyType;
                this.$refs.applyAudition.show();
            },
            getCode: function () {
                if (codeWait.time > 0) {
                    return;
                }
                if (!this.accountInfo.msisdn) {
                    layer.tips('请输入', '#msisdn');
                    return;
                }
                var me = this;
                AjaxClient.get({
                    url: contextPath + '/stu/user/sendVerification',
                    data: {phone: this.accountInfo.msisdn,type:2},
                    success: function (resp) {
                        if (resp.code == '0') {
                            codeWait.start();
                        }else if (resp.code == '20006')
                        {
                        	message.error(resp.message);
                        } else {
                            message.error(me.tipMessage.fu_wu_qi_error);
                        }
                    }
                });
            }
        }
    });

    var codeWait = {
        time: 0,
        start: function () {
            if (app.sendCodeWait) {
                return;
            }
            this.time = 60;
            app.sendCodeWait = true;
            app.codeMessage = this.time + "s后重新获取";
            this.timeOut();
        },
        end: function () {
            app.sendCodeWait = false;
            app.codeMessage = "获取验证码";
        },
        timeOut: function () {
            setTimeout(function () {
                codeWait.time--;
                app.codeMessage = codeWait.time + "s后重新获取";
                if (codeWait.time >= 0) {
                    codeWait.timeOut();
                } else {
                    codeWait.end();
                }
            }, 1 * 1000);
        }
    };
</script>
</html>
