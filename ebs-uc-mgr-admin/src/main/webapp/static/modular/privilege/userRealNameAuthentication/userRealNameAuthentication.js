/**
 * 实名认证管理初始化
 */
var UserRealNameAuthentication = {
    id: "UserRealNameAuthenticationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserRealNameAuthentication.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '审核状态', field: 'verify', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '手机号', field: 'loginMobile', visible: true, align: 'center', valign: 'middle'},
        {title: '真实姓名', field: 'realName', visible: true, align: 'center', valign: 'middle'},
        {title: '实名手机号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '等级', field: 'type', align: 'center', valign: 'middle', sortable: false},
        {title: '注册时间', field: 'registTime', visible: true, align: 'center', valign: 'middle'},
        {title: '审核状态', field: 'verifyChinese', visible: true, align: 'center', valign: 'middle'},
        {title: '生肖', field: 'born', visible: true, align: 'center', valign: 'middle'},
        {title: '是否有效', field: 'flag', visible: true, align: 'center', valign: 'middle'},
        {title: '申请时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '审核人', field: 'verifyName', visible: true, align: 'center', valign: 'middle'},
        {title: '审核时间', field: 'verifyTime', visible: true, align: 'center', valign: 'middle'},
        {title: '证件类型1', field: 'id1Type', visible: true, align: 'center', valign: 'middle'},
        {title: '证件号码1', field: 'id1No', visible: true, align: 'center', valign: 'middle'},
        {title: '证件类型2', field: 'id2Type', visible: true, align: 'center', valign: 'middle'},
        {title: '证件号码2', field: 'id2No', visible: true, align: 'center', valign: 'middle'},
        {title: '银行卡号', field: 'bankCardNo', visible: true, align: 'center', valign: 'middle'},
        {title: '数据来源', field: 'dataSource', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserRealNameAuthentication.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserRealNameAuthentication.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加实名认证
 */
UserRealNameAuthentication.openAddUserRealNameAuthentication = function () {
    var index = layer.open({
        type: 2,
        title: '添加实名认证',
        area: ['800px', '468px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userRealNameAuthentication/userRealNameAuthentication_add'
    });
    this.layerIndex = index;
};

/**
 * 打开实名认证审核
 */
UserRealNameAuthentication.openUserRealNameAuthenticationUpdate = function () {
    if (this.check()) {
    	
//    	if(UserRealNameAuthentication.seItem.verify != 1){
//			Feng.info("不是待审核状态，不能审核！");
//			return false;
//		}
        var index = layer.open({
            type: 2,
            title: '实名认证编辑',
            area: ['800px', '468px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userRealNameAuthentication/userRealNameAuthentication_update/' + UserRealNameAuthentication.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看实名认证详情
 */
UserRealNameAuthentication.openUserRealNameAuthenticationDetail = function () {
	if (this.check()) {
		
		var index = layer.open({
			type: 2,
			title: '实名认证详情',
			area: ['800px', '468px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: Feng.ctxPath + '/userRealNameAuthentication/detail/' + UserRealNameAuthentication.seItem.id
		});
		this.layerIndex = index;
	}
};

/**
 * 删除实名认证
 */
UserRealNameAuthentication.delete = function () {
    if (this.check()) {
    
    	var operation = function() {
    	
	        var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/delete", function (data) {
	            Feng.success("删除成功!");
	            UserRealNameAuthentication.table.refresh();
	        }, function (data) {
	            Feng.error("删除失败!" + data.responseJSON.message + "!");
	        });
	        ajax.set("userRealNameAuthenticationId",UserRealNameAuthentication.seItem.id);
	        ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该实名认证?", operation);
    }
};

UserRealNameAuthentication.initCommitCount = function(){
	var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/find/commitCount", function (data) {
		if(data.commitCount){
			$("#sourceCommitCount").val(data.commitCount);
		}
	}, function (data) {
    });
    ajax.start();
}

/**
 * 打开修改认证次数弹窗
 */
UserRealNameAuthentication.openCommitCount = function () {
		
		var index = layer.open({
			type: 1,
			title: '改认证次数',
			area: ['600px', '400px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: UserRealNameAuthentication.commitCountLayer,
			success: function(layero){
				UserRealNameAuthentication.initCommitCount();
				$("#commitCount").val("");
			}
		});
		this.layerIndex = index;
};

/**
 * 修改认证次数
 */
UserRealNameAuthentication.updateCommitCount = function () {
	var sourceCommitCount = $("#sourceCommitCount").val();
	var commitCount = $("#commitCount").val().trim();
	if(commitCount == ""){
		Feng.info("请填写认证次数！");
		return false;
	}else if(commitCount == sourceCommitCount){
		Feng.info("认证次数不能与原认证次数相同！");
		return false;
	}else if(commitCount < 5){
		Feng.info("认证次数不能小于5！");
		return false;
	}
		
	var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/update/commitCount", function (data) {
		if(data.code == 0){
			Feng.error("操作失败!" + data.message + "!");
			return;
		}
		Feng.success("操作成功!");
		layer.close(UserRealNameAuthentication.layerIndex);
	}, function (data) {
		Feng.error("操作失败!" + data.responseJSON.message + "!");
	});
	ajax.set("commitCount", commitCount);
	ajax.start();
	
};

/**
 * 实名认证置为失效
 */
UserRealNameAuthentication.disabled = function () {
	if (this.check()) {
		
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/disabled", function (data) {
				Feng.success("设置成功!");
				UserRealNameAuthentication.table.refresh();
			}, function (data) {
				Feng.error("设置失败!" + data.responseJSON.message + "!");
			});
			ajax.set("userRealNameAuthenticationId", UserRealNameAuthentication.seItem.id);
			ajax.start();
		}
		
		var msg = "";
		if(UserRealNameAuthentication.seItem.name){
			msg += "用户：" + UserRealNameAuthentication.seItem.name + "，";
		}
		if(UserRealNameAuthentication.seItem.mobile){
			msg += "手机号：" + UserRealNameAuthentication.seItem.mobile+ "，";
		}
		msg += "实名认证即将置为失效，是否确认？";
		
		Feng.confirm(msg, operation);
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserRealNameAuthentication.formParams = function () {
	var queryData = {};
	
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    if(startTime != ""){
    	queryData['startTime'] = (new Date(startTime)).getTime();
    }else{
    	queryData['startTime'] = null;
    }
    if(endTime != ""){
    	queryData['endTime'] = (new Date(endTime)).getTime();
    }else{
    	queryData['endTime'] = null;
    }
    
    var startRegistTime = $("#startRegistTime").val();
    var endRegistTime = $("#endRegistTime").val();
    if(startRegistTime != ""){
    	queryData['startRegistTime'] = (new Date(startRegistTime)).getTime();
    }else{
    	queryData['startRegistTime'] = null;
    }
    if(endRegistTime != ""){
    	queryData['endRegistTime'] = (new Date(endRegistTime)).getTime();
    }else{
    	queryData['endRegistTime'] = null;
    }

    queryData['userId'] = $("#userId").val().trim();
    queryData['name'] = $("#name").val().trim();
    queryData['bankCardNo'] = $("#bankCardNo").val().trim();
    queryData['idNo'] = $("#idNo").val().trim();
    queryData['type'] = $("#type").val();
    queryData['realName'] = $("#realName").val().trim();
    queryData['mobile'] = $("#mobile").val().trim();
    queryData['loginMobile'] = $("#loginMobile").val().trim();
    queryData['verify'] = $("#verify").val();
    queryData['verifyName'] = $("#verifyName").val();
    queryData['flag'] = $("#flag").val();
    queryData['dataSource'] = $("#dataSource").val();
    queryData['bankCardNoFlag'] = $("#bankCardNoFlag").val();

    return queryData;
}

/**
 * 查询实名认证列表
 */
UserRealNameAuthentication.search = function () {
    UserRealNameAuthentication.table.refresh({query: UserRealNameAuthentication.formParams()});
};

$(function () {
	Feng.findCodeType("dataSource", "realNameAuthDataSource", null);
	
    var defaultColunms = UserRealNameAuthentication.initColumn();
    var table = new BSTable(UserRealNameAuthentication.id, Feng.ctxPath + "/userRealNameAuthentication/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(UserRealNameAuthentication.formParams());
    UserRealNameAuthentication.table = table.init();
    
    UserRealNameAuthentication.commitCountLayer = $("#commitCountEdit").html();
    $("#commitCountEdit").remove();
});
