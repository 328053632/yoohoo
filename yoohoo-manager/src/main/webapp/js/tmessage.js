$(function () {
    function messageType(type){
        switch (type) {
            case 1:
                return '报名';
            case 2:
                return '请假';
            case 3:
                return '旁听';
            case 4:
                return '试听';
            case 5:
                return '测评';
            case 6:
                return '补课';
            case 7:
                return '换时间';
            case 8:
                return '插班';
            case 9:
                return '授课';
            case 10:
                return '开发课程';
        }
    }
    $("#jqGrid").jqGrid({
        url: '../tmessage/list',
        datatype: "json",
        colModel: [
            {label: 'mId', name: 'mId', width: 40, key: true, hidden: true},
            {label: '用户ID', name: 'uId', width: 30},
            {
                label: '用户类型', name: 'uType', width: 30, formatter: function (cellValue) {
                    switch (cellValue) {
                        case 1:
                            return '老师';
                        case 2:
                            return '学生';
                        case 3:
                            return '系统';
                    }
                }
            },
            {
                label: '消息类型', name: 'mType', width: 40, formatter: function (cellValue, options, rowObject) {
                    return '申请' + messageType(cellValue);
                }
            },
            {
                label: '消息内容', name: 'cotent', width: 100, formatter: function (cellValue, options, rowObject) {
                    var messageInfo = JSON.parse(cellValue);
                    var type = rowObject.mType;
                    var msgTemplate = (messageInfo.name ? messageInfo.name : '用户') + '[variable]' + messageInfo.applyPhone;
                    var temp = ', 申请了' + messageType(type) + '! 手机号码: ';
                    return '<a href="javascript:;" onclick="viewMessageDetil('+rowObject.mId+')">' + msgTemplate.replace('[variable]', temp) + '</a>';
                }
            },
            {label: '添加时间', name: 'addTime', width: 50},
            {
                label: '状态', name: 'status', width: 30, formatter: function (cellvalue, options, rowObject) {

                    if (cellvalue == 0) {
                        return '<span class="label label-warning">未读</span>';
                    }
                    return '<span class="label label-success">已读</span>';
                }
            }
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

Vue.filter('leaveTime', function (value) {
    if (!value) return "";
    var date = new Date(value);
    return date.Format('yyyy-MM-dd');
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        tMessage: {},
        queryArgs: {
            type: '',
            status: '',
            startTime: null,
            endTime: null
        },
        viewWindowShow: false,
        viewMessageInfo: {}
    },
    watch: {
        'queryArgs.type': function (val, oldVal) {
            this.reload();
        },
        'queryArgs.status': function (val, oldVal) {
            this.reload();
        },
        'queryArgs.startTime': function (val, oldVal) {
            if (this.queryArgs.startTime && this.queryArgs.endTime) {
                this.reload();
            }
            if (!this.queryArgs.startTime && !this.queryArgs.endTime) {
                this.reload();
            }
        },
        'queryArgs.endTime': function (val, oldVal) {
            if (this.queryArgs.startTime && this.queryArgs.endTime) {
                this.reload();
            }
            if (!this.queryArgs.startTime && !this.queryArgs.endTime) {
                this.reload();
            }
        }
    },
    created: function(){
    },
    mounted: function(){
    	this.initDatePicker();
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reset: function () {
            this.queryArgs = {
                type: '',
                status: '',
                startTime: null,
                endTime: null
            };
            $('#start_form_date').val("");
            $('#end_form_date').val("");
        },
        add: function () {
            vm.showList = false;
            vm.title = "新增";
            vm.tMessage = {};
        },
        update: function (event) {
            var mIds = getSelectedRows();
            if (mIds == null) {
                return;
            }
            confirm('确定要标记选中的记录为已读状态？', function () {
                $.ajax({
                    type: "POST",
                    url: "../tmessage/update",
                    data: JSON.stringify(mIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.tMessage.mId == null ? "../tmessage/save" : "../tmessage/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tMessage),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var mIds = getSelectedRows();
            if (mIds == null) {
                return;
            }
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../tmessage/delete",
                    data: JSON.stringify(mIds),
                    success: function (r) {
                        if (r.code == 0) {
                            alert('操作成功', function (index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function (mId) {
            $.get("../tmessage/info/" + mId, function (r) {
                vm.tMessage = r.tMessage;
                vm.viewMessageInfo = JSON.parse(vm.tMessage.cotent);
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page,
                postData: {
                    type: this.queryArgs.type,
                    status: this.queryArgs.status,
                    startTime: this.queryArgs.startTime,
                    endTime: this.queryArgs.endTime
                }
            }).trigger("reloadGrid");
        },
        showViewFrame: function (mid) {
            this.getInfo(mid);
            this.viewWindowShow = true;
            var me = this;
            $.ajax({
                type: "POST",
                url: "../tmessage/update",
                data: JSON.stringify([mid]),
                success: function (r) {
                    me.reload();
                }
            });
        },
        closeViewFrame: function () {
            this.viewWindowShow = false;
        },
        initDatePicker: function(){
            var me=this;
        	$('#start_form_date').datetimepicker({
                language: 'zh-CN',
                format: "yyyy-mm-dd",
                minView: "month",//设置只显示到月份
                weekStart: 1,
                autoclose: 1,
                todayHighlight: 1,
                //startView: 2,
                //minView: 2,
                forceParse: 0,
                showMeridian: 1,
                clearBtn:1
            }).on('hide',function(ev){
            	var value = $("#start_form_date").val();
            	me.queryArgs.startTime = value;
            });
        	$('#end_form_date').datetimepicker({
                language: 'zh-CN',
                format: "yyyy-mm-dd",
                minView: "month",//设置只显示到月份
                weekStart: 1,
                autoclose: 1,
                todayHighlight: 1,
                //startView: 2,
                //minView: 2,
                forceParse: 0,
                showMeridian: 1,
                clearBtn:1
            }).on('hide',function(ev){
            	var value = $("#end_form_date").val();
            	me.queryArgs.endTime = value;
            });
        },
    }
});

/**
 * 查看消息详细
 * @param mid
 */
function viewMessageDetil(mid) {
    vm.showViewFrame(mid);
}

//日期格式化
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,            //月份
        "d+": this.getDate(),               //日
        "h+": this.getHours(),               //小时
        "m+": this.getMinutes(),             //分
        "s+": this.getSeconds(),             //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),   //季度
        "S": this.getMilliseconds()            //毫秒
    };

    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}