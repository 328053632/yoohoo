<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/29
  Time: 9:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            font-size: 16px;
        }
        #MyTimeLayer{
            width: 300px;
            height: 100%;
            margin: 0 auto;
            display: block;
            /*background-color: #0b76ac;*/
        }
        .mydiv{
            width: 280px;
            height: 400px;
            background-color: #DFFAFB;;
            margin: 0 auto;
            overflow-y: auto;
        }
        .z-ptop {
            width: 100%;
            height: 45px;
            display: block;
            line-height: 45px;
            text-align: center;
        }
        .z-ul{
            width: 235px;
            height: 300px;
            margin: 0 auto;
            list-style-type: none;
        }


        .z-ul li {
            display: block;
            height: 30px;
            line-height: 20px;
        }
        .z-divdown{
            width: 280px;
            height: 50px;
            background-color: #DFFAFB;;
            margin: 0 auto;
            position: relative;
        }
        .divdown{
            position: absolute;
            top: 5px;
            right: 10px;
        }
        .skk {
            background-color: #62C456;
            color: #333;
            cursor: pointer;
        }

        .btn {
            height: 36px;
            width: 88px;
            border-radius: 4px;
            display: inline-block;
            line-height: 36px;
            text-align: center;
        }


    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath }/dep/jquery/jquery-3.2.1.min.js" ></script>
   <script src="${pageContext.request.contextPath }/dep/layer/layer.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/dep/vue/vue.min.js" ></script>
    <script>var contextPath = '${pageContext.request.contextPath }';</script>
</head>
<body>
<div id="MyTimeLayer">
    <div class="mydiv">
        <form action="" method="get">
            <p class="z-ptop">Today´s Available Time Slots</p>
            <ul class="z-ul">
                <li>
                    <label><input name="Time" type="checkbox" value="07:00-07:25" />07:00-07:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="07:30-07:55" />07:30-07:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="08:00-08:25" />08:00-08:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="08:30-08:55" />08:30-08:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="09:00-09:25" />09:00-09:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="09:30-09:55" />09:30-09:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="10:00-10:25" />10:00-10:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="10:30-10:55" />10:30-10:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="11:00-11:25" />11:00-11:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="11:30-11:55" />11:30-11:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="12:00-12:25" />12:00-12:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="12:30-12:55" />12:30-12:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="13:00-13:25" />13:00-13:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="13:30-13:55" />13:30-13:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="14:00-14:25" />14:00-14:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="14:30-14:55" />14:30-14:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="15:00-15:25" />15:00-15:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="15:30-15:55" />15:30-15:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="16:00-16:25" />16:00-16:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="16:30-16:55" />16:30-16:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="17:00-17:25" />17:00-17:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="17:30-17:55" />17:30-17:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="18:00-18:25" />18:00-18:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="18:30-18:55" />18:30-18:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="19:00-19:25" />19:00-19:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="19:30-19:55" />19:30-19:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="20:00-20:25" />20:00-20:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="20:30-20:55" />20:30-20:55</label>
                </li>
                <li>
                    <label><input name="Time" type="checkbox" value="21:00-21:25" />21:00-21:25</label>
                    <label style="margin-left: 15px"></label>
                    <label><input name="Time" type="checkbox" value="21:30-21:55" />21:30-21:55</label>
                </li>
            </ul>
        </form>
    </div>
    <div style="width: 280px;height: 50px;margin: 0 auto">
        <button class="skk btn" @click="btnconfirm">Confirm</button>
        <button class="skk btn" @click="btncancel">Edit</button>
        <button class="skk btn" @click="Checkalltime" id="Checkalltime">Choose all </button>
    </div>
</div>
<script>
    var vm = new Vue({
        el:"#MyTimeLayer",
        data:{
            checkval:[],
            date:"",
            teacherthis:"",//上课时间已选择，同时也已经被排课信息
            teacherLessonList:"",//上课时间已经选择，没有被排课 返回已经选择的时间
            currentDateLessonSchedule:"",//上课时间老师还未选择，但是已经被排课 ，返回已经被排课的时间
            DateLessonStimr:"",//203未被排课 没有选择时间,
            teacherthisArray:[],
            Checkall:false
        },
        created: function () {
           this.getteachertime;
        },
        mounted:function(){
            var _this = this;
            _this.date = this.zgetquerstring("date");
            $.ajax({
                url: contextPath + "/teach/user/getTeacherSchedule",
                data: {
                    dateTime:_this.date,
                },
                type: 'GET',
                dataType: 'JSON',
                success: function (resp) {
                    console.log(resp);
                    // 返回码:200 上课时间已选择，同时也已经被排课信息
                    // 返回码:201上课时间已经选择，没有被排课 返回已经选择的时间
                    // 返回码:202上课时间老师还未选择，但是已经被排课 ，返回已经被排课的时间
                    // 返回码:203未被排课 没有选择时间
                    if(resp.code==200){// 返回码:200 上课时间已选择，同时也已经被排课信息
                        var obj = document.getElementsByName("Time");
                        _this.teacherthis=resp.data;
                        for (var a in _this.teacherthis) {
                            for(var k in obj) {
                                if(obj[k].defaultValue == a) {
                                    obj[k].checked = true;
                                    if (_this.teacherthis[a]==1||_this.teacherthis[a]==2){
                                        obj[k].disabled = true;
                                    }
                                }
                            }
                        }
                    }
                    if(resp.code==201){// 返回码:201上课时间已经选择，没有被排课 返回已经选择的时间
                        _this.teacherLessonList=resp.data.teacherLessonList;
                        var obj = document.getElementsByName("Time");
                        for(var k in obj) {
                            for(var i=0;i< _this.teacherLessonList.length;i++){
                                if(obj[k].defaultValue == _this.teacherLessonList[i]) {
                                    obj[k].checked = true;
                                }
                            }
                        }
                    }
                    if(resp.code==202){// 返回码:202上课时间老师还未选择，但是已经被排课 ，返回已经被排课的时间
                        _this.currentDateLessonSchedule=resp.data.currentDateLessonSchedule;
                        var obj = document.getElementsByName("Time");
                        for(var k in obj) {
                            for(var i=0;i< _this.currentDateLessonSchedule.length;i++){
                                if(obj[k].defaultValue == _this.currentDateLessonSchedule[i]) {
                                    obj[k].checked = true;
                                    obj[k].disabled=true;
                                }
                            }
                        }
                    }
                    if(resp.code==203){
                        _this.DateLessonStimr=resp.data;
                    }

                },
                error:function () {
                    layer.alert('获取时间失败，请刷新！',{
                        icon: 6,
                    })
                }
            });
        },
        methods:{
            Lond_ckeck:function(objname) {
                this.checkval=[];
                var obj = document.getElementsByName("Time");
                for(var i=0;i<obj.length;i++){
                    if (obj[i].checked==true){
                        this.checkval.push(obj[i].value);
                    }
                }
                return this.checkval;
            },
            btnconfirm:function () {//确定按钮
                var _this = this;
                 var valuetime = this.zgetquerstring("date");
                var checkallww = this.Lond_ckeck();
                var str='';
                for (var i=0;i<checkallww.length;i++){
                    str+=checkallww[i]+',';
                }
                var basic = str.substring(0, str.length - 1);
                $.ajax({
                    url: contextPath + "/teach/user/saveTeacherSchedule",
                    data: {
                        date:_this.date,
                        timeScheduleStr:basic
                    },
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (resp) {
                        console.log(resp);
                        if(resp.code==0){
                            layer.alert( '提交成功！',
                                {icon: 6,closeBtn: 0 },
                                function () {
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                });
                        }else {
                            layer.alert('提交失败，请刷新！',{
                                icon: 6,
                            })
                        }
                    },
                    error:function () {
                        layer.alert('提交失败，请刷新！',{
                            icon: 6,
                        })
                    }
                });


            },
            btncancel:function () {//修改按钮
                var _this = this;
                 var valuetime = this.zgetquerstring("date");
                var checkallww = this.Lond_ckeck();
                var str='';
                for (var i=0;i<checkallww.length;i++){
                    str+=checkallww[i]+',';
                }
                var basic = str.substring(0, str.length - 1);
                $.ajax({
                    url: contextPath + "/teach/user/saveTeacherSchedule",
                    data: {
                        date:_this.date,
                        timeScheduleStr:basic
                    },
                    type: 'GET',
                    dataType: 'JSON',
                    success: function (resp) {
                        console.log(resp);
                        if(resp.code==0){
                            layer.alert( '提交成功！',
                                {icon: 6,closeBtn: 0 },
                                function () {
                                    var index = parent.layer.getFrameIndex(window.name);
                                    parent.layer.close(index);
                                });
                        }else {
                            layer.alert('提交失败，请刷新！',{
                                icon: 6,
                            })
                        }
                    },
                    error:function () {
                        layer.alert('提交失败，请刷新！',{
                            icon: 6,
                        })
                    }
                });

            },
             zgetquerstring:function(name){
                var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
                var zr = window.location.search.substr(1).match(reg);
                if(zr!=null)
                    return  decodeURI(zr[2]);
                return null;
            },
             Checkalltime:function () {
                var _this = this;
                 var obj = document.getElementsByName("Time");
                if (_this.Checkall==false){
                    for(var k in obj) {
                        obj[k].checked=true;
                    }
                    $("#Checkalltime").text("Cancel all");
                    _this.Checkall=true;
                    debugger
                }else {
                    debugger
                    for(var k in obj) {
                        if (obj[k].disabled){
                            obj[k].checked=true;
                        }
                        else {
                            obj[k].checked=false;
                        }
                    }
                    _this.Checkall=false;
                    $("#Checkalltime").text("Choose all");
                }
             }

        }
    })
</script>

</body>
</html>
