<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/index.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-lib.css?version=${_dt}">
    <%@ include file="import.jsp" %>
</head>

<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<a name="page-top"></a>
<div id="lesson-page" v-cloak>
    <div id="q-div" class="query-zone">
        <div class="menu-box">
            <div class="menu">
						<span class="menu-item">
                    <label :class="currMenu == -1 ? 'cur' : ''" @click="clickTopMenu(-1, 0)">全部</label>
                </span>
                <span class="menu-item" v-for="(menu, index) in menus">
                    <label :class="currMenu == index ? 'cur' : ''"
                           @click="clickTopMenu(index, menu.id)"
                           @mouseover="showTopMenu(index)"
                           @mouseout="autoHideMenu">{{menu.name}}</label>
                    <ul v-show="menu.show"
                        @mouseout="autoHideMenu"
                        @mouseover="clearAutoHide">
                        <li v-for="(item, inx) in menu.items"
                            :title="item.name"
                            :class="(currMenu == index && currMenuSub == inx) ? 'cur' : ''"
                            @click="clickTopSubMenu(index, inx, item.id)">{{item.name}}</li>
                    </ul>
                </span>
            </div>
        </div>
        <div class="search-box">
            <img class="search-fdj" src="${pageContext.request.contextPath }/src/student/img/fdj.png">
            <input type="text" class="search-txt" placeholder="搜索课程名" v-model="lessonName" />
            <button class="search-btn" type="submit" @click="query(1); page.pageNo=1;">搜索</button>
        </div>
    </div>
    <div id="wrap" class="main1 lesson-warp">
        <i class="top-left-bg"></i>
        <i class="top-right-bg"></i>
        <div class="top-div"></div>
        <div v-if="hasData">
            <%--<div class="zlesson-info" v-for="(lesson,index) in lessonList" v-if="lesson.chapterList && lesson.chapterList.length > 0">--%>
                <div class="zlesson-info" v-for="(lesson,index) in lessonList">
                <div class="title" @click="goLessonDetail(lesson)">
                    <img src="/src/student/img/v-line.png">
                    <span title="点击查看课程详情"> {{lesson.title}}</span>
                    <span class="more"> 查看课程详情</span>
                </div>
                <%--<div v-if="null != lesson.chapterList" class="chapter-list">--%>
                    <%--<div v-for="chapter in lesson.chapterList" class="chapter" :id="chapter.chapterId">--%>
                        <%--<span @mouseover="showTips(chapter, $event)" @mouseout="hiddTips(chapter, $event)">{{chapter.title}}</span>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <div class="h5"></div>
            <div class="page">
                <a href="javascript:;" class="prev" v-show="pageInfo.hasPrev" @click="query(--page.pageNo)">&lt;</a>

                <label v-for="item in pageInfo.items">
                    <a href="javascript:;" :class="page.pageNo == item.page ? 'active' : ''"
                       @click="if(item.page == 0){return};page.pageNo=item.page;query(page.pageNo);"
                       v-if="item.click">{{item.text}}</a>
                    <span v-else>{{item.text}}</span>
                </label>

                <a href="javascript:;" class="next" v-show="pageInfo.hasNext" @click="query(++page.pageNo)">&gt;</a>
            </div>
        </div>
        <div v-else class="lesson-list-none">
            <div>没有查询到课程！</div>
        </div>
    </div>
    <div v-if="null != showTipchapter" v-show="showtips" class="layui-layer-tips layer-anim" id="show_tips_div" :style="'position: absolute; width: 210px;' + tipStyle">
        <div class="layui-layer-content" style="background-color: rgb(53, 149, 204);">
            {{showTipchapter.title}}<br>{{showTipchapter.introduce}}
            <i class="layui-layer-TipsG layui-layer-TipsT" style="border-right-color: rgb(53, 149, 204);"></i>
        </div>
        <span class="layui-layer-setwin"></span>
    </div>
</div>
<script type="text/javascript">
    $("#menu-div").mouseleave(function() {
        $("#menu-div").css("display", "none");
    });
    var app = new Vue({
        el: '#lesson-page',
        data: {
            options: [{
                key: "",
                name: "全部课程"
            }, {
                key: "1",
                name: "系统课程"
            }, {
                key: "2",
                name: "拓展课程"
            }],
            choseVal: "0",
            lessonName: "",
            showChapterMenu: false,
            lessonList: null,
            selectedChapter: {},
            selectLesson: {},
            showTipchapter: null,
            showStudentApplyDialog: false,
            studentApplyType: 4, //1报名 2请假  3旁听 4试听 5测试  6补课  7补课  8插班
            applyStatus: {},
            showtips: false,
            tipStyle: '',
            currMenu: -1,
            currMenuSub: -1,
            autoHideMenuIndex: null,
            menus: [{
                show: false,
                name: '拓展课',
                id: 1001,
                items: [{
                    name: '一年级上册',
                    id: 1004
                }, {
                    name: '二年级上册',
                    id: 1005
                }, {
                    name: '三年级上册',
                    id: 1006
                }]
            },
                {
                    show: false,
                    name: '系统课',
                    id: 1002,
                    items: [{
                        name: '四年级上册',
                        id: 1007
                    }, {
                        name: '五年级上册',
                        id: 1008
                    }, {
                        name: '六年级上册',
                        id: 1009
                    }]
                },
                {
                    show: false,
                    name: '公开课',
                    id: 1003,
                    items: [{
                        name: '七年级上册',
                        id: 1010
                    }, {
                        name: '八年级上册',
                        id: 1011
                    }, {
                        name: '九年级上册',
                        id: 1012
                    }]
                }
            ],
            page: {
                pageNo: 1,
                limit: 36,
                more: true
            },
            pageInfo: {}
        },
        created: function() {
            this.query(this.page.pageNo);
            this.menuData();
        },
        mounted: function() {},
        updated: function() {},
        computed: {
            hasData: function() {
                var result = false;
                if(!this.lessonList) {
                    return result;
                }
                for(var i = 0, len = this.lessonList.length; i < len; i++) {
                    if(this.lessonList[i].chapterList && this.lessonList[i].chapterList.length > 0) {
                        result = true;
                    }
                }
                return result;
            }
        },
        methods: {
            menuData: function() {
                var me = this;
                $.get(
                    contextPath + '/stu/user/menuData',
                    function(resp) {
                        me.menus = JSON.parse(resp).data.menus;
                    });
            },
            query: function(pageNo) {
                if(pageNo == 0) {
                    return;
                }
                if(!pageNo) {
                    pageNo = 1;
                }
                var me = this;
                AjaxClient.post({
                    url: contextPath + '/teach/user/queryLessonList',
                    data: {
                        "lessonName": me.lessonName,
                        "categoryId": me.choseVal,
                        pageNo: pageNo,
                        limit: me.page.limit
                        /* type:1*/
                    },
                    success: function(resp) {
                        if(resp.code === '0') {
                            me.pageInfo = resp.data.page;
                            if(me.pageInfo && me.pageInfo.data) {
                                me.lessonList = me.pageInfo.data;
                            } else {
                                me.lessonList = [];
                            }
                            window.location.href = "#page-top";
                        } else {
                            message.error('服务器异常, 请稍后重试提交申请!');
                        }
                    }
                });
            },
            selectChange: function(option) {
                this.choseVal = option;
                this.query();
            },
            goLessonDetail: function(lesson) {
                location.href = contextPath + "/teacher/views/detail/" + lesson.lessonId;
            },
            hidChapterMenu: function() {
                this.showChapterMenu = false
            },
            closeDialog: function(flag) {
                this.showStudentApplyDialog = flag;
            },
            showTips: function(chapter, event) {
                var me = this;
                me.showTipchapter = chapter;
                var obj = $(event.target);
                var offset = obj.offset();
                me.showtips = true;
                me.$nextTick(function() {
                    // 界面未显示出来获取不到 $('#show_tips_div') 的高度. 导致坐标计算错误.
                    me.tipStyle = 'top: ' + (offset.top - $('#show_tips_div').height() - 10) + 'px; left: ' + (offset.left) + 'px;';
                });
            },
            hiddTips: function(chapter, event) {
                var me = this;
                me.showtips = false;
                me.showTipchapter = null;
            },
            showTopMenu: function(key) {
                this.clearAutoHide();
                this.hideTopMenu();
                Vue.set(this.menus[key], 'show', true);
            },
            hideTopMenu: function() {
                for(var i = 0, len = this.menus.length; i < len; i++) {
                    Vue.set(this.menus[i], 'show', false);
                }
            },
            autoHideMenu: function() {
                var _this = this;
                this.autoHideMenuIndex = setTimeout(function() {
                    _this.hideTopMenu();
                }, 0.5 * 1000);
            },
            clearAutoHide: function() {
                clearTimeout(this.autoHideMenuIndex);
            },
            clickTopMenu: function(index, id) {
                this.currMenu = index;
                this.currMenuSub = -1;
                this.choseVal = id;
                this.hideTopMenu();
                this.page.pageNo = 1;
                this.query(1);
            },
            clickTopSubMenu: function(index, inx, id) {
                this.currMenu = index;
                this.currMenuSub = inx;
                this.choseVal = id;
                this.hideTopMenu();
                this.page.pageNo = 1;
                this.query(1);
            }
        }
    });
</script>
</body>

</html>