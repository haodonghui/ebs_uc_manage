/**
 * 用户管理初始化
 */
var YestaeUser = {
    id: "YestaeUserTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeUser.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: 'status', field: 'status', visible: false, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'name', align: 'center', valign: 'middle', sortable: false},
        {title: '手机号', field: 'mobile', align: 'center', valign: 'middle', sortable: false},
        {title: '状态', field: 'statusName', align: 'center', valign: 'middle', sortable: false},
        {title: '来源渠道', field: 'source', align: 'center', valign: 'middle', sortable: false},
        {title: '性别', field: 'gender', align: 'center', valign: 'middle', sortable: false},
        {title: '注册时间', field: 'createTime', align: 'center', valign: 'middle', sortable: false},
        {title: '生日', field: 'birthday', align: 'center', valign: 'middle', sortable: false},
        {title: '等级', field: 'type', align: 'center', valign: 'middle', sortable: false},
        {title: '升级时间', field: 'upgradeTime', align: 'center', valign: 'middle', sortable: false},
        {title: '默认区域', field: 'address', align: 'center', valign: 'middle', sortable: false},
        {title: '详细地址', field: 'consigneeFullAddress', align: 'center', valign: 'middle', sortable: false}
    ];
};

/**
 * 检查是否选中
 */
YestaeUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeUser.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户
 */
YestaeUser.openAddYestaeUser = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeUser/yestaeUser_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户详情
 */
YestaeUser.openYestaeUserDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户详情',
            area: ['1000px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeUser/yestaeUser_update/' + YestaeUser.seItem.id
        });
        this.layerIndex = index;
    }
};
/**
 * 打开修改手机号弹窗
 */
YestaeUser.openYestaeUserMobile = function () {
	if (this.check()) {
		/*if(YestaeUser.seItem.status != 0){
			Feng.info("请先停用该用户！");
			return false;
		}*/
		
		var index = layer.open({
			type: 1,
			title: '修改手机号',
			area: ['600px', '400px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: YestaeUser.mobileLayer,
			success: function(layero){
				$("#sourceMobile").val("");
				var ajax = new $ax(Feng.ctxPath + "/yestaeUser/detail/"+YestaeUser.seItem.id, function (data) {
					if(data){
						$("#sourceMobile").val(data.mobile);
					}
	    		}, function (data) {
	    			
	    		});
	    		ajax.start();
				
				$("#targetMobile").val("");
				$("#remark").val("");
			}
		});
		this.layerIndex = index;
	}
};

/**
 * 打开强制修改手机号弹窗
 */
YestaeUser.openYestaeUserMobileForce = function () {
	if (this.check()) {
		/*if(YestaeUser.seItem.status != 0){
			Feng.info("请先停用该用户！");
			return false;
		}*/

		var index = layer.open({
			type: 1,
			title: '修改手机号',
			area: ['600px', '400px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: YestaeUser.mobileLayerForce,
			success: function(layero){
				$("#sourceMobile2").val("");
				var ajax = new $ax(Feng.ctxPath + "/yestaeUser/detail/"+YestaeUser.seItem.id, function (data) {
					if(data){
						$("#sourceMobile2").val(data.mobile);
					}
				}, function (data) {

				});
				ajax.start();

				$("#targetMobile2").val("");
				$("#remark2").val("");
			}
		});
		this.layerIndex = index;
	}
};

/**
 * 删除用户
 */
YestaeUser.delete = function () {
    if (this.check()) {
    	if(YestaeUser.seItem.status != 0){
    		Feng.info("请先停用该用户！");
    		return false;
    	}
    	var operation = function() {
    		
    		var ajax = new $ax(Feng.ctxPath + "/yestaeUser/delete", function (data) {
    			Feng.success("删除成功!");
    			YestaeUser.table.refresh();
    		}, function (data) {
    			Feng.error("删除失败!" + data.responseJSON.message + "!");
    		});
    		ajax.set("yestaeUserId",YestaeUser.seItem.id);
    		ajax.start();
    	}
        
        Feng.confirm("是否刪除该用户?", operation);
    }
};
/**
 * 启用用户
 */
YestaeUser.online = function () {
	if (this.check()) {
		if(YestaeUser.seItem.status == 1){
			Feng.info("该用户已启用！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/yestaeUser/online", function (data) {
				Feng.success("操作成功!");
				YestaeUser.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("yestaeUserId",YestaeUser.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否启用该用户?", operation);
	}
};
/**
 * 停用用户
 */
YestaeUser.offline = function () {
	if (this.check()) {
		if(YestaeUser.seItem.status == 0){
			Feng.info("该用户已停用！");
			return false;
		}
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/yestaeUser/offline", function (data) {
				Feng.success("操作成功!");
				YestaeUser.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("yestaeUserId",YestaeUser.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否停用该用户?", operation);
	}
};
/**
 * 重置密码
 */
YestaeUser.resetPassword = function () {
	if (this.check()) {
		/*if(YestaeUser.seItem.status != 0){
    		Feng.info("请先停用该用户！");
    		return false;
    	}*/
		var operation = function() {
			
			var ajax = new $ax(Feng.ctxPath + "/yestaeUser/reset/password", function (data) {
				Feng.success("操作成功!");
				YestaeUser.table.refresh();
			}, function (data) {
				Feng.error("操作失败!" + data.responseJSON.message + "!");
			});
			ajax.set("yestaeUserId",YestaeUser.seItem.id);
			ajax.start();
		}
		
		Feng.confirm("是否给该用户重置密码?", operation);
	}
};
/**
 * 发送支付验证码弹窗
 */
YestaeUser.sendCode = function () {
	if (this.check()) {
		var index = layer.open({
			type: 1,
			title: '发送支付验证码',
			area: ['460px', '300px'], //宽高
			fix: false, //不固定
			maxmin: true,
			content: YestaeUser.sendCodeWindow,
			success: function(layero){
				$("#clientType").val("");
			}
		});
		this.layerIndex = index;
	}
};
/**
 * 提交手机号修改
 */
YestaeUser.editMobileSubmit = function(){
	
	var sourceMobile = $("#sourceMobile").val();
	var targetMobile = $("#targetMobile").val().trim();
	var remark = $("#remark").val().trim();
	if(targetMobile == ""){
		Feng.info("请填写新手机号！");
		return false;
	}else if(targetMobile == sourceMobile){
		Feng.info("新手机号不能与原手机号相同！");
		return false;
	}
	if(remark == ""){
		Feng.info("请填写备注！");
		return false;
	}
	
	var yestaeUser = {id: YestaeUser.seItem.id, userId: YestaeUser.seItem.userId, mobile: targetMobile, remark: remark};
	var ajax = new $ax(Feng.ctxPath + "/yestaeUser/update/mobile", function (data) {
		if(data.code == 0){
			Feng.error("操作失败!" + data.message + "!");
			return;
		}
		Feng.success("操作成功!");
		YestaeUser.table.refresh();
		layer.close(YestaeUser.layerIndex);
	}, function (data) {
		Feng.error("操作失败!" + data.responseJSON.message + "!");
	});
	ajax.set(yestaeUser);
	ajax.start();
}
/**
 * 强制提交手机号修改
 */
YestaeUser.forceEditMobileSubmit = function(){

	var sourceMobile = $("#sourceMobile2").val();
	var targetMobile = $("#targetMobile2").val().trim();
	var remark = $("#remark2").val().trim();
	if(targetMobile == ""){
		Feng.info("请填写新手机号！");
		return false;
	}else if(targetMobile == sourceMobile){
		Feng.info("新手机号不能与原手机号相同！");
		return false;
	}
	if(remark == ""){
		Feng.info("请填写备注！");
		return false;
	}

	var yestaeUser = {id: YestaeUser.seItem.id, userId: YestaeUser.seItem.userId, mobile: targetMobile, remark: remark};
	var ajax = new $ax(Feng.ctxPath + "/yestaeUser/update/mobile/forcedModification", function (data) {
		if(data.code == 0){
			Feng.error("操作失败!" + data.message + "!");
			return;
		}
		Feng.success("操作成功!");
		YestaeUser.table.refresh();
		layer.close(YestaeUser.layerIndex);
	}, function (data) {
		Feng.error("操作失败!" + data.responseJSON.message + "!");
	});
	ajax.set(yestaeUser);
	ajax.start();
}
/**
 * 提交发送支付验证码
 */
YestaeUser.sendCodeSubmit = function(){
	
	var clientType = $("#clientType").val();
	if(clientType == ""){
		Feng.info("请选择客户端类型！");
		return false;
	}
	var ajax = new $ax(Feng.ctxPath + "/yestaeUser/send/code", function (data) {
		layer.close(YestaeUser.layerIndex);
		if(data.code == 0){
			Feng.error("操作失败!" + data.message + "!");
			return;
		}
		Feng.tip("本次发送【" + YestaeUser.seItem.mobile + "】支付验证码为【" +data.verifyCode+ "】", ['380px', '168px']);
	}, function (data) {
		Feng.error("操作失败!" + data.responseJSON.message + "!");
	});
	ajax.set({yestaeUserId: YestaeUser.seItem.id, type: clientType});
	ajax.start();
}

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeUser.formParams = function () {
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
    
    var startUpgradeTime = $("#startUpgradeTime").val();
    var endUpgradeTime = $("#endUpgradeTime").val();
    if(startUpgradeTime != ""){
    	queryData['startUpgradeTime'] = (new Date(startUpgradeTime)).getTime() - 8 * 60 * 60 * 1000;
    }else{
    	queryData['startUpgradeTime'] = null;
    }
    if(endUpgradeTime != ""){
    	queryData['endUpgradeTime'] = (new Date(endUpgradeTime)).getTime() + 16 * 60 * 60 * 1000;
    }else{
    	queryData['endUpgradeTime'] = null;
    }
    
    queryData['name'] = $("#name").val();
    queryData['userId'] = $("#userId").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['type'] = $("#type").val();
    queryData['source'] = $("#source").val();

    return queryData;
}

/**
 * 查询用户列表
 */
YestaeUser.search = function () {
	
	YestaeUser.table.refresh({query: YestaeUser.formParams()});
};

$(function () {
    var defaultColunms = YestaeUser.initColumn();
    var table = new BSTable(YestaeUser.id, "/yestaeUser/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeUser.formParams());
    YestaeUser.table = table.init();
    YestaeUser.mobileLayer = $("#mobileEdit").html();
    $("#mobileEdit").remove();
	YestaeUser.mobileLayerForce = $("#mobileEditForce").html();
	$("#mobileEditForce").remove();
    YestaeUser.sendCodeWindow = $("#sendCodeWindow").html();
    $("#sendCodeWindow").remove();
    
    Feng.findCodeType("source", "source", null);
});
