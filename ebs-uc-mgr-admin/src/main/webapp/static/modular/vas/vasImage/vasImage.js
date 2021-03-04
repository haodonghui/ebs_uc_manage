/**
 * 图片表管理初始化
 */
var VasImage = {
    id: "VasImageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VasImage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
VasImage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        VasImage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加图片表
 */
VasImage.openAddVasImage = function () {
    var index = layer.open({
        type: 2,
        title: '添加图片表',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/vasImage/vasImage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看图片表详情
 */
VasImage.openVasImageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '图片表详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/vasImage/vasImage_update/' + VasImage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除图片表
 */
VasImage.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/vasImage/delete", function (data) {
	            Feng.success("删除成功!");
	            VasImage.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("vasImageId",VasImage.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该图片表?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
VasImage.formParams = function () {
	var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();

    return queryData;
}

/**
 * 查询图片表列表
 */
VasImage.search = function () {
    VasImage.table.refresh({query: VasImage.formParams()});
};

$(function () {
    var defaultColunms = VasImage.initColumn();
    var table = new BSTable(VasImage.id, Feng.ctxPath + "/vasImage/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(VasImage.formParams());
    VasImage.table = table.init();
});
