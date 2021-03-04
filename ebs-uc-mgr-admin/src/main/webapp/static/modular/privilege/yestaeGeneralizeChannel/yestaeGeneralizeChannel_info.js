/**
 * 初始化推广渠道详情对话框
 */
var YestaeGeneralizeChannelInfoDlg = {
    yestaeGeneralizeChannelInfoData : {}
};

/**
 * 清除数据
 */
YestaeGeneralizeChannelInfoDlg.clearData = function() {
    this.yestaeGeneralizeChannelInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeGeneralizeChannelInfoDlg.set = function(key, val) {
    this.yestaeGeneralizeChannelInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeGeneralizeChannelInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeGeneralizeChannelInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeGeneralizeChannel.layerIndex);
}

/**
 * 收集数据
 */
YestaeGeneralizeChannelInfoDlg.collectData = function() {
    this.set('id').set('channelCode').set('name')
    //.set('ifEnd')
    .set('pid');
}

/**
 * 表单数据验证
 */
YestaeGeneralizeChannelInfoDlg.validate = function(){
	var channelCode = this.get("channelCode");
	var name = this.get("name");
	var pid = this.get("pid");
	
	if(channelCode == ""){
		Feng.info("请填写渠道编码！");
		return false;
	}
	if(name == ""){
		Feng.info("请填写渠道名称！");
		return false;
	}
	if(pid == null || pid == ""){
		Feng.info("请选择上级渠道！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
YestaeGeneralizeChannelInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeChannel/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeGeneralizeChannel.table.refresh();
        window.parent.YestaeGeneralizeChannel.refreshZtree();
        YestaeGeneralizeChannelInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeGeneralizeChannelInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeGeneralizeChannelInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeGeneralizeChannel/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeGeneralizeChannel.table.refresh();
        window.parent.YestaeGeneralizeChannel.refreshZtree();
        YestaeGeneralizeChannelInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeGeneralizeChannelInfoData);
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
YestaeGeneralizeChannelInfoDlg.onClickChannel = function (e, treeId, treeNode) {
    $("#channelName").attr("value", instance.getSelectedVal());
    $("#pid").attr("value", treeNode.id);
};

/**
 * 显示渠道选择树
 */
YestaeGeneralizeChannelInfoDlg.showChannelSelectTree = function(){
    Feng.showInputTree("channelName", "channelContent");
}

$(function() {
	
	//初始化是否末级下拉框
	//$("#ifEnd").val($("#ifEndValue").val());

	var ztree = new $ZTree("channelTree", Feng.ctxPath + "/yestaeGeneralizeChannel/tree", {pid: 0});
    ztree.bindOnClick(YestaeGeneralizeChannelInfoDlg.onClickChannel);
    ztree.init();
    instance = ztree;
});
