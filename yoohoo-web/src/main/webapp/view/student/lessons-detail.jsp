<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <%--课程详情页--%>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        .chapter-list {
            display: inline-block;
            padding: 10px 30px;
            font-size: 18px;
        }

        .chapter-list .chapter {
            width: 147px;
            height: 40px;
            border-radius: 4px;
            float: left;
            cursor: pointer;
            margin: 0 2px 5px 0;
            background-image: url("../img/lesson-1.png");
            background-size: 100% 100%;
        }

        .z-recommend {
            margin: 30px 30px 0;
            color: #292929;
            font-size: 18px;
        }
        /*
        */

        .wrap {
            border: 1px solid red;
        }

        .clearfix {
            zoom: 1;
        }

        .clearfix:after {
            content: '.';
            display: block;
            visibility: hidden;
            height: 0;
            clear: both;
        }

        .z-div {
            margin: 30px;
            color: #292929;
            font-size: 18px;
        }

        .ImgOutside {
            width: 100px;
            height: 150px;
            float: left;
            margin-right: 10px;
            /*border: 1px solid #CCCCCC;*/
        }

        .Img {
            width: 100px;
            height: 100px;
            font-size:13px;
            display: block;
            border: 1px solid #CCCCCC;
        }

        .pCenter {
            text-align: center;
            color:#999999;
            font-size: 14px;
        }
        .mainstudent {
            width: 72%;
            min-height: 80%;
            max-width: 1200px;
            border-radius: 30px;
            background-color: #DFFAFB;
            margin: 28px auto;
            -webkit-box-shadow: 0 0 10px #80C7CB;
            box-shadow: 0 0 10px #80C7CB;
           /* position: relative;*/
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-detail.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-lib.css?version=${_dt}">
    <script src="${pageContext.request.contextPath }/src/student/js/TcPlayer-2.2.1.js"></script>
    <%@ include file="import.jsp"%>
    <script>
        var lessonId = '${requestScope.lessonId}';
    </script>
    <script>var voideurl</script>
</head>

<body class="html-bottom-bg">
<div id="lessonDetailApp" class="mainstudent lesson-detail">
    <i class="top-left-bg"></i>
    <i class="top-right-bg"></i>
    <div v-show="loadFinish" >
        <div class="base-info">
            <div class="cover">
                <div style="width: 400px;height: 200px;float: left;border: 1px solid #CCCCCC ;margin-top: 30px;margin-left: 50px" v-if="viveoshow==2">
                    没有视频
                </div>
                <div style="width: 400px;height: 200px;float: left;background-color: #CCCCCC;margin-top: 30px;margin-left: 50px" v-if="viveoshow==1">
                    <div id="videoMask" style="width: 460px;height: 280px;overflow: hidden;">
                        <div  id="closeVideo"></div>
                        <div id="videoContainer" ></div>
                    </div>
                </div>

            </div>
            <%--<div class="info">--%>
            <%--<p class="title" v-if="lessonInfo">{{lessonInfo.title}}</p>
            <p class="title" v-if="lessonInfo">课程特色：</p>
            &lt;%&ndash;<p class="time" style="word-wrap: break-word;word-break: break-all;overflow: hidden;padding-right: 30px">&ndash;%&gt;
            <p v-if="lessonInfo" class="time" style="word-wrap: break-word;word-break: break-all;overflow: hidden;padding-right: 30px">
                {{lessonInfo.times}}
            </p>--%>
            <%--</div>--%>
        </div>
        <div class="introduce-line">
            <span class="tip" v-if="lessonInfo"><i></i>{{lessonInfo.title}}</span>
            <span class="car"></span>
        </div>
        <div class="fit-object">
            <p>课程特色:</p>
            <div class="content" v-if="lessonInfo">
                {{lessonInfo.times}}
            </div>
        </div>
        <div class="fit-object">
            <p>学习建议</p>
            <div class="content" v-if="lessonInfo">
                {{lessonInfo.ageSection}}
            </div>
        </div>
        <div class="introduce">
            <p>课程描述</p>
            <div class="content" v-if="lessonInfo">
                {{lessonInfo.introduce}}
            </div>
        </div>
        <%--学生课程详情页--%>
        <div class="chapter-list">
            <%--学生在线课程--%>
            <div v-for="zitem in lessonChapterListArray"
                 class="chapter"
                 :id="zitem.chapterId"
                 @click="showMenu(zitem, $event)">
                        <span @mouseover="showTips(zitem, $event)"
                              @mouseout="ZhiddTips(zitem, $event)">{{zitem.title}}</span>
            </div>
        </div>

        <div class="z-recommend  clearfix" v-if="lessonTeacherListshow" >
            <p>推荐外教</p>
            <div class="ImgOutside" @click="teacherViewVideo(item)" v-for="item in lessonTeacherListArray">
                <img v-bind:src="'//file.yoohooabc.com/'+item.teacherImage" class="Img" v-bind:alt="item.name" title="点击进入详情页">
                <p class="pCenter">{{item.name}}</p>
            </div>
        </div>
        <div class="z-recommend  clearfix" v-if="lessonMasterTeacherListshow">
            <p>推荐课长</p>
            <div class="ImgOutside" @click="teacherViewVideo(item)"  v-for="item in lessonMasterTeacherListArray">
                <img v-bind:src="'//file.yoohooabc.com/'+item.teacherImage" class="Img" v-bind:alt="item.name" title="点击进入详情页">
                <p class="pCenter">{{item.name}}</p>
            </div>
        </div>

    </div>
    <%--课程鼠标放上显示--%>
    <div  v-show="showtips" class="layui-layer-tips layer-anim" id="show_tips_div" :style="'position: absolute; width: 180px;' + tipStyle">
        <div class="layui-layer-content" style="background-color: rgb(53, 149, 204);">
            {{showTipchapter.title}}+x:{{x}}+{{y}}<br> {{showTipchapter.introduce}}
            <i class="layui-layer-TipsG layui-layer-TipsT" style="border-right-color: rgb(53, 149, 204);"></i>
        </div>
        <span class="layui-layer-setwin"></span>
    </div>
    <%--课程鼠标放上显示结束--%>

    <%--申请测试旁听弹出框开始--%>
    <div id="menu-div" class="tip-menu" @mouseleave="hidChapterMenu" v-show="showChapterMenu">
        <div v-if="null != selectedChapter && null != selectedChapter.title" class="chapter-title">{{selectedChapter.title}}</div>
        <div class="menus">
            <div class="menu" @click="applyShiting">申请试听</div>
            <div class="menu" @click="applyPingce">申请测评</div>
            <div class="menu" @click="applyPangting">申请旁听</div>
        </div>
    </div>
    <%--申请测试旁听弹出框结束--%>
    <poohoo-no-data v-show="showNodata" :text="'没有找到对应的课程'"></poohoo-no-data>
    <student-apply-dialog :show-flag="showStudentApplyDialog" :apply-type="studentApplyType" :lesson-info="selectLesson" :chapter-info="selectedChapter" @close-dialog="closeDialog"></student-apply-dialog>
</div>
<%@include file="tool-service.jsp" %>
<%@include file="tpl-no-data.jspf" %>
<%@include file="tpl-student-apply-dialog.jspf"%>
</body>
<script>
    var voidurl ;


    $("#menu-div").mouseleave(function() {
        $("#menu-div").css("display", "none");
    });
    var lessonDetailApp = new Vue({
        el: '#lessonDetailApp',
        data: function() {
            return {
                lessonInfo: null,
                prefix: '',
                loadFinish: false,
                showNodata: false,
                lessonList: null,
                chapterList: [],
                showtips: false,
                selectLesson: {},
                selectedChapter: {},
                showStudentApplyDialog: false,
                studentApplyType: 4, //1报名 2请假  3旁听 4试听 5测试  6补课  7补课  8插班
                showTipchapter:{},
                count: 0,
                tipStyle: '',
                showChapterMenu: false,
                zlessonArray:{},
                lessonMasterTeacherListArray:[],/*课长*/
                lessonTeacherListArray:[],/*老师*/
                lessonChapterListArray:[],/*课程*/
                lessonMasterTeacherListshow:false,
                lessonTeacherListshow:false,
                lessonChapterListshow:false,
                viveoshow:1,
                applyStatus: {},
                x:"",
                y:"",
                zurl:""

            }
        },
        created: function() {
            var me = this;
            this.loadLessonInfo(me);

        },
        mounted: function() {
        },
        methods: {
            loadLessonInfo: function(me) {/*页面加载页*/
                var _this = this;
                AjaxClient.get({
                    url: contextPath + "/stu/lesson/detail/" + lessonId,
                    data: {},
                    success: function(resp) {

                        me.prefix = resp.data.prefix;
                        me.lessonInfo = resp.data.lessonInfo;
                        me.zurl= me.lessonInfo.coverUrl;
                        me.count = resp.data.count;
                        me.loadChapterList(lessonId)
                        me.$nextTick(function() {
                            if(!!me.lessonInfo) {
                                me.loadFinish = true;
                            } else {
                                me.showNodata = true;
                            }
                        })
                    }
                });
                $.ajax({
                    type:"get",
                    url:contextPath + "/stu/lesson/chapterList/"+lessonId,
                    dataType: "json",
                    data:{
                        type:1
                    },
                    complete:function () {

                    },
                    success: function(data) {
                        if(data.code==0){
                            // console.log(_this.chapterList = data.data.chapterList);
                            _this.zlessonArray=data.data.lessonDetailPack;
                            _this.lessonMasterTeacherListArray= _this.zlessonArray.lessonMasterTeacherList;//课程相关课长
                            // console.log(_this.lessonMasterTeacherListArray);
                            _this.lessonTeacherListArray= _this.zlessonArray.lessonTeacherList;//课程相关老师
                            _this.lessonChapterListArray= _this.zlessonArray.lessonChapterList;//课程相关章节
                            if(_this.lessonMasterTeacherListArray.length>0){
                                _this.lessonMasterTeacherListshow=true
                            }
                            if (_this.lessonTeacherListArray.length>0) {
                                _this.lessonTeacherListshow=true;

                            }
                            if (_this.lessonChapterListArray.length>0){
                                _this.lessonChapterListshow=true;

                            }
                        }else {
                            alert("出错")
                        }

                    },
                    error:function(){
                        alert("出错")
                    }

                })

            },
            loadChapterList(lessonId) {
                var me = this;
                $.ajax({
                    url: contextPath + "/stu/lesson/chapterList/" + lessonId,
                    type: "get",
                    dataType: "json",
                    success: function(data) {
                        console.log(me.chapterList = data.data.chapterList);
                    }
                });
            },
            /*学生没上过的课的课程详情页*/
            showTips: function(chapter, event) {
                var me = this;
                me.showtips = true;
                var obj = $(event.target);
                var offset = obj.offset();
                me.showTipchapter = chapter;
                var top = offset.top - 180;
                var left1 = offset.left - 300
                this.x=event.clientX;
                this.y=event.clientY;
                me.$nextTick(function() {
                    // 界面未显示出来获取不到 $('#show_tips_div') 的高度. 导致坐标计算错误.
                    me.tipStyle = 'top: ' + (offset.top - $('#show_tips_div').height()-10) + 'px; left: ' + offset.left + 'px;';
                });
            },
            ZhiddTips: function(chapter, event) {
                var me = this;
                me.showtips = false;
            },
            hidChapterMenu: function() {
                this.showChapterMenu = false
            },
            /*展示学生课程课程库的申请页面*/

            showMenu: function( item, event) {
                var me = this;
                me.applyStatus = {
                    normal: 1
                };
                $.get(contextPath + "/stu/user/applyStatus", {
                    lessonId: item.lessonId,
                    chapterId: item.chapterId
                }, function(resp) {
                    me.applyStatus = {};
                    Vue.set(me.applyStatus, 'normal', JSON.parse(resp).data.normal);
                    if(JSON.parse(resp).data.normal === null) {
                        return;
                    }
                    me.selectedChapter = item;
                    me.selectLesson = item;
                    me.showStudentApplyDialog = false;
                    if(!me.showChapterMenu) {
                        var obj = $(event.target);
                        var offset = obj.offset();
                        var menu = $("#menu-div");
                        menu.css("top", (offset.top+30 ) + "px");
                        menu.css("left", (offset.left) + "px");
                        me.showChapterMenu = true;
                    } else {
                        me.showChapterMenu = false;
                    }
                });
            },
            applyShiting: function() {
                this.hidChapterMenu();
                this.studentApplyType = 4;
                this.showStudentApplyDialog = true;
            },
            applyPingce: function() {
                this.hidChapterMenu();
                this.studentApplyType = 5;
                this.showStudentApplyDialog = true;
            },
            applyPangting: function() {
                this.hidChapterMenu();
                this.studentApplyType = 3;
                this.showStudentApplyDialog = true;
            },
            teacherViewVideo:function (item) {
                location.href = contextPath + "/student/views/Teacher-ViewVideo/"+item.teacherId;
            },
            closeDialog: function(flag) {
                this.showStudentApplyDialog = flag;
            },
        }
    });
    $.ajax({
        type: "get",
        url: contextPath + "/stu/lesson/detail/" + lessonId,
        data: {},
        cache:false,
        async:false,
        dataType:"json",
        success: function(er){
            voidurl=er.data.lessonInfo.coverUrl
        },
        error:function () {
        }
    })
    var player = new TcPlayer(
        //页面放置播放位置的元素 ID
        "videoContainer", {
            "m3u8": "//file.yoohooabc.com/"+voidurl,
            // "m3u8": "https://1251352844.vod2.myqcloud.com/45f5686avodtransgzp1251352844/3b4dc3f34564972818953227732/v.f20.mp4",
            "autoplay": false,
            "coverpic": "",
            "width": '400',
            "height": '200',
            "live": false,
            "systemFullscreen": true
        });


    //观看视频
    $('#viewVideo').click(function() {/*播放按钮*/
        $('#videoMask').fadeIn(300);/*视频内容部分打开*/
    });

    //关闭视频
    $('#closeVideo').click(function() {
        player.pause();
        $('#videoMask').fadeOut(300);/*视频内容关闭*/
    });
</script>

</html>