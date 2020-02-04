$(function () {
    $("#jqGrid").jqGrid({
        url: '../feetemplate/list',
        datatype: "json",
        colModel: [			
			{ label: '模板ID', name: 'id', width: 45, key: true },
			{ label: '费用类型', name: 'teacherMemo', width: 75 },
			{ label: '费用（太阳花）', name: 'fee', width: 90 },
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'addtime', width: 80}                   
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
		q:{
			
		},
		showList: true,
		title:null,
		feetemplateInfo:{
			status:1
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.feetemplateInfo = {status:1,roleIdList:[]};
		},
		update: function () {
			var feetemplateId = getSelectedRow();
			if(feetemplateId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getInfo(feetemplateId);
		},
		del: function () {
			var feetemplateIds = getSelectedRows();
			if(feetemplateIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../feetemplate/delete",
				    data: JSON.stringify(feetemplateIds),
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
		saveOrUpdate: function (event) {
			var url = vm.feetemplateInfo.id == null ? "../feetemplate/save" : "../feetemplate/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.feetemplateInfo),
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
		getInfo: function(feetemplateId){
			$.get("../feetemplate/info/"+feetemplateId, function(r){
				vm.feetemplateInfo = r.feetemplateInfo;
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