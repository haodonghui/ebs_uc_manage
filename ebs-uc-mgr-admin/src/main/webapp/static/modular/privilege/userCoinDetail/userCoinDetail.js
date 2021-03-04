/**
 * 用户账户流水管理初始化
 */
var UserCoinDetail = {
    id: "UserCoinDetailTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserCoinDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '变动渠道', field: 'product', visible: true, align: 'center', valign: 'middle'},
        {title: '订单编号', field: 'orderNo', visible: true, align: 'center', valign: 'middle'},
        {title: '变动方向', field: 'direction', visible: true, align: 'center', valign: 'middle'},
        {title: '受益券金额', field: 'amount', visible: true, align: 'center', valign: 'middle'},
        {title: '交易前余额', field: 'preAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '交易后余额', field: 'laterAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '交易类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
        {title: '时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserCoinDetail.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserCoinDetail.seItem = selected[0];
        return true;
    }
};

UserCoinDetail.export = function () {
	var str = "";
	var queryData = UserCoinDetail.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/userCoinDetail/export?s=1" + str
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserCoinDetail.formParams = function () {
	var queryData = {};
	
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    if(startTime != ""){
    	queryData['startTime'] = (new Date(startTime)).getTime() - 8 * 60 * 60 * 1000;
    }else{
    	queryData['startTime'] = null;
    }
    if(endTime != ""){
    	queryData['endTime'] = (new Date(endTime)).getTime() + 16 * 60 * 60 * 1000;
    }else{
    	queryData['endTime'] = null;
    }

    queryData['userId'] = $("#userId").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['type'] = $("#type").val();
    queryData['direction'] = $("#direction").val();
    queryData['product'] = $("#product").val();
    queryData['source'] = $("#source").val();
    queryData['amount'] = $("#amount").val();
    queryData['amountEgt'] = $("#amountEgt").val();
    queryData['amountElt'] = $("#amountElt").val();

    return queryData;
}

/**
 * 查询用户账户流水列表
 */
UserCoinDetail.search = function () {
	
	var egt = parseInt($("#amountEgt").val());
	var elt = parseInt($("#amountElt").val());
	if(egt > elt){
		Feng.info("【发生金额大于等于】填写的数量不能大于【发生金额小于等于】填写的数量");
		return;
	}
	
    UserCoinDetail.table.refresh({query: UserCoinDetail.formParams()});
};

$(function () {
    var defaultColunms = UserCoinDetail.initColumn();
    var table = new BSTable(UserCoinDetail.id, "/userCoinDetail/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(UserCoinDetail.formParams());
    UserCoinDetail.table = table.init();
    
    Feng.findCodeType("product", "product", null);
    Feng.findCodeType("type", "coinType", null);
});
