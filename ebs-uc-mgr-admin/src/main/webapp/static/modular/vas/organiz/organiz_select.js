/**
 * 机构管理初始化
 */
var OrganizSelect = {
    id: "OrganizTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrganizSelect.initColumn = function () {
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
OrganizSelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrganizSelect.seItem = selected[0];
        return true;
    }
};

/**
 * 选择机构
 */
OrganizSelect.chooseSubmit = function () {
    if (this.check()) {
    	var seItem = this.seItem;
    	window.parent.OrganizList.organizSubmit(seItem.id, seItem.organizName);	
    }
};

/**
 * 关闭此对话框
 */
OrganizSelect.close = function() {
    window.parent.OrganizList.close();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
OrganizSelect.formParams = function () {
	var queryData = {};
	
	queryData['name'] = $("#name").val();
    queryData['code'] = $("#code").val();
    queryData['status'] = $("#status").val();

    return queryData;
}

/**
 * 查询机构列表
 */
OrganizSelect.search = function () {
    OrganizSelect.table.refresh({query: OrganizSelect.formParams()});
};

$(function () {
    var defaultColunms = OrganizSelect.initColumn();
    var table = new BSTable(OrganizSelect.id, "/organiz/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(OrganizSelect.formParams());
    OrganizSelect.table = table.init();
});
