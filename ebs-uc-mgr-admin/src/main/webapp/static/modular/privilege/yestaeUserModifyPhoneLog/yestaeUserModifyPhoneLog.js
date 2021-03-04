/**
 * 用户手机号变更日志管理初始化
 */
var YestaeUserModifyPhoneLog = {
    id: "YestaeUserModifyPhoneLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserModifyPhoneLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '原手机号', field: 'sourceMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '变动后手机号', field: 'targetMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'operatorName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUserModifyPhoneLog.formParams = function () {
	var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['targetMobile'] = $("#targetMobile").val();
    queryData['sourceMobile'] = $("#sourceMobile").val();

    return queryData;
}

/**
 * 查询用户手机号变更日志列表
 */
YestaeUserModifyPhoneLog.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    queryData['targetMobile'] = $("#targetMobile").val();
    queryData['sourceMobile'] = $("#sourceMobile").val();
    YestaeUserModifyPhoneLog.table.refresh({query: YestaeUserModifyPhoneLog.formParams()});
};

$(function () {
    var defaultColunms = YestaeUserModifyPhoneLog.initColumn();
    var table = new BSTable(YestaeUserModifyPhoneLog.id, "/yestaeUserModifyPhoneLog/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUserModifyPhoneLog.formParams());
    YestaeUserModifyPhoneLog.table = table.init();
});
