$(function () {
    $("#jqGrid").jqGrid({
        url: '../teacher/list',
        datatype: "json",
        colModel: [
            { label: '老师ID', name: 'teacherId', width: 50, key: true},
			{ label: '所属合伙人', name: 'subAdminName', width: 80 },
            /*{ label: '中文名', name: 'enName', width: 80 },/!*英文名边成中文名*!/
            { label: '英文名+工号', name: 'name', width: 80 },*//*中文名变英文名+工号*/
            { label: '英文名+工号', name: 'name', width: 80 },
            { label: '中文名', name: 'enName', width: 80 },
            { label: '登录账号', name: 'account', width: 80 },
            { label: '手机号码', name: 'phone', width: 80 },
            { label: '老师工号', name: 'jobNumber', width: 80 },
            { label: '教龄', name: 'teacherAge', width:30 },
            { label: '性别', name: 'sex', width: 80,formatter:function (cellValue) {
                    switch (cellValue){
                        case 1:
                            return '男';
                        case 2:
                            return '女';
                        default:
                            return '未选择性别';
                    }
                }
                },
            { label: '学历', name: 'educational', width: 60 },
            { label: '邮箱地址', name: 'email', width: 80 },
            { label: '国家名称', name: 'country', width: 60 },
            { label: '职位', name: 'position', width: 80 },
            { label: '地址', name: 'address', width: 80 },
            { label: '账号添加时间', name: 'createTime', width: 80 },
            { label: '账号最后修改时间', name: 'updateTime', width: 80 },
        ],

        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList : [10,30,50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page",
            rows:"limit",
            order: "order"
        },
        gridComplete:function(){
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
        }
    });
});
VeeValidate.Validator.localize('en', {
    custom: {
        account: {
            required: '登陆账号不能为空',
            alpha_dash: '账号只能由字母、数字、下划线(_)、中划线(-)组成',
            min:'账号长度不能小于3个字符',
            max:'账号长度不能超过20个字符'
        },
        password: {
            required: '密码不能为空',
            min:'密码长度不能小于6个字符',
            max:'密码长度不能超过20个字符'
        }
    }
});
Vue.use(VeeValidate);
var vm = new Vue({
    el:'#rrapp',
    data: {
        teacherPositionArray:[],
        showList: 1,
        title: null,
        doingFlag:false,
        tTeacherInfo: {},
        //日历
        dateLabel: '',
        date: new Date(),
        today: new Date(),// 今天日期
        dateInfo: {},
        month: [
            [{}, {}, {}, {}, {}, {}, {}],
            [{}, {}, {}, {}, {}, {}, {}],
            [{}, {}, {}, {}, {}, {}, {}],
            [{}, {}, {}, {}, {}, {}, {}],
            [{}, {}, {}, {}, {}, {}, {}],
            [{}, {}, {}, {}, {}, {}, {}]
        ],
        selecttodaytime:"",
        currentDateLessonList:"",//已排课
        TeacherScheduleList:"",//已选择为排课
        colorpaike:true,
        colorpaikeff:false,
        zteacherId:"",
        objctzz:{},
        jsonstr:[],
        jsonarray:[],/*存放选课和没选课的对比*/
        subAdminList: []

    },
    created: function () {//日历
        // this.todayInfo();
        // this.computeMonth();
        // this.selectDay(this.dateInfo.day, this.dateInfo.month, this.dateInfo.year);
    },
    updated: function() {
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
            clearBtn: 1,
        });
    },

    mounted: function(){
        var _this = this;
        this.initUploadPlus();
    },
    watch: {
        selecttodaytime: function(){
            var _this = this ;
            $.ajax({
                type: "GET",
                url: '../teacher/getTeacherSchedule',
                data: {
                    teacherId:_this.zteacherId,
                    dateTime:_this.selecttodaytime
                },
                success: function(r){
                    if(r.code == 200){
                        var arr={};
                        var brr={};
                        _this.jsonarray=[];
                        _this.currentDateLessonList = r.currentDateLessonList;   //已排课
                        _this.TeacherScheduleList = r.TeacherScheduleList;      //已选择为排课
                        if(_this.TeacherScheduleList!=null){
                            for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                arr =
                                    {
                                        "name" : _this.TeacherScheduleList[i],
                                        "value" : false
                                    }
                                _this.jsonarray.push(arr);

                            }
                        }
                        if(_this.currentDateLessonList!=null){
                            for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                brr={
                                    "name" : _this.currentDateLessonList[j],
                                    "value" : true
                                }
                                _this.jsonarray.push(brr);
                            }
                        }

                    }
                    if(r.code == 201){
                        var arr={};
                        var brr={};
                        _this.jsonarray=[];
                        _this.currentDateLessonList = r.currentDateLessonList;   //已排课
                        _this.TeacherScheduleList =  r.TeacherScheduleList;      //已选择为排课
                        if(_this.TeacherScheduleList!=null){
                            for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                arr =
                                    {
                                        "name" : _this.TeacherScheduleList[i],
                                        "value" : false
                                    }
                                _this.jsonarray.push(arr);

                            }
                        }
                        if(_this.currentDateLessonList!=null){
                            for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                brr={
                                    "name" : _this.currentDateLessonList[j],
                                    "value" : true
                                }
                                _this.jsonarray.push(brr);
                            }
                        }
                    }
                    if(r.code == 202){
                        var arr={};
                        var brr={};
                        _this.jsonarray=[];
                        _this.currentDateLessonList =  r.currentDateLessonList;   //已排课
                        _this.TeacherScheduleList =  r.TeacherScheduleList;      //已选择为排课
                        if(_this.TeacherScheduleList!=null){
                            for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                arr =
                                    {
                                        "name" : _this.TeacherScheduleList[i],
                                        "value" : false
                                    }
                                _this.jsonarray.push(arr);

                            }
                        }
                        if(_this.currentDateLessonList!=null){
                            for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                brr={
                                    "name" : _this.currentDateLessonList[j],
                                    "value" : true
                                }
                                _this.jsonarray.push(brr);
                            }
                        }
                    }
                    if(r.code == 203){
                        _this.currentDateLessonList = null;   //已排课
                        _this.TeacherScheduleList =  null;      //已选择为排课
                        _this.jsonarray=[];
                    }
                },
                errors:function () {
                    alert("错误")
                }
            });
        },
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = 2;
            vm.title = "新增";
            vm.tTeacherInfo = {sex:1};
        },
        update: function (event) {
            var teacherId = getSelectedRow();
            if(teacherId == null){
                return ;
            }
            vm.showList = 2;
            vm.title = "修改";
            vm.getInfo(teacherId)
        },
        updateAdmin: function (event) {
            var teacherId = getSelectedRow();
            if(teacherId == null){
                return ;
            }
            vm.tTeacherInfo.teacherId = teacherId;
            vm.showList = 4;
            vm.title = "修改所属子管理员";
            vm.getSubAdminList()
        },
        ztudentTime:function(){
            var _this = this;
            var _this = this;
            var teacherId = getSelectedRow();
            if(teacherId == null){
                return ;
            }
            _this.zteacherId=teacherId;
            this.todayInfo();
            this.computeMonth();
            this.selectDay(this.dateInfo.day, this.dateInfo.month, this.dateInfo.year);
            vm.showList = 3;
            vm.title = "时间";
            var rowData = $("#jqGrid").jqGrid('getRowData',teacherId);
            var teacherposition = rowData.position;
            var t = teacherposition.search("老师") != -1;
            if (t){
                $.ajax({
                    type: "GET",
                    url: '../teacher/getTeacherSchedule',
                    data: {
                        teacherId:teacherId,
                        dateTime:_this.selecttodaytime
                    },
                    success: function(r){
                        if(r.code == 200){
                            var arr={};
                            var brr={};
                            _this.jsonarray=[];
                            _this.currentDateLessonList = r.currentDateLessonList;   //已排课
                            _this.TeacherScheduleList = r.TeacherScheduleList;      //已选择为排课
                            if(_this.TeacherScheduleList!=null){
                                for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                    arr =
                                        {
                                            "name" : _this.TeacherScheduleList[i],
                                            "value" : false
                                        }
                                    _this.jsonarray.push(arr);

                                }
                            }
                            if(_this.currentDateLessonList!=null){
                                for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                    brr={
                                        "name" : _this.currentDateLessonList[j],
                                        "value" : true
                                    }
                                    _this.jsonarray.push(brr);
                                }
                            }
                        }
                        if(r.code == 201){
                            var arr={};
                            var brr={};
                            _this.jsonarray=[];
                            _this.currentDateLessonList = r.currentDateLessonList;   //已排课
                            _this.TeacherScheduleList =  r.TeacherScheduleList;      //已选择为排课
                            if(_this.TeacherScheduleList!=null){
                                for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                    arr =
                                        {
                                            "name" : _this.TeacherScheduleList[i],
                                            "value" : false
                                        }
                                    _this.jsonarray.push(arr);

                                }
                            }
                            if(_this.currentDateLessonList!=null){
                                for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                    brr={
                                        "name" : _this.currentDateLessonList[j],
                                        "value" : true
                                    }
                                    _this.jsonarray.push(brr);
                                }
                            }
                        }
                        if(r.code == 202){
                            var arr={};
                            var brr={};
                            _this.jsonarray=[];
                            _this.currentDateLessonList =  r.currentDateLessonList;   //已排课
                            _this.TeacherScheduleList =  r.TeacherScheduleList;      //已选择为排课
                            if(_this.TeacherScheduleList!=null){
                                for (var i=0; i<_this.TeacherScheduleList.length;i++){
                                    arr =
                                        {
                                            "name" : _this.TeacherScheduleList[i],
                                            "value" : false
                                        }
                                    _this.jsonarray.push(arr);

                                }
                            }
                            if(_this.currentDateLessonList!=null){
                                for(var j=0 ;j<(_this.currentDateLessonList.length);j++){
                                    brr={
                                        "name" : _this.currentDateLessonList[j],
                                        "value" : true
                                    }
                                    _this.jsonarray.push(brr);
                                }
                            }
                        }
                        if(r.code == 203){
                            _this.currentDateLessonList = null;   //已排课
                            _this.TeacherScheduleList =  null;      //已选择为排课
                            _this.jsonarray=[];
                        }
                    },
                    errors:function () {
                        alert("错误")
                    }
                });

            }else {
                layer.alert("请选择老师类型！");
            }


         },
        //老师查询
        findenName:function(){
            var name = $("#findteachername").val()
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                page: page,
                postData: {
                    LikeName: name,
                }
            }).trigger("reloadGrid");
        },
        saveOrUpdate: function (event) {
            console.log(vm.teacherPositionArray);
            if(vm.teacherPositionArray.length===0){
                alert("请至少选择一个员工类型！")
                return;
            }
            var me=this;
            if(me.doingFlag)return ;
            me.doingFlag=true;
            var url = vm.tTeacherInfo.teacherId == null ? "../teacher/save" : "../teacher/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tTeacherInfo),
                success: function(r){
                    me.doingFlag=false;
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                        var teacherID=vm.tTeacherInfo.teacherId;
                        if(teacherID==null){
                            teacherID=r.teacherId;
                        }
                        vm.saveBatchPositionType(teacherID);
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        updateBind: function (event) {
            var me=this;
            if(me.doingFlag)return ;
            me.doingFlag=true;
            var url = "../teacher/updateBind";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tTeacherInfo),
                success: function(r){
                    me.doingFlag=false;
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                        var teacherID=vm.tTeacherInfo.teacherId;
                        if(teacherID==null){
                            teacherID=r.teacherId;
                        }
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        saveBatchPositionType:function(teacherID){
            for (var i = 0; i < vm.teacherPositionArray.length; i++)
            {
                vm.teacherPositionArray[i].teacherId = teacherID;
            }
            $.ajax({
                type: "POST",
                url: '../teacher/saveBatchPositonType',
                data: JSON.stringify(vm.teacherPositionArray),
                success: function(r){
                    if(r.code === 0){
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
        del: function (event) {
            var teacherIds = getSelectedRows();
            if(teacherIds == null){
                return ;
            }
            confirm('确定要删除选中的记录？', function(){
                $.ajax({
                    type: "POST",
                    url: "../teacher/delete",
                    data: JSON.stringify(teacherIds),
                    success: function(r){
                        if(r.code == 0){
                            alert('操作成功', function(index){
                                $("#jqGrid").trigger("reloadGrid");
                            });
                        }else{
                            alert(r.msg);
                        }
                    }
                });
            });
        },
        getInfo: function(teacherId){
            $.get("../teacher/info/"+teacherId, function(r){
                r.tTeacherInfo.password="";
                vm.tTeacherInfo = r.tTeacherInfo;
                vm.teacherPositionArray=r.teacherPositionArray;
                
            });
        },
        getSubAdminList: function(teacherId){
            $.get("../sys/user/listAll", function(r){
            	vm.subAdminList = r.userList;
            });
        },
        reload: function (event) {
            vm.showList = 1;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
        },
        choiceFileTeacher: function(){/*老师图片上传input*/
            $('#fileuploadTeacher').click();
        },
        choicevideoTeacher:function(){/*老师视频上传input*/
            $('#videoploadTeacher').click();
        },
        initUploadPlus: function(){
            var me = this;
            $('#fileuploadTeacher').fileupload({
                dataType: 'json',
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                done: function (e, data) {
                    if (data.result.code == 0) {
                        Vue.set(me.tTeacherInfo, "teacherImage", data.result.tempPath);
                    } else {
                        alert("上传文件失败");
                    }
                }
            });
            $('#videoploadTeacher').fileupload({
                dataType: 'json',
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                done: function (e, data) {
                    if (data.result.code == 0) {
                        Vue.set(me.tTeacherInfo, "teacherVideo", data.result.tempPath);
                    } else {
                        alert("上传文件失败");
                    }
                }
            });
        },
        addPositionType:function () {
            var me = this;
            var position_id= $("#teacher_type").find("option:selected").attr("value")
            var position_name= $("#teacher_type").find("option:selected").attr("name")
            if(position_name!=null ||  position_id!=null){

                if (0 === position_id)
                {
                    return;
                }
                for (var i = 0; i < vm.teacherPositionArray.length; i++)
                {
                    if (position_id === vm.teacherPositionArray[i].positionType)
                    {
                        return;
                    }
                }
                var PosotionSchedule = {
                    teacherId:me.tTeacherInfo.teacherId,
                    positionType:position_id,
                    positionName: position_name
                };
                vm.teacherPositionArray.push(PosotionSchedule);
            }else{
                alert("请选择该老师类型！！！")
            }
        },
        handleClose2 :function(event, name) {
            const index = this.teacherPositionArray.indexOf(name);
            this.teacherPositionArray.splice(index, 1);
        },
        loadData: function () {
            if (!!this.month[0][0].day && !!this.month[5][6].day) {
                var startTime = this.dateInfo2dateLabel(this.month[0][0]);
                var endTime = this.dateInfo2dateLabel(this.month[5][6]);
                var me = this;
                /* $.ajax({
                     url: contextPath + "/teach/user/lessonCountOfDay",
                     data: {
                         startTime: startTime,
                         endTime: endTime
                     },
                     type: 'GET',
                     dataType: 'JSON',
                     success: function (resp) {
                         if (resp.code == '0') {
                             var data = resp.data.data;
                             for (var i = 0, len = data.length; i < len; i++) {
                                 for (var j = 0, mlen = me.month.length; j < mlen; j++) {
                                     me.fillText(j, data[i].dateLabel, data[i].count);
                                 }
                             }
                         }
                     }
                 });*/
            }
        },
        fillText: function (inx, label, count) {
            for (var i = 0, len = this.month[inx].length; i < len; i++) {
                if (this.dateInfo2dateLabel(this.month[inx][i]) == label) {
                    Vue.set(this.month[inx][i], 'countLabel', count + "节课");
                }
            }
        },
        todayInfo: function () {
            var date = this.date.getDate();
            var month = this.date.getMonth() + 1;
            var year = this.date.getFullYear();
            this.dateInfo = {
                day: date,
                month: month,
                year: year
            };
            this.dateLabel = year + "年" + month + "月";
        },
        /**
         *
         * 复制日期
         */
        copyDate: function (date) {
            var tempDate = new Date();
            tempDate.setFullYear(date.getFullYear());
            tempDate.setMonth(date.getMonth());
            tempDate.setDate(date.getDate());
            return tempDate;
        },
        computeMonth: function () {
            var date = this.firstDate();
            for (var i = 0; i < 6; i++) {
                var week = new Array();
                for (var j = 0; j < 7; j++) {
                    week.push(this.createDay(date));
                    date.setDate(date.getDate() + 1);
                }
                Vue.set(this.month, i, week);
            }
            this.loadData();
        },
        /*** 获取上个开始展示日期*/
        firstDate: function () {
            var date = this.copyDate(this.date);
            date.setDate(1);
            var lastMonthDate = this.lastMonthDate(date);
            switch (date.getDay()) {
                case 1:
                    // 月的第一天是周一, 显示上个月最后七天
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate((monthDays + 1) - 7);
                    return lastMonthDate;
                case 2:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays);
                    return lastMonthDate;
                case 3:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays - 1);
                    return lastMonthDate;
                case 4:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays - 2);
                    return lastMonthDate;
                case 5:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays - 3);
                    return lastMonthDate;
                case 6:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays - 4);
                    return lastMonthDate;
                case 0:
                    var monthDays = this.monthDays(lastMonthDate);
                    lastMonthDate.setDate(monthDays - 5);
                    return lastMonthDate;
            }
        },
        /**
         * 获取上个月时间, 日期为上个月最后一天
         * @param date
         * @returns {Date}
         */
        lastMonthDate: function (date) {
            var temp = this.copyDate(date);
            temp.setMonth(date.getMonth() - 1);
            temp.setDate(this.monthDays(temp));
            return temp;
        },
        createDay: function (date) {
            return {
                day: date.getDate(),
                countLabel: '',
                today: this.today.getDate() == date.getDate(),
                month: date.getMonth() + 1,
                year: date.getFullYear()
            }
        },
        monthDays: function (date) {
            var temp = new Date(date.getFullYear(), date.getMonth() + 1, 0);
            return temp.getDate();
        },
        lastMonth: function () {
            var date = this.date;
            this.date.setMonth(date.getMonth() - 1);
            this.computeMonth()
            this.dateLabel = this.date.getFullYear() + "年" + (this.date.getMonth()+1) + "月";
        },
        nextMonth: function () {
            var date = this.date;
            this.date.setMonth(date.getMonth() + 1);
            this.computeMonth()
            this.dateLabel = this.date.getFullYear() + "年" + (this.date.getMonth()+1) + "月";
        },
        toThisMonth: function () {
            this.date = this.copyDate(this.today);
            this.todayInfo();
            this.computeMonth()
            this.dateLabel = this.date.getFullYear() + "年" + (this.date.getMonth() + 1) + "月";
        },
        selectDay: function (day, month, year) {
            var computed = false;
            if (year != this.dateInfo.year || month != this.dateInfo.month) {
                computed = true;
            }
            this.date.setFullYear(parseInt(year));
            this.date.setMonth(parseInt(month) - 1);
            this.date.setDate(day);
            this.todayInfo();
            if (computed) {
                this.computeMonth();
            }
            var time = year + "-" + (parseInt(month) > 9 ? month : '0' + month) + "-" + (parseInt(day) > 9 ? day : '0' + day);
            this.selecttodaytime=time;
            /*this.$emit('change-date', time);*/
        },
        dateInfo2dateLabel: function (info) {
            if (!info) {
                return "";
            }
            var year = info.year;
            var month = info.month;
            var day = info.day;
            return year + "-" + (parseInt(month) > 9 ? month : '0' + month) + "-" + (parseInt(day) > 9 ? day : '0' + day);
        }

    }
});
var NumberUtil = {
    chnUnitChar : ["", "十", "百", "千"],
    chnUnitSection : ["", "万", "亿", "万亿", "亿亿"],
    chnNumChar : ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九"],
    SectionToChinese: function(section){
        var strIns = '', chnStr = '';
        var unitPos = 0;
        var zero = true;
        while (section > 0) {
            var v = section % 10;
            if (v === 0) {
                if (!zero) {
                    zero = true;
                    chnStr = this.chnNumChar[v] + chnStr;
                }
            } else {
                zero = false;
                strIns = this.chnNumChar[v];
                strIns += this.chnUnitChar[unitPos];
                chnStr = strIns + chnStr;
            }
            unitPos++;
            section = Math.floor(section / 10);
        }
        return chnStr;
    },
    NumberToChinese : function(num){
        var unitPos = 0;
        var strIns = '', chnStr = '';
        var needZero = false;

        if (num === 0) {
            return this.chnNumChar[0];
        }

        while (num > 0) {
            var section = num % 10000;
            if (needZero) {
                chnStr = this.chnNumChar[0] + chnStr;
            }
            strIns = this.SectionToChinese(section);
            strIns += (section !== 0) ? this.chnUnitSection[unitPos] : this.chnUnitSection[0];
            chnStr = strIns + chnStr;
            needZero = (section < 1000) && (section > 0);
            num = Math.floor(num / 10000);
            unitPos++;
        }
        return chnStr;
    }
};
