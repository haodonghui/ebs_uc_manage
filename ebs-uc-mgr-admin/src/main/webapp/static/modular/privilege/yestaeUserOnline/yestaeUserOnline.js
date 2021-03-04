/**
 * 登录用户管理初始化
 */
var YestaeUserOnline = {
    id: "YestaeUserOnlineTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserOnline.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'source', field: 'source', visible: false, align: 'center', valign: 'middle'},
        {title: 'userId', field: 'userId', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '登录时间', field: 'onlineTime', visible: true, align: 'center', valign: 'middle'},
        {title: '登录平台', field: 'sourceName', visible: true, align: 'center', valign: 'middle'},
        {title: '登录IP', field: 'realIp', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeUserOnline.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeUserOnline.seItem = selected[0];
        YestaeUserOnline.seItems = [];
        for(var i = 0; i < selected.length; i++){
        	YestaeUserOnline.seItems[i] = {userId: selected[i].userId, source: selected[i].source};
        }
        return true;
    }
};



/**
 * 批量踢出登录用户
 */
YestaeUserOnline.quitBatch = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/yestaeUserOnline/quit/batch", function (data) {
	        	if(data.code == 0){
	    			Feng.error("操作失败!" + data.message + "!");
	    			return;
	    		}
	            Feng.success("操作成功!");
	            YestaeUserOnline.table.refresh();
	        }, function (data) {
	            Feng.error("操作失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("json", JSON.stringify(YestaeUserOnline.seItems));
	        ajax.start();
    	}
    	
    	Feng.confirm("是否踢出登录用户?", operation);
    }
};
/**
 * 按平台踢出登录用户
 */
YestaeUserOnline.quitBySource = function () {
		
	var operation = function() {
		
		var ajax = new $ax(Feng.ctxPath + "/yestaeUserOnline/quit/source", function (data) {
			if(data.code == 0){
				Feng.error("操作失败!" + data.message + "!");
				return;
			}
			Feng.success("操作成功!");
			YestaeUserOnline.table.refresh();
		}, function (data) {
			Feng.error("操作失败!" + data.responseJSON.message + "!");
		});
		ajax.set("source", $("#quitBySource").val());
		ajax.start();
	}
	
	Feng.confirm("是否踢出【"+$("#quitBySource option:selected").text()+"】中所有登录用户?", operation);
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUserOnline.formParams = function () {
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
	
    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['source'] = $("#source").val();

    return queryData;
}

/**
 * 查询登录用户列表
 */
YestaeUserOnline.search = function () {
    YestaeUserOnline.table.refresh({query: YestaeUserOnline.formParams()});
};

$(function () {
    var defaultColunms = YestaeUserOnline.initColumn();
    var table = new BSTable(YestaeUserOnline.id, Feng.ctxPath + "/yestaeUserOnline/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUserOnline.formParams());
    YestaeUserOnline.table = table.init();
});
