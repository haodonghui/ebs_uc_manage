/**
 * 初始化用户增值服务详情对话框
 */
var RecordBindDlg = {
    RecordBindData : {},
    id: "VasTable",	//表格id
    seItem: null,	//选中的条目
    table: null
};

var WarnTable = {
	id: "WarnTable",
	table: null
}

/**
 * 清除数据
 */
RecordBindDlg.clearData = function() {
    this.RecordBindData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecordBindDlg.set = function(key, val) {
    this.RecordBindData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RecordBindDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
RecordBindDlg.close = function() {
    parent.layer.close(window.parent.Record.layerIndex);
}

/**
 * 检查是否选中
 */
RecordBindDlg.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
    	RecordBindDlg.seItem = selected[0];
        return true;
    }
};

/**
 * 初始化表格的列
 */
RecordBindDlg.initColumn = function () {
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
 * 初始化表格的列
 */
WarnTable.initColumn = function () {
	return [
	        {field: 'selectItem', visible: false, radio: false},
	        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
	        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
	        {title: '备注', field: 'warn', visible: true, align: 'center', valign: 'middle'}
	        ];
};

/**
 * 提交添加
 */
RecordBindDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/record/add", function(data){
        Feng.success("添加成功!");
        window.parent.Record.table.refresh();
        RecordBindDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.RecordBindData);
    ajax.start();
}

/**
 * 用户绑定增值服务
 */
RecordBindDlg.import = function(){
	if(this.check()){
		$("#excelFile").click();
	}
}


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
RecordBindDlg.formParams = function () {
	var queryData = {};

	queryData['vasName'] = $("#vasName").val();
    queryData['vasCode'] = $("#vasCode").val();
    queryData['vasStatus'] = $("#vasStatusHidden").val();//只查询已发布的增值服务
    queryData['validType'] = $("#validType").val();
    queryData['organizName'] = $("#organizNameFind").val();
    queryData['organizCode'] = $("#organizCode").val();

    return queryData;
}

/**
 * 查询增值服务列表
 */
RecordBindDlg.search = function () {
	RecordBindDlg.table.refresh({query: RecordBindDlg.formParams()});
};


$(function() {
	
	Feng.findCodeType("vasStatus", "vasStatus", "vasStatusHidden");
	Feng.findCodeType("validType", "vasValidType", null);
	
	var defaultColunms = RecordBindDlg.initColumn();
    var table = new BSTable(RecordBindDlg.id, Feng.ctxPath + "/vas/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(RecordBindDlg.formParams());
    RecordBindDlg.table = table.init();
    
    var defaultColunmsWarn = WarnTable.initColumn();
    var tableWarn = new BSTable(WarnTable.id, "", defaultColunmsWarn);
	tableWarn.setPaginationType("client");
	tableWarn.setUniqueId("mobile");
	WarnTable.table = tableWarn.init();

	
	$("#excelFile").change(function(){
		if($("#excelFile").val() == ""){
			return;
		}
		$("#excelForm").submit();
	});
	$("#excelForm").submit(function(){
		Feng.loadOpen();
		$(this).ajaxSubmit({
            type: "post",
            url: Feng.ctxPath + "/record/import",
            data:{vasId: RecordBindDlg.seItem.id},
            success: function (data) {
            	$("#excelFile").val("");
            	Feng.loadClose();
            	if(data.code == 200){
            		 Feng.success(data.msg);
            		 if(data.warnList != null && data.warnList.length > 0){
            			 WarnTable.table.load(data.warnList);
            			 $("#vasList").hide();
            			 $("#warnList").show();
            		 }
            	}else{
            	
            		Feng.error(data.msg);
            	} 
            }, 
            error: function (data) {
            	Feng.loadClose();
            	Feng.error("导入失败!"); 
            },
            timeout:600000
    	});
		return false;

	});
});
