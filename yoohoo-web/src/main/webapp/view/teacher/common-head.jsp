<%@ page pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common-head.css?version=${_dt}">
<div class="common-head" id="headApp">
    <div class="content">
        <div class="left">
            <i class="home-icon"></i>
            <span class="tab "   :class="tabIndex == 0 ? 'cur' : ''"  @click="toTeacherShechulde">我的课表</span>
            <span class="tab "   :class="tabIndex == 1 ? 'cur' : ''"  @click="toLessonLib">课程库</span>
            <span class="tab "   :class="tabIndex == 2 ? 'cur' : ''"  @click="toHowToUse">如何使用</span>
        </div>
        <div class="right">
            <div class="content">
            <!-- 
                <span class="notify-title">通知</span> <span class="notify-count">10</span>
              -->
                <span class="name">{{userId ? userName : '未登录'}}</span>
                <span class="head-portraits"
                      :class="'${sessionScope.LOGIN_TEACHER.name}' ? 'boy' : 'default'"
                      @click="showUserInfo($event)"></span>
                <div class="user-info" :style="infoStyle" v-show="showMenu" @click="stopEvent($event)" style="height: 115px;">
                    <p class="name" style="margin-bottom: 30px;">${sessionScope.LOGIN_TEACHER.name}</p>
                    <span class="line"></span>
                    <p class="logout" @click="logout">退出</p>
                </div>
                    <p style="margin-bottom: 15px; margin-left: 20px; color: #4db14d">${sessionScope.LOGIN_TEACHER.type}</p>
            </div>
        </div>
    </div>
</div>
<script>
    var headApp = new Vue({
        el: '#headApp',
        data: function(){
            return {
                // tabs:['study','lesson','zdetail'],
                tabs:['library','lesson','zdetail','Teacher-ViewVideo','index'],
                // tabIndex: 1,
                tabIndex: 1,
                showMenu: false,
                userId: '${sessionScope.LOGIN_TEACHER.teacherId}',
                userName: '${sessionScope.LOGIN_TEACHER.name}',
                infoStyle: ''
            }
        },
        created: function () {
            if (!this.userId) {
                window.location.href = '${pageContext.request.contextPath}/teacher/views/login';
                return;
            }
            var href = location.href;
            var ss=href.split("/");
            var temp='z'+ss[ss.length-2];
            if (temp==this.tabs[2]){
                this.tabIndex = 1;
            }
            if (temp==this.tabs[3]){
                 this.tabIndex = 1;
            }
            // if (href.indexOf(this.tabs[0]) > -1) {
            //     this.tabIndex = 0;
            // }else if (href.indexOf(this.tabs[1]) > -1) {
            //     this.tabIndex = 1;
            // }else{
            //      this.tabIndex = 0;
            // }
            if (href.indexOf(this.tabs[0]) > -1) {
                this.tabIndex = 0;
            }
            if (href.indexOf(this.tabs[1]) > -1) {
                this.tabIndex = 1;
            }
            if (href.indexOf(this.tabs[4]) > -1) {
                this.tabIndex = 0;
            }
        },
        mounted: function(){
            if(!this.userId){
                window.location.href = contextPath +"/teacher/views/login";
                return;
            }
            var me = this;
            $('body').click(function(){
                me.showMenu = false;
            });
        },
        methods: {
            logout: function(){
                window.location.href = '${pageContext.request.contextPath}/teacher/views/logout';
            },
            showUserInfo: function(e){
                if(!this.userId){
                    window.location.href = '${pageContext.request.contextPath}/teacher/views/login';
                    return;
                }
                this.infoStyle = 'left: ' + (e.clientX-$('.user-info').width()) + 'px;';
                this.showMenu = ! this.showMenu;
                e.stopPropagation();
            },
            stopEvent: function(e){
                e.stopPropagation();
            },
            toLessonLib:function () {
                window.location.href = '${pageContext.request.contextPath}/teacher/views/lesson/library';
            },
            toHowToUse:function () {
                window.location.href = 'http://tykj.nat100.top/yoohoo-web/index/class.html';
            },
            toTeacherShechulde:function () {
                window.location.href = '${pageContext.request.contextPath}/teacher/views/index';
            }
        }
    });
</script>
