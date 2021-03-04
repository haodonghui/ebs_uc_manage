/**
 * 二维码场景管理初始化
 */
var YestaeQrcodeScene = {
    id: "YestaeQrcodeSceneTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeQrcodeScene.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '场景名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScope', visible: false, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScopeName', visible: true, align: 'center', valign: 'middle'},
        {title: '跳转页面类型', field: 'jumpType', visible: false, align: 'center', valign: 'middle'},
        {title: '跳转页面类型', field: 'jumpTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '跳转页面', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'H5外链页URL', field: 'jumpPageUrl', visible: true, align: 'center', valign: 'middle'},
        {title: '场景描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeQrcodeScene.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeQrcodeScene.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码场景
 */
YestaeQrcodeScene.openAddYestaeQrcodeScene = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码场景',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeQrcodeScene/yestaeQrcodeScene_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码场景详情
 */
YestaeQrcodeScene.openYestaeQrcodeSceneDetail = function () {
    if (this.check()) {
    	
    	if(YestaeQrcodeScene.seItem.status != 0){
			Feng.info("请先停用该二维码场景！");
			return false;
		}
    	
        var index = layer.open({
            type: 2,
            title: '二维码场景详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeQrcodeScene/yestaeQrcodeScene_update/' + YestaeQrcodeScene.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码场景
 */
YestaeQrcodeScene.delete = function () {
    if (this.check()) {
    	
    	if(YestaeQrcodeScene.seItem.status != 0){
			Feng.info("请先停用该二维码场景！");
			return false;
		}
    	
    	var operation = function() {
    		
    		var ajax = new $ax(Feng.ctxPath + "/yestaeQrcodeScene/delete", function (data) {
    			Feng.success("删除成功!");
    			YestaeQrcodeScene.table.refresh();
    		}, function (data) {
    			Feng.error("删除失败!" + data.responseJSON.message + "!");
    		});
    		ajax.set("yestaeQrcodeSceneId",YestaeQrcodeScene.seItem.id);
    		ajax.start();
    	}
    	
    	Feng.confirm("刪除该二维码场景会同时删除关联的二维码，是否刪除该二维码场景?", operation);
    }
};

/**
 * 启用二维码场景
 */
YestaeQrcodeScene.online = function () {
	if (this.check()) {
		if(YestaeQrcodeScene.seItem.status == 1){
			Feng.info("该二维码场景已启用！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/yestaeQrcodeScene/online", function (data) {
				Feng.success("操作成功!");
				YestaeQrcodeScene.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("yestaeQrcodeSceneId",YestaeQrcodeScene.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否启用该二维码场景?", operation);
	}
};
/**
 * 停用二维码场景
 */
YestaeQrcodeScene.offline = function () {
	if (this.check()) {
		if(YestaeQrcodeScene.seItem.status == 0){
			Feng.info("该二维码场景已停用！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/yestaeQrcodeScene/offline", function (data) {
				Feng.success("操作成功!");
				YestaeQrcodeScene.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("yestaeQrcodeSceneId",YestaeQrcodeScene.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否停用该二维码场景?", operation);
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeQrcodeScene.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['type'] = $("#type").val();
    queryData['applyScope'] = $("#applyScope").val();
    queryData['jumpType'] = $("#jumpType").val();
    queryData['status'] = $("#status").val();

    return queryData;
}

/**
 * 查询二维码场景列表
 */
YestaeQrcodeScene.search = function () {
    YestaeQrcodeScene.table.refresh({query: YestaeQrcodeScene.formParams()});
};

$(function () {
	Feng.findCodeType("type", "qrcodeSceneType", null);
	Feng.findCodeType("applyScope", "qrcodeSceneApplyScope", null);
	Feng.findCodeType("jumpType", "qrcodeSceneJumpType", null);
	
    var defaultColunms = YestaeQrcodeScene.initColumn();
    var table = new BSTable(YestaeQrcodeScene.id, Feng.ctxPath + "/yestaeQrcodeScene/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeQrcodeScene.formParams());
    YestaeQrcodeScene.table = table.init();
});
