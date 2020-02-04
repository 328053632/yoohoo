<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>雅英</title>
    <meta name="description" content="雅英">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-detail.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-lib.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/layui.css?version=${_dt}">
    <%@ include file="import.jsp"%>
    <script src="${pageContext.request.contextPath }/src/student/js/TcPlayer-2.2.1.js"></script>
    <script>
        var lessonId = '${requestScope.lessonId}';
    </script>
    <script>
        var url
    </script>
    <style type="text/css">
        .apply_teach {
            width: 160px;
            height: 20px;
            padding: 8px;
            background-color: #428bca;
            border-color: #357ebd;
            color: #fff;
            margin-right: 120px;
            margin-top: 20px;
            margin-left: 50px;
            -moz-border-radius: 20px;
            -webkit-border-radius: 20px;
            border-radius: 20px;
            /* future proofing */
            -khtml-border-radius: 20px;
            /* for old Konqueror browsers */
            text-align: center;
            vertical-align: middle;
            border: 1px solid transparent;
            font-weight: 100;
            font-size: 95%;
            cursor: pointer;
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
            margin-right: 10px
        }

        .Img {
            width: 100px;
            height: 100px;
            font-size: 13px;
        }

        .pCenter {
            text-align: center;
            font-size: 16px;
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
</head>

<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<div id="lessonDetailApp" class="mainstudent lesson-detail">
    <i class="top-left-bg"></i>
    <i class="top-right-bg"></i>
    <div v-show="loadFinish" style="display: none;">
        <div class="base-info">
            <div class="cover">
                <div style="width: 400px;height: 200px;float: left;border: 1px solid #CCCCCC ;margin-top: 30px;margin-left: 50px" v-if="viveoshow==2">
                    没有视频
                </div>
                <div style="width: 400px;height: 200px;float: left;background-color: #CCCCCC;margin-top: 30px;margin-left: 50px" v-if="viveoshow==1">
                    <div id="videoMask" style="width: 400px;height: 280px;overflow: hidden;">
                        <div id="closeVideo"></div>
                        <div id="videoContainer"></div>
                    </div>
                </div>
            </div>
            <%--<div class="info" style="margin-top: 30px;margin-left: 30px">--%>
                <%--<p class="title" v-if="lessonInfo">{{lessonInfo.title}}</p>--%>
                <%--<p class="time">共 {{count}} 节课</p>--%>
            <%--</div>--%>
            <%--<div class="apply_teach" v-if="lessonType==1" @click="applyTeach()">--%>
                <%--申请授课--%>
            <%--</div>--%>
            <%--<div class="apply_teach" v-if="lessonType==2" @click="applyDevelopmentCourse()">--%>
                <%--申请制作课件--%>
            <%--</div>--%>
        </div>
        <div class="apply_teach" v-if="lessonType==1" @click="applyTeach()">
            申请授课
        </div>
        <div class="apply_teach" v-if="lessonType==2" @click="applyDevelopmentCourse()">
            申请课程设计
        </div>
        <div class="introduce-line">
            <span class="tip" v-if="lessonInfo"><i></i>{{lessonInfo.title}}</span>
            <span class="car"></span>
        </div>
        <div class="fit-object">
            <p>课程特色</p>
            <div class="content" v-if="lessonInfo">
                {{lessonInfo.temis}}
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
        <%--老师课程详情页--%>
        <div class="chapter-list clearfix" style="font-size: 18px" v-if="lessonChapterListshow">
            <div class="chapter" v-for="zitem in lessonChapterListArray">
						<span @mouseover="ZshowTips(zitem,$event)" @mouseout="ZhiddTips(zitem,$event)">
                     {{zitem.title}}
                 </span>
            </div>
        </div>
        <div class=" clearfix z-div" v-if="lessonTeacherListshow">
            <p>推荐老师</p>
            <div class="ImgOutside" @click="teacherViewVideo(item)" v-for="item in lessonTeacherListArray">
                <img v-bind:src="'//file.yoohooabc.com/'+item.teacherImage" class="Img" v-bind:alt=item.name title="点击进入详情页">
                <p class="pCenter">{{item.name}}</p>
            </div>
        </div>
        <div class=" clearfix z-div" v-if="lessonMasterTeacherListshow">
            <p>推荐课长</p>
            <div class="ImgOutside" @click="teacherViewVideo(item)" v-for="item in lessonMasterTeacherListArray">
                <img v-bind:src="'//file.yoohooabc.com/'+item.teacherImage" class="Img" v-bind:alt=item.name title="点击进入详情页">
                <p class="pCenter">{{item.name}}</p>
            </div>
        </div>
    </div>
    <div v-show="showtips" class="layui-layer-tips layer-anim" id="show_tips_div" :style="'position: absolute; width: 180px;' + tipStyle">
        <div class="layui-layer-content" style="background-color: rgb(53, 149, 204);">
            {{showTipchapter.title}}<br> {{showTipchapter.introduce}}
            <i class="layui-layer-TipsG layui-layer-TipsT" style="border-right-color: rgb(53, 149, 204);"></i>
        </div>
        <span class="layui-layer-setwin"></span>
    </div>
</div>
<poohoo-no-data v-show="showNodata" :text="'没有找到对应的课程'"></poohoo-no-data>

</div>
</body>
<script>
    /*老师课程详情页*/
    var voidurl;
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
                showChapterMenu: false,
                selectLesson: {},
                selectedChapter: {},
                showStudentApplyDialog: false,
                studentApplyType: 4, //1报名 2请假  3旁听 4试听 5测试  6补课  7补课  8插班
                tipStyle: '',
                showTipchapter: {},
                count: 0,
                lessonType: null,
                phone: '${sessionScope.LOGIN_TEACHER.phone}',
                applyInfo: {
                    title: '',
                    applyPhone: '${sessionScope.LOGIN_TEACHER.phone}',
                    lessonName: '',
                    name: '${sessionScope.LOGIN_TEACHER.name}'
                },
                showtips: false,
                tipStyle: '',
                zlessonArray: {},
                lessonMasterTeacherListArray: [],
                /*课长*/
                lessonTeacherListArray: [],
                /*老师*/
                lessonChapterListArray: [],
                /*课程*/
                lessonMasterTeacherListshow: false,
                lessonTeacherListshow: false,
                lessonChapterListshow: false,
                viveoshow: 1
            }
        },
        created: function() {
            var me = this;
            this.loadLessonInfo(me);
        },
        mounted: function() {},
        methods: {
            loadLessonInfo: function(me) {
                AjaxClient.get({
                    url: contextPath + "/teach/user/detail/" + lessonId,
                    data: {},
                    success: function(resp) {

                        me.prefix = resp.data.prefix;
                        url = resp.data.lessonInfo.coverUrl;
                        me.lessonInfo = resp.data.lessonInfo;
                        me.count = resp.data.count;
                        me.loadChapterList(lessonId)
                        me.$nextTick(function() {
                            if(!!me.lessonInfo) {
                                me.loadFinish = true;
                                me.lessonType = resp.data.lessonInfo.lessonType;
                            } else {
                                me.showNodata = true;
                            }
                        })
                    }
                });
                var _this = this;
                $.ajax({
                    type: "get",
                    url: contextPath + "/stu/lesson/chapterList/" + lessonId,
                    dataType: "json",
                    data: {
                        type: 1
                    },
                    complete: function() {

                    },
                    success: function(data) {
                        if(data.code == 0) {
                            /* console.log(_this.chapterList = data.data.chapterList);*/
                            _this.zlessonArray = data.data.lessonDetailPack;
                            _this.lessonMasterTeacherListArray = _this.zlessonArray.lessonMasterTeacherList; //课程相关课长
                            console.log(_this.lessonMasterTeacherListArray);
                            _this.lessonTeacherListArray = _this.zlessonArray.lessonTeacherList; //课程相关老师
                            _this.lessonChapterListArray = _this.zlessonArray.lessonChapterList; //课程相关章节
                            if(_this.lessonMasterTeacherListArray.length>0){
                                _this.lessonMasterTeacherListshow=true;//课程相关课长
                            }
                            if (_this.lessonTeacherListArray.length>0) {
                                _this.lessonTeacherListshow=true;//课程相关老师

                            }
                            if (_this.lessonChapterListArray.length>0){
                                _this.lessonChapterListshow=true;//课程相关章节

                            }

                        } else {
                            layer.alert("出错")
                            // alert("出错")
                        }

                    },
                    error: function() {
                        layer.alert("出错")
                        // alert("出错")
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
            applyTeach: function() {
                var me = this;
                me.applyInfo.title = "申请授课"
                me.applyInfo.lessonName = me.lessonInfo.title;
                me.applyInfo.messageType = 9;
                me.applyInfo.uType=7;
                AjaxClient.post({
                    url: contextPath + "/teach/user/applyLesson",
                    data: this.applyInfo,
                    success: function(resp) {
                        if(resp.code === '0') {
                            layer.alert("申请授课成功");
                            // alert("申请授课成功");
                        }
                    }
                });
            },
            applyDevelopmentCourse: function() {
                var me = this;
                me.applyInfo.title = "申请课程设计"
                me.applyInfo.lessonName = me.lessonInfo.title;
                me.applyInfo.messageType = 10;
                AjaxClient.post({
                    url: contextPath + "/teach/user/applyLesson",
                    data: this.applyInfo,
                    success: function(resp) {
                        if(resp.code === '0') {
                            layer.alert("申请课程设计成功")
                            // alert("申请课程设计成功")
                        }
                    }
                });

            },
            ZshowTips: function(zitem, event) {
                var me = this;
                /*老师课程详情页*/
                var obj = $(event.target);
                var offset = obj.offset();
                me.showtips = true;
                me.showTipchapter = zitem;
                var top = offset.top - 170;
                var left1 = offset.left - 340
                me.$nextTick(function() {
                    // 界面未显示出来获取不到 $('#show_tips_div') 的高度. 导致坐标计算错误.
                    me.tipStyle = 'top: ' + (offset.top - $('#show_tips_div').height()-10) + 'px; left: ' + offset.left + 'px;';
                });
            },
            ZhiddTips: function(chapter, event) {
                var me = this;
                me.showtips = false;
                /* me.showTipchapter = null;*/
            },
            teacherViewVideo: function(item) {
                location.href = contextPath + "/teacher/views/Teacher-ViewVideo/" + item.teacherId;
            }
        }
    });
    $.ajax({
        type: "get",
        url: contextPath + "/teach/user/detail/" + lessonId,
        data: {},
        cache:false,
        async:false,
        dataType:"json",
        success: function(er){
            voidurl=er.data.lessonInfo.coverUrl
        },
        error:function () {
           layer.alert("系统错误！");
        }
    })

    var player = new TcPlayer(
        //页面放置播放位置的元素 ID
        "videoContainer", {
            "m3u8": "//file.yoohooabc.com/" + voidurl,
            /* "m3u8": "https://1251352844.vod2.myqcloud.com/45f5686avodtransgzp1251352844/3b4dc3f34564972818953227732/v.f20.mp4",*/
            "autoplay": false,
            "coverpic": "",
            "width": '400',
            "height": '200',
            "live": false,
            "systemFullscreen": true
        });

    //观看视频
    $('#viewVideo').click(function() { /*播放按钮*/
        $('#videoMask').fadeIn(300); /*视频内容部分打开*/
    });

    //关闭视频
    $('#closeVideo').click(function() {
        player.pause();
        $('#videoMask').fadeOut(300); /*视频内容关闭*/
    });
</script>

</html>