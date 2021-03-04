/**
 * 保留词管理初始化
 */
var YestaeReserve = {
    id: "YestaeReserveTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeReserve.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeReserve.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeReserve.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加保留词
 */
YestaeReserve.openAddYestaeReserve = function () {
    var index = layer.open({
        type: 2,
        title: '添加保留词',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeReserve/yestaeReserve_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看保留词详情
 */
YestaeReserve.openYestaeReserveDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '保留词详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeReserve/yestaeReserve_update/' + YestaeReserve.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除保留词
 */
YestaeReserve.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/yestaeReserve/delete", function (data) {
	            Feng.success("删除成功!");
	            YestaeReserve.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("yestaeReserveId",YestaeReserve.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该保留词?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeReserve.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['code'] = $("#code").val();

    return queryData;
}

/**
 * 查询保留词列表
 */
YestaeReserve.search = function () {
    YestaeReserve.table.refresh({query: YestaeReserve.formParams()});
};

$(function () {
    var defaultColunms = YestaeReserve.initColumn();
    var table = new BSTable(YestaeReserve.id, Feng.ctxPath + "/yestaeReserve/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(YestaeReserve.formParams());
    YestaeReserve.table = table.init();
});
