/**
 * 用户等级变动明细管理初始化
 */
var YestaeUserModifyGradeLog = {
    id: "YestaeUserModifyGradeLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserModifyGradeLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
        {title: '时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '原等级', field: 'sourceCard', visible: true, align: 'center', valign: 'middle'},
        {title: '变动后等级', field: 'targetCard', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '操作人', field: 'operatorName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUserModifyGradeLog.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询用户等级变动明细列表
 */
YestaeUserModifyGradeLog.search = function () {
    YestaeUserModifyGradeLog.table.refresh({query: YestaeUserModifyGradeLog.formParams()});
};

$(function () {
    var defaultColunms = YestaeUserModifyGradeLog.initColumn();
    var table = new BSTable(YestaeUserModifyGradeLog.id, "/yestaeUserModifyGradeLog/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUserModifyGradeLog.formParams());
    YestaeUserModifyGradeLog.table = table.init();
});
