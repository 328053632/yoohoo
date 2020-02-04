$(function () {
    $("#jqGrid").jqGrid({
        url: '../file/list',
        datatype: "json",
        colModel: [
            { label: '文件ID', name: 'fileId', width: 50, key: true},
            { label: '文件名称', name: 'fileName', width: 80 },
            { label: '文件类型', name: 'fileType', width: 80,
                formatter:function(value,options,row){
                    if(parseInt(value)==1){
                        return "预习课件";
                    }
                    if(parseInt(value)==2){
                        return "上课课件";
                    }
                }},
            { label: '文件大小', name: 'fileSize', width: 50},
            { label: '创建时间', name: 'createTime', width: 80 },
            { label: '上传者', name: 'uplaodUser', width: 80 },
            {
                label: '操作', name: '', width: 50,
                formatter: function (cellvalue, options, rowObject) {

                    var btn = $('<a>',{id:"asa",class:'label label-success', style:'background-color: #204d74',  onclick:'deleteFile('+rowObject.fileId+')'})
                        .text('删除');
                    return $('<div>').append(btn).html();
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
        LibraryArray:[],
        tLibraryInfo: {"createTime":"","fileId":"","fileName":"","fileSize":"","fileType":"","fileUrl":"","uplaodUser":""},
        title:null,
        q:{
            FileId:"",
            FileType:0,
            FileName:""
        }
    },
    mounted: function(){
        this.init();
        this.initUploadPlus();

    },
    computed: function(){
    },
    methods: {
        init:function(){

            /*
            * 获取所有文件列表
            */
          /*  $.ajax({
                type: "GET",
                url: '../file/all_list',
                success: function(r){
                    if(r.code === 0){
                    }
                }
            });*/
        },
        add: function(){
            vm.showList = 2;
            vm.title = "新增";
            vm.tLibraryInfo={createTime:"",fileId:"",fileName:"",fileSize:"",fileType:2,fileUrl:"",uplaodUser:""}
        },
        update:function(event){
            var fileId = getSelectedRow();
            if(fileId == null){
                return;
            }
            vm.showList = 2;
            vm.title = "修改";
            this.loadFileMessage(fileId);
        },
        choiceFile: function(){
            $('#fileUpload').click();
        },
        initUploadPlus: function(){
            var me=this;
            $('#fileUpload').fileupload({
                dataType: 'json',
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                done: function (e, data) {
                    me.tLibraryInfo.fileUrl=data.result.tempPath;

                    me.tLibraryInfo.fileName=data.result.fileName;
                }
            });
        },
        saveFile: function () {
            var me=this;
            if(!me.tLibraryInfo.fileUrl){
                alert("请上传文件")
                return;
            }
            if(!me.tLibraryInfo.fileName){
                alert("请输入文件名字")
                return;
            }

            var url = vm.tLibraryInfo.fileId === "" ? "../file/save" : "../file/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.tLibraryInfo),
                success: function(r){
                    me.isDoSaving=false;
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
        reload: function (event) {
            vm.showList = 1;
            var me =this;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                page:page,
                postData:{
                    "FileId":me.q.FileId,
                    "FileType":me.q.FileType,
                    "FileName":me.q.FileName
                }
            }).trigger("reloadGrid");
        },
        query: function () {
            vm.reload();
        },
        DeleteFile:function (fileId) {
            $.ajax({
                type: "GET",
                url: "../file/delete",
                data: {'fileid':fileId},
                success: function(r){
                    if (r.FileCode===0){
                        jQuery("#jqGrid").trigger("reloadGrid");

                    }else{
                        alert("删除文件失败")
                        jQuery("#jqGrid").trigger("reloadGrid");
                    }
                }
            });
        },
        loadFileMessage:function (fileId) {
            $.ajax({
                type: "GET",
                url: "../file/getById",
                data: {fileId:fileId},
                success: function(r){
                    if(r.code===0){
                        vm.tLibraryInfo=r.file;
                    }
                }
            });
        }
    }
});

function deleteFile(fileId) {
   vm.DeleteFile(fileId);
}
