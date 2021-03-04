/**
 * 推广渠道管理初始化
 */
var YestaeGeneralizeChannel = {
    id: "YestaeGeneralizeChannelTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    ztree: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeGeneralizeChannel.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '渠道编码', field: 'channelCode', visible: true, align: 'center', valign: 'middle'},
        {title: '渠道名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '上级渠道编码', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
        {title: '上级渠道名称', field: 'pname', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '是否末级', field: 'ifEnd', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeGeneralizeChannel.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeGeneralizeChannel.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加推广渠道
 */
YestaeGeneralizeChannel.openAddYestaeGeneralizeChannel = function () {
    var index = layer.open({
        type: 2,
        title: '添加推广渠道',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeGeneralizeChannel/yestaeGeneralizeChannel_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看推广渠道详情
 */
YestaeGeneralizeChannel.openYestaeGeneralizeChannelDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '推广渠道详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeGeneralizeChannel/yestaeGeneralizeChannel_update/' + YestaeGeneralizeChannel.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除推广渠道
 */
YestaeGeneralizeChannel.delete = function () {
    if (this.check()) {
    	
    	var operation = function() {
    		
    		var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeChannel/delete", function (data) {
    			Feng.success("删除成功!");
    			YestaeGeneralizeChannel.table.refresh();
    			YestaeGeneralizeChannel.refreshZtree();
    		}, function (data) {
    			Feng.error("删除失败!" + data.responseJSON.message + "!");
    		});
    		ajax.set("yestaeGeneralizeChannelId",YestaeGeneralizeChannel.seItem.id);
    		ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该推广渠道?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeGeneralizeChannel.formParams = function (flag) {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['channelCode'] = $("#channelCode").val();
    queryData['pname'] = $("#pname").val();
    queryData['pcode'] = $("#pcode").val();
    queryData['ifEnd'] = $("#ifEnd").val();
    if(flag){
    	queryData['pid'] = YestaeGeneralizeChannel.pid;
    }else{
    	queryData['pid'] = null;
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

    return queryData;
}

/**
 * 查询推广渠道列表
 */
YestaeGeneralizeChannel.search = function (flag) {
    YestaeGeneralizeChannel.table.refresh({query: YestaeGeneralizeChannel.formParams(flag)});
};

YestaeGeneralizeChannel.onClickChannel = function (e, treeId, treeNode) {
	YestaeGeneralizeChannel.pid = treeNode.id;
	YestaeGeneralizeChannel.search(true);
};

YestaeGeneralizeChannel.refreshZtree = function(){
	if(YestaeGeneralizeChannel.ztree){
		YestaeGeneralizeChannel.ztree.destroy();
		YestaeGeneralizeChannel.initZtree();
	}
}

YestaeGeneralizeChannel.initZtree = function(){
	var ztree = new $ZTree("channelTree", "/yestaeGeneralizeChannel/tree");
    ztree.bindOnClick(YestaeGeneralizeChannel.onClickChannel);
    ztree.init();
    YestaeGeneralizeChannel.ztree = ztree;
}

$(function () {
    var defaultColunms = YestaeGeneralizeChannel.initColumn();
    var table = new BSTable(YestaeGeneralizeChannel.id, "/yestaeGeneralizeChannel/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeGeneralizeChannel.formParams());
    YestaeGeneralizeChannel.table = table.init();
    
    YestaeGeneralizeChannel.initZtree();
});
