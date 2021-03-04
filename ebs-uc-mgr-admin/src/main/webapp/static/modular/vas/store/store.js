/**
 * 门店管理初始化
 */
var Store = {
    id: "StoreTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Store.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '门店名称', field: 'storeName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '门店编码', field: 'storeCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '机构编码', field: 'organizCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '机构名称', field: 'organizName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '门店简介', field: 'intro', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '联系方式', field: 'tel', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '联系人', field: 'linkman', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '详细地址', field: 'address', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '省份编码', field: 'provinceCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '省份名称', field: 'provinceName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '城市编码', field: 'cityCode', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '城市名称', field: 'cityName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '地区编码', field: 'areaCode', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '地区名称', field: 'areaName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '无线账号', field: 'wifiName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '无线密码', field: 'wifiPassword', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '开始营业时间', field: 'startTime', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '结束营业时间', field: 'endTime', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '推荐标识', field: 'recommendName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'statusName', visible:true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', sortable: true}
        ];
};

/**
 * 检查是否选中
 */
Store.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Store.seItem = selected[0];
        return true;
    }
};


/**
 * 点击添加门店
 */
Store.openAddStore = function () {
    var index = layer.open({
        type: 2,
        title: '添加门店',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/store/store_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看门店详情
 */
Store.openStoreDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '门店修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/store/store_update/' + Store.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除门店
 */
Store.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/store/delete", function (data) {
	            Feng.success("删除成功!");
	            Store.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("storeId",Store.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该门店?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
Store.formParams = function () {
	var queryData = {};

    queryData['storeName'] = $("#storeName").val();
    queryData['storeCode'] = $("#storeCode").val();
    queryData['organizCode'] = $("#organizCode").val();
    queryData['recommendFlag'] = $("#recommendFlag").val();
    queryData['status'] = $("#status").val();

    return queryData;
}

/**
 * 查询门店列表
 */
Store.search = function () {
    Store.table.refresh({query: Store.formParams()});
};
/**
 * 门店启用
 */
Store.online = function () {
	if (this.check()) {
		if(Store.seItem.status == 1){
			Feng.info("该门店已经是启用状态！");
			return;
		}
		
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/store/online", function (data) {
				Feng.success("操作成功!");
				Store.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("storeId",Store.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否启用该门店?", operation);
	}
};	
/**
 * 门店停用
 */
Store.offline = function () {
	if (this.check()) {
		if(Store.seItem.status == 2){
			Feng.info("该门店已经是停用状态！");
			return;
		}
		
		var operation = function() {
			var ajax = new $ax(Feng.ctxPath + "/store/offline", function (data) {
				Feng.success("操作成功!");
				Store.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("storeId",Store.seItem.id);
			ajax.start();
		}
		Feng.confirm("是否停用该门店?", operation);
	}
};

$(function () {
	Feng.findCodeType("recommendFlag", "recommendFlag", null);
	Feng.findCodeType("status", "status", null);
	
    var defaultColunms = Store.initColumn();
    var table = new BSTable(Store.id, Feng.ctxPath + "/store/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(Store.formParams());
    Store.table = table.init();
});
