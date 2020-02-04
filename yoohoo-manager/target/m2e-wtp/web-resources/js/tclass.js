$(function() {
    $("#jqGrid").jqGrid({
        url: '../tclass/list',
        datatype: "json",
        colModel: [{
            label: '班级ID',
            name: 'classId',
            index: 'class_id',
            width: 50,
            key: true
        },
            {
                label: '组合名称',
                name: 'className',
                index: 'class_name',
                width: 80
            },
            {
                label: '组合类型',
                name: 'classType',
                index: 'class_type',
                width: 80,
                formatter: function(value, options, row) {
                    if(parseInt(value) == 1) {
                        return "测电脑";
                    }
                    if(parseInt(value) == 2) {
                        return "测评";
                    }
                    if(parseInt(value) == 3) {
                        return "试听";
                    }
                    if(parseInt(value) == 4) {
                        return "正常";
                    }
                    if(parseInt(value) == 5) {
                        return "补课";
                    }
                    if(parseInt(value) == 6) {
                        return "公开";
                    }
                    if(parseInt(value) == 7) {
                        return "电教";
                    }
                    if(parseInt(value) == 8) {
                        return "暂停";
                    }
                    if(parseInt(value) == 9) {
                        return "结课";
                    } else {
                        return "请选择课程类型";
                    }
                }
            },
            {
                label: '费用类型',
                name: 'classItem',
                index: 'class_item_id',
                width: 80

            },
            {
                label: '课本',
                name: 'lessonName',
                index: 'lesson_id',
                width: 80
            },
            {
                label: '建班时间',
                name: 'beginDate',
                index: 'begin_date',
                width: 80
            },
            // { label: '结束时间', name: 'endDate', index: 'end_date', width: 80 },
            {
                label: '课长',
                name: 'masterTeacher',
                index: 'master_teacher_id',
                width: 80
            },
            {
                label: '上课教师',
                name: 'teacher',
                index: 'teacher_id',
                width: 80
            },
            {
                label: '助教',
                name: 'assistant',
                index: 'assistant_id',
                width: 80
            },
            {
                label: '电教',
                name: 'eTeacher',
                index: 'e_teacher_id',
                width: 80
            },
            {
                label: '组合人数',
                name: 'studentNum',
                index: 'student_num',
                width: 80
            },
            {
                label: '最后修改人',
                name: 'lastUpdateAccount',
                index: 'last_update_admin',
                width: 80
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
        sortable:true,
        sortorder:'asc',
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
        gridComplete: function() {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({
                "overflow-x": "hidden"
            });
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: 1,
        title: null,
        tClass: {},
        lessonArray: [],
        q: {
            lessonId: 0,
            className: '',
            classType: 0,
            consumeType: 0,
            teacherName: ''
        },
        classItemArray: [],
        classItemMap: {},
        teacherArray: [],
        teacherArrayList: [],
        ClassteacherArrayList: [],
        TelephoneteacherArrayList: [],
        AssistTeacherArrayList: [],
        studentArray: [],
        studentMap: {},
        classStuArray: [],
        doingFlag: false,
        stuStatusArray: [{
            key: 1,
            value: '正常'
        },
            {
                key: 2,
                value: '插班'
            }
        ],
        maxStudentNum: 0,
        classScheduleArray: [], //asdasd
        classScheduleIndex: 0,
        classScheduleStatus: 0,
        stuScheduleArray: [],
        showStuFlag: false,
        classType: '',
        consumeTypeArray: [],
        basic: "",
        isRoutalive: true ,/*页面刷新用*/
        zkechengneirong:[],/*课程表头部信息 */
        chuaninformation:[],
        zstate:0,
        selectteacherArray:[],
        teacherState:0

    },
    filters: {
        classScheduleStr: function(value) {
            var str = "";
            if(value.checkStatus == 2) {
                return "已结束（已确认）";
            }
            switch(value.status) {
                case 0:
                    str = "未发布";
                    break;
                case 1:
                    str = "已发布";
                    break;
                case 2:
                    str = "已结束";
                    break;
            }
            return str;
        },
        toWeekDay: function(dateStr) {
            var str = "";
            if(dateStr && dateStr != '') {
                var dateTime = new Date(dateStr + ":00");
                switch(dateTime.getDay()) {

                    case 0:
                        str = "周日";
                        break;
                    case 1:
                        str = "周一";
                        break;
                    case 2:
                        str = "周二";
                        break;
                    case 3:
                        str = "周三";
                        break;
                    case 4:
                        str = "周四";
                        break;
                    case 5:
                        str = "周五";
                        break;
                    case 6:
                        str = "周六";
                        break;
                }
            }

            return str;
        }
    },
    created: function() {
        var _this = this;
        this.init();
        var url = location.search; //获取url中"?"符后的字串
         var zclassId = this.zgetquerstring("zclassId");/*班级id*/
        var zlessonId = this.zgetquerstring("zlessonId");/*课程id*/
        var lessonName = this.zgetquerstring("lessonName");/*课程名*/
        var classType = this.zgetquerstring("classType");/*组合类型num*/
        var classItem = this.zgetquerstring("classItem");/*费用类型*/
        var theRequest = new Object();
        theRequest.zclassId=zclassId;
        theRequest.lessonId=zlessonId;
        theRequest.lessonName=lessonName;
        var classTypenum =parseInt(classType);
        switch (classTypenum) {
            case 1:{
                theRequest.classType="测电脑";
                break;
            }
            case 2:{
                theRequest.classType="测评";
                break;
            }
            case 3:{
                theRequest.classType="试听";
                break;
            }
            case 4:{
                theRequest.classType="正常";
                break;
            }
            case 5:{
                theRequest.classType="补课";
                break;
            }
            case 6:{
                theRequest.classType="公开";
                break;
            }
            case 7:{
                theRequest.classType="电教";
                break;
            }
            case 8:{
                theRequest.classType="暂停";
                break;
            }
            case 9:{
                theRequest.classType="结课";
                break;
            }
        }
        theRequest.classItem=classItem;
        _this.chuaninformation= theRequest;
        _this.zkechengneirong =theRequest;
        if (_this.chuaninformation.zclassId!=null){
            this.zstate=1
            _this.showList=3
            _this.title = "课程表";
            var classId = _this.chuaninformation.zclassId;
            if(classId == null) {
                return;
            }
            $.get("../tclass/info/" + classId, function(r) {
                vm.tClass = r.tClass;
                assistantName = vm.tClass.assistantName;
                if(!vm.tClass) {
                    layer.alert('获取班级信息失败', {
                        icon: 5
                    });
                    return;
                }
                $.ajax({
                    type: "GET",
                    url: '../tclassschedule/list',
                    data: {
                        classId: vm.tClass.classId,
                        lessonId: vm.tClass.lessonId,
                        page: 1,
                        limit: 1000
                    },
                    success: function(r) {
                        if(r.code === 0) {
                            vm.classScheduleArray = r.page.list;
                            for(var i = 0; i < vm.classScheduleArray.length; i++) {
                                vm.classScheduleArray[i].isUpload;
                                vm.classScheduleArray[i].classItemId = vm.tClass.classItemId;
                                if(vm.classScheduleArray[i].isUpload === 1) {
                                    //已经上传文件
                                    vm.classScheduleArray[i].isUploadFile = "已上传";
                                } else {
                                    vm.classScheduleArray[i].isUploadFile = "上传课件";
                                }
                            }
                        }
                    }
                });
            });
        }
    },
    updated: function() {
        vm.initDatePicker();
    },
    watch: {
    },
    methods: {
        zgetquerstring:function(name){
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            var zr = window.location.search.substr(1).match(reg);
            if(zr!=null)
                return  decodeURI(zr[2]);
            return null;
        },
        dateChoseIsActive: function(type) {
            if(type != 2) {
                return "date-yyyymmddhhmiss";
            }
            return "";
        },
        init: function() {
            $.ajax({
                type: "GET",
                url: '../tlessoninfo/list',
                data: {
                    page: 1,
                    limit: 1000
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.lessonArray = r.page.list;
                    }
                }
            });
            $.ajax({
                type: "GET",
                url: '../class/list',
                data: {
                    page: 1,
                    limit: 1000
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.classItemArray = r.page.list;
                        if(vm.classItemArray.length > 0) {
                            $(vm.classItemArray).each(function(index, item) {
                                vm.classItemMap[item.classItemId] = item;
                            });
                        }
                    }
                }
            });

            $.ajax({
                type: "GET",
                url: '../teacher/list',
                data: {
                    page: 1,
                    limit: 1000
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.teacherArray = r.page.list;
                    }
                }
            });
            /*获取上课老师*/
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {
                    positionType: 1
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.teacherArrayList = r.list;
                    }
                }
            });
            /*获取课长*/
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {
                    positionType: 2
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.ClassteacherArrayList = r.list;
                    }
                }
            });
            /*获取电教 */
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {
                    positionType: 3
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.TelephoneteacherArrayList = r.list;
                    }
                }
            });
            /**获取助教*/
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {
                    positionType: 4
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.AssistTeacherArrayList = r.list;
                    }
                }
            });
            $.ajax({
                type: "GET",
                url: '../student/list',
                data: {
                    page: 1,
                    limit: 1000
                },
                success: function(r) {
                    if(r.code === 0) {
                        if(r.page.list.length > 0) {
                            vm.studentArray = [];
                            $(r.page.list).each(function(index, item) {
                                vm.studentMap[item.userId] = item;
                                // 过滤没有名字的学生
                                if(item.name) {
                                    vm.studentArray.push(item);
                                }
                            });
                        }
                    }
                }
            });
            /**
             * 获取费用配置信息
             */
            $.ajax({
                type: "GET",
                url: '../class/allList',
                success: function(r) {
                    if(r.code === 0) {
                        vm.consumeTypeArray = r.comunseList;
                    }
                }
            });

        },
        initDatePicker: function() {
            var me = this;
            $('.date-yyyymmdd').datetimepicker({
                language: 'zh-CN',
                format: "yyyy-mm-dd",
                minView: "month", //设置只显示到月份
                weekStart: 1,
                autoclose: 1,
                todayHighlight: 1,
                //startView: 2,
                //minView: 2,
                forceParse: 0,
                showMeridian: 1,
                clearBtn: 1
            });
            $('.date-yyyymmddhhmiss').datetimepicker({
                language: 'zh-CN',
                format: "yyyy-mm-dd hh:ii",
                minView: "month", //设置只显示到月份
                weekStart: 1,
                autoclose: 1,
                todayHighlight: 1,
                weeks: true,
                forceParse: 0,
                showMeridian: 1,
                clearBtn: 1
            }).on('change', function() {
                var inx = $(this).data('inx');
                var type = $(this).data('type');
                var val = $(this).val();
                me.basic = val.substr(0, val.length - 6);
                if(type == 0) {
                    if(me.classScheduleArray[parseInt(inx)]) {
                        me.classScheduleArray[parseInt(inx)].lessonTimeStr = me.basic;

                    }
                } else {
                    if(me.classScheduleArray[parseInt(inx)]) {
                        me.classScheduleArray[parseInt(inx)].endTimeStr = me.basic;
                    }
                }
            });
        },
        query: function() {
            vm.reload();
        },
        add: function() {/*增加按钮*/
            vm.showList = 2;
            vm.title = "新增";
            vm.tClass = {};
            vm.classStuArray = [];
            vm.addStudent();
            $('#classBeginDate').val('');
        },
        update: function(event) {/*第一行的修改按钮*/
            var classId = getSelectedRow();
            if(classId == null) {
                return;
            }
            vm.showList = 2;
            vm.title = "修改";

            vm.getInfo(classId, function() {
                if(!vm.classStuArray || vm.classStuArray.length < 1) {
                    vm.addStudent();
                }
            });
        },
        saveOrUpdate: function(event) {/*新增保存按钮*/
            vm.tClass.beginDateStr = $('#classBeginDate').val();
            // if(!vm.tClass.className || !vm.tClass.classItemId || !vm.tClass.lessonId || !vm.tClass.beginDateStr || !vm.tClass.masterTeacherId ||
            //     !vm.tClass.teacherId || !vm.tClass.assistantId || !vm.tClass.eTeacherId) {
            //     layer.alert('请将信息填写完整', {
            //         icon: 5
            //     });
            //     return;
            // }
            //组合名称.className,费用类型.classItemId,课本.lessonId,建班时间.beginDateStr,课长.masterTeacherId,助教.assistantId,电教.eTeacherId
            if(!vm.tClass.className || !vm.tClass.classItemId || !vm.tClass.lessonId || !vm.tClass.beginDateStr || !vm.tClass.masterTeacherId || !vm.tClass.assistantId || !vm.tClass.eTeacherId) {
                layer.alert('请将信息填写完整', {
                    icon: 5
                });
                return;
            }
            var me = this;
            if(me.doingFlag) return;
            me.doingFlag = true;
            var url = vm.tClass.classId == null ? "../tclass/save" : "../tclass/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tClass),
                success: function(r) {
                    me.doingFlag = false;
                    if(r.code === 0) {
                        alert('操作成功', function(index) {
                            vm.reload();
                        });
                    } else {
                        layer.alert(r.msg, {
                            icon: 5
                        });
                    }
                }
            });
        },
        saveBatchStudent: function(classId) {
            for(var i = 0; i < vm.classStuArray.length; i++) {
                vm.classStuArray[i].classId = classId;
            }
            $.ajax({
                type: "POST",
                url: '../tclass/saveBatchStudent',
                data: JSON.stringify(vm.classStuArray),
                success: function(r) {
                    if(r.code === 0) {
                        alert('操作成功', function(index) {
                            vm.reload();
                        });
                    } else {
                        layer.alert(r.msg, {
                            icon: 5
                        });
                    }
                }
            });
        },
        delete: function(event) {
            var classId = getSelectedRow();
            if(classId == null) {
                return;
            }
            confirm('删除班级将会清空该班下学生的课程表，确定要删除选中的记录？', function() {
                $.ajax({
                    type: "POST",
                    url: "../tclass/delete",
                    data: JSON.stringify(classId),
                    success: function(r) {
                        if(r.code == 0) {
                            alert('操作成功', function(index) {
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        } else {
                            layer.alert(r.msg, {
                                icon: 5
                            });
                        }
                    }
                });
            });
        },
        getInfo: function(classId, callback) {
            $.get("../tclass/info/" + classId, function(r) {
                vm.tClass = r.tClass;
                $('#classBeginDate').val(vm.tClass.beginDateStr);
            });
            $.ajax({
                type: "GET",
                url: '../tclassstudentr/list',
                data: {
                    classId: classId,
                    page: 1,
                    limit: 1000
                },
                success: function(r) {
                    if(r.code === 0) {
                        vm.classStuArray = r.page.list;
                        if(callback) {
                            callback();
                        }
                    }
                }
            });
        },
        reload: function(event) {/*新增放回*/
            vm.showList = 1;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    lessonId: vm.q.lessonId,
                    beginDate: $('#beginDate').val(),
                    endDate: $('#endDate').val(),
                    className: vm.q.className,
                    classType: vm.q.classType,
                    consumeType: vm.q.consumeType,
                    teacherName: vm.q.teacherName
                },
                page: page
            }).trigger("reloadGrid");
        },
        changeClassItem: function() {/*费用类型改变*/
            if(!vm.tClass.classItemId) {
                return;
            }
            vm.maxStudentNum = vm.classItemMap[vm.tClass.classItemId].studentNum;
        },
        addStudent: function() {
            vm.classStuArray.push({
                studentId: 0,
                status: 1
            });
        },
        delStudent: function(index) {
            vm.classStuArray.splice(index, 1)
        },
        /*课程表内容*/
        setSchedule: function() {
            //教室id
            var classId = getSelectedRow();
            if(classId == null) {
                return;
            }
            this.zkechengneirong = $("#jqGrid").jqGrid('getRowData',classId);//获得第一行的数据
            vm.showList = 3;
            vm.title = "课程表";
            $.get("../tclass/info/" + classId, function(r) {
                vm.tClass = r.tClass;
                assistantName = vm.tClass.assistantName;
                if(!vm.tClass) {
                    layer.alert('获取班级信息失败', {
                        icon: 5
                    });
                    return;
                }
                $.ajax({
                    type: "GET",
                    url: '../tclassschedule/list',
                    data: {
                        classId: vm.tClass.classId,
                        lessonId: vm.tClass.lessonId,
                        page: 1,
                        limit: 1000
                    },
                    success: function(r) {
                        if(r.code === 0) {
                            vm.classScheduleArray = r.page.list;
                            for(var i = 0; i < vm.classScheduleArray.length; i++) {
                                vm.classScheduleArray[i].isUpload;
                                vm.classScheduleArray[i].classItemId = vm.tClass.classItemId;
                                if(vm.classScheduleArray[i].isUpload === 1) {
                                    //已经上传文件
                                    vm.classScheduleArray[i].isUploadFile = "已上传";
                                } else {
                                    vm.classScheduleArray[i].isUploadFile = "上传课件";
                                }
                            }
                        }
                    }
                });
            });
        },
        /*保存按钮*/
        saveSchedule: function(classSchedule, index) {
            if (classSchedule.teacherId==null){
                layer.alert('老师为空!');
                return;
            }
            ;
            var me = this;
            var ZJFarray = [];
            var type = 1
            $.ajax({
                type: "GET",
                url: '../tclass/getStuScheduleList',
                data: {
                    scheduleId: classSchedule.scheduleId,
                    type: type,
                },
                async: false,
                dataType: "json",
                success: function(data) {
                    if(data.code === 0) {
                        if(data.stuScheduleList) {
                            ZJFarray = data.stuScheduleList;
                        } else {
                            ZJFarray = null;
                        }
                    } else {
                        ZJFarray = null;
                    }
                },
                error: function(data) {
                    ZJFarray = null;
                }
            });
            if(ZJFarray == null) {
                layer.alert('正常学生不能为空！', {
                    icon: 5
                });
                return
            } else {
                var lessonTime = $('#lessonTime' + index).val(); //年月日
                var objRegExp = /^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/
                if(objRegExp.test(lessonTime)) {
                    var valuetime = $('#endTime' + index).val(); //一起时间
                    if(!valuetime) {
                        layer.alert('上课时间和结束时间不能为空', {
                            icon: 5
                        });
                        return;
                    } else {
                        var startime = valuetime.substr(0, 5); //前面时间
                        var clocetime = valuetime.substr(6, valuetime.length); //后面时间
                        var statesdata = lessonTime.substr(0, 10); //切到年月日
                        classSchedule.lessonTimeStr = statesdata + " " + startime;
                        classSchedule.endTimeStr = statesdata + " " + clocetime;
                        if(me.doingFlag) return;
                        me.doingFlag = true;
                        $.ajax({
                            type: "POST",
                            url: '../tclass/saveSchedule',
                            data: JSON.stringify(classSchedule),
                            beforeSend: function() {
                                layer.msg('正在保存...', {
                                    icon: 16,
                                    shade: 0.01,
                                    time: -1
                                });
                            },
                            complete: function() {
                                me.doingFlag = false;
                                layer.closeAll();
                            },
                            success: function(r) {
                                if(r.code !== "0") {
                                   alert(r.message);
                                    /* alert(r.message);*/
                                } else {
                                    alert('操作成功', function() {
                                        Vue.set(me.classScheduleArray[index], 'status', 1);
                                        Vue.set(me.classScheduleArray[index], 'roomId', r.roomId);
                                    });
                                }
                            }
                        });
                    }
                } else {
                    alert("具体日前错误！");
                }
            }

        },
        /*取消按钮*/
        updateTime: function(classSchedule, index) {
            var me = this;
            classSchedule.lessonTimeStr = $('#lessonTime' + index).val();
            classSchedule.lessonTime = $('#lessonTime' + index).val() + ":00";
            classSchedule.endTimeStr = $('#endTime' + index).val();
            if(!classSchedule.lessonTimeStr || !classSchedule.endTimeStr) {
                layer.alert('上课时间和结束时间不能为空！', {
                    icon: 5
                });
                return;
            }
            var me = this;
            if(me.doingFlag) return;
            me.doingFlag = true;
            $.ajax({
                type: "POST",
                url: '../tclass/updateTime',
                data: JSON.stringify(classSchedule),
                beforeSend: function() {
                    layer.msg('正在修改...', {
                        icon: 16,
                        shade: 0.01,
                        time: -1
                    });
                },
                complete: function() {
                    me.doingFlag = false;
                    layer.closeAll();
                },
                success: function(r) {
                    if(r.code == 400) {
                        alert("时间已过，已开课，取消失败");
                    } else if(r.code==500){
                        alert("删除课件失败");
                    }
                    else{
                        alert('操作成功', function() {
                            Vue.set(me.classScheduleArray[index], 'status', 0);
                            Vue.set(me.classScheduleArray[index], 'roomId', r.roomId);
                        });

                        Vue.set(me.classScheduleArray[index], 'lessonTimeStr', "");
                        Vue.set(me.classScheduleArray[index], 'lessonTimeS', "");
                        Vue.set(me.classScheduleArray[index], 'teacherId', "");
                        Vue.set(me.classScheduleArray[index], 'isUploadFile', "上传课件");
                    }
                }
            });
        },
        /*其他按钮1代表正常按钮，7代表插班按钮*/
        showStuSchedule: function(type, index) {
            $('#stuSchType').val(type);
            $('#stuSchId').val(0);
            vm.showStuFlag = true;
            vm.classScheduleIndex = index;

            vm.classScheduleStatus = vm.classScheduleArray[index].status;
            vm.stuScheduleArray = [];
            $.ajax({
                type: "GET",
                url: '../tclass/getStuScheduleList',
                data: {
                    scheduleId: vm.classScheduleArray[index].scheduleId,
                    type: type,
                },
                success: function(r) {
                    if(r.code === 0) {
                        if(r.stuScheduleList) {
                            vm.stuScheduleArray = r.stuScheduleList;
                        }
                    }
                }
            });
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['500px', '400px'], //宽高500px,300px
                content: $('#stuSchedule')
            });

        },
        uploadFile: function(classSchedule, index) {/*上传课件按钮*/
            $.ajax({
                type: "POST",
                url: '../tclass/uploadFile',
                data: JSON.stringify(classSchedule),
                beforeSend: function() {
                    layer.msg('正在上传文件...', {
                        icon: 16,
                        shade: 0.01,
                        time: -1
                    });
                },
                complete: function() {
                    layer.closeAll();
                },
                success: function(r) {
                    if(r.code === 0) {
                        classSchedule.isUploadFile = "已上传";
                        vm.init();
                        vm.initDatePicker();
                        alert("上传成功！");
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        delStuSchedule: function(index) {/*正常添加中的删除按钮*/
            var rec = vm.stuScheduleArray[index];
            var getclassid=getSelectedRow()/*新增的*/
            if (getclassid==null){/*新增的*/
                getclassid=this.zkechengneirong.zclassId;/*新增的*/
            }/*新增的*/
            if(rec.recordId) {
                $.ajax({
                    type: "GET",
                    url: '../tclass/delStuSchedule',
                    data: {
                        recordId: rec.recordId,
                        type: $('#stuSchType').val(),
                        /*classId: getSelectedRow(),*/
                        classId: getclassid,
                        studentId: rec.studentId

                    },
                    success: function(r) {
                        /*if (r==0){
                        }else {

                        }*/
                    },
                    error: function(r) {

                    }
                });
            }
            vm.stuScheduleArray.splice(index, 1);
        },
        addStuSchedule: function() {/*正常添加按钮*/
            var studentId = $('#stuSchId').val();
            if(0 === studentId) {
                return;
            }
            for(var i = 0; i < vm.stuScheduleArray.length; i++) {
                if(studentId === vm.stuScheduleArray[i].studentId) {
                    return;
                }
            }
            var classSchedule = vm.classScheduleArray[vm.classScheduleIndex];
            var stuSchedule = {
                type: $('#stuSchType').val(),
                studentId: studentId,
                studentName: vm.studentMap[studentId].name,
                scheduleId: classSchedule.scheduleId,
                lessonId: classSchedule.lessonId,
                chapterId: classSchedule.chapterId,
                status: 1,
            };
            vm.stuScheduleArray.push(stuSchedule);
        },
        saveBatchStuSchedule: function() {/*正常保存按钮*/
            var me = this;
            for(var i = 0; i < vm.stuScheduleArray.length; i++) {
                /**/
                if(me.zstate==1){
                     vm.stuScheduleArray[i].classId=this.zkechengneirong.zclassId;/*新增的*/
                }else {
                    vm.stuScheduleArray[i].classId = getSelectedRow();
                }

            }
            if(me.doingFlag) return;
            me.doingFlag = true;
            $.ajax({
                type: "POST",
                url: '../tclass/saveBatchStuSchedule',
                data: JSON.stringify(vm.stuScheduleArray),
                beforeSend: function() {
                    layer.msg('正在保存...', {
                        icon: 16,
                        shade: 0.01,
                        time: -1
                    });
                },
                complete: function() {
                    layer.closeAll();
                },
                success: function(r) {
                    me.doingFlag = false;
                    if(r.code === 0) {
                        alert('操作成功', function(index) {
                            vm.showStuFlag = false;
                            layer.closeAll();
                        });
                    } else {
                        layer.alert(r.msg, {
                            icon: 5
                        });
                    }
                },
                error:function () {
                    alert("保存失败！")
                }
            });
        },
        /*获取助教url的方法*/
        getAssistTeacherUrl: function(classSchedule, index) {
            $.ajax({
                type: "GET",
                url: '../tclass/getAssistTeacherUrl',
                data: {
                    'roomId': classSchedule.roomId
                },
                success: function(r) {
                    if(r != 0) {
                        window.open().location.href = r;
                    } else {
                        layer.alert('获取助教url失败', {
                            icon: 5
                        });
                    }
                }
            });
        },
        closeStuSchedule: function() {/*正常中的取消按钮*/
            layer.closeAll();
        },
        VideoReplay: function(classSchedule) {
            layer.open({
                type: 2,
                title: '录像回放',
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['750px', '500px'],
                content: '../tclass/videoReplay?id=' + classSchedule.scheduleId
            });
        },
        selectteacher:function (lessonTimeStr,lessonTimeS) {

            if(lessonTimeStr==null){
                layer.alert("请选择日期！")
                return;
            }
            var _this = this;
            $.ajax({
                type: "GET",
                url: '../tclassschedule/getTeacherListByTime',
                data: {
                    dateTime: lessonTimeStr,      //日期
                    timeSchedule:lessonTimeS     //时间段
                },
                success: function(r) {
                    if(r != 0) {
                       _this.selectteacherArray=r.teacherList;
                       if (_this.selectteacherArray==null){
                           layer.alert("该时间段没有老师，请重新选择时间！")
                       }
                    } else {
                        layer.alert("系统出错，重新登入！")
                    }
                },
                error:function () {
                    layer.alert("系统出错，重新登入！")
                }
            });
            
        }
    }
});