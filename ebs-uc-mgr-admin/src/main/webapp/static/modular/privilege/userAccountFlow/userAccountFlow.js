/**
 * 用户账户流水管理初始化
 */
var UserAccountFlow = {
    id: "UserAccountFlowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserAccountFlow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '账户编码', field: 'accountNo', visible: true, align: 'center', valign: 'middle'},
        {title: '方向', field: 'type', visible: true, align: 'center', valign: 'middle'},
        {title: '发生金额（元）', field: 'amount', visible: true, align: 'center', valign: 'middle'},
        {title: '当期余额（元）', field: 'laterAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '交易类型', field: 'tradeType', visible: true, align: 'center', valign: 'middle'},
        {title: '充值类型', field: 'payType', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '大益卡流水号', field: 'tradeNo', visible: true, align: 'center', valign: 'middle'},
        {title: '第三方交易单号', field: 'thirdpartyTradeNo', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserAccountFlow.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserAccountFlow.seItem = selected[0];
        return true;
    }
};

UserAccountFlow.export = function () {
	var str = "";
	var queryData = UserAccountFlow.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/userAccountFlow/export?s=1" + str
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserAccountFlow.formParams = function () {
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
    queryData['tradeNo'] = $("#tradeNo").val();
    queryData['thirdpartyTradeNo'] = $("#thirdpartyTradeNo").val();
    queryData['tradeType'] = $("#tradeType").val();
    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['type'] = $("#type").val();
    queryData['payType'] = $("#payType").val();
    queryData['accountNo'] = $("#accountNo").val();
    queryData['amountEgt'] = $("#amountEgt").val();
    queryData['amountElt'] = $("#amountElt").val();

    return queryData;
}

/**
 * 查询用户账户流水列表
 */
UserAccountFlow.search = function () {
	
	var egt = parseInt($("#amountEgt").val());
	var elt = parseInt($("#amountElt").val());
	if(egt > elt){
		Feng.info("【发生金额大于等于】填写的数量不能大于【发生金额小于等于】填写的数量");
		return;
	}
	
    UserAccountFlow.table.refresh({query: UserAccountFlow.formParams()});
};

$(function () {
    var defaultColunms = UserAccountFlow.initColumn();
    var table = new BSTable(UserAccountFlow.id, "/userAccountFlow/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(UserAccountFlow.formParams());
    UserAccountFlow.table = table.init();
    
    Feng.findCodeType("tradeType", "tradeType", null);
    Feng.findCodeType("payType", "payType", null);
});
