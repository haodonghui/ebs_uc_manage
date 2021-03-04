/**
 * 初始化二维码详情对话框
 */
var YestaeQrcodeInfoDlg = {
    yestaeQrcodeInfoData : {},
    sceneFlag: 0,
    type: 1   //1:茶博会
};

/**
 * 推广人选择列表
 */
var YestaeGeneralizeUserList = {
	layerIndex: -1,
	yestaeGeneralizeUserSubmit: function(id, name){
		$("#generalizeUserId").val(id);
		$("#generalizeUserName").val(name);
		this.close();
	},
	close: function(){
		parent.layer.restore(window.parent.YestaeQrcode.layerIndex);
		layer.close(this.layerIndex);
	}
};

/**
 * 清除推广人
 */
YestaeQrcodeInfoDlg.clearYestaeGeneralizeUser = function(){
	$("#generalizeUserId").val("");
	$("#generalizeUserName").val("");
}

/**
 * 推广场景选择列表
 */
var YestaeQrcodeSceneList = {
		layerIndex: -1,
		yestaeQrcodeSceneSubmit: function(id, name){
			if(YestaeQrcodeInfoDlg.sceneFlag == 1){
				$("#sceneId").val(id);
				$("#sceneName").val(name);
			}else if(YestaeQrcodeInfoDlg.sceneFlag == 2){
				$("#nextSceneId").val(id);
				$("#nextSceneName").val(name);
			}
			this.close();
		},
		close: function(){
			parent.layer.restore(window.parent.YestaeQrcode.layerIndex);
			layer.close(this.layerIndex);
		}
};

/**
 * 清除推广场景
 */
YestaeQrcodeInfoDlg.clearYestaeQrcodeScene = function(type){
	if(type == 1){
		$("#sceneId").val("");
		$("#sceneName").val("");
	}else if(type == 2){
		$("#nextSceneId").val("");
		$("#nextSceneName").val("");
	}
}

/**
 * 清除数据
 */
YestaeQrcodeInfoDlg.clearData = function() {
    this.yestaeQrcodeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeQrcodeInfoDlg.set = function(key, val) {
    this.yestaeQrcodeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeQrcodeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeQrcodeInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeQrcode.layerIndex);
}

/**
 * 收集数据
 */
YestaeQrcodeInfoDlg.collectData = function() {
    this.set('id').set('generalizeUserId').set('sceneId').set('bizId')
    .set('nextSceneId').set('nextBizId').set('nextCodeInfo').set('remark').set('codeInfo');
}

/**
 * 验证数据
 */
YestaeQrcodeInfoDlg.validate = function(){
	var generalizeUserId = this.get("generalizeUserId");
	var sceneId = this.get("sceneId");
	
	if(generalizeUserId == null || generalizeUserId == ""){
		Feng.info("请请选择推广人！");
		return false;
	}
	if(sceneId == null || sceneId == ""){
		Feng.info("请选择二维码场景！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
YestaeQrcodeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeQrcode/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeQrcode.table.refresh();
        YestaeQrcodeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeQrcodeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeQrcodeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeQrcode/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeQrcode.table.refresh();
        YestaeQrcodeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeQrcodeInfoData);
    ajax.start();
}

/**
 * 显示推广人选择列表
 */
YestaeQrcodeInfoDlg.showYestaeGeneralizeUserList = function(){
	parent.layer.full(window.parent.YestaeQrcode.layerIndex);
	var index = layer.open({
        type: 2,
        title: '推广人选择',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeGeneralizeUser/yestaeGeneralizeUser_select',
        cancel: function(index, layero){ 
        	YestaeGeneralizeUserList.close(index)
        	return false; 
    	}    
    });
    this.layerIndex = index;
    YestaeGeneralizeUserList.layerIndex = index;
}
/**
 * 显示推广场景选择列表
 */
YestaeQrcodeInfoDlg.showYestaeQrcodeSceneList = function(sceneFlag){
	this.sceneFlag = sceneFlag;
	parent.layer.full(window.parent.YestaeQrcode.layerIndex);
	var index = layer.open({
		type: 2,
		title: '推广场景选择',
		area: ['800px', '420px'], //宽高
		fix: false, //不固定
		maxmin: true,
		content: Feng.ctxPath + '/yestaeQrcodeScene/yestaeQrcodeScene_select',
		cancel: function(index, layero){ 
			YestaeQrcodeSceneList.close(index)
			return false; 
		}    
	});
	this.layerIndex = index;
	YestaeQrcodeSceneList.layerIndex = index;
}

YestaeQrcodeInfoDlg.doBind = function(){
	
	$("#type").on('change', function(){
		if(YestaeQrcodeInfoDlg.type == $(this).val()){
			$("#goodsIdHidden").show();
		}else{
			$("#goodsIdHidden").hide();
		}
	})
	
}

$(function() {
	
//	YestaeQrcodeInfoDlg.doBind();
	
	//初始化【类型】下拉框
//	$("#type").val($("#typeValue").val());
	Feng.findCodeType("type", "qrcodeType", "typeValue");
	//设置【跳转商品货号】是否显示
	if(YestaeQrcodeInfoDlg.type == $("#typeValue").val()){
		$("#goodsIdHidden").show();
	}else{
		$("#goodsIdHidden").hide();
	}
});
