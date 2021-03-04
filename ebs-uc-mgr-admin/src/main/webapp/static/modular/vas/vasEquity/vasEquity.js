/**
 * 增值服务权益管理初始化
 */
var VasEquity = {
    id: "VasEquityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VasEquity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VasEquity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VasEquity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加增值服务权益
 */
VasEquity.openAddVasEquity = function () {
    var index = layer.open({
        type: 2,
        title: '添加增值服务权益',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vasEquity/vasEquity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看增值服务权益详情
 */
VasEquity.openVasEquityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '增值服务权益详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vasEquity/vasEquity_update/' + VasEquity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除增值服务权益
 */
VasEquity.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/vasEquity/delete", function (data) {
	            Feng.success("删除成功!");
	            VasEquity.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("vasEquityId",VasEquity.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该增值服务权益?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
VasEquity.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询增值服务权益列表
 */
VasEquity.search = function () {
    VasEquity.table.refresh({query: VasEquity.formParams()});
};

$(function () {
    var defaultColunms = VasEquity.initColumn();
    var table = new BSTable(VasEquity.id, Feng.ctxPath + "/vasEquity/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(VasEquity.formParams());
    VasEquity.table = table.init();
});
