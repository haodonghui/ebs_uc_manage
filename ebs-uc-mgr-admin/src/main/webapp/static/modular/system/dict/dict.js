/**
 * 数据字典管理初始化
 */
var Dict = {
    id: "DictTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dict.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        //{title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '编码', field: 'code', visible: true, align: 'center', sortable: true, valign: 'middle'},
        {title: '上级编码', field: 'pcode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Dict.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Dict.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加数据字典
 */
Dict.openAddDict = function () {
    var index = layer.open({
        type: 2,
        title: '添加数据字典',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/dict/dict_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看数据字典详情
 */
Dict.openDictDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '数据字典详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/dict/dict_update/' + Dict.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除数据字典
 */
Dict.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/dict/delete", function (data) {
	            Feng.success("删除成功!");
	            Dict.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("dictId",Dict.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该数据字典?", operation);
    }
};

/**
 * 刷新缓存数据字典
 */
Dict.refresh = function () {
	var operation = function() {
		
		var ajax = new $ax(Feng.ctxPath + "/dict/refresh", function (data) {
			Feng.success("刷新缓存成功!");
			Dict.table.refresh();
		}, function (data) {
			Feng.error("刷新缓存失败!" + data.responseJSON.message + "!");
		});
		ajax.start();
	}
	
	Feng.confirm("是否刷新缓存?", operation);
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Dict.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['code'] = $("#code").val();
    queryData['pcode'] = $("#pcode").val();

    return queryData;
}

/**
 * 查询数据字典列表
 */
Dict.search = function () {
    Dict.table.refresh({query: Dict.formParams()});
};

$(function () {
    var defaultColunms = Dict.initColumn();
    var table = new BSTreeTable(Dict.id, Feng.ctxPath + "/dict/list", defaultColunms);
    table.setExpandColumn(1);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setRootCodeValue("ROOT");
    table.setExpandAll(false);
    table.init();
    Dict.table = table;
});
