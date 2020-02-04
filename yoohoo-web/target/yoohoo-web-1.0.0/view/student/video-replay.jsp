<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="http://unpkg.com/iview/dist/styles/iview.css">
   <%-- <script type="text/javascript" src="http://vuejs.org/js/vue.min.js"></script>--%>
    <script type="text/javascript"  src="${pageContext.request.contextPath }/dep/vue/vue.min.js"></script>
    <script type="text/javascript" src="http://unpkg.com/iview/dist/iview.min.js"></script>
    <script src="${pageContext.request.contextPath }/dep/ajax-utils.js?version=${_dt}"></script>
    <script src="${pageContext.request.contextPath }/dep/jquery/jquery-3.2.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/dep/layer/layer.js"></script>
    <script src="${pageContext.request.contextPath }/dep/laydate/laydate.js"></script>
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
            this.scheduleId=this.getQueryString("id")
                var me = this;
               AjaxClient.get({
                    url: contextPath + "/stu/user/getStuVideoReplay",
                    data: {scheduleId:this.scheduleId},
                    success: function (resp) {
                        if(resp.code==="0"){
                            me.VieoRecordArray=resp.data.recodListResp.recordlist;
                        }else if(resp.code==="-1"){
                            layer.alert(resp.message, {icon: 6});
                           /* alert(resp.message)*/
                        }else{
                            layer.alert(resp.message, {icon: 6});
                           /* alert(resp.message)*/
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
