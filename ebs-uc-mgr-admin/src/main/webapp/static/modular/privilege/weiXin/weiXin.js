/**
 * 微信管理初始化
 */
var WeiXin = {
    id: "WeiXinTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
WeiXin.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
WeiXin.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        WeiXin.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加微信
 */
WeiXin.openAddWeiXin = function () {
    var index = layer.open({
        type: 2,
        title: '添加微信',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/weiXin/weiXin_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看微信详情
 */
WeiXin.openWeiXinDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '微信详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/weiXin/weiXin_update/' + WeiXin.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除微信
 */
WeiXin.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/weiXin/delete", function (data) {
	            Feng.success("删除成功!");
	            WeiXin.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("weiXinId",WeiXin.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该微信?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
WeiXin.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询微信列表
 */
WeiXin.search = function () {
    WeiXin.table.refresh({query: WeiXin.formParams()});
};

$(function () {
    var defaultColunms = WeiXin.initColumn();
    var table = new BSTable(WeiXin.id, Feng.ctxPath + "/weiXin/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(WeiXin.formParams());
    WeiXin.table = table.init();
});
