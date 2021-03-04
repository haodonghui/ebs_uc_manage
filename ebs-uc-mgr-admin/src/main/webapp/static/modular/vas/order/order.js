/**
 * 订单管理初始化
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'left'},
        {title: '订单编号', field: 'orderNo', visible: true, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: 'addedServiced', field: 'addedServiceId', visible: false, align: 'center', valign: 'middle'},
        {title: '会员名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '会员手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '增值服务名称', field: 'vasName', visible: true, align: 'center', valign: 'middle'},
        {title: '增值服务编码', field: 'vasCode', visible: true, align: 'center', valign: 'middle'},
        {title: '支付单号', field: 'payNo', visible: true, align: 'center', valign: 'middle'},
        {title: '订单支付总金额', field: 'payAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '支付平台订单号', field: 'payOrderNo', visible: true, align: 'center', valign: 'middle'},
        {title: '支付方式', field: 'payTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '支付币种', field: 'currency', visible: false, align: 'center', valign: 'middle'},
        {title: '支付币种汇率', field: 'curRate', visible: false, align: 'center', valign: 'middle'},
        {title: '发起支付时间', field: 'initPayTime', visible: false, align: 'center', valign: 'middle'},
        {title: '支付平台回调时间', field: 'notifyPayTime', visible: false, align: 'center', valign: 'middle'},
        {title: '支付状态', field: 'payStateName', visible: true, align: 'center', valign: 'middle'},
        {title: '年度', field: 'createYear', visible: true, align: 'center', valign: 'middle'},
        {title: '月份', field: 'createMonth', visible: true, align: 'center', valign: 'middle'},
        /*{title: '状态(隐藏)', field: 'orderState', visible: false, align: 'center', valign: 'middle'},*/
        {title: '订单类型', field: 'orderTypeName', visible: false, align: 'center', valign: 'middle'},
	    {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
	    {title: '修改时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'}];
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单
 */
Order.openAddOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/order_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单详情
 */
Order.openOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/order_update/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单
 */
Order.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/order/delete", function (data) {
	            Feng.success("删除成功!");
	            Order.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("orderId",Order.seItem.id);
	        ajax.start();
    	}
    	Feng.confirm("是否刪除该订单?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Order.formParams = function () {
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
    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['vasName'] = $("#vasName").val();
    queryData['vasCode'] = $("#vasCode").val();
    queryData['orderNo'] = $("#orderNo").val();
    queryData['payAmount'] = $("#payAmount").val();
    queryData['payPt'] = $("#payPt").val();
    queryData['payOrderNo'] = $("#payOrderNo").val();
    queryData['payType'] = $("#payType").val();
    queryData['payState'] = $("#payState").val();
    queryData['orderstate'] = $("#orderstate").val();
    
    return queryData;
}

Order.export = function () {
	var str = "";
	var queryData = Order.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/order/export?s=1" + str
}

/**
 * 查询订单列表
 */
Order.search = function () {
    Order.table.refresh({query: Order.formParams()});
};

$(function () {
	//Feng.findCodeType("payState", "payStatus", null);
	
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, Feng.ctxPath + "/order/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Order.formParams());
    Order.table = table.init();
});
