/**
 * 初始化权益详情对话框
 */
var EquityInfoDlg = {
    equityInfoData : {}
};



/**
 * 清除数据
 */
EquityInfoDlg.clearData = function() {
    this.equityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EquityInfoDlg.set = function(key, val) {
    this.equityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
EquityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
EquityInfoDlg.close = function() {
    parent.layer.close(window.parent.Equity.layerIndex);
}

/**
 * 收集数据
 */
EquityInfoDlg.collectData = function() {
    this.set('id').set('equityCode').set('equityName').set('intro').set('status').set('type').set('surfaceId').set('delFlag');
}
/**
 * 验证数据
 */
EquityInfoDlg.validate = function(){
	
	var equityCode = this.get("equityCode");
	var equityName = this.get("equityName");
	var intro = this.get("intro");
	var status = this.get("status");
	var type = this.get("type");
	var surfaceId = this.get("surfaceId");
	var delFlag = this.get("delFlag");
	
	
	if(equityCode == ""){
		Feng.info("请填写权益编码！");
		return false;
	}
	if(equityName == ""){
		Feng.info("请填写权益名称！");
		return false;
	}
	if(intro == ""){
		Feng.info("请填写权益简介！");
		return false;
	}
	
	if(status == ""){
		Feng.info("请填写权益状态！");
		return false;
	}
	if(type == ""){
		Feng.info("请填写线上线下类型！");
		return false;
	}
	if(surfaceId == ""){
		Feng.info("请添加图片！");
		return false;
	}
	
	return true;
}

/**
 * 提交添加
 */
EquityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/equity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Equity.table.refresh();
        EquityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.equityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
EquityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/equity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Equity.table.refresh();
        EquityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.equityInfoData);
    ajax.start();
}

$(function() {
	Feng.findCodeType("type", "equityType", "typeValue");
	Feng.initImage("surfaceId", "/vasImage/upload/equity");
});
