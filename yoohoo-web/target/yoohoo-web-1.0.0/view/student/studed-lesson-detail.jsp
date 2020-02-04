<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            font-size:13px;
        }

        .pCenter {
            text-align: center;
            font-size:16px;
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
            /*position: relative;*/
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
<%@ include file="common-head.jsp" %>
<div id="lessonDetailApp" class="mainstudent lesson-detail" >
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
            <div class="chapter" v-for="zitem in lessonChapterListArray"  :class="zitem.light ? 'light' : ''">
				 <span @mouseover="showTips(zitem,$event)" @mouseout="ZhiddTips(zitem,$event)">
                     {{zitem.title}}
                 </span>
            </div>
        </div>

        <div class="z-recommend  clearfix" v-if="lessonTeacherListshow">
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
    <div v-show="showtips" class="layui-layer-tips layer-anim" id="show_tips_div" :style="'position: absolute; width: 180px;' + tipStyle">
        <div class="layui-layer-content" style="background-color: rgb(53, 149, 204);">
            {{showTipchapter.title}}<br> {{showTipchapter.introduce}}
            <i class="layui-layer-TipsG layui-layer-TipsT" style="border-right-color: rgb(53, 149, 204);"></i>
        </div>
        <span class="layui-layer-setwin"></span>
    </div>
    <%--课程鼠标放上显示结束--%>
    <poohoo-no-data v-show="showNodata" :text="'没有找到对应的课程'"></poohoo-no-data>
</div>
<%@include file="tool-service.jsp" %>
<%@include file="tpl-no-data.jspf" %>
</body>
<script>
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
                showStudentApplyDialog: false,
                studentApplyType: 4, //1报名 2请假  3旁听 4试听 5测试  6补课  7补课  8插班
                tipStyle: '',
                showTipchapter: {},
                count: 0,
                showtips: false,
                zlessonArray:{},
                lessonMasterTeacherListArray:[],/*课长*/
                lessonTeacherListArray:[],/*老师*/
                lessonChapterListArray:[],/*课程*/
                lessonMasterTeacherListshow:false,
                lessonTeacherListshow:false,
                lessonChapterListshow:false,
                viveoshow:1
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
                    url: contextPath + "/stu/lesson/detail/" + lessonId,
                    data: {},
                    success: function(resp) {

                        me.prefix = resp.data.prefix;
                        me.lessonInfo = resp.data.lessonInfo;
                        me.count = resp.data.count;
                        me.$nextTick(function() {
                            if(!!me.lessonInfo) {
                                me.loadFinish = true;
                            } else {
                                me.showNodata = true;
                            }
                        })
                    }
                });
                var _this = this;
                $.ajax({
                    type:"get",
                    url:contextPath + "/stu/user/myStudyLessonList/",
                    dataType: "json",
                    data:{
                        lessonId:lessonId,
                        "name": "",
                        "categoryId": 0,
                        pageNo: 1,
                        limit: 1000
                    },
                    complete:function () {

                    },
                    success: function(resp) {
                        if (resp.code == 0) {
                             me.lessonChapterListArray = resp.data.page.data[0].chapterList;
                        }

                    },
                    error:function(){
                        layer.alert("系统出错");
                        // alert("出错")
                    }

                });
                $.ajax({
                    type:"get",
                    url:contextPath + "/stu/lesson/chapterList/"+lessonId,
                    dataType: "json",
                    data:{
                       type:2
                    },
                    complete:function () {

                    },
                    success: function(data) {
                        _this.zlessonArray=data.data.lessonDetailPack;
                        if(data.code==0){
                            _this.lessonMasterTeacherListArray= _this.zlessonArray.lessonMasterTeacherList;//课程相关课长
                            // console.log(_this.lessonMasterTeacherListArray);
                            _this.lessonTeacherListArray= _this.zlessonArray.lessonTeacherList;//课程相关老师
                            if (_this.lessonMasterTeacherListArray.length>0){
                                _this.lessonMasterTeacherListshow=true;
                            }
                            if (_this.lessonTeacherListArray>0){
                                _this.lessonTeacherListshow=true;
                            }
                        }else {
                            layer.alert("出错")
                            // alert("出错")
                        }

                    },
                    error:function(){
                        layer.alert("系统出错")
                        // alert("出错")
                    }

                })

            },
            /*学生上过课的课程详情页*/
            showTips: function(zitem,event) {
                var me = this;
                me.showTipchapter = zitem
                var obj = $(event.target);
                var offset = obj.offset();
                me.showtips = true;
                me.$nextTick(function() {
                    // 界面未显示出来获取不到 $('#show_tips_div') 的高度. 导致坐标计算错误.
                    me.tipStyle = 'top: ' + (offset.top- $('#show_tips_div').height()-10) + 'px; left: ' + (offset.left) + 'px;';
                });
            },
            ZhiddTips: function() {
                var me = this;
                me.showtips = false;
            },
            teacherViewVideo:function (item) {
                location.href = contextPath + "/student/views/Teacher-ViewVideo/"+item.teacherId;
            }
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
            layer.alert("系统出错！")
        }
    })
    var player = new TcPlayer(
        //页面放置播放位置的元素 ID
        "videoContainer", {
            "m3u8": "'//file.yoohooabc.com/'"+voidurl,
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