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
            fu_wu_qi_error: '服务器异常, 稍后重试!',
            zhu_ce_cheng_gong:'注册成功，稍后会有人联系您！'
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
                        location.href = contextPath + "/student/views/addChild";
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
                data: {phone: this.accountInfo.msisdn,type:1},
                success: function (resp) {
                    if (resp.code == '0') {
                        codeWait.start();
                    } else if (resp.code == '20005')
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