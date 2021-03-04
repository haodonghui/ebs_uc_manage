/**
 * 推广人管理初始化
 */
var YestaeGeneralizeUser = {
    id: "YestaeGeneralizeUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeGeneralizeUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '推广人名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '推广人手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '推荐码', field: 'recommendCode', visible: true, align: 'center', valign: 'middle'},
        {title: '推广渠道', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userType', visible: false, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '关联用户手机号', field: 'userMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '关联用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeGeneralizeUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeGeneralizeUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加推广人
 */
YestaeGeneralizeUser.openAddYestaeGeneralizeUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加推广人',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeGeneralizeUser/yestaeGeneralizeUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看推广人详情
 */
YestaeGeneralizeUser.openYestaeGeneralizeUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '推广人详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeGeneralizeUser/yestaeGeneralizeUser_update/' + YestaeGeneralizeUser.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除推广人
 */
YestaeGeneralizeUser.delete = function () {
    if (this.check()) {
    	
    	var operation = function() {
    		
    		var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeUser/delete", function (data) {
    			Feng.success("删除成功!");
    			YestaeGeneralizeUser.table.refresh();
    		}, function (data) {
    			Feng.error("删除失败!" + data.responseJSON.message + "!");
    		});
    		ajax.set("yestaeGeneralizeUserId",YestaeGeneralizeUser.seItem.id);
    		ajax.start();
    	}
    	
    	Feng.confirm("刪除该推广人会同时删除关联的二维码，是否刪除该推广人?", operation);
    }
};

YestaeGeneralizeUser.doImport = function(){
	$("#excelFile").click();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeGeneralizeUser.formParams = function () {
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
    queryData['userMobile'] = $("#userMobile").val();
    queryData['userName'] = $("#userName").val();
    queryData['userType'] = $("#userType").val();

    return queryData;
}

/**
 * 查询推广人列表
 */
YestaeGeneralizeUser.search = function () {
    YestaeGeneralizeUser.table.refresh({query: YestaeGeneralizeUser.formParams()});
};

$(function () {
    var defaultColunms = YestaeGeneralizeUser.initColumn();
    var table = new BSTable(YestaeGeneralizeUser.id, "/yestaeGeneralizeUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeGeneralizeUser.formParams());
    YestaeGeneralizeUser.table = table.init();
    
    $("#excelFile").change(function(){
		if($("#excelFile").val() == ""){
			return;
		}
		$("#excelForm").submit();
	});
    
    $("#excelForm").submit(function(){
    	Feng.loadOpen();
		var that = this;
		$(this).ajaxSubmit({
            type: "post",
            url: Feng.ctxPath + "/yestaeGeneralizeUser/export",
            data:{},
            success: function (data) {
            	Feng.loadClose();
            	Feng.success("导入成功!");
            	YestaeGeneralizeUser.search();
            }, 
            error: function (msg) {
            	Feng.loadClose();
            	Feng.error("导入失败!"); 
            },
            timeout:60000
        });
		return false;
	});
    
    Feng.findCodeType("userType", "platformUserType", null);
});
