/**
 * 增值服务管理初始化
 */
var Vas = {
    id: "VasTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Vas.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '服务名称', field: 'vasName', visible: true, align: 'center', valign: 'middle'},
        {title: '服务编码', field: 'vasCode', visible: true, align: 'center', valign: 'middle'},
        {title: '机构编码', field: 'organizCode', visible: true, align: 'center', valign: 'middle'},
        {title: '机构名称', field: 'organizName', visible: true, align: 'center', valign: 'middle'},
        {title: '服务售价', field: 'salePrice', visible: true, align: 'center', valign: 'middle'},
        {title: '到期提醒（天）', field: 'remindDays', visible: true, align: 'center', valign: 'middle'},
        {title: '有效期类型', field: 'validTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '有效期类型', field: 'validType', visible: false, align: 'center', valign: 'middle'},
        {title: '服务状态', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '服务状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Vas.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Vas.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加增值服务
 */
Vas.openAddVas = function () {
    var index = layer.open({
        type: 2,
        title: '添加增值服务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vas/vas_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看增值服务详情
 */
Vas.openVasDetail = function () {
    if (this.check()) {
    	
        var index = layer.open({
            type: 2,
            title: '增值服务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vas/vas_update/' + Vas.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除增值服务
 */
Vas.delete = function () {
    if (this.check()) {
    	
    	if(Vas.seItem.status != 1){
			Feng.info("该增值服务不是待发布状态，不能删除！");
			return;
		}
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/vas/delete", function (data) {
	            Feng.success("删除成功!");
	            Vas.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("vasId",Vas.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该增值服务?", operation);
    }
};

/**
 * 发布增值服务
 */
Vas.publish = function () {
	if (this.check()) {
		
		if(Vas.seItem.status == 2){
			Feng.info("该增值服务已是发布状态！");
			return;
		}
		
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/vas/publish", function (data) {
				Feng.success("操作成功!");
				Vas.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("vasId",Vas.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否发布该增值服务?", operation);
	}
};

/**
 * 增值服务停用
 */
Vas.offline = function () {
	if (this.check()) {
		
		if(Vas.seItem.status != 2){
			Feng.info("该增值服务不是发布状态，不能停用！");
			return;
		}
		
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/vas/offline", function (data) {
				Feng.success("操作成功!");
				Vas.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("vasId",Vas.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否停用该增值服务?", operation);
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Vas.formParams = function () {
	var queryData = {};

	queryData['vasName'] = $("#vasName").val();
    queryData['vasCode'] = $("#vasCode").val();
    queryData['vasStatus'] = $("#vasStatus").val();
    queryData['validType'] = $("#validType").val();
    queryData['organizName'] = $("#organizNameFind").val();
    queryData['organizCode'] = $("#organizCode").val();

    return queryData;
}

/**
 * 查询增值服务列表
 */
Vas.search = function () {
    Vas.table.refresh({query: Vas.formParams()});
};

$(function () {
	
	Feng.findCodeType("vasStatus", "vasStatus", null);
	Feng.findCodeType("validType", "vasValidType", null);
	
    var defaultColunms = Vas.initColumn();
    var table = new BSTable(Vas.id, Feng.ctxPath + "/vas/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Vas.formParams());
    Vas.table = table.init();
});
