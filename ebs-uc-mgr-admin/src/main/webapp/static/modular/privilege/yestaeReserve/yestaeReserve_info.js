/**
 * 初始化保留词详情对话框
 */
var YestaeReserveInfoDlg = {
    yestaeReserveInfoData : {}
};

/**
 * 清除数据
 */
YestaeReserveInfoDlg.clearData = function() {
    this.yestaeReserveInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeReserveInfoDlg.set = function(key, val) {
    this.yestaeReserveInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeReserveInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeReserveInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeReserve.layerIndex);
}

/**
 * 收集数据
 */
YestaeReserveInfoDlg.collectData = function() {
    this.set('id').set('name').set('code');
}

/**
 * 表单数据验证
 */
YestaeReserveInfoDlg.validate = function(){
	
	var name = this.get("name");
	var code = this.get("code");
	
	if(name == ""){
		Feng.info("请填写名称！");
		return false;
	}
	if(code == ""){
		Feng.info("请填写编码！");
		return false;
	}
	
	return true;
}

/**
 * 提交添加
 */
YestaeReserveInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeReserve/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeReserve.table.refresh();
        YestaeReserveInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeReserveInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeReserveInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeReserve/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeReserve.table.refresh();
        YestaeReserveInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeReserveInfoData);
    ajax.start();
}

$(function() {

});
