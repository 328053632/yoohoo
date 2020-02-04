var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		tCategoryInfo: {},
        baseData: [],
		selectNode: null,
        doingFlag: false,
		modalInfo:{
			show: false,
			model:{},
			type: 0 // 0: 新增, 1: 修改
		}
	},
    created: function () {
		this.treeDate();
    },
	methods: {
        add: function () {
            if (!this.selectNode) {
                alert('请选择需要添加的节点');
                return;
            }
            if (this.selectNode.level > 0) {
                alert('该节点下无法添加新的节点');
                return;
            }
            this.modalInfo.type = 0;
            Vue.set(this.modalInfo, 'model', {});
            this.showModal();
        },
        update: function () {
            if (!this.selectNode) {
                alert('请选择需要修改的节点');
                return;
            }
            this.modalInfo.type = 1;
            Vue.set(this.modalInfo, 'model', JSON.parse(JSON.stringify(this.selectNode)));
            this.showModal();
        },
		del: function (event) {
			if(!this.selectNode){
                alert('请选择需要删除的节点');
                return;
			}

            if(this.selectNode.level <=0 ){
                alert('该节点无法删除');
                return;
            }

            if (this.selectNode.children && this.selectNode.children.length > 0) {
                alert('该节点下还有子节点无法删除');
                return;
            }

            var ids = [this.selectNode.id];
			var _this = this;
            confirm('确定要删除选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../tcategoryinfo/delete",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            _this.selectNode.del = true;
                            _this.clearNode(_this.baseData);
                            alert('操作成功');
                        } else {
                            alert(r.msg);
                        }
                    }
                });
            });
		},
		getInfo: function(id){
			$.get("../tcategoryinfo/info/"+id, function(r){
                vm.tCategoryInfo = r.tCategoryInfo;
            });
		},
        treeDate: function () {
            var _this = this;
            $.get('../tcategoryinfo/categoryTreeData', function (resp) {
                if (resp.code == 0) {
                    _this.baseData = resp.tree;
                }
            });
        },
        changeNode: function (nodes) {
            this.selectNode = nodes[0];
        },
        showModal: function () {
            Vue.set(this.modalInfo, 'show', true);
        },
        hideModal: function () {
            Vue.set(this.modalInfo, 'show', false);
            Vue.set(this.modalInfo, 'model', {});
        },
        saveCategory: function () {
            if(this.modalInfo.model.title.length > 40){
                layer.tips('输入名字过长', '#modal-category-name');
                return;
            }
            var category = {
                categoryName: this.modalInfo.model.title,
                type: this.modalInfo.type,
                level: (this.modalInfo.type == 0 ? this.selectNode.level + 1 : this.modalInfo.model.level),
                parentId: (this.modalInfo.type == 0 ? this.selectNode.id : this.modalInfo.model.parentId),
				id: (this.modalInfo.type == 0 ? null : this.modalInfo.model.id)
            };

            var me=this;
            if(me.doingFlag)return ;
            me.doingFlag=true;

            var requestUrl = (this.modalInfo.type == 0 ? '../tcategoryinfo/save' : '../tcategoryinfo/update');
            var _this = this;
            $.ajax({
                type: "POST",
                url: requestUrl,
                data: JSON.stringify(category),
                success: function(r){
                    me.doingFlag=false;
                    if (r.code === 0) {
                        if (_this.modalInfo.type == 0) {
                            _this.selectNode.children.push(r.treeModel);
                        } else {
                            Vue.set(_this.selectNode, 'title', r.treeModel.title);
                        }
                        _this.hideModal();
                        alert('操作成功');
                    } else {
                        alert(r.msg);
                    }
                }
            });

        },
        clearNode: function (arr) {
            var len = arr.length;
            for (var i = 0; i < len; i++) {
                if (!arr[i]) {
                    continue;
                }
                if (arr[i].children && arr[i].children.length > 0) {
                    this.clearNode(arr[i].children);
                } else {
                    if (arr[i].del) {
                        arr.splice(i, 1)
                    }
                }
            }
        },
        checkNameLength: function(){
                if(this.modalInfo.model.title.length > 40){
                layer.tips('输入名字过长', '#modal-category-name');
            }
        }
	}
});