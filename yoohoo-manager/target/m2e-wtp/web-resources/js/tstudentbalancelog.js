
$(function () {
    $("#jqGrid").jqGrid({
        url: '../consume/list',
        datatype: "json",
        colModel: [			
			{ label: '姓名', name: 'studentName', width: 80},
			{ label: '业务类型', name: 'consumeType', width: 50 ,
				formatter: function (cellvalue, options, rowObject) {
					if(cellvalue == 1) return "充值";
					if(cellvalue == 2) return "消费";
				}}, 			
			{ label: '原因', name: 'mark', width: 80 },
			{ label: '太阳花数', name: 'amount', width: 50 },
			{ label: '业务后余额', name: 'afterBalance', width: 80 },
			{ label: '课本', name: 'lessonName', width: 80 },
			{ label: '课节', name: 'chapterName', width: 80 },
			{ label: '老师', name: 'teacherName', width: 60 },
            { label: '课长', name: 'masterTeacherName', width: 60 },
            { label: '助教', name: 'assistantTeacherName', width: 60 },
            { label: '电教', name: 'eTeacherName', width: 60 },
            { label: '组合名称', name: 'className', width: 80 },
			{ label: '课程时间', name: 'timeStr', width: 120 },
			{ label: '添加时间', name: 'addTime', width: 120 , /*
				formatter: function (cellvalue, options, rowObject) {
					return cellvalue.format("yyyy-MM-dd hh:mm:ss");
				}*/}, 			
			{ label: '备注说明', name: 'memo', width: 100 }
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        /*rownumWidth: 25,*/
        rownumWidth:true,/*新改变的*/
        autowidth:true,/*页面长宽根据父容器的大小改变而改变*/
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		lessonArray:[],
        classItemArray:[],
        typeArray:[{"key": 1,"value":"充值"},{"key": 2,"value":"消费"}]
	},
	created:function () {
		this.init();
    },
    updated: function () {
        this.initDatePicker();
    },
	methods: {
		init: function(){
			var me=this;
            $.ajax({
                type: "GET",
                url: '../tlessoninfo/list',
                data: {page:1,limit:1000},
                success: function(r){
                    if(r.code === 0){
						me.lessonArray = r.page.list;
                    }
                }
            });
            $.ajax({
                type: "GET",
                url: '../class/list',
                data: {page:1,limit:1000},
                success: function(r){
                    if(r.code === 0){
                        me.classItemArray = r.page.list;
                    }
                }
            });
		},
        initDatePicker: function(){
            var me=this;
        	$('.form_date').datetimepicker({
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
            }).on('changeDate',function(ev){
            	me.reload;
            });
        },
        query: function () {
			vm.reload();
		},
		reload: function (event) {
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			var lessonId = $("#lesson_id").val();
			var classItemId = $("#class_item_id").val();
			var stime = $("#start-date-str").val();
			var etime = $("#end-date-str").val();
			var keyWord = $("#key-word").val();
			var type = $("#cm_type").val();
			
			$("#jqGrid").jqGrid('setGridParam',{ 
				postData:{
					"type": type,
	                "stime": stime,
	                "etime": etime,
	                "lessonId": lessonId,
	                "classItemId": classItemId,
	                "keyWord": keyWord
				},
                page: page
            }).trigger("reloadGrid");
		}
	}
});