/**
 * 权益管理初始化
 */
var Equity = {
    id: "EquityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Equity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'left'},
        {title: '权益编码', field: 'equityCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益名称', field: 'equityName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益简介', field: 'intro', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益状态', field: 'statusName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '线上线下类型', field: 'typeName', visible:true, align: 'center', valign: 'middle', sortable: true},
        /*{title: '删除标识', field: 'delFlag', visible: false, align: 'center', valign: 'middle', sortable: true}*/];
};

/**
 * 检查是否选中
 */
Equity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Equity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加权益
 */
Equity.openAddEquity = function () {
    var index = layer.open({
        type: 2,
        title: '添加权益',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/equity/equity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看权益详情
 */
Equity.openEquityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '权益详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/equity/equity_update/' + Equity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除权益
 */
Equity.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/equity/delete", function (data) {
	            Feng.success("删除成功!");
	            Equity.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("equityId",Equity.seItem.id);
	        ajax.start();
    	}
    	Feng.confirm("是否刪除该权益?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Equity.formParams = function () {
	var queryData = {};
	
	queryData['equityCode'] = $("#equityCode").val();
    queryData['equityName'] = $("#equityName").val();
    queryData['status'] = $("#status").val();
    queryData['type'] = $("#type").val();
    return queryData;
}

/**
 * 查询权益列表
 */
Equity.search = function () {
    Equity.table.refresh({query: Equity.formParams()});
};

/**
 * 权益启用
 */
Equity.online = function () {
	if (this.check()) {
		if(Equity.seItem.status == 1){
			Feng.info("该权益已经是启用状态！");
			return;
		}
		
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/equity/online", function (data) {
				Feng.success("操作成功!");
				Equity.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("equityId",Equity.seItem.id);
			ajax.start();
		}
		Feng.confirm("是否启用该权益?", operation);
	}
};	
/**
 * 权益停用
 */
Equity.offline = function () {
	if (this.check()) {
		if(Equity.seItem.status == 2){
			Feng.info("该权益已经是停用状态！");
			return;
		}
		
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/equity/offline", function (data) {
				Feng.success("操作成功!");
				Equity.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("equityId",Equity.seItem.id);
			ajax.start();
		}
		Feng.confirm("是否停用该权益?", operation);
	}
};


$(function () {
    var defaultColunms = Equity.initColumn();
    var table = new BSTable(Equity.id, Feng.ctxPath + "/equity/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Equity.formParams());
    Equity.table = table.init();
});
