/**
 * 二维码场景管理初始化
 */
var YestaeQrcodeSceneSelect = {
    id: "YestaeQrcodeSceneTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeQrcodeSceneSelect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '场景名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScope', visible: false, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScopeName', visible: true, align: 'center', valign: 'middle'},
        {title: '跳转页面类型', field: 'jumpType', visible: false, align: 'center', valign: 'middle'},
        {title: '跳转页面类型', field: 'jumpTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '跳转页面', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'H5外链页URL', field: 'jumpPageUrl', visible: true, align: 'center', valign: 'middle'},
        {title: '场景描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeQrcodeSceneSelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeQrcodeSceneSelect.seItem = selected[0];
        return true;
    }
};

/**
 * 选择二维码场景
 */
YestaeQrcodeSceneSelect.chooseSubmit = function () {
    if (this.check()) {
    	var seItem = this.seItem;
    	window.parent.YestaeQrcodeSceneList.yestaeQrcodeSceneSubmit(seItem.id, seItem.name);	
    }
};

/**
 * 关闭此对话框
 */
YestaeQrcodeSceneSelect.close = function() {
    window.parent.YestaeQrcodeSceneList.close();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeQrcodeSceneSelect.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['type'] = $("#type").val();
    queryData['applyScope'] = $("#applyScope").val();
    queryData['jumpType'] = $("#jumpType").val();
    queryData['status'] = $("#status").val();

    return queryData;
}

/**
 * 查询二维码场景列表
 */
YestaeQrcodeSceneSelect.search = function () {
    YestaeQrcodeSceneSelect.table.refresh({query: YestaeQrcodeSceneSelect.formParams()});
};

$(function () {
    var defaultColunms = YestaeQrcodeSceneSelect.initColumn();
    var table = new BSTable(YestaeQrcodeSceneSelect.id, Feng.ctxPath + "/yestaeQrcodeScene/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeQrcodeSceneSelect.formParams());
    YestaeQrcodeSceneSelect.table = table.init();
    
    Feng.findCodeType("type", "qrcodeSceneType", null);
    Feng.findCodeType("applyScope", "qrcodeSceneApplyScope", null);
	Feng.findCodeType("jumpType", "qrcodeSceneJumpType", null);
});
