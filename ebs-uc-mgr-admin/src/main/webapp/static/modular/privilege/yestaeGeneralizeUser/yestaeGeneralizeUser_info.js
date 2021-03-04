/**
 * 初始化推广人详情对话框
 */
var YestaeGeneralizeUserInfoDlg = {
    yestaeGeneralizeUserInfoData : {}
};

/**
 * 用户选择列表
 */
var PlatformUserList = {
	layerIndex: -1,
	platformUserSubmit: function(id, name){
		$("#userId").val(id);
		$("#platformUserName").val(name);
		this.close();
	},
	close: function(){
		parent.layer.restore(window.parent.YestaeGeneralizeUser.layerIndex);
		layer.close(this.layerIndex);
	}
};

/**
 * 清除用户
 */
YestaeGeneralizeUserInfoDlg.clearPlatformUser = function(){
	$("#userId").val("");
	$("#platformUserName").val("");
}

/**
 * 清除数据
 */
YestaeGeneralizeUserInfoDlg.clearData = function() {
    this.yestaeGeneralizeUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeGeneralizeUserInfoDlg.set = function(key, val) {
    this.yestaeGeneralizeUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeGeneralizeUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeGeneralizeUserInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeGeneralizeUser.layerIndex);
}

/**
 * 收集数据
 */
YestaeGeneralizeUserInfoDlg.collectData = function() {
    this.set('id').set('channelId').set('userId').set('name').set('mobile').set('remark');
}

/**
 * 验证数据
 */
YestaeGeneralizeUserInfoDlg.validate = function(){
	var channelId = this.get("channelId");
	var name = this.get("name");
	
	if(name == ""){
		Feng.info("请填写推广人名称！");
		return false;
	}
	if(channelId == null || channelId == ""){
		Feng.info("请选择渠道！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
YestaeGeneralizeUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeGeneralizeUser.table.refresh();
        YestaeGeneralizeUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeGeneralizeUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeGeneralizeUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeGeneralizeUser.table.refresh();
        YestaeGeneralizeUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeGeneralizeUserInfoData);
    ajax.start();
}

/**
 * 点击渠道input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
YestaeGeneralizeUserInfoDlg.onClickChannel = function (e, treeId, treeNode) {
    $("#channelName").attr("value", instance.getSelectedVal());
    $("#channelId").attr("value", treeNode.id);
};

/**
 * 显示渠道选择树
 */
YestaeGeneralizeUserInfoDlg.showChannelSelectTree = function(){
	
	Feng.showInputTree("channelName", "channelContent");
}

/**
 * 显示用户选择列表
 */
YestaeGeneralizeUserInfoDlg.showPlatformUserList = function(){
	parent.layer.full(window.parent.YestaeGeneralizeUser.layerIndex);
	var index = layer.open({
        type: 2,
        title: '用户选择',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/platformUser/platformUser_select',
        cancel: function(index, layero){ 
        	PlatformUserList.close(index)
        	return false; 
    	}    
    });
    this.layerIndex = index;
    PlatformUserList.layerIndex = index;
}

$(function() {

	//渠道选择的树
	var ztree = new $ZTree("channelTree", "/yestaeGeneralizeChannel/tree");
    ztree.bindOnClick(YestaeGeneralizeUserInfoDlg.onClickChannel);
    ztree.init();
    instance = ztree;
});
