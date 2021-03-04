/**
 * 初始化门店详情对话框
 */
var StoreInfoDlg = {
    storeInfoData : {}
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
		parent.layer.restore(window.parent.Store.layerIndex);
		layer.close(this.layerIndex);
	}
};

/**
 * 清除机构
 */
StoreInfoDlg.clearOrganiz = function(){
	$("#organizId").val("");
	$("#organizName").val("");
}

/**
 * 清除数据
 */
StoreInfoDlg.clearData = function() {
    this.storeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StoreInfoDlg.set = function(key, val) {
    this.storeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StoreInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StoreInfoDlg.close = function() {
    parent.layer.close(window.parent.Store.layerIndex);
}

/**
 * 收集数据
 */
StoreInfoDlg.collectData = function() {
    this.set('id').set('storeName').set('storeCode').set('organizId').set('organizCode').set('intro').set('tel').set('linkman').set('address')
    .set('provinceCode').set('provinceName', $("#province").find("option:selected").text())
    	.set('cityCode').set('cityName', $("#city").find("option:selected").text())
    	.set('areaCode').set('areaName', $("#area").find("option:selected").text()).set('wifiName').set('wifiPassword')
    	.set('startTime', Feng.convertTimePicker(this.get('startTime'))).set('endTime', Feng.convertTimePicker(this.get('endTime')))
    	.set('recommendFlag').set('status').set('delFlag').set('surfaceId');
}
/**
 * 验证数据
 */
StoreInfoDlg.validate = function(){
	
	var storeName = this.get("storeName");
	var storeCode = this.get("storeCode");
	var organizId = this.get("organizId");
	var organizCode = this.get("organizCode");
	var intro = this.get("intro");
	var tel = this.get("tel");
	var linkman = this.get("linkman");
	var address = this.get("address");
	var provinceCode = this.get("provinceCode");
	var provinceName = this.get("provinceName");
	var cityCode = this.get("cityCode");
	var cityName = this.get("cityName");
	var areaCode = this.get("areaCode");
	var areaName = this.get("areaName");
	var wifiName = this.get("wifiName");
	var wifiPassword = this.get("wifiPassword");
	var startTime = this.get("startTime");
	var endTime = this.get("endTime");
	var recommendFlag = this.get("recommendFlag");
	var status = this.get("status");
	var delFlag = this.get("delFlag");
	var surfaceId = this.get("surfaceId");

	if(storeName == ""){
		Feng.info("请填写门店名称！");
		return false;
	}
	
	if(organizId == ""){
		Feng.info("请填写机构！");
		return false;
	}
	
	if(intro == ""){
		Feng.info("请填写门店简介！");
		return false;
	}
	if(tel == ""){
		Feng.info("请填写联系方式！");
		return false;
	}
	if(linkman == ""){
		Feng.info("请填写联系人！");
		return false;
	}
	if(address == ""){
		Feng.info("请填写详细地址！");
		return false;
	}
	if(provinceName == ""){
		Feng.info("请填写省份名称！");
		return false;
	}
	if(cityName == ""){
		Feng.info("请填写城市名称！");
		return false;
	}
	if(areaName == ""){
		Feng.info("请填写地区名称！");
		return false;
	}
	
	if(recommendFlag == ""){
		Feng.info("请填写推荐标识！");
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
StoreInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/store/add", function(data){
        Feng.success("添加成功!");
        window.parent.Store.table.refresh();
        StoreInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.storeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StoreInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/store/update", function(data){
        Feng.success("修改成功!");
        window.parent.Store.table.refresh();
        StoreInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.storeInfoData);
    ajax.start();
}

/**
 * 初始化用户地址
 * @domId: html标签id
 * @pid:父级地址ID
 */
StoreInfoDlg.initAddress = function(domId, pid){
	$.post("/yestaeCity/list", {pid: pid}, function(data){
		$("#" + domId).html('<option value="0">请选择</option>')
		for(var i = 0; i < data.length; i++){
			$("#" + domId).append('<option value="'+data[i].cityCodeId+'">'+data[i].name+'</option>');
		}
		$("#" + domId).val($("#" + domId + "Code").val());
	})
}

/**
 * 事件绑定
 */
StoreInfoDlg.doBind = function(){
	
	//选择省份
	$("#province").change(function(){
		$("#provinceCode").val($(this).val());
		if($(this).val() == 0){
			$("#city").html('<option value="0" selected="selected">请选择</option>');
			$("#area").html('<option value="0" selected="selected">请选择</option>');
		}else{
			StoreInfoDlg.initAddress("city", $(this).val());
		}
	});
	//选择城市
	$("#city").change(function(){
		$("#cityCode").val($(this).val());
		if($(this).val() == 0){
			$("#area").html('<option value="0" selected="selected">请选择</option>');
		}else{
			StoreInfoDlg.initAddress("area", $(this).val())
		}
	})
	//选择地区
	$("#area").change(function(){
		$("#areaCode").val($(this).val());
	})
}


/**
 * 显示机构选择列表
 */
StoreInfoDlg.showOrganizList = function(){
	parent.layer.full(window.parent.Store.layerIndex);
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


$(function() {
	
	Feng.initImage("surfaceId", "/vasImage/upload/store");
	Feng.findCodeType("recommendFlag", "recommendFlag", "typeValue");
	
	StoreInfoDlg.doBind();
	
	//初始化省
	StoreInfoDlg.initAddress("province", 0);
	//初始化市
	var provinceCode = $("#provinceCode").val();
	if(provinceCode){
		StoreInfoDlg.initAddress("city", provinceCode);
	}
	//初始化区
	var cityCode = $("#cityCode").val();
	if(cityCode){
		StoreInfoDlg.initAddress("area", cityCode);
	}

});
