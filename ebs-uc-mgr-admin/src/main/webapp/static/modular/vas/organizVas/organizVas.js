/**
 * 机构服务管理初始化
 */
var OrganizVas = {
    id: "OrganizVasTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
OrganizVas.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
OrganizVas.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        OrganizVas.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加机构服务
 */
OrganizVas.openAddOrganizVas = function () {
    var index = layer.open({
        type: 2,
        title: '添加机构服务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/organizVas/organizVas_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看机构服务详情
 */
OrganizVas.openOrganizVasDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '机构服务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/organizVas/organizVas_update/' + OrganizVas.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除机构服务
 */
OrganizVas.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/organizVas/delete", function (data) {
	            Feng.success("删除成功!");
	            OrganizVas.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("organizVasId",OrganizVas.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该机构服务?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
OrganizVas.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询机构服务列表
 */
OrganizVas.search = function () {
    OrganizVas.table.refresh({query: OrganizVas.formParams()});
};

$(function () {
    var defaultColunms = OrganizVas.initColumn();
    var table = new BSTable(OrganizVas.id, Feng.ctxPath + "/organizVas/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(OrganizVas.formParams());
    OrganizVas.table = table.init();
});
