/**
 * 茶票绑定商品管理初始化
 */
var EquityBind = {
    id: "EquityBindTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    equityList: {}
};

/**
 * 初始化表格的列
 */
EquityBind.initColumn = function () {
    return [
        {field: 'selectItem', visible: false, radio: false},
        {title: '权益id', field: 'equityId', visible: false, align: 'center', valign: 'middle'},
        {title: '权益编码', field: 'equityCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益名称', field: 'equityName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '权益状态', field: 'statusName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '线上线下类型', field: 'typeName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '排序', field: 'sort', visible: false, align: 'center', valign: 'middle'},
        {title: '排序', field: 'sortValue', visible: true, align: 'center', valign: 'middle'
        	, formatter: function(value, row, index){
        		var sort = row.sort? row.sort: '';
        		return '<input style="border: 0;" id="sort_'+row.id+'" onBlur="EquityBind.setRowSort(this, \''+ row.equityId +'\', '+ index +')" value="'+ sort +'" onkeyup="Feng.validateNumber(\'sort_'+row.id+'\')" maxlength="9"/>';
        	}},
        {title: '操作', field: 'manage', visible: true, align: 'center', valign: 'middle'
        	, formatter : function (value, row, index) {
        		return '<button type="button" class="btn btn-danger" onclick="EquityBind.deleteEquityId(\'' + row.equityId + '\')">删除</button>'
        		;
        	}	
        }
    ];
};

EquityBind.setRowSort = function(dom, id, index){
	var row = $('#' + this.id).bootstrapTable('getRowByUniqueId', id);
	row.sort = $(dom).val();
	$('#' + this.id).bootstrapTable('updateRow', index, row);
	EquityBind.equityList[id].sort = row.sort;
}

EquityBind.getEquityIds = function(){
	var equityIds = "";
	for(key in EquityBind.equityList){
		equityIds += "," + key;
	}
	if(equityIds.length > 0){
		return equityIds.substring(1);
	}
	return "";
}

EquityBind.getVasEquityList = function(){
	var vasEquityList = [];
	for(key in EquityBind.equityList){
		vasEquityList.push(EquityBind.equityList[key]);
	}
	return vasEquityList;
}

EquityBind.setEquityIds = function(data){
	var list = data;
	for(var i = 0; i < list.length; i++){
		EquityBind.equityList[list[i].equityId] = list[i];
	}
}

EquityBind.deleteEquityId = function(equityId){
	delete EquityBind.equityList[equityId];
	EquityBind.search();
}

/**
 * 查询茶票绑定商品列表
 */
EquityBind.search = function () {
	var vasEquityList = EquityBind.getVasEquityList();
    EquityBind.table.load(vasEquityList);
};

$(function () {
	
	var defaultColunms = EquityBind.initColumn();
	var table = new BSTable(EquityBind.id, "", defaultColunms);
	table.setPaginationType("client");
	table.setUniqueId("equityId");
	
	var vasId = $("#id").val();
	
	if(vasId != ""){
		var ajax = new $ax(Feng.ctxPath + "/vasEquity/list", function (data) {
			table.setTableData(data);
			EquityBind.setEquityIds(data);
			EquityBind.table = table.init();
		}, function (data) {
		});
		ajax.set("vasId", vasId);
		ajax.start();
	}else{
		EquityBind.table = table.init();
	}
	
});
