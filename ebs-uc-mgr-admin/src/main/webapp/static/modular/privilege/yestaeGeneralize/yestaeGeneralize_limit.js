/**
 * 推广明细管理初始化
 */
var YestaeGeneralize = {
    id: "YestaeGeneralizeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeGeneralize.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '推广人', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '推荐码', field: 'recommendCode', visible: true, align: 'center', valign: 'middle'},
        {title: '推广渠道一级', field: 'firstChannelName', visible: true, align: 'center', valign: 'middle'},
        {title: '推广渠道二级', field: 'secondChannelName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userType', visible: false, align: 'center', valign: 'middle'},
        {title: '场景名称', field: 'sceneName', visible: true, align: 'center', valign: 'middle'},
        {title: '推广注册人数', field: 'registNum', visible: true, align: 'center', valign: 'middle'},
        {title: '注册升级人数', field: 'registUpgradeNum', visible: true, align: 'center', valign: 'middle'},
        {title: '推广金卡人数', field: 'upgradeNum', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeGeneralize.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeGeneralize.seItem = selected[0];
        return true;
    }
};


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeGeneralize.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['recommendCode'] = $("#recommendCode").val();
    queryData['channelName'] = $("#channelName").val();
    queryData['userType'] = $("#userType").val();
    queryData['sceneName'] = $("#sceneName").val();
    queryData['orderByRegist'] = $("#orderByRegist").val();
    queryData['orderByUpgrade'] = $("#orderByUpgrade").val();


    return queryData;
}

YestaeGeneralize.export = function () {
	var str = "";
	var queryData = YestaeGeneralize.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/yestaeGeneralize/export?s=1" + str
}

/**
 * 点击渠道input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
YestaeGeneralize.onClickChannel = function (e, treeId, treeNode) {
    $("#channelName").attr("value", instance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示渠道选择树
 */
YestaeGeneralize.showChannelSelectTree = function(){
	var channelName = document.getElementById('channelName');
	var left = channelName.offsetLeft + channelName.parentNode.parentNode.offsetLeft + channelName.parentNode.offsetLeft;
	var top = channelName.offsetTop + channelName.offsetHeight;
    Feng.showInputTree("channelName", "channelContent", left, top);
}

/**
 * 查询推广明细列表
 */
YestaeGeneralize.search = function () {
    YestaeGeneralize.table.refresh({query: YestaeGeneralize.formParams()});
};

$(function () {
    var defaultColunms = YestaeGeneralize.initColumn();
    var table = new BSTable(YestaeGeneralize.id, Feng.ctxPath + "/yestaeGeneralize/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeGeneralize.formParams());
    YestaeGeneralize.table = table.init();
    
    Feng.findCodeType("userType", "platformUserType", null);
    
    
  //初始化是否末级下拉框
	//$("#ifEnd").val($("#ifEndValue").val());

	var ztree = new $ZTree("channelTree", Feng.ctxPath + "/yestaeGeneralizeChannel/tree", {code: 'SHCC'});
    ztree.bindOnClick(YestaeGeneralize.onClickChannel);
    ztree.init();
    instance = ztree;
});
