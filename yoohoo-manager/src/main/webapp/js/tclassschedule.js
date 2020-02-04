$(function () {
    $("#jqGrid").jqGrid({
        url: '../tclassschedule/list',
        datatype: "json",
        colModel: [			
			{ label: 'scheduleId', name: 'scheduleId', index: 'schedule_id', width: 50, key: true },
			{ label: '班级ID', name: 'classId', index: 'class_id', width: 80 }, 			
			{ label: '课程ID', name: 'lessonId', index: 'lesson_id', width: 80 }, 			
			{ label: '课节ID', name: 'chapterId', index: 'chapter_id', width: 80 }, 			
			{ label: '上课时间', name: 'lessonTime', index: 'lesson_time', width: 80 }, 			
			{ label: '老师ID', name: 'teacherId', index: 'teacher_id', width: 80 }, 			
			{ label: '直播音间ID', name: 'roomId', index: 'room_id', width: 80 }, 			
			{ label: '直播间密码', name: 'roomPasswd', index: 'room_passwd', width: 80 }, 			
			{ label: '直播间URL', name: 'roomUrl', index: 'room_url', width: 80 }			
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

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tClassSchedule: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tClassSchedule = {};
		},
		update: function (event) {
			var scheduleId = getSelectedRow();
			if(scheduleId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(scheduleId)
		},
		saveOrUpdate: function (event) {
			var url = vm.tClassSchedule.scheduleId == null ? "../tclassschedule/save" : "../tclassschedule/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tClassSchedule),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var scheduleIds = getSelectedRows();
			if(scheduleIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tclassschedule/delete",
				    data: JSON.stringify(scheduleIds),
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
		getInfo: function(scheduleId){
			$.get("../tclassschedule/info/"+scheduleId, function(r){
                vm.tClassSchedule = r.tClassSchedule;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});