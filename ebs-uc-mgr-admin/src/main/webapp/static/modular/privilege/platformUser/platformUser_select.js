/**
 * 推广人管理初始化
 */
var PlatformUserSelect = {
    id: "PlatformUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PlatformUserSelect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '关联用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '用户手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userType', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PlatformUserSelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PlatformUserSelect.seItem = selected[0];
        return true;
    }
};

/**
 * 选择推广人
 */
PlatformUserSelect.chooseSubmit = function () {
    if (this.check()) {
    	var seItem = this.seItem;
    	window.parent.PlatformUserList.platformUserSubmit(seItem.userId, seItem.name);	
    }
};

/**
 * 关闭此对话框
 */
PlatformUserSelect.close = function() {
    window.parent.PlatformUserList.close();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PlatformUserSelect.formParams = function () {
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
    queryData['userId'] = $("#userId").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['userType'] = $("#userType").val();

    return queryData;
}

/**
 * 查询推广人列表
 */
PlatformUserSelect.search = function () {
    PlatformUserSelect.table.refresh({query: PlatformUserSelect.formParams()});
};

$(function () {
    var defaultColunms = PlatformUserSelect.initColumn();
    var table = new BSTable(PlatformUserSelect.id, "/platformUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PlatformUserSelect.formParams());
    PlatformUserSelect.table = table.init();
    
    Feng.findCodeType("userType", "platformUserType", null);
});
