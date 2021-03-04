/**
 * 推广人管理初始化
 */
var YestaeGeneralizeUserSelect = {
    id: "YestaeGeneralizeUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeGeneralizeUserSelect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '推广人名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '推广人手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '推荐码', field: 'recommendCode', visible: true, align: 'center', valign: 'middle'},
        {title: '推广渠道', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
        {title: '是否益购会员', field: 'ifYestaeUser', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '关联用户手机号', field: 'userMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '关联用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeGeneralizeUserSelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeGeneralizeUserSelect.seItem = selected[0];
        return true;
    }
};

/**
 * 选择推广人
 */
YestaeGeneralizeUserSelect.chooseSubmit = function () {
    if (this.check()) {
    	var seItem = this.seItem;
    	window.parent.YestaeGeneralizeUserList.yestaeGeneralizeUserSubmit(seItem.id, seItem.name);	
    }
};

/**
 * 关闭此对话框
 */
YestaeGeneralizeUserSelect.close = function() {
    window.parent.YestaeGeneralizeUserList.close();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeGeneralizeUserSelect.formParams = function () {
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
    queryData['recommendCode'] = $("#recommendCode").val();
    queryData['channelName'] = $("#channelName").val();
    queryData['ifYestaeUser'] = $("#ifYestaeUser").val();
    queryData['userMobile'] = $("#userMobile").val();
    queryData['userName'] = $("#userName").val();
    queryData['inviterIsFCode'] = $("#inviterIsFCode").val();

    return queryData;
}

/**
 * 查询推广人列表
 */
YestaeGeneralizeUserSelect.search = function () {
    YestaeGeneralizeUserSelect.table.refresh({query: YestaeGeneralizeUserSelect.formParams()});
};

$(function () {
    var defaultColunms = YestaeGeneralizeUserSelect.initColumn();
    var table = new BSTable(YestaeGeneralizeUserSelect.id, "/yestaeGeneralizeUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeGeneralizeUserSelect.formParams());
    YestaeGeneralizeUserSelect.table = table.init();
});
