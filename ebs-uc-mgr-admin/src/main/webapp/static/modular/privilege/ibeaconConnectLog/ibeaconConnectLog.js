/**
 * ibeacon连接记录管理初始化
 */
var IbeaconConnectLog = {
    id: "IbeaconConnectLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
IbeaconConnectLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '设备名/Mac地址', field: 'mac', visible: true, align: 'center', valign: 'middle'},
        {title: 'UUID', field: 'uuid', visible: true, align: 'center', valign: 'middle'},
        {title: '是否有效', field: 'status', visible: true, align: 'center', valign: 'middle', 
        	formatter: function(value, row, index){
        		switch(value){
	        		case 0 : return '否'; 
	        		case 1 : return '是'; 
	        		default: return '';
        		}
        	}
        },
        {title: '访问时间', field: 'createTimeStr', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
IbeaconConnectLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        IbeaconConnectLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加ibeacon连接记录
 */
IbeaconConnectLog.openAddIbeaconConnectLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加ibeacon连接记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ibeaconConnectLog/ibeaconConnectLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看ibeacon连接记录详情
 */
IbeaconConnectLog.openIbeaconConnectLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'ibeacon连接记录详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ibeaconConnectLog/ibeaconConnectLog_update/' + IbeaconConnectLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除ibeacon连接记录
 */
IbeaconConnectLog.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/ibeaconConnectLog/delete", function (data) {
	            Feng.success("删除成功!");
	            IbeaconConnectLog.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("ibeaconConnectLogId",IbeaconConnectLog.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该ibeacon连接记录?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
IbeaconConnectLog.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询ibeacon连接记录列表
 */
IbeaconConnectLog.search = function () {
    IbeaconConnectLog.table.refresh({query: IbeaconConnectLog.formParams()});
};

/**
 * 更新uuid
 */
IbeaconConnectLog.updateUuid = function () {
	var uuid = $('#uuid').val();
	if(uuid == ''){
		Feng.info("请填写实时UUID！");
		return;
	}
	var operation = function() {
    	
        var ajax = new $ax(Feng.ctxPath + "/ibeaconConnectLog/update/uuid", function (data) {
        	IbeaconConnectLog.initUuid();
        	$('#uuid').val('');
            Feng.success("更新成功!");
            IbeaconConnectLog.table.refresh();
        }, function (data) {
            Feng.error("更新失败!" + data.responseJSON.message + "!");
        });
        ajax.set("uuid", $('#uuid').val());
        ajax.start();
	}
	
	Feng.confirm("是否更新该ibeacon的uuid?", operation);
};

IbeaconConnectLog.initUuid = function(){
	var ajax = new $ax(Feng.ctxPath + "/ibeaconConnectLog/get/uuid", function (data) {
        $("#currentUuid").html(data.uuid? data.uuid: '');
    }, function (data) {
    });
    ajax.start();
}

$(function () {
    var defaultColunms = IbeaconConnectLog.initColumn();
    var table = new BSTable(IbeaconConnectLog.id, Feng.ctxPath + "/ibeaconConnectLog/list", defaultColunms);
    table.setPaginationType("client");
    table.setQueryParams(IbeaconConnectLog.formParams());
    IbeaconConnectLog.table = table.init();
    
    IbeaconConnectLog.initUuid();
});
