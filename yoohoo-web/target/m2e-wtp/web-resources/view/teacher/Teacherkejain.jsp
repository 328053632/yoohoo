<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        #app{
            width: 400px;
        }
        .ynname{
            font-size: 16px;
            width: 400px;
            height: 40px;
            margin: 10px 0px 10px 0px;
            /* padding-bottom: 5px;*/
            text-align: center;
            word-break:break-all;
            line-height: 40px;
            border-bottom: 1px dashed #64C558;
            border-radius: 4px;
            cursor:pointer
        }
    </style>
    <link rel="stylesheet" type="text/css" href="http://unpkg.com/iview/dist/styles/iview.css">
    <%--<script type="text/javascript" src="http://vuejs.org/js/vue.min.js"></script>--%>
    <script type="text/javascript" src="${pageContext.request.contextPath }/dep/vue/vue.min.js" ></script>
    <script type="text/javascript" src="http://unpkg.com/iview/dist/iview.min.js"></script>
    <script src="${pageContext.request.contextPath }/dep/ajax-utils.js?version=${_dt}"></script>
    <script src="${pageContext.request.contextPath }/dep/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/dep/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath }/dep/laydate/laydate.js"></script>
    <script>var contextPath = '${pageContext.request.contextPath }';</script>
</head>
<body>
<div style="width: 200px" id="app">
    <div ></div>
    <div >
        <div class="ynname" v-for="(item, inx) in VieoRecordArray" @click="Openbook(item)" >
            {{item.fileName}}
        </div>
        <div class="ynname" style="color: red" v-if="VieoRecordArray.length<=0">!对不起，次课节还没有课件!</div>
    </div>
</div>
</body>
<script>
    new Vue({
        el: '#app',
        data: {
            VieoRecordArray: [],
            scheduleId:"",
            showtishi:false,
            cardId:""

        },
        created:function () {
            this.init();
        },
        methods:{
            init: function() {
                this.scheduleId=this.getQueryString("id");
                this.cardId =this.getQueryString("cardid");
                var type =2;
                if(this.cardId==2){
                    type=2;
                }else {
                    type=2
                }
                var me = this;
                $.ajax({
                    url : contextPath + '/stu/user/getChapterFile',
                    dataType : "json",//数据格式
                    data: {chapterId:this.getQueryString("id"),type:type},
                    type : "get",//请求方式
                    async : false,//是否异步请求
                    success : function(data){
                        if(data.code==0){
                            me.VieoRecordArray=data.data.fileList;
                            if ( me.VieoRecordArray.length<=0){
                                me.showtishi=true;
                            }
                        }else{
                            layer.alert(data.message);
                        }
                    },
                    error: function () {
                        layer.alert("系统出现错误！")
                        // alert("error");
                    }
                })
            },
            getQueryString:function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);
                return null;
            },
            Openbook:function(item){
                var _this = this;
                var as= item.fileUrl;
                var index1 = as.lastIndexOf(".");
                var index2 = as.length;
                var postf = as.substring(index1, index2); //后缀名
                var way = 1;
                if(postf=='.doc'||postf=='.docx'||postf=='.ppt'||postf=='.ppts'||postf=='.xls'||postf=='.xlsx'||postf=='.pptx'){
                    way =2;
                }
                var url =contextPath + "/student/views/OpenBook?valus="+as+"&way="+way;
                /*location.href = contextPath + "/student/views/OpenBook?valus="+as+"&way="+way;*/
                window.open(url)
            }

        }
    })
</script>

</html>
