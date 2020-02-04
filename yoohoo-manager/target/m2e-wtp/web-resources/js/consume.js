$(function(){
	$("#date-str").val((new Date()).format("yyyy-MM-dd"));
});
var vm = new Vue({
	el:'#rrapp',
	data:{
		showType:1,
        contextPath : "${pageContext.request.contextPath",
		lessonArray:[],
        classItemArray:[],
        doingFlag:false,
        q:{
            lessonId: "",
            classId: "",
            classItemId: "",
            dateStr: (new Date()).format("yyyy-MM-dd")
        },
        consumeList:[],
        selectConsume:null,
        index:null,
        stuTypeItemArray:[],
        feeMemoItemArray:['正常扣100%：','补课(50-100)%：','测评100%：','试听(0-100)%：',
                          '旁听(0-100)%：','平台管理费5%：','学生迟到(95-100)%：','提前请假(0-80)%：',
                          '临时请假(5-100)%：','无故缺课(50-100)%:','学员网络欠佳(90-100)%：','本次优惠(90-100)%：','本次赠送扣0原因：','教师网络欠佳(90-100)%描述：','教师迟到(90-100)%描述：','教师缺勤扣0：','教师其他状况(50-100)%：'],
        feeMemoResultArray:[]
	},
    filters: {
        lessonDateScope: function (consume) {
        	var str='';
        	if(consume.lessonTimeStr && consume.lessonTimeStr.length > 10)
        	{
        		str+=consume.lessonTimeStr.substr(0,10);
        	}
        	str+="-";
        	if(consume.endTimeStr && consume.endTimeStr.length > 10)
        	{
        		str+=consume.endTimeStr.substr(0,10);
        	}
            return str;
        },
        lessonDateFilter: function(consume){
        	var str='';
        	if(consume.dateLabel && consume.dateLabel.length >= 10)
        	{
        		str+=consume.dateLabel.substr(0,4)+"年"+consume.dateLabel.substr(5,2)+"月"+consume.dateLabel.substr(8,2)+"日";
        	}
            return str;
        },
        lessontimeFilter: function(consume){
        	var str='';
        	if(consume.lessonTimeStr && consume.lessonTimeStr.length > 10)
        	{
        		str+=consume.lessonTimeStr.substr(11);
        	}
        	str+="-";
        	if(consume.endTimeStr && consume.endTimeStr.length > 10)
        	{
        		str+=consume.endTimeStr.substr(11);
        	}
            return str;
        },
        getTeacherStatus:function(status)
        {
        	//0未上课 1课中  2已上过课
        	switch(status)
	        {
        		case 0: return "未上课";
        		case 1: return "上课中";
        		case 2: return "已上课";
        		default:return status+"";
	        }
        }
    },
	created:function () {
		this.init();
    },
    updated: function () {
        vm.initDatePicker();
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
            me.query();
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
            	me.q.dateStr = ev.date.format("yyyy-MM-dd");
            });
        },
		query: function () {
			var me=this;
            $.ajax({
                type: "GET",
                url: '../consume/list-lesson',
                data: me.q,
                success: function(r){
                    if(r.code === 0){
                        me.consumeList = r.page.list;
                        for(var i=0;i<me.consumeList.length;i++){
                        	Vue.set(me.feeMemoResultArray,i,'正常扣100%：');
                        }
                    }
                }
            });
		},
		update : function(index,selectConsume){
			var me=this;
			me.selectConsume=selectConsume;
			me.index=index;
			me.showType=2;
            $.ajax({
                type: "GET",
                url: '../consume/getStuTypeItemList',
                data: {},
                success: function(r){
                    if(r.code === 0){
                        me.stuTypeItemArray = r.stuTypeItemArray;
                    }
                }
            });
		},
		changeStatus:function (idx,consume){
			for (var i=0;i<this.stuTypeItemArray.length;i++)
			{
				if(this.stuTypeItemArray[i].id == consume.id)
				{
					consume.price = this.stuTypeItemArray[i].fee;
					consume.typeName = this.stuTypeItemArray[i].teacherMemo;
				}
			}
		},
		changeFeeMemo:function (e,consume){
			Vue.set(this.feeMemoResultArray,e.target.getAttribute('data-idx'),consume.memo);
		},
		saveChange: function(selectConsume,callback){
			var me=this;
			var consumeList = selectConsume.studentConsumeList
			for(var i=0;i<consumeList.length;i++){
				var consume = consumeList[i];
				consume.memo = this.feeMemoResultArray[i];
			}
			var dataStr = JSON.stringify(selectConsume);
			confirm('确定认后系统会扣除学生的太阳花数，并且不可再次修改，请确认每个学生扣费的太阳花数量是否正确？', function(){
                if(me.doingFlag)return ;
                me.doingFlag=true;
				$.ajax({
					type: "POST",
					url: '../consume/saveSureResult',
					data: dataStr,
					success: function(r){
                        me.doingFlag=false;
						if(r.code === 0){
							alert('确认成功', function(index){
								me.query();
								if(callback)
								{
									callback();
								}
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		sure: function(){
			var me=this;
			me.saveChange(me.selectConsume,function(){me.showType=1});
		},
		goBack: function(){
			this.showType=1;
			$("#date-str").val(this.q.dateStr);
			this.query();
		},
        enter:function(schedule){
            $.ajax({
                type: "GET",
                url: '../tclass/getAssistTeacherUrl',
                data: {"roomId":schedule.roomId},
                success: function(r){
                    if(r != 0){
                        window.open().location.href=r;
                    }else{
                        alert("获取助教url失败");
                    }
                },
                error:function () {
                    alert("系统出现错误");
                }
            });
        }
	}
});