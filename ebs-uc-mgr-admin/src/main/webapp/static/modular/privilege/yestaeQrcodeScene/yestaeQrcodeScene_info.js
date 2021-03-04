/**
 * 初始化二维码场景详情对话框
 */
var YestaeQrcodeSceneInfoDlg = {
    yestaeQrcodeSceneInfoData : {},
    qrcodeSceneApplyScopeWxH5: 4,   //小程序专享 H5外链页
    qrcodeSceneJumpTypeOriginal: 1, //原生页
    qrcodeSceneJumpTypeH5: 2,       //H5外链页
    qrcodeSceneApplyScopeApp: 1,    //APP专享
    qrcodeSceneApplyScopeWx: 2,     //小程序专享
    qrcodeSceneApplyScopeNormal: 3  //通用
};

/**
 * 清除数据
 */
YestaeQrcodeSceneInfoDlg.clearData = function() {
    this.yestaeQrcodeSceneInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeQrcodeSceneInfoDlg.set = function(key, val) {
    this.yestaeQrcodeSceneInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeQrcodeSceneInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeQrcodeSceneInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeQrcodeScene.layerIndex);
}

/**
 * 收集数据
 */
YestaeQrcodeSceneInfoDlg.collectData = function() {
    this.set('id').set('name').set('applyScope').set('jumpType')
    .set('type').set('jumpPageUrl').set('description');
}

/**
 * 验证数据
 */
YestaeQrcodeSceneInfoDlg.validate = function(){
	var type = this.get("type");
	var name = this.get("name");
	var applyScope = this.get("applyScope");
	var jumpType = this.get("jumpType");
	
	if(name == ""){
		Feng.info("请填写场景名称！");
		return false;
	}
	if(applyScope == null || applyScope == ""){
		Feng.info("请选择应用范围！");
		return false;
	}
	if(jumpType == null || jumpType == ""){
		Feng.info("请选择跳转页面类型！");
		return false;
	}
	if(this.qrcodeSceneApplyScopeApp != $("#applyScope").val()
			|| this.qrcodeSceneJumpTypeH5 != $("#jumpType").val()){
		
		if(type == null || type == ""){
			Feng.info("请选择跳转页面！");
			return false;
		}
	}
	return true;
}

/**
 * 提交添加
 */
YestaeQrcodeSceneInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeQrcodeScene/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeQrcodeScene.table.refresh();
        YestaeQrcodeSceneInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeQrcodeSceneInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeQrcodeSceneInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeQrcodeScene/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeQrcodeScene.table.refresh();
        YestaeQrcodeSceneInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeQrcodeSceneInfoData);
    ajax.start();
}

/**
 * 初始化应用范围
 * flag为true代表第一次初始化
 */
YestaeQrcodeSceneInfoDlg.initApplyScope = function(flag){
	var valueStr = "";
	if(flag){
		 valueStr = "Value";
	}
	
	//【APP专享】并且是【H5外链页】时不用选【跳转页面】
	if(this.qrcodeSceneApplyScopeApp == $("#applyScope" + valueStr).val()
			&& this.qrcodeSceneJumpTypeH5 == $("#jumpType" + valueStr).val()){
		$('#typeDiv').hide();
		$('#type').val('');
	}else{
		var type = $("#applyScope" + valueStr).val();
		if(this.qrcodeSceneJumpTypeH5 == $("#jumpType" + valueStr).val()){
			type = this.qrcodeSceneApplyScopeWxH5;
		}
		if(!flag){
			Feng.findCodeType("type", "qrcodeSceneType" + type, null);
		}
		$('#typeDiv').show();
	}
}

YestaeQrcodeSceneInfoDlg.initJumpType = function(dom, flag){
	if(this.qrcodeSceneJumpTypeOriginal == $(dom).val()){
		$('#urlDiv').hide();
		$('#jumpPageUrl').val('');
	}else if(this.qrcodeSceneJumpTypeH5 == $(dom).val()){
		$('#urlDiv').show();
	}
	YestaeQrcodeSceneInfoDlg.initApplyScope(flag);
}

YestaeQrcodeSceneInfoDlg.doBind = function(){

	$("#applyScope").on('change', function(){
		YestaeQrcodeSceneInfoDlg.initApplyScope(false);
	})
	$("#jumpType").on('change', function(){
		YestaeQrcodeSceneInfoDlg.initJumpType(this, false);
	})
}

$(function() {

//	YestaeQrcodeSceneInfoDlg.initApplyScope();
	YestaeQrcodeSceneInfoDlg.initJumpType($("#jumpTypeValue"), true);
	
	Feng.findCodeType("applyScope", "qrcodeSceneApplyScope", "applyScopeValue");
	Feng.findCodeType("jumpType", "qrcodeSceneJumpType", "jumpTypeValue");
	
	var type = $("#applyScopeValue").val();
	if(YestaeQrcodeSceneInfoDlg.qrcodeSceneJumpTypeH5 == $("#jumpTypeValue").val()){
		type = YestaeQrcodeSceneInfoDlg.qrcodeSceneApplyScopeWxH5;
	}
	Feng.findCodeType("type", "qrcodeSceneType" + type, "typeValue");
	YestaeQrcodeSceneInfoDlg.doBind();
	
});
