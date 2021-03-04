/**
 * 用户会员等级管理初始化
 */
var YestaeUserGrade = {
    id: "YestaeUserGradeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserGrade.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '等级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '所需消费金额（元）', field: 'money', visible: true, align: 'center', valign: 'middle'},
        {title: '会员折扣', field: 'discount', visible: true, align: 'center', valign: 'middle'},
        {title: '是否默认', field: 'ifDefault', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
YestaeUserGrade.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeUserGrade.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户会员等级
 */
YestaeUserGrade.openAddYestaeUserGrade = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户会员等级',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeUserGrade/yestaeUserGrade_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户会员等级详情
 */
YestaeUserGrade.openYestaeUserGradeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户会员等级修改',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeUserGrade/yestaeUserGrade_update/' + YestaeUserGrade.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户会员等级
 */
YestaeUserGrade.delete = function () {
    if (this.check()) {
    	var operation = function() {
	        var ajax = new $ax(Feng.ctxPath + "/yestaeUserGrade/delete", function (data) {
	            Feng.success("删除成功!");
	            YestaeUserGrade.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("yestaeUserGradeId",YestaeUserGrade.seItem.id);
	        ajax.start();
    	}
		
		Feng.confirm("是否删除该用户等级?", operation);
    }
};

/**
 * 查询用户会员等级列表
 */
YestaeUserGrade.search = function () {
    var queryData = {};
    queryData['name'] = $("#name").val();
    YestaeUserGrade.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = YestaeUserGrade.initColumn();
    var table = new BSTable(YestaeUserGrade.id, "/yestaeUserGrade/list", defaultColunms);
    table.setPaginationType("client");
    YestaeUserGrade.table = table.init();
});
