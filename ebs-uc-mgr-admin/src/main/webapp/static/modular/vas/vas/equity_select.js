/**
 * 权益绑定商品管理初始化
 */
var EquitySelect = {
    id: "EquitySelectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
EquitySelect.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'left'},
        {title: '权益编码', field: 'equityCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益名称', field: 'equityName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益简介', field: 'intro', visible:false, align: 'center', valign: 'middle', sortable: true},
        {title: '权益状态', field: 'statusName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益状态', field: 'status', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '线上线下类型', field: 'typeName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '线上线下类型', field: 'type', visible: false, align: 'center', valign: 'middle', sortable: true}
        ];
};

/**
 * 检查是否选中
 */
EquitySelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        EquitySelect.seItem = selected[0];
        return true;
    }
};

/**
 * 查询权益列表
 */
EquitySelect.search = function () {
    var queryData = {};
    queryData['equityName'] = $("#equityName").val();
    EquitySelect.table.refresh({query: queryData});
};

EquitySelect.addEquity = function(){
	var selected = $('#' + this.id).bootstrapTable('getSelections');
	for(i = 0; i < selected.length; i++){
		var data = selected[i];
		var vasEquity = {};
		vasEquity.equityId = data.id;
		vasEquity.equityCode = data.equityCode;
		vasEquity.equityName = data.equityName;
		vasEquity.statusName = data.statusName;
		vasEquity.typeName = data.typeName;
		EquityBind.equityList[data.id] = vasEquity;
	}
	$('#' + this.id).bootstrapTable('uncheckAll');
	EquityBind.search();
}

$(function () {
    var defaultColunms = EquitySelect.initColumn();
    var table = new BSTable(EquitySelect.id, "/equity/list", defaultColunms);
    table.setPaginationType("server");
    EquitySelect.table = table.init();
});
