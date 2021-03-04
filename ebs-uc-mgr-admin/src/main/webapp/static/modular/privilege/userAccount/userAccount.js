/**
 * 用户账户管理初始化
 */
var UserAccount = {
    id: "UserAccountTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserAccount.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'state', field: 'state', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '账户编码', field: 'accountNo', visible: true, align: 'center', valign: 'middle'},
        {title: '账户状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'},
        {title: '当前余额（元）', field: 'amount', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
UserAccount.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserAccount.seItem = selected[0];
        return true;
    }
};

/**
 * 余额修改
 */
UserAccount.openUserAccountDetail = function () {
    if (this.check()) {
    	
    	if(UserAccount.seItem.state != 2){
			Feng.info("请先冻结该用户账户！");
			return false;
		}
    	
        var index = layer.open({
            type: 2,
            title: '用户账户详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userAccount/userAccount_update/' + UserAccount.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 冻结该用户账户
 */
UserAccount.freeze = function () {
	if (this.check()) {
		if(UserAccount.seItem.state == 2){
			Feng.info("该用户账户已冻结！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/userAccount/freeze", function (data) {
				Feng.success("操作成功!");
				UserAccount.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("userAccountId",UserAccount.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否冻结该用户账户?", operation);
	}
};
/**
 * 解冻该用户账户
 */
UserAccount.unfreeze = function () {
	if (this.check()) {
		if(UserAccount.seItem.status == 1){
			Feng.info("该用户账户已解冻！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/userAccount/unfreeze", function (data) {
				Feng.success("操作成功!");
				UserAccount.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("userAccountId",UserAccount.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否解冻该用户账户?", operation);
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserAccount.formParams = function () {
	var queryData = {};

    queryData['userId'] = $("#userId").val();
    queryData['accountNo'] = $("#accountNo").val();
    queryData['name'] = $("#name").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['amountEgt'] = $("#amountEgt").val();
    queryData['amountElt'] = $("#amountElt").val();
    queryData['state'] = $("#state").val();
    
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

    return queryData;
}

UserAccount.export = function () {
	var str = "";
	var queryData = UserAccount.formParams();
	for(q in queryData){
		if(queryData[q] != null && queryData[q] != ''){
			str += "&" + q + "=" + queryData[q];
		}
	}
	location.href = Feng.ctxPath + "/userAccount/export?s=1" + str
}

/**
 * 查询用户账户列表
 */
UserAccount.search = function () {
    UserAccount.table.refresh({query: UserAccount.formParams()});
};

$(function () {
    var defaultColunms = UserAccount.initColumn();
    var table = new BSTable(UserAccount.id, "/userAccount/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(UserAccount.formParams());
    UserAccount.table = table.init();
});
