/**
 * 用户增值服务管理初始化
 */
var Record = {
    id: "RecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Record.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '会员名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '会员手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index, field) {
                return "<p  onmouseover=\"getInfo_click('"+row.id+"')\">"+value+"</p>";
            }
        },
        {title: '增值服务名称', field: 'vasName', visible: true, align: 'center', valign: 'middle'},
        {title: '增值服务编码', field: 'vasCode', visible: true, align: 'center', valign: 'middle'},
        {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
        {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
        {title: '增值服务状态', field: 'stopName', visible: true, align: 'center', valign: 'middle'},//列表显示内容
        {title: '是否有效', field: 'invalidName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'updateTime', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Record.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Record.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户增值服务
 */
Record.openAddRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户增值服务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/record/record_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户增值服务详情
 */
Record.openRecordDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户增值服务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/record/record_update/' + Record.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开用户绑定增值服务
 */
Record.userBindVas = function () {
	var index = layer.open({
		type: 2,
		title: '用户绑定增值服务',
		area: ['800px', '420px'], //宽高
		fix: false, //不固定
		maxmin: true,
		content: Feng.ctxPath + '/record/record_bind/'
	});
	this.layerIndex = index;
};

/**
 * 删除用户增值服务
 */
Record.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/record/delete", function (data) {
	            Feng.success("删除成功!");
	            Record.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("recordId",Record.seItem.id);
	        ajax.start();
    	}
    	Feng.confirm("是否刪除该用户增值服务?", operation);
    }
};



/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Record.formParams = function () {
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
    queryData['userId'] = $("#userId").val();//数据库字段
    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['vasName'] = $("#vasName").val();
    queryData['vasCode'] = $("#vasCode").val();
    queryData['addedServiceId'] = $("#addedServiceId").val();
    queryData['stop'] = $("#stop").val();
    queryData['invalid'] = $("#invalid").val();
    return queryData;
}

/**
 * 查询用户增值服务列表
 */
Record.search = function () {
    Record.table.refresh({query: Record.formParams()});
};

getInfo_click = function(id){

    var ajax = new $ax(Feng.ctxPath + "/record/getInfo", function (data) {
        Feng.info(data.mobile);
    }, function (data) {
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });
    ajax.set("recordId",id);
    ajax.start();
}


$(function () {
	
	Feng.findCodeType("invalid", "recordInvalid", null);
	Feng.findCodeType("stop", "recordStop", null);//("数据库字段","码表字段",null)
	
    var defaultColunms = Record.initColumn();
    var table = new BSTable(Record.id, Feng.ctxPath + "/record/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Record.formParams());
    Record.table = table.init();

    
});