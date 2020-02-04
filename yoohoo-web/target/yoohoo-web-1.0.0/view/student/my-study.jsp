<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!doctype html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <meta http-equiv="refresh" content="60">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/my-study.css?version=${_dt}">
    <%@ include file="import.jsp"%>
</head>
<body>
    <%@ include file="common-head.jsp" %>
    <div id="wrap" class="main" ref="main">
        <i class="top-left-bg"></i>
        <i class="top-right-bg"></i>
        <div class="top-tab" ref="tab">
            <%--<span :class="tabs[0] === tabs[currentTabInx] ? 'cur' : ''" @click="toTab1">我的课表</span>--%>
            <%--<span :class="tabs[1] === tabs[currentTabInx] ? 'cur' : ''" @click="toTab2">在学课程</span>--%>
        </div>
        <div class="content" ref="content">
            <poohoo-class-schedule ref="schedule" v-if="tabs[0] === tabs[currentTabInx]"></poohoo-class-schedule>
            <poohoo-lesson-online v-if="tabs[1] === tabs[currentTabInx]"></poohoo-lesson-online>
        </div>
        <div class="bottom" ref="bottom" v-if="tabs[0] === tabs[currentTabInx]">
            <i class="house"></i>
            <i class="car"></i>
            <div class="btns">
                <div>
                    <span class="i-last-week" @click="lastWeek">&nbsp;</span>
                    <span>上一周</span>
                    &nbsp;&nbsp;&nbsp;
                    <span>下一周&nbsp;</span>
                    <span class="i-next-week" @click="nextWeek">&nbsp;</span>
                </div>
            </div>
        </div>
    </div>
    <%@include file="tool-service.jsp"%>
</body>
<%@include file="tpl-no-data.jspf"%>
<%@include file="tpl-recommend-window.jspf"%>
<%@include file="tpl-schedule-card.jspf"%>
<%@include file="tpl-class-schedule.jspf"%>
<%@include file="tpl-my-lesson-detail.jspf"%>
<%@include file="tpl-lesson-online.jspf"%>
<script>
    new Vue({
        el: '#wrap',
        data: {
            tabs: ['schedule', 'lesson'],
            currentTabInx: ${tabInx}
        },
        created: function () {

        },
        mounted: function () {
            this.initView();
        },
        methods: {
            initView: function(){
                var me = this;
                this.$nextTick(function () {
                    // 计算高度
                    var height = $(me.$refs.main).height() - $(me.$refs.tab).height() - $(me.$refs.bottom).height();
                    if (height) {
                        $(me.$refs.content).css("min-height", height + 'px');
                    }
                    $('.list').css("min-height", $(me.$refs.content).height() - 41 + "px");
                });
            },
            lastWeek: function () {
                this.$refs.schedule.lastWeek();
            },
            nextWeek: function () {
                this.$refs.schedule.nextWeek();
            },
            toTab1: function () {
                if (this.currentTabInx == 0) {
                    return;
                }
                window.open(contextPath + "/student/views/study?tabInx=0");
               /* location.href = contextPath + "/student/views/study?tabInx=0";*/
            },
            toTab2: function () {
                if (this.currentTabInx == 1) {
                    return;
                }
                window.open(contextPath + "/student/views/myLesson");
               /* location.href = contextPath + "/student/views/myLesson";*/
            }
    }
    });
</script>
</html>
