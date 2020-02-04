$(function () {
    $("#jqGrid").jqGrid({
        url: '../tlessoninfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'lessonId', name: 'lessonId', width: 50, key: true, hidden: true },
			{ label: '课本名称', name: 'title', width: 80 },
			{ label: '课本简介', name: 'introduce', width: 80 },
            {
                label: '封面', name: 'coverUrl', width: 50,
                formatter: function (cellvalue, options, rowObject) {
                    var $img = $('<img>', {class: 'view-cover', src: cellvalue});
                    return $('<div>').append($img).html();
                }
            },
			{ label: '适用学龄', name: 'ageSection', width: 80 },
			{ label: '课时数', name: 'times', width: 80 }, 			
			{ label: '添加时间', name: 'addtime', width: 80 },
			{ label: '课程类型', name: 'lessonType', width: 80 ,
                formatter:function(value,options,row){
                    if(parseInt(value)==1){
                        return "正式课";
                    }
                    if(parseInt(value)==2){
                        return "待开发课";
                    }
                    }},
            {
                label: '操作', name: '', width: 50,
                formatter: function (cellvalue, options, rowObject) {
                	var btn = $('<a>',{class:'label label-success', style:'background-color: #204d74', onclick:'addChapter('+rowObject.lessonId+')'})
						.text('添加目录');
                	return $('<div>').append(btn).html();
                }
            },
       /*     {
                label: '上课文件', name: '', width: 50,
                formatter: function (cellvalue, options, rowObject) {
                    var btn = $('<a>',{class:'label label-success', style:'background-color: #204d74', onclick:'addFile('+rowObject.lessonId+')'})
                        .text('添加');
                    return $('<div>').append(btn).html();
                }
            },*/
            {
                label:'上下架',name:"status",width:30,
                formatter: function (cellvalue, options, rowObject) {
                    if(parseInt(rowObject.status)==1){
                     return   vm.under(rowObject.lessonId);
                    }else if(parseInt(rowObject.status)==0){
                        return vm.UP(rowObject.lessonId)
                    }else{
                        var btn = $('<a>',{class:'label label-success', style:'background-color: #ff0101ad', onclick:'('+rowObject.lessonId+')'})
                            .text('请添加上下架状态');
                        return $('<div>').append(btn).html();
                    }
                }
            }
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
    data: {
        showList: 1,
        isShow: true,
        title: null,
        tLessonInfo: {},
		chapterList: [],
		delChapterIds:[],
        teacherArray:[],
        teacherArrayList:[],
        fileArray:[],
        fileArrayList:[],
        PrefileArray:[],
        PrefileArrayList:[],
        masterteacherArrayList:[],
        eteacherArrayList:[],
        modifyChapter: false,
		showAddChapterWindow:false,
        showAddFileWindow:false,
		modifyChapterIdx:null,
		currentAddLessonId: null,
        addChapterInfo: {title: '', introduce: ''},
        baseData: [],
        selectNode: null,
		showTreeFlag: false,
        isDoSaving:false,
        lessonTeacherArray: [],
        lessonmasterTeacherArray: [],
        lessoneTeacherArray: [],
        AllTeacherArray:[],
        AllFileArray:[]
    },
	mounted: function(){
		this.initUploadPlus();
        this.treeDate();
        this.init();
	},
	computed: function(){
	},
	methods: {
	    init:function(){
            $.ajax({
                type: "GET",
                url: '../teacher/list',
                data: {page:1,limit:1000},
                success: function(r){
                    if(r.code === 0){
                        vm.teacherArray = r.page.list;
                    }
                }
            });
            /*获取上课老师*/
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {positionType:1},
                success: function(r){
                    if(r.code === 0){
                        vm.teacherArrayList = r.list;
                    }
                }
            });
            /*获取课长*/
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {positionType:2},
                success: function(r){
                    if(r.code === 0){
                        vm.masterteacherArrayList = r.list;
                    }
                }
            });
            /*获取电教 */
            $.ajax({
                type: "GET",
                url: '../teacher/getListByType',
                data: {positionType:3},
                success: function(r){
                    if(r.code === 0){
                        vm.eteacherArrayList = r.list;
                    }
                }
            });
            /* 获取上课文件 */
            $.ajax({
                type:"GET",
                url:'../file/all_list',
                data:{fileType:2},
                success: function (r) {
                    if(r.code === 0){
                        vm.fileArrayList=r.FileList;
                    }
                }
            });
            /*获取预习课件*/
            $.ajax({
                type:"GET",
                url:'../file/all_list',
                data:{fileType:1},
                success: function (r) {
                    if(r.code === 0){
                        vm.PrefileArrayList=r.FileList;
                    }
                }
            });

        },
        treeDate: function () {
            var _this = this;
            $.get('../tcategoryinfo/categoryTreeData',{expand: true}, function (resp) {
                if (resp.code == 0) {
                    _this.baseData = resp.tree;
                }
            });
        },
        changeNode: function (nodes) {
            this.selectNode = nodes[0];
        },
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = 2;
			vm.title = "新增";
			vm.tLessonInfo = {lessonType: 1};
		},
		update: function (event) {
			var lessonId = getSelectedRow();
			if(lessonId == null){
				return ;
			}
			vm.showList = 2;
            vm.title = "修改";

            vm.getInfo(lessonId)
		},
		saveOrUpdate: function (event) {
		    if(!this.tLessonInfo.title){
		        alert('请填写课程名称');
		        return;
            }
            if(!this.tLessonInfo.coverUrl){
                alert('上传封面图片');
		        return;
            }
            if(vm.lessonTeacherArray.length===0 &&  vm.lessonmasterTeacherArray.length===0
                && vm.lessoneTeacherArray.length===0)
            {
                alert('至少选择一个老师')
                return;

            }
            var me = this;
            if(me.isDoSaving)return;
            me.isDoSaving=true;

			var url = vm.tLessonInfo.lessonId == null ? "../tlessoninfo/save" : "../tlessoninfo/update";
			if(parseInt(vm.tLessonInfo.lessonType)==2){
                vm.tLessonInfo.categoryName=null;
                vm.tLessonInfo.categoryId=null;
            }
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.tLessonInfo),
			    success: function(r){
                    me.isDoSaving=false;
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						var	lessonId=vm.tLessonInfo.lessonId;
                            if (lessonId == null)
                            {
                                lessonId = r.lessonId;
                            }
							vm.saveBatchTeacher(lessonId);
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
        saveBatchTeacher:function(lessonId){
            for (var i = 0; i < vm.lessonTeacherArray.length; i++)
            {
                vm.lessonTeacherArray[i].lessonId = lessonId;
            }
            for (var i = 0; i < vm.lessonmasterTeacherArray.length; i++)
            {
                vm.lessonmasterTeacherArray[i].lessonId = lessonId;
            }
            for (var i = 0; i < vm.lessoneTeacherArray.length; i++)
            {
                vm.lessoneTeacherArray[i].lessonId = lessonId;
            }
            var newData= vm.AllTeacherArray.concat(vm.lessonTeacherArray).concat(vm.lessonmasterTeacherArray).concat(vm.lessoneTeacherArray);
            console.log(newData);
            $.ajax({
                type: "POST",
                url: '../tlessoninfo/saveBatchTeacher',
                data: JSON.stringify(newData),
                success: function(r){
                    if(r.code === 0){
                    }else{
                        alert(r.msg);
                    }
                }
            });
        },
		del: function (event) {
			var lessonIds = getSelectedRows();
			if(lessonIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../tlessoninfo/delete",
				    data: JSON.stringify(lessonIds),
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
		getInfo: function(lessonId){
			$.get("../tlessoninfo/info/"+lessonId, function(r){
                vm.tLessonInfo = r.tLessonInfo;
                if(parseInt(vm.tLessonInfo.lessonType)==2){
                    vm.isShow=false;
                }
            });
            /**
             *
             * 获取当前课堂的上课教师
             */
            $.ajax({
                type:"GET",
                url:'../tlessoninfo/LessonTeahcerList',
                data:{lessonId:lessonId,positionType:1},
                success:function (r) {
                    if(r.code===0){
                        vm.lessonTeacherArray=r.list;
                    }
                }
            })
            /**
             * 获取当前课堂的课长
             */
            $.ajax({
                type:"GET",
                url:'../tlessoninfo/LessonTeahcerList',
                data:{lessonId:lessonId,positionType:2},
                success:function (r) {
                    if(r.code===0){
                        vm.lessonmasterTeacherArray=r.list;
                    }
                }
            })
            /**
             *
             * 获取当前课堂的电教
             */
            $.ajax({
                type:"GET",
                url:'../tlessoninfo/LessonTeahcerList',
                data:{lessonId:lessonId,positionType:3},
                success:function (r) {
                    if(r.code===0){
                        vm.lessoneTeacherArray=r.list;
                    }
                }
            })
        },
        reload: function (event) {
			vm.showList = 1;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                page:page
            }).trigger("reloadGrid");
		},
        choiceFile: function(){
            $('#fileupload').click();
		},
		initUploadPlus: function(){
			var me = this;
			$('#fileupload').fileupload({
                dataType: 'json',
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                done: function (e, data) {
                    if (data.result.code == 0) {
                        Vue.set(me.tLessonInfo, "coverUrl", data.result.tempPath);
                    } else {
                        alert("上传文件失败");
                    }
                }
			});
		},
		showAddChapterFrame: function(lessonId){
			this.showAddChapterWindow = true;
			this.modifyChapter = false;
			this.currentAddLessonId = lessonId;
			this.loadLessonChapters();
		},
        closeAddChapterFrame: function () {
            if (this.modifyChapter) {
                var me = this;
                alert('修改内容未保存是否退出?', function (index) {
                    me.showAddChapterWindow = false;
                    me.addChapterInfo = {};
                    me.currentAddLessonId = null;
                    me.chapterList = [];
                    me.delChapterIds = [];

                    me.PrefileArray=[];
                    me.fileArray=[];
                });
                return;
            }
            this.showAddChapterWindow = false;
            this.addChapterInfo = {};
            this.currentAddLessonId = null;
            this.chapterList = [];
            this.delChapterIds = [];

            this.PrefileArray=[];
            this.fileArray=[];
        },
        loadLessonChapters: function () {
            var me = this;
            $.get('../tlessoninfo/chapterList/' + this.currentAddLessonId, function (resp) {
                me.chapterList = resp.data;
            }, 'json');
        },
        addChapter: function () {
            if (!this.addChapterInfo.title) {
            	alert("请输入课节名称");
                return;
            }
            var info = this.addChapterInfo;
            info.orderNum = this.chapterList.length + 1;
            this.addChapterInfo = {};
            this.chapterList.push(info);
            this.modifyChapter = true;
        },
        removeChapter: function(index){
			if(this.chapterList[index].chapterId){
                this.delChapterIds.push(this.chapterList[index].chapterId);
			}
            this.chapterList.splice(index, 1);
            this.modifyChapter = true;
		},
		shwoChapterModifyWin:function(index){
			var me=this;
			this.modifyChapterIdx=index;
            var str='<form class="form-horizontal">'
                +'<div class="form-group">'
                +'	<div class="col-sm-2 control-label">课节名称:</div>'
                +'	<div class="col-sm-10">'
                +'		<input type="text" id="new_chapter_name" value="'+this.chapterList[index].title+'"  class="form-control" placeholder="课节名称"/>'
                +'	</div>'
                +'</div>'
                +'<div class="form-group">'
                +'	<div class="col-sm-2 control-label">课节简介:</div>'
                +'	<div class="col-sm-10">'
                +'		<input type="text"  id="new_introduce" value="'+this.chapterList[index].introduce+'" class="form-control" placeholder="课节简介"/>'
                +'	</div>'
                +'</div>'
                +'</form>'
			layer.open({
				  type:1
				  ,title:"修改课节信息"
				  ,area: ['600px', '250px']
				  ,content: str
				  ,btn: ['修改']
				  ,yes: function(index, layero){
					    var newName=$("#new_chapter_name").val();
                        var newIntroduce=$("#new_introduce").val();
                        if('' != $.trim(newName) && newName != me.chapterList[me.modifyChapterIdx].title)
                        {
                              me.chapterList[me.modifyChapterIdx].title=newName;
                              me.modifyChapter = true;
                        }
                        if('' != $.trim(newIntroduce)  && newIntroduce !=  me.chapterList[me.modifyChapterIdx].introduce)
                        {
                            me.chapterList[me.modifyChapterIdx].introduce=newIntroduce;
                            me.modifyChapter = true;
                        }
					    layer.close(index);
				  }
				  ,cancel: function(){
				    return true;
				  }
				})
		},
        saveChapterList: function () {
			var me = this;
			if(me.isDoSaving)return;
			me.isDoSaving=true;
            $.post('../tlessoninfo/addLessonChapter',
                JSON.stringify({lessonId: this.currentAddLessonId, chapters: this.chapterList, delChapterIds: this.delChapterIds}),
                function (resp) {
                    me.modifyChapter = false;
                    me.isDoSaving=false;
                    me.closeAddChapterFrame();
                    alert("保存成功!");
                }, 'json');
        },
        afterSort: function () {
            for (var i = 0, len = this.chapterList.length; i < len; i++) {
                this.chapterList[i].orderNum = i + 1;
            }
            this.modifyChapter = true;
        },
        showTreeModal: function(){
        	this.showTreeFlag = true;
		},
		hideTreeModal: function(){
            this.showTreeFlag = false;
            this.selectNode = null;
            this.treeDate();
		},
		selectCategory: function(){
            if (!this.selectNode) {
                alert('请选择分类');
                return;
            }
            if (this.selectNode.level <= 0) {
                alert('请选择二级分类');
                return;
            }
            Vue.set(this.tLessonInfo, 'categoryName', this.selectNode.title);
            Vue.set(this.tLessonInfo, 'categoryId', this.selectNode.id);
            this.hideTreeModal();
		},
        /**
         *
         * 关联推荐上课老师
         *
         */
        TeacherAdd :function() {
            var teacher_id= $("#select_teacher").find("option:selected").attr("value")
            var teacher_name= $("#select_teacher").find("option:selected").attr("name")
            if(teacher_name!=null ||  teacher_id!=null){

                if (0 === teacher_id)
                {
                    return;
                }
                for (var i = 0; i < vm.lessonTeacherArray.length; i++)
                {
                    if (teacher_id === vm.lessonTeacherArray[i].teacherId)
                    {
                        return;
                    }
                }
                var teahcerSchedule = {
                    teacherId:teacher_id,
                    lessonId: this.tLessonInfo.lessonId,
                    teacherName: teacher_name,
                    positionType: 1
                };
                vm.lessonTeacherArray.push(teahcerSchedule);
            }else{
                alert("请选择您要添加的老师！！！")
            }
         },
        /**
         * 删除上课老师
         * @param event
         * @param name
         * @constructor
         */
        TeacherDel:function(event, name) {
            const index = this.lessonTeacherArray.indexOf(name);
            this.lessonTeacherArray.splice(index, 1);
        },
        /**
         * 关联课长
         *
         */
        MasterTeacherAdd :function() {
            var teacher_id= $("#select_masterteacher").find("option:selected").attr("value")
            var teacher_name= $("#select_masterteacher").find("option:selected").attr("name")
            if(teacher_name!=null ||  teacher_id!=null){

                if (0 === teacher_id)
                {
                    return;
                }
                for (var i = 0; i < vm.lessonmasterTeacherArray.length; i++)
                {
                    if (teacher_id === vm.lessonmasterTeacherArray[i].teacherId)
                    {
                        return;
                    }
                }
                var teahcerSchedule = {
                    teacherId:teacher_id,
                    lessonId: this.tLessonInfo.lessonId,
                    teacherName: teacher_name,
                    positionType:2
                };
                vm.lessonmasterTeacherArray.push(teahcerSchedule);
            }else{
                alert("请选择您要添加的老师！！！")
            }
        },
        /**
         * 删除推荐课长
         * @param event
         * @param name
         * @constructor
         */
        MasterTeacherDel:function(event, name) {
            const index = this.lessonmasterTeacherArray.indexOf(name);
            this.lessonmasterTeacherArray.splice(index, 1);
        },
        /**
         * 关联电教
         */
        ETeacherAdd :function() {
            var teacher_id= $("#select_eteacher").find("option:selected").attr("value")
            var teacher_name= $("#select_eteacher").find("option:selected").attr("name")
            if(teacher_name!=null ||  teacher_id!=null){
                if (0 === teacher_id)
                {
                    return;
                }
                for (var i = 0; i < vm.lessoneTeacherArray.length; i++)
                {
                    if (teacher_id === vm.lessoneTeacherArray[i].teacherId)
                    {
                        return;
                    }
                }
                var teahcerSchedule = {
                    teacherId:teacher_id,
                    lessonId: this.tLessonInfo.lessonId,
                    teacherName: teacher_name,
                    positionType:3
                };
                vm.lessoneTeacherArray.push(teahcerSchedule);
            }else{
                alert("请选择您要添加的老师！！！")
            }
        },
        /**
         * 删除电教
         * @param event
         * @param name
         * @constructor
         */
        ETeacherDel:function(event, name) {
            const index = this.lessoneTeacherArray.indexOf(name);
            this.lessoneTeacherArray.splice(index, 1);
        },
        configData:function (index) {
           if(parseInt(index)==1){
              vm.isShow=true;
           }else if(parseInt(index)==2){
               vm.isShow=false;

           }
        },
        LessonUp:function (lessonId) {
            $.ajax({
                type:"GET",
                url:'../tlessoninfo/LessonUp',
                data:{lessonId:lessonId},
                success:function (r) {
                    if(r.code===0){
                        jQuery("#jqGrid").trigger("reloadGrid");
                    }
                }
            })
        },
        LessonUnder:function (lessonId) {
            $.ajax({
                type:"GET",
                url:'../tlessoninfo/LessonUnder',
                data:{lessonId:lessonId},
                success:function (r) {
                    if(r.code===0){
                        //局部刷新
                        jQuery("#jqGrid").trigger("reloadGrid");
                    }
                }
            })
        },
        under:function (lessonId) {
            var btn = $('<a>',{class:'label label-success', style:'background-color: #169bd5', onclick:'underLesson('+lessonId+')'})
                .text('下 架');
            return $('<div>').append(btn).html();
        },
        UP:function (lessonId) {
            var btn = $('<a>',{class:'label label-success', style:'background-color: #ccccff', onclick:'upLesson('+lessonId+')'})
                .text('上 架');
            return $('<div>').append(btn).html();
        },
         showAddFileFrame:function (lessonId) {
             var  me=this;
             me.showAddFileWindow=true;
             vm.tLessonInfo.lessonId=lessonId;
             //获取当前课程所关联文件
             this.loadLessonFiles(lessonId);
         },
         closeAddFileFrame:function () {
             var me=this;
             me.showAddFileWindow=false;
             me.PrefileArray=[];
             me.fileArray=[];

         },
         FileDel:function(event, name) {
             const index = this.fileArray.indexOf(name);
             this.fileArray.splice(index, 1);
         },
         FileAdd :function() {
             var file_id= $("#select_file").find("option:selected").attr("value")
             var file_name= $("#select_file").find("option:selected").attr("name")
             if(file_name!=null ||  file_id!=null){

                 if (0 === file_id)
                 {
                     return;
                 }
                 for (var i = 0; i < vm.fileArray.length; i++)
                 {
                     if (file_id === vm.fileArray[i].fileId)
                     {
                         return;
                     }
                 }
                 var fileSchedule = {
                     fileId:file_id,
                     lessonId: this.tLessonInfo.lessonId,
                     fileName: file_name
                 };
                 vm.fileArray.push(fileSchedule);
             }else{
                 alert("请选择您要添加的课件！！！")
             }
         },
         PreFileAdd:function () {
             var file_id= $("#select_prefile").find("option:selected").attr("value")
             var file_name= $("#select_prefile").find("option:selected").attr("name")
             if(file_name!=null ||  file_id!=null){
                 if (0 === file_id)
                 {
                     return;
                 }
                 for (var i = 0; i < vm.PrefileArray.length; i++)
                 {
                     if (file_id === vm.PrefileArray[i].fileId)
                     {
                         return;
                     }
                 }
                 var fileSchedule = {
                     fileId:file_id,
                     lessonId: this.tLessonInfo.lessonId,
                     fileName: file_name
                 };
                 vm.PrefileArray.push(fileSchedule);
             }else{
                 alert("请选择您要添加的课件！！！")
             }
         },
         saveFileList:function () {
             var newData= vm.AllFileArray.concat(vm.PrefileArray).concat(vm.fileArray);
             console.log(newData);
             console.log(this.tLessonInfo.lessonId)
             if(newData.length=== 0){
                 var fileSchedule = {
                     lessonId: this.tLessonInfo.lessonId
                 }
                 var data=fileSchedule;
                 console.log(data)
             }else{
                 alert("你好")
             }
             $.ajax({
                 type: "POST",
                 url: '../tlessoninfo/saveBatchFile',
                 data: JSON.stringify(newData),
                 success: function(r){
                     if(r.code === 0){
                         vm.closeAddFileFrame();
                         alert("保存成功")
                     }else{
                         alert(r.msg);
                     }
                 }
             });
         },
         loadLessonFiles:function (lessonId) {
             /**
              * 获取当前课程的所有课件
              */
             $.ajax({
                 type: "GET",
                 url: '../tlessoninfo/loadLessonFile',
                 data: {lessonId:lessonId},
                 success: function(r){
                     if(r.code === 0){
                        for(var i=0;i<r.list.length;i++){
                            if(r.list[i].fileType===1){
                                console.log(vm.PrefileArray=vm.PrefileArray.concat(r.list[i]))
                            }else{
                                console.log(vm.fileArray= vm.fileArray.concat(r.list[i]))
                            }
                        }
                     }else{
                         alert(r.msg);
                     }
                 }
             });
         },
         PreFileDel:function (event, name) {
             const index = this.PrefileArray.indexOf(name);
             this.PrefileArray.splice(index, 1);
         }
    }
});


/**
 * 课程下架
 */
function underLesson(lessonId){

    //课程下架
    vm.LessonUnder(lessonId);
}

/**
 *
 * 课程上架
 * @param lessonId
 */
function upLesson(lessonId){

    //课程上架
    vm.LessonUp(lessonId);
}

function addChapter(lessonId) {
    vm.showAddChapterFrame(lessonId);
}

function addFile(lessonId) {

    vm.showAddFileFrame(lessonId)
}