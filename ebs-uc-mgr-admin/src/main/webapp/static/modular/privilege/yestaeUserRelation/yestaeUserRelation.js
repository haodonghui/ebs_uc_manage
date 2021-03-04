/**
 * 用户邀请关系管理初始化
 */
var YestaeUserRelation = {
    id: "YestaeUserRelationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUserRelation.initColumn = function () {
    return [
        {field: 'selectItem', radio: false},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '注册推荐人', field: 'inviterName', visible: true, align: 'center', valign: 'middle'},
        {title: '注册推荐人手机号', field: 'inviterMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '升级推荐人', field: 'upgradeName', visible: true, align: 'center', valign: 'middle'},
        {title: '升级推荐人手机号', field: 'upgradeMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '注册时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};


/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUserRelation.formParams = function () {
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
    queryData['userId'] = $("#userId").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['inviterName'] = $("#inviterName").val();
    queryData['inviterMobile'] = $("#inviterMobile").val();
    queryData['upgradeName'] = $("#upgradeName").val();
    queryData['upgradeMobile'] = $("#upgradeMobile").val();

    return queryData;
}

/**
 * 查询用户邀请关系列表
 */
YestaeUserRelation.search = function () {
	
	YestaeUserRelation.table.refresh({query: YestaeUserRelation.formParams()});
};

$(function () {
    var defaultColunms = YestaeUserRelation.initColumn();
    var table = new BSTable(YestaeUserRelation.id, "/yestaeGeneralize/relationList", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUserRelation.formParams());
    YestaeUserRelation.table = table.init();
});
