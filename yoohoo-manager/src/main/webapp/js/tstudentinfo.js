$(function () {
    $("#jqGrid").jqGrid({
        url: '../student/list',
        datatype: "json",
        colModel: [			
			{ label: '学生ID', name: 'userId', width: 50, key: true },
			{ label: '所属合伙人', name: 'subAdminName', width: 80 },
			{ label: '英文名+学号', name: 'name', width: 80 }, /*姓名改为英文名加学号*/
			{ label: '手机号码', name: 'msisdn', width: 80 }, 			
			{ label: '中文名字', name: 'enName', width: 80 },/*英文名改为中文名*/
			{ label: '生日', name: 'birthday', width: 80 }, 			
			{ label: '状态', name: 'regStatus', width: 80, formatter: function (cellvalue, options, rowObject) {
				if(cellvalue == 0) return "未注册";
				if(cellvalue == 1) return "已注册";
        }}, 			
			{ label: '太阳花余额', name: 'balance', width: 80 ,formatter: function (cellvalue, options, rowObject) {
            	var btn = $('<a>',{class:'label', style:'background-color: #204d74', onclick:'showAmountList('+rowObject.userId+')'})
				.text(cellvalue);
        	return $('<div>').append(btn).html();
        }}, 
        	{ label: '赠送总额', name: 'presentBalance', width: 80 },
        	{ label: '赚取总额', name: 'earbBalance', width: 80 },
			{ label: '注册时间', name: 'regTime', width: 80 }			
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
    
	   $("#jqGridRecharge").jqGrid({
	        url: '../student/blanceLogList',
	        //postData : {userId : userId},
	        datatype: "json",
	        colModel: [			
				{ label: '业务类型', name: 'consumeType', width: 80 , 
					formatter: function (cellvalue, options, rowObject) {
						if(cellvalue == 1) return "充值";
						if(cellvalue == 2) return "消费";
					}}, 			
				{ label: '原因', name: 'typeName', width: 80 }, 	
				{ label: '太阳花数', name: 'amount', width: 80 }, 			
				{ label: '业务后余额', name: 'afterBalance', width: 80 },
				{ label: '课程', name: 'lessonName', width: 80 }, 			
				{ label: '课时', name: 'chapterName', width: 80 }, 			
				{ label: '老师', name: 'teacherName', width: 80 }, 			
				{ label: '班级', name: 'className', width: 80 },	
				{ label: '添加时间', name: 'addTime', width: 80 }, 			
				{ label: '备注说明', name: 'mark', width: 80 }			
	        ],
			viewrecords: true,
	        height: 385,
	        rowNum: 10,
			rowList : [10,30,50],
	        rownumbers: true, 
	        rownumWidth: 25, 
	        pager: "#jqGridRechargePager",
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
	        	$("#jqGridRecharge").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
	        }
	    });
	   
    $('#student-birthday').datepicker({
        format: "yyyy-mm-dd",
        language: "zh-CN"
    });
});
VeeValidate.Validator.localize('en', {
	  custom: {
		  name: {
		      required: '姓名不能为空'
		  },
		  msisdn: {
		      required: '手机号码不能为空',
		      regex:'请输入正确的手机号码'
		  },
		  amount:{
			  required:'充值太阳花数不能为0或空',
			  regex: '太阳花的个数必需为正整数或负整数,例如： -10 或  10',
			  max: '太阳花数需要在 -99999 到  999999之间',
		  },
		  remark:{
				  required:'请输入备注',
				  max: '备注长度在 5-120字符之间',
				  min: '备注长度在 5-120字符之间'
					  
		  }
	  }
});
Vue.use(VeeValidate);
var vm = new Vue({
	el:'#rrapp',
	data:{
		windowFlag: 1,
		title: null,
		tStudentInfo: {},
		reChargeTypeArray:[{"item":"10","name":"充值"},{"item":"11","name":"赠送"},{"item":"12","name":"赚取"}],
		amount:'',
		remark:'',
		reChargeType:'',
        doingFlag: false,
		q:{
			classItemId:"",
			studentName:""
		},
		classItemArray:[],
        lessonPaths:[],
        subAdminList:[]
	},
	mounted: function(){
    	this.init();
    },
	methods: {
		init: function(){
			var me=this;
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
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.windowFlag = 2;
			vm.title = "新增";
			vm.tStudentInfo = {};
		},
		update: function (event) {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			vm.windowFlag = 2;
            vm.title = "修改";
            
            vm.getInfo(userId)
		},
		updateAdmin: function (event) {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
            vm.tStudentInfo.userId = userId;
            vm.windowFlag = 6;
            vm.title = "修改所属子管理员";
            vm.getInfo(userId);
            vm.getSubAdminList();
        },
		saveOrUpdate: function (event) {
            var me=this;
            if(me.doingFlag)return ;
            me.doingFlag=true;

			var url = vm.tStudentInfo.userId == null ? "../student/save" : "../student/update";
			vm.tStudentInfo.birthday=$("#student-birthday").val();
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tStudentInfo),
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
        updateBind: function (event) {
            var me=this;
            if(me.doingFlag)return ;
            me.doingFlag=true;
            var url = "../student/updateBind";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tStudentInfo),
                success: function(r){
                    me.doingFlag=false;
                    if(r.code === 0){
                        alert('操作成功', function(index){
                            vm.reload();
                        });
                        var userId=vm.tStudentInfo.userId;
                        if(userId==null){
                        	userId=r.userId;
                        }
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
		del: function (event) {
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../student/delete",
				    data: JSON.stringify(userIds),
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
		recharge: function(event){
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			this.getInfo(userId,true);
			this.title="为学生："+this.tStudentInfo.name+"("+this.tStudentInfo.msisdn+")充值太阳花";
			this.reChargeType="10";
			this.errors.clear();
			this.windowFlag=3;
			
		},
		doRecharge:function(){
			
			var me=this;

			var msg;
			if(me.amount > 0)
			{
				msg="充值"+me.amount;
			}else{
				msg="扣除"+me.amount;
			}
			confirm('确定要为该学生'+msg+"太阳花？", function(){
                if(me.doingFlag)return ;
                me.doingFlag=true;
				$.ajax({
					type: "POST",
				    url: "../student/recharge",
				    data: JSON.stringify({userId:me.tStudentInfo.userId,amount:me.amount,remark:me.remark,reChargeType:me.reChargeType}),
				    success: function(r){
                        me.doingFlag=false;
				    	if(r.code === 0){
							alert('操作成功', function(index){
								me.amount="";
								me.remark="";
								vm.reload();
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
			
		},
		doBack:function(){
			vm.tStudentInfo={};
			vm.windowFlag=1;
		},
		getInfo: function(userId,asyncFlag){
			var me=this;
			$.ajax({type: "get",
				url: "../student/info/"+userId, 
				async : !asyncFlag ,
				success : function(r){
				me.tStudentInfo = r.tStudentInfo;
				me.tStudentInfo.passwd="";
				me.tStudentInfo.oldAddUserId = me.tStudentInfo.addUserId;
            }});
		},
        getSubAdminList: function(teacherId){
            $.get("../sys/user/listAll", function(r){
            	vm.subAdminList = r.userList;
            });
        },
		reload: function (event) {
			vm.windowFlag = 1;
			var me=this;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{
                	"classItemId":me.q.classItemId,
                	"studentName":me.q.studentName
                }
            }).trigger("reloadGrid");
		
		},
		showAmountList:function(userId){
			vm.windowFlag = 4;
		},
        lightLearnPath: function () {
            var userId = getSelectedRow();
            if(userId == null){
                return ;
            }
            vm.getInfo(userId)
            vm.windowFlag = 5;

            $.ajax({type: "get",
                url: "../student/queryStudentLearnPath",
                data: {studentId:userId},
                success : function(r){
                    if(r.code === 0){
                        vm.lessonPaths = r.datas;
                    }
                }});
        },
        //点亮学习路径
        doLight:function (lesson,chapter) {
            $.ajax({type: "get",
                url: "../student/addStudentLearnPath",
                data: {studentId:this.tStudentInfo.userId,lessonId:lesson.lessonId,chapterId:chapter.chapterId},
                success : function(r){
                    if(r.code === 0){
                        chapter.light=true;
                    }
                }});
        },
        //修改学习路径为未点亮
        unLight: function (lesson,chapter) {
            $.ajax({type: "get",
                url: "../student/removeStudentLearnPath",
                data: {studentId:this.tStudentInfo.userId,lessonId:lesson.lessonId,chapterId:chapter.chapterId},
                success : function(r){
                    if(r.code === 0){
                        chapter.light=false;
                    }
                }});
        }
	}
});
function showAmountList(userId)
{
	$("#jqGridRecharge").jqGrid('setGridParam',{ 
        page:1,
        datatype:'json',  
        postData:{'userId':userId}
    }).trigger("reloadGrid");
	vm.showAmountList(userId);
}