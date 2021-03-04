/**
 * 门店注册会员商城销售统计管理初始化
 */
var Sale = {
    id: "SaleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sale.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '门店名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道', field: 'secondChannelName', visible: true, align: 'center', valign: 'middle'},
        {title: '推广注册人数', field: 'registNum', visible: true, align: 'center', valign: 'middle'},
        {title: '商城消费金额', field: 'money', visible: true, align: 'center', valign: 'middle'},
        {title: '明细导出', field: 'export', visible: true, align: 'center', valign: 'middle', 
        	formatter: function(value, row, index, field) {
        		if (row.money > 0) {
        			return `
        			<button type="button" class="btn btn-primary" onclick="Sale.exportDetail('${row.id}')" >
        				明细导出
	        		</button>
        			`;
    			}
    			return '';
    		}
        }
    ];
};

/**
 * 检查是否选择时间
 */
Sale.check = function () {
	if($("#startRegistTime").val() == ""){
		Feng.info("请请选择注册时间！");
		return false;
	}
	if($("#startTime").val() == ""){
		Feng.info("请请选择消费时间！");
		return false;
	}
	return true;
};


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Sale.formParams = function () {
	var queryData = {};
	
	var startRegistTime = $("#startRegistTime").val();
	var endRegistTime = $("#endRegistTime").val();
	if(startRegistTime != ""){
		queryData['startRegistTime'] = (new Date(startRegistTime)).getTime() - 8 * 60 * 60 * 1000;
	}else{
		queryData['startRegistTime'] = null;
	}
	if(endRegistTime != ""){
		queryData['endRegistTime'] = (new Date(endRegistTime)).getTime() + 16 * 60 * 60 * 1000;
	}else{
		queryData['endRegistTime'] = null;
	}

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
    
    queryData['channelId'] = $("#channelId").val();
    queryData['generalizeUserId'] = $("#generalizeUserId").val();

    return queryData;
}

Sale.export = function () {
	if(Sale.check()){
		
		var str = "";
		var queryData = Sale.formParams();
		for(q in queryData){
			if(queryData[q] != null && queryData[q] != ''){
				str += "&" + q + "=" + queryData[q];
			}
		}
		location.href = Feng.ctxPath + "/yestaeGeneralize/sale/export?s=1" + str
	}
}

Sale.exportDetail = function (id) {
	if(Sale.check()){
		
		var str = "";
		if(id != null && id != ''){
			str = "&generalizeUserId=" + id;
		}
		var queryData = Sale.formParams();
		for(q in queryData){
			if(queryData[q] != null && queryData[q] != ''){
				str += "&" + q + "=" + queryData[q];
			}
		}
		location.href = Feng.ctxPath + "/yestaeGeneralize/sale/export/detail?s=1" + str
	}
}

/**
 * 查询门店注册会员商城销售统计列表
 */
Sale.search = function () {
	if(Sale.check()){
		
		Sale.table.refresh({query: Sale.formParams()});
	}
};

$(function () {
    var defaultColunms = Sale.initColumn();
    var table = new BSTable(Sale.id, Feng.ctxPath + "/yestaeGeneralize/sale/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Sale.formParams());
    Sale.table = table.init();
    
    Feng.initSelect("channelId", "/yestaeGeneralizeChannel/list/DYCT", {}, "name", "id", null);
    Feng.initSelect("generalizeUserId", "/yestaeGeneralizeUser/list/DYCT", {}, "name", "id", null);
    $("#channelId").change(function(){
    	Feng.initSelect("generalizeUserId", "/yestaeGeneralizeUser/list/DYCT", {channelId: $(this).val()}, "name", "id", null);
	})
});
