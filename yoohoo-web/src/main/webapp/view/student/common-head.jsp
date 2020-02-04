<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common-head.css?version=${_dt}">
<div class="common-head" id="headApp">
    <div class="content">
        <div class="left">
            <i class="home-icon" @click="toLessonLib"></i>
            <span class="tab" :class="tabIndex == 0 ? 'cur' : ''" @click="toMyStudy">我的课表</span>
            <%--新加--%>
            <%--<span :class="tabs[1] === tabs[currentTabInx] ? 'cur' : ''" @click="toTab2">在学课程</span>--%>
            <span class="tab" :class="tabIndex == 1 ? 'cur' : ''" @click="toTab2">在学课程</span>
            <span class="tab" :class="tabIndex == 2 ? 'cur' : ''" @click="toLessonLib">课程库</span>
            <span class="tab" :class="tabIndex == 3 ? 'cur' : ''" @click="toClass">如何上课</span>
            <span class="tab "   :class="tabIndex == 4 ? 'cur' : ''"  @click="toHowToUse">如何使用</span>
        </div>
        <div class="right">
            <div class="content">
                <!--
                <span class="notify-title">通知</span> <span class="notify-count">10</span>
              -->
                <span class="name">{{userId ? userName : '未登录'}}</span>
                <span class="head-portraits" :class="'${sessionScope.LOGIN_STUDENT.name}' ? 'boy' : 'default'" @click="showUserInfo($event)">
                    <i class="hint" v-show="studentInfo.balance < 100"></i>
                </span>

                <div class="user-info" :style="infoStyle" v-show="showMenu" @click="stopEvent($event)">
                    <p class="name">${sessionScope.LOGIN_STUDENT.name}</p>
                    <p class="p-balance">
                        <i class="hint" v-show="studentInfo.balance < 100">&nbsp;</i>
                        <i class="sunflower">&nbsp;</i>
                        <span class="balance">{{studentInfo.balance || 0}}个</span>
                        <span class="history">(累计赠送{{studentInfo.presentBalance || 0}}个，赚取{{studentInfo.earbBalance || 0}}个)</span>
                    </p>
                    <span class="line"></span>
                    <p class="account" @click="toAccount">个人信息</p>
                    <p class="log" @click="toConsume">消费记录</p>
                    <span class="line"></span>
                    <p class="logout" @click="logout">退出</p>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var headApp = new Vue({
        el: '#headApp',
        data: function() {
            return {
                tabs: ['study','myLesson','library','zdetail','zstudied'],
                //detail没有学习的课程,studied学习了的
                tabIndex: '1',
                showMenu: false,
                userId: '${sessionScope.LOGIN_STUDENT.userId}',
                userName: '${sessionScope.LOGIN_STUDENT.name}',
                studentInfo: {
                    balance: 0,
                    presentBalance: 0,
                    earbBalance: 0
                },
                infoStyle: ''
            }
        },
        created: function() {
            // 未登录跳转登录页面
            if(!this.userId) {
                window.location.href = '${pageContext.request.contextPath}/student/views/login';
                return;
            }
            var href = location.href;
            var ss=href.split("/");
            var temp='z'+ss[ss.length-2];
            if (temp==this.tabs[3]){
                this.tabIndex = 2;
            }
            if (temp==this.tabs[4]){
                this.tabIndex = 1;
            }
            if(href.indexOf(this.tabs[0]) > -1)
            {
                this.tabIndex = 0;
            }
            if (href.indexOf(this.tabs[1]) > -1){
                this.tabIndex = 1;
            }
            if (href.indexOf(this.tabs[2]) > -1){
                this.tabIndex = 2;
            }


            // if(href.indexOf(this.tabs[0]) > -1) {
            //     this.tabIndex = 0;
            // } else if(href.indexOf(this.tabs[1]) > -1) {
            //     this.tabIndex = 1;
            // } else {
            //     if (href.indexOf(this.tabs[2]) > -1)
            //         this.tabIndex = 2;
            // }

            this.queryStudentInfo();
        },
        mounted: function() {
            var me = this;
            $('body').click(function() {
                me.showMenu = false;
            });
        },
        methods: {
            queryStudentInfo: function() {
                var me = this;
                $.get(contextPath + "/stu/user/loginStudentInfo", {}, function(resp) {
                    me.studentInfo = JSON.parse(resp).data.studentInfo;
                    me.userName = me.studentInfo.name;
                })
            },
            toMyStudy: function() {
                window.location.href = '${pageContext.request.contextPath}/student/views/study';
            },
            toLessonLib: function() {
                window.location.href = '${pageContext.request.contextPath}/student/views/lesson/library';
            },
            toClass:function(){
                window.location.href='../../index/class.html';
            },
            toHowToUse:function () {
                window.location.href = 'http://tykj.nat100.top/yoohoo-web/index/class.html';
            },
            toAccount: function() {
                this.showMenu = !this.showMenu;
                window.location.href = '${pageContext.request.contextPath}/student/views/account';
            },
            toConsume: function() {
                this.showMenu = !this.showMenu;
                window.location.href = '${pageContext.request.contextPath}/student/views/log/consume';
            },
            logout: function() {
                window.location.href = '${pageContext.request.contextPath}/student/views/logout';
            },
            showUserInfo: function(e) {
                if(!this.userId) {
                    window.location.href = '${pageContext.request.contextPath}/student/views/login';
                    return;
                }
                this.queryStudentInfo();
                this.infoStyle = 'left: ' + (e.clientX - $('.user-info').width()) + 'px;';
                this.showMenu = !this.showMenu;
                e.stopPropagation();
            },
            stopEvent: function(e) {
                e.stopPropagation();
            },
            toTab2: function() {
                window.location.href = '${pageContext.request.contextPath}/student/views/myLesson';
            }
        }
    });
</script>