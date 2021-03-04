/**
 * 用户管理初始化
 */
var YestaeUserSelect = {
    id: "YestaeUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserSelect.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'userId', field: 'userId', visible: false, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', align: 'center', valign: 'middle', sortable: false},
        {title: '手机号', field: 'mobile', align: 'center', valign: 'middle', sortable: false},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: false},
        {title: '来源渠道', field: 'source', align: 'center', valign: 'middle', sortable: false},
        {title: '性别', field: 'gender', align: 'center', valign: 'middle', sortable: false},
        {title: '注册时间', field: 'createTime', align: 'center', valign: 'middle', sortable: false},
        {title: '生日', field: 'birthday', align: 'center', valign: 'middle', sortable: false},
        {title: '等级', field: 'type', align: 'center', valign: 'middle', sortable: false},
        {title: '升级时间', field: 'upgradeTime', align: 'center', valign: 'middle', sortable: false},
        {title: '默认区域', field: 'address', align: 'center', valign: 'middle', sortable: false}
    ];
};

/**
 * 检查是否选中
 */
YestaeUserSelect.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeUserSelect.seItem = selected[0];
        return true;
    }
};


/**
 * 选择用户
 */
YestaeUserSelect.chooseSubmit = function () {
    if (this.check()) {
    	var seItem = this.seItem;
    	window.parent.YestaeUserList.yestaeUserSubmit(seItem.userId, seItem.name);	
    }
};

/**
 * 关闭此对话框
 */
YestaeUserSelect.close = function() {
    window.parent.YestaeUserList.close();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUserSelect.formParams = function () {
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

    return queryData;
}

/**
 * 查询用户列表
 */
YestaeUserSelect.search = function () {
	
	YestaeUserSelect.table.refresh({query: YestaeUserSelect.formParams()});
};

$(function () {
    var defaultColunms = YestaeUserSelect.initColumn();
    var table = new BSTable(YestaeUserSelect.id, "/yestaeUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUserSelect.formParams());
    YestaeUserSelect.table = table.init();
});
