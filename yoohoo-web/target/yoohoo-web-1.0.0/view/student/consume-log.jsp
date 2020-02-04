<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="v-bind" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--消费记录页面--%>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/consume-log.css?version=${_dt}">
    <%@ include file="import.jsp" %>
</head>
<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<div id="consumeLogApp" class="main consume-log" v-cloak>
    <div class="title">消费记录</div>
    <div class="content">
        <div class="top-bar">
            <div class="m-item">
                <div class="select-box">
                    <input-select :selected="choseVal" :options="options" @select-change="selectChange"> </input-select>
                </div>
            </div>
            <div class="m-item">
                <label>时间:</label>
                <span>
                    <input id="start-time" v-model="queryArgs.stime" type="text" class="input-common" placeholder="开始时间" readonly/>
                    <i @click="transform('start-time')"></i>
                </span>
                —
                <span>
                    <input id="end-time" v-model="queryArgs.etime" type="text" class="input-common" placeholder="结束时间" readonly/>
                    <i @click="transform('end-time')"></i>
                </span>
            </div>
        </div>
        <idv class="content-table">
            <div class="row head">
                <span class="column2 mg-r">时间</span>
                <span class="column1 mg-r">课程名称</span>
                <span class="column1 mg-r">课时名称</span>
                <span class="column1-4 mg-r">班级</span>
                <span class="column1-back mg-r">课堂录像</span>
                <span class="column-5 mg-r">状态</span>
                <span class="column-5">花费</span>
            </div>
            <div class="row bg1" v-for="(item, inx) in logList" :class="inx % 2 == 0 ? 'bg1' : 'bg2'">
                <span class="column2">{{item.timeStr}}</span>
                <span class="column1">{{item.lessonName}}</span>
                <span class="column1">{{item.chapterName}}</span>
                <span class="column1-4">{{item.className}}</span>
                <span class="column1-back" @click="test_back(item)" id="video_back" >回放</span>
                <span class="column-5">{{item.typeName}}</span>
                <span class="column-5">{{item.amount}}</span>
            </div>
            <div class="row" v-show="!page.hasMore && logList.length > 0">
                <span class="column1" style="text-align: center;">没有更多数据..</span>
            </div>
            <div v-show="logList.length == 0" class="no-data">
                <span>暂无数据..</span>
            </div>
        </idv>
    </div>
</div>
<%@include file="tpl-input-select.jspf"%>
<%@include file="tool-service.jsp" %>
</body>
<script>
    new Vue({
        el: '#consumeLogApp',
        data: function () {
            return {
                queryArgs: {
                    stime: null,
                    etime: null,
                    start: 0,
                    limit: 20,
                    type: 0
                },
                logList: [],
                page: {
                    no: 1,
                    size: 20,
                    hasMore: true
                },
                options: [{key:"0",name:"全部课程"},{key:"1",name:"系统课程"},{key:"2",name:"拓展课程"},{key:"3",name:"公开课程"}],
                choseVal: "0",
            }
        },
        watch: {

        },
        created: function () {
            this.loadLogs();
            // this.defaultData();
        },
        mounted: function () {
            this.initLayDate();
            this.createPageScroll();
        },
        methods: {
            loadLogs: function () {
                var me = this;
                AjaxClient.get({
                    url: contextPath + "/stu/user/consumerLog/" + this.queryArgs.type,
                    data: this.queryArgs,
                    success: function (resp) {
                        if (me.queryArgs.start == 0) {
                            me.logList = resp.data.logList;
                        } else {
                            me.logList.concat(resp.data.logList);
                        }
                        me.updatePage(resp.data.total);
                    }
                });
            },
            updatePage: function (total) {
                if (this.page.size * this.page.no >= total) {
                    Vue.set(this.page, 'hasMore', false);
                } else {
                    Vue.set(this.page, 'hasMore', true);
                }
            },
            initLayDate: function () {
                var me = this;
                laydate.render({
                    elem: '#start-time',
                    done: function (value, date) {
                        Vue.set(me.queryArgs, 'stime', value);
                        if(me.queryArgs.stime && me.queryArgs.etime){
                            me.initPageAndParam();
                            me.loadLogs();
                        } else if(!me.queryArgs.stime && !me.queryArgs.etime){
                            me.initPageAndParam();
                            me.loadLogs();
                        }
                    }
                });

                laydate.render({
                    elem: '#end-time',
                    done: function (value, date) {
                        Vue.set(me.queryArgs, 'etime', value);
                        if(me.queryArgs.stime && me.queryArgs.etime){
                            me.initPageAndParam();
                            me.loadLogs();
                        } else if(!me.queryArgs.stime && !me.queryArgs.etime){
                            me.initPageAndParam();
                            me.loadLogs();
                        }
                    }
                });
            },
            selectChange:function(option){/*下拉列表改变值*/
                this.choseVal=option;
                this.queryArgs.type = option;
                this.initPageAndParam();
                this.loadLogs();

            },
            initPageAndParam: function(){
              this.page.no = 1;
              this.queryArgs.start = 0;
            },
            createPageScroll: function () {
                var me = this;
                this.$nextTick(function () {
                    $(window).scroll(function () {
                        var totalHeight = parseFloat($(this).height()) + parseFloat($(this).scrollTop());
                        var documentHeight = $(document).height();
                        if ((totalHeight + 30) >= documentHeight) {
                            if (me.page.hasMore) {
                                me.page.no = me.page.no + 1;
                                me.queryArgs.start = (me.page.no - 1) * me.page.size;
                                me.loadLogs();
                            }
                        }
                    });
                });
            },
            transform: function (tagId) {
                $('#' + tagId).click();
            },
            test_back:function (item) {
             layer.open({
                type: 2,
                title: '录像回放',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area : ['750px' , '500px'],
                content: '/view/student/video-replay.jsp?id='+item.scheduleId
            });
        },
            defaultData: function(){
                this.logList = [
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                    {timeStr:'', lessonName: ''},
                ];
            }
        }
    });

</script>
</html>
