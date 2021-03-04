/**
 * 页面内容管理初始化
 */
var PageContent = {
    id: "PageContentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
PageContent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PageContent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PageContent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加页面内容
 */
PageContent.openAddPageContent = function () {
    var index = layer.open({
        type: 2,
        title: '添加页面内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pageContent/pageContent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看页面内容详情
 */
PageContent.openPageContentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '页面内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pageContent/pageContent_update/' + PageContent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除页面内容
 */
PageContent.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/pageContent/delete", function (data) {
	            Feng.success("删除成功!");
	            PageContent.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("pageContentId",PageContent.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该页面内容?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
PageContent.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询页面内容列表
 */
PageContent.search = function () {
    PageContent.table.refresh({query: PageContent.formParams()});
};

$(function () {
    var defaultColunms = PageContent.initColumn();
    var table = new BSTable(PageContent.id, Feng.ctxPath + "/pageContent/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(PageContent.formParams());
    PageContent.table = table.init();
});
