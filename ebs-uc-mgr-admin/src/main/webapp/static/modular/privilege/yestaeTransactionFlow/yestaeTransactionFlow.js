/**
 * 用户消费流水管理初始化
 */
var YestaeTransactionFlow = {
    id: "YestaeTransactionFlowTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeTransactionFlow.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '类型', field: 'flowType', visible: true, align: 'center', valign: 'middle'},
        {title: '支付方式', field: 'payMethod', visible: true, align: 'center', valign: 'middle'},
        {title: '金额（元）', field: 'amountMoney', visible: true, align: 'center', valign: 'middle'},
        {title: '关联订单号', field: 'orderNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '关联交易单号', field: 'tradeNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '第三方流水号', field: 'flowNumber', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeTransactionFlow.formParams = function () {
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
    queryData['flowType'] = $("#flowType").val();
    queryData['orderNumber'] = $("#orderNumber").val();
    queryData['tradeNumber'] = $("#tradeNumber").val();
    queryData['flowNumber'] = $("#flowNumber").val();
    queryData['name'] = $("#name").val();
    queryData['payMethod'] = $("#payMethod").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['amountMoneyEgt'] = $("#amountMoneyEgt").val();
    queryData['amountMoneyElt'] = $("#amountMoneyElt").val();

    return queryData;
}

YestaeTransactionFlow.export = function () {
	var str = "";
	var queryData = YestaeTransactionFlow.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/yestaeTransactionFlow/export?s=1" + str
}

/**
 * 查询用户消费流水列表
 */
YestaeTransactionFlow.search = function () {
	
	var egt = parseInt($("#amountMoneyEgt").val());
	var elt = parseInt($("#amountMoneyElt").val());
	if(egt > elt){
		Feng.info("【金额大于等于】填写的数量不能大于【金额小于等于】填写的数量");
		return;
	}
	
    YestaeTransactionFlow.table.refresh({query: YestaeTransactionFlow.formParams()});
};

$(function () {
	Feng.findCodeType("payMethod", "payMethod", null);
	Feng.findCodeType("flowType", "flowType", null);
	
    var defaultColunms = YestaeTransactionFlow.initColumn();
    var table = new BSTable(YestaeTransactionFlow.id, "/yestaeTransactionFlow/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeTransactionFlow.formParams());
    YestaeTransactionFlow.table = table.init();
});
