var app = new Vue({
    el: '#app',
    data: {
        accountInfo: {
            account: '',
            password: ''
        },
        showPassword: false,
        // 4试听 5测试
        applyType:4 
    },
    computed: {
        loginCanDo: function () {
            if (this.accountInfo.account && this.accountInfo.password) {
                return true;
            }
        }
    }
    ,
    created: function () {

    },
    mounted: function () {

    },
    methods: {
        doLogin: function () {
            /*if (!this.loginCanDo) {
                return;
            }*/
            AjaxClient.post({
                url: contextPath + '/stu/user/login',
                data: {account: this.accountInfo.account, password: $.md5(this.accountInfo.password)},
                success: function (resp) {
                    if (resp.code == '0') {
                        location.href = contextPath + "/student/views/study";
                    } else if (resp.code == '20001') {
                        message.error('用户名密码不匹配');
                    } else {
                        message.error('用户不存在');
                    }
                }
            });
        },
        applyAudition: function (applyType) {
        	this.applyType=applyType;
            this.$refs.apply_Audition.show();
        }
    }
});