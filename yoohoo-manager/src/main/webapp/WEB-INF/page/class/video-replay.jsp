<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="http://unpkg.com/iview/dist/styles/iview.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/vue"></script>
    <script type="text/javascript" src="http://vuejs.org/js/vue.min.js"></script>
    <script type="text/javascript" src="http://unpkg.com/iview/dist/iview.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/sys/ajax-utils.js?version=${_dt}"></script>
    <script src="${pageContext.request.contextPath }/js/sys/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/sys/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath }/js/sys/laydate/laydate.js"></script>
    <script>var contextPath = '${pageContext.request.contextPath }';</script>
</head>
<body>
<div id="app">
    <i-table :columns="VieoTitle" :data="VieoRecordArray">
    </i-table>
</div>
</body>

<script>
    new Vue({
        el: '#app',
        data(){
            return{
                VieoTitle: [
                    {
                        title: '视频大小',
                        key: 'size',
                        minWidth:'100px',
                        align:"left"
                    },
                    {
                        title: '录制时间',
                        key: 'duration',
                        minWidth:'100px',
                        align:"left"
                    },
                    {
                        title: '回放地址',
                        key: 'playpath',
                        minWidth: '470px',
                        align: "left"
                    },
                    {
                        title: '操作',
                        minWidth: "80px",
                        align: 'left',
                        render:function (h, params) {
                        return h('div', [
                            h('Button', {
                                props: {
                                    type: 'primary',
                                    size: 'small'
                                },
                                style: {
                                    marginRight: '5px'
                                },
                                on: {
                                    click:function () {
                                        this.window.open(params.row.playpath)
                                    }
                                 }
                 }, '播放')
             ]);
             }
        }
                ],
                VieoRecordArray: []
              }
            },
        created:function () {
            this.init()
        },
        methods:{
            init:function () {


           this.scheduleId=    ${scheduleId}
                var me = this;
               AjaxClient.get({
                    url: contextPath + "../tclass/getScheduleVideoReplay",
                    data: {scheduleId:this.scheduleId},
                    success: function (resp) {
                        if(resp.code===0){
                            me.VieoRecordArray=resp.recordlist;
                        }else if(resp.code===-1){
                            alert(resp.msg)
                        }else{
                            alert(resp.msg)
                        }

                    }
                });
            },
            getQueryString:function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]);
                return null;
            }
    }
    })
</script>
</html>
