<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/1
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
        .wrap{
            /*border: 1px red solid;*/
            height: 700px;
            overflow: hidden;
        }
        .clearfix{
            zoom: 1;
        }
        .clearfix:after{
            content: '.';
            display: block;
            visibility: hidden;
            height: 0;
            clear: both;
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
        .mainstudent {
            width: 72%;
            min-height: 80%;
            max-width: 1200px;
            border-radius: 30px;
            background-color: #DFFAFB;
            margin: 28px auto;
            -webkit-box-shadow: 0 0 10px #80C7CB;
            box-shadow: 0 0 10px #80C7CB;
            position: relative;
        }
        .pp{
            csstext-indent:2em;
            padding-left: 10px;
            padding-right: 20px;
            font-size: 16px;
            line-height: 30px;
        }
        .spand11{
            csstext-indent:2em;
            padding-left: 10px;
            padding-right: 20px;
            font-size: 16px;
            line-height: 30px;
        }
        .p11{
            font-size: 18px;
            line-height: 30px;
            margin-left: 10px
        }
        .zvideo{
            width: 100%;
            height: 250px;
        }
        .zvideo_div{
            width: 400px;
            height: 230px;
            margin-left: 50px;
            margin-top: 30px;
        }
        .englishname{
            width: 1160px;
            margin-top: 20px;
            margin-bottom: 20px;
            padding-left: 30px;
            padding-right: 30px;
        }
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/common.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-detail.css?version=${_dt}">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/src/student/css/lessons-lib.css?version=${_dt}">
    <%@ include file="import.jsp"%>
    <script src="${pageContext.request.contextPath }/src/student/js/TcPlayer-2.2.1.js"></script>
    <script>
        var teacherId = '${requestScope.teacherId}';
    </script>
</head>
<body class="html-bottom-bg">
<%@ include file="common-head.jsp" %>
<div id="app">
    <div id="ViewVideo" class="mainstudent clearfix wrap" style="position: relative;">
        <i class="top-left-bg"></i>
        <i class="top-right-bg"></i>
        <div class="zvideo">
            <div class="zvideo_div">
                <div id="videoMask" style="width: 400px;height: 230px;">
                    <div  id="closeVideo"></div>
                    <div id="videoContainer" ></div>
                </div>
            </div>
        </div>
        <div class="englishname">
            <span class="p11">英文名+工号：</span>
            <span class="pp" id="englist"></span>
        </div>
        <div class="englishname">
            <p class="p11">机构评价：</p>
            <p class="pp" id="institutions"></p>
        </div>
        <div class="englishname">
            <p class="p11">基本介绍：</p>
            <p id="introduce" class="spand11">
            </p>
        </div>

    </div>
</div>

</body>
<script>
    $(document).ready(function(fn) {
        var videourl ;
        //初始化视频
        $.ajax({
            type:"get",
            url:contextPath + "/teach/user/getTeacherInfo/"+teacherId,
            dataType: "json",
            data:{
            },
            async:false,
            complete:function () {

            },
            success: function(data) {
                if(data.code==0){
                    var a =data.data.teacherInfo;
                    $("#institutions").text(data.data.teacherInfo.evaluation);/*机构评价*/
                    $("#englist").text(data.data.teacherInfo.enName);/*英文名*/
                    // $("#schoolage").text(data.data.teacherInfo.teacherage);/*教龄*/
                    //$("#formalschooling").text(data.data.teacherInfo.educational);/*学历*/
                    //$("#countries").text(data.data.teacherInfo.country);/*国家*/
                    $("#introduce").text(data.data.teacherInfo.introduce);/*基本介绍*/
                    videourl = data.data.teacherInfo.teacherVideo;/*地址*/
                }else {
                    alert("出错")
                }
            },
            error:function(){
                alert("出错")
            }

        });
        var player = new TcPlayer(
            //页面放置播放位置的元素 ID
            "videoContainer", {
                "m3u8": "//file.yoohooabc.com/"+videourl,
                /*"m3u8": "https://1251352844.vod2.myqcloud.com/45f5686avodtransgzp1251352844/3b4dc3f34564972818953227732/v.f20.mp4",*/
                "autoplay": false,
                "coverpic": "",
                "width": '400',
                "height": '230',
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
    });
</script>
</html>
