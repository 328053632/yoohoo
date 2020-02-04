$(function () {
    $("#jqGrid").jqGrid({
        url: '../class/list',
        datatype: "json",
        colModel: [															//要呈现字段的属性设置
			{ label: '费用ID', name: 'classItemId', width: 50, key: true },
			{ label: '费用类型', name: 'classItemName', width: 80 },
			{ label: '最后修改时间', name: 'addTime', width: 100 }

			/*
			 *  price_1   -1退课
				absenteeism_price   0 缺课
				lesson_price   1 正常上课
				leave_price 2请假（上课前24小时内）
				attend_price 3旁听
				try_price 4试听
				price5 5评测 
				price6 6补课
				price7 7请假（上课前24小时之前）
				price8 8线下课
				price9 9学生故障
				price10 新生旁听
				price11 建教室补课
				price12 本次优惠
				price13 本次折扣
				price14 本次特价
				price15 本次赠送
				price16 本次赠送（不同于上）
			 * */
        ],
		viewrecords: true,
        height: 385,//385
        rowNum: 10,
		rowList : [10,40,50],
        rownumbers: true, 
        rownumWidth: 25,
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage", 												//当前页
            total: "page.totalPage",											//总页数
            records: "page.totalCount" 											//查询出的记录数
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
		tClassDefine: {},
		doingFlag:false
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.tClassDefine = {};
		},
		update: function (event) {
			var classItemId = getSelectedRow();
			if(classItemId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(classItemId)
		},
		saveOrUpdate: function (event) {
			var me=this;
			if(me.doingFlag)return ;
            me.doingFlag=true;
			var url = vm.tClassDefine.classItemId == null ? "../class/save" : "../class/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tClassDefine),
			    success: function(r){
					me.doingFlag=false;
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
			var classItemIds = getSelectedRows();
			if(classItemIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../class/delete",
				    data: JSON.stringify(classItemIds),
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
		getInfo: function(classItemId){
			$.get("../class/info/"+classItemId, function(r){
                vm.tClassDefine = r.tClassDefine;
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