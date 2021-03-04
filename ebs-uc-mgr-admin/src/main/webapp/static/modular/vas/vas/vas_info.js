/**
 * 初始化增值服务详情对话框
 */
var VasInfoDlg = {
    vasInfoData : {}
};

/**
 * 机构选择列表
 */
var OrganizList = {
	layerIndex: -1,
	organizSubmit: function(id, name){
		$("#organizId").val(id);
		$("#organizName").val(name);
		this.close();
	},
	close: function(){
		parent.layer.restore(window.parent.Vas.layerIndex);
		layer.close(this.layerIndex);
	}
};

/**
 * 清除机构
 */
VasInfoDlg.clearOrganiz = function(){
	$("#organizId").val("");
	$("#organizName").val("");
}

/**
 * 清除数据
 */
VasInfoDlg.clearData = function() {
    this.vasInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VasInfoDlg.set = function(key, val) {
    this.vasInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VasInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VasInfoDlg.close = function() {
    parent.layer.close(window.parent.Vas.layerIndex);
}

/**
 * 收集数据
 */
VasInfoDlg.collectData = function() {
	var vasEquityList = EquityBind.getVasEquityList();
	for(var i = 0; i < vasEquityList.length; i++){
		for(key in vasEquityList[i]){
			this.set('vasEquityList['+i+'].' + key, vasEquityList[i][key]);
		}
	}
	
    this.set('id').set('vasName').set('organizCode').set('organizId').set('salePrice').set('remindDays')
    .set('intro').set('validType').set('serviceTerms').set('equityDetail').set('faq').set('surfaceId');
}

/**
 * 验证数据
 */
VasInfoDlg.validate = function(){
	var vasName = this.get("vasName");
	var organizId = this.get("organizId");
	var salePrice = this.get("salePrice");
	var remindDays = this.get("remindDays");
	var intro = this.get("intro");
	var validType = this.get("validType");
	var serviceTerms = this.get("serviceTerms");
	var equityDetail = this.get("equityDetail");
	var faq = this.get("faq");
	
	var surfaceId = this.get("surfaceId");
	
	if(surfaceId == ""){
		Feng.info("请上传封面图 ！");
		return false;
	}
	if(vasName == ""){
		Feng.info("请填写增值服务名称！");
		return false;
	}
	if(organizId == ""){
		Feng.info("请选择机构！");
		return false;
	}
	if(remindDays == ""){
		Feng.info("请填写到期提醒！");
		return false;
	}
	if(validType == ""){
		Feng.info("请选择有效期类型！");
		return false;
	}
	if(salePrice == ""){
		Feng.info("请填写售价！");
		return false;
	}else if(!/^\d{1,9}(\.\d{1,2})?$/.test(salePrice)){
		Feng.info("请填写正确的售价！");
		return false;
	}
	return true;
}

/**
 * 显示机构选择列表
 */
VasInfoDlg.showOrganizList = function(){
	parent.layer.full(window.parent.Vas.layerIndex);
	var index = layer.open({
        type: 2,
        title: '机构选择',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/organiz/organiz_select',
        cancel: function(index, layero){ 
        	OrganizList.close(index)
        	return false; 
    	}    
    });
    this.layerIndex = index;
    OrganizList.layerIndex = index;
}

/**
 * 提交添加
 */
VasInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vas/add", function(data){
        Feng.success("添加成功!");
        window.parent.Vas.table.refresh();
        VasInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vasInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VasInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vas/update", function(data){
        Feng.success("修改成功!");
        window.parent.Vas.table.refresh();
        VasInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vasInfoData);
    ajax.start();
}

$(function() {
	Feng.findCodeType("validType", "vasValidType", "validTypeValue");
	
	Feng.initImage("surfaceId", "/vasImage/upload/vas");
});
