/**
 * 机构管理初始化
 */
var Organiz = {
    id: "OrganizTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Organiz.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '机构名称', field: 'organizName', visible: true, align: 'center', valign: 'middle'},
        {title: '机构编码', field: 'organizCode', visible: true, align: 'center', valign: 'middle'},
        {title: '机构简称', field: 'simpleName', visible: true, align: 'center', valign: 'middle'},
        {title: '联系人', field: 'linkman', visible: false, align: 'center', valign: 'middle'},
        {title: '联系方式', field: 'tel', visible: false, align: 'center', valign: 'middle'},
        {title: '详细地址', field: 'address', visible: false, align: 'center', valign: 'middle'},
        {title: '商品分类', field: 'category', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '机构简介', field: 'intro', visible: false, align: 'center', valign: 'middle'},
        {title: '邮箱', field: 'email', visible: false, align: 'center', valign: 'middle'},
        {title: '传真', field: 'fax', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Organiz.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Organiz.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加机构
 */
Organiz.openAddOrganiz = function () {
    var index = layer.open({
        type: 2,
        title: '添加机构',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/organiz/organiz_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看机构详情
 */
Organiz.openOrganizDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '机构详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/organiz/organiz_update/' + Organiz.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除机构
 */
Organiz.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/organiz/delete", function (data) {
	            Feng.success("删除成功!");
	            Organiz.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("organizId",Organiz.seItem.id);
	        ajax.start();
    	}
    	Feng.confirm("是否刪除该机构?", operation);
    }
};
/**
 * 机构启用
 */
Organiz.online = function () {
	if (this.check()) {
		if(Organiz.seItem.status == 1){
			Feng.info("该机构已经是启用状态！");
			return;
		}
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/organiz/online", function (data) {
				Feng.success("操作成功!");
				Organiz.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("organizId",Organiz.seItem.id);
			ajax.start();
		}
		Feng.confirm("是否启用该机构?", operation);
	}
};
/**
 * 机构停用
 */
Organiz.offline = function () {
	if (this.check()) {
		if(Organiz.seItem.status == 2){
			Feng.info("该机构已经是停用状态！");
			return;
		}
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/organiz/offline", function (data) {
				Feng.success("操作成功!");
				Organiz.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("organizId",Organiz.seItem.id);
			ajax.start();
		}
		Feng.confirm("是否停用该机构?", operation);
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Organiz.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['code'] = $("#code").val();
    queryData['status'] = $("#status").val();

    
    return queryData;
}

/**
 * 查询机构列表
 */
Organiz.search = function () {
    Organiz.table.refresh({query: Organiz.formParams()});
};

$(function () {
    var defaultColunms = Organiz.initColumn();
    var table = new BSTable(Organiz.id, Feng.ctxPath + "/organiz/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Organiz.formParams());
    Organiz.table = table.init();
});
