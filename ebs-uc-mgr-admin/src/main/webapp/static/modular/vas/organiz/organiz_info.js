/**
 * 初始化机构详情对话框
 */
var OrganizInfoDlg = {
    organizInfoData : {}
};

/**
 * 清除数据
 */
OrganizInfoDlg.clearData = function() {
    this.organizInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizInfoDlg.set = function(key, val) {
    this.organizInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrganizInfoDlg.close = function() {
    parent.layer.close(window.parent.Organiz.layerIndex);
}

/**
 * 收集数据
 */
OrganizInfoDlg.collectData = function() {
    this.set('id').set('organizName').set('simpleName').set('intro').set('content')
    .set('category').set('organizLogo').set('logo').set('surfaceId').set('organizLogoInvalid');
}

/**
 * 验证数据
 */
OrganizInfoDlg.validate = function(){
	var organizName = this.get("organizName");
	var intro = this.get("intro");
	var simpleName = this.get("simpleName");
	var category = this.get("category");
	var organizLogo = this.get("organizLogo");
	var surfaceId = this.get("surfaceId");
	var organizLogoInvalid = this.get("organizLogoInvalid");
	
	if(organizName == ""){
		Feng.info("请填写机构名称！");
		return false;
	}
	if(simpleName == ""){
		Feng.info("请填写机构简称！");
		return false;
	}
	if(category == ""){
		Feng.info("请填写商品分类！");
		return false;
	}
	if(logo == ""){
		Feng.info("请上传扫码页logo！");
		return false;
	}
	if(surfaceId == ""){
		Feng.info("请上传封面图 ！");
		return false;
	}
	if(organizLogo == ""){
		Feng.info("请上传彩色logo！");
		return false;
	}
	if(organizLogoInvalid == ""){
		Feng.info("请上传普通logo！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
OrganizInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organiz/add", function(data){
        Feng.success("添加成功!");
        window.parent.Organiz.table.refresh();
        OrganizInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrganizInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organiz/update", function(data){
        Feng.success("修改成功!");
        window.parent.Organiz.table.refresh();
        OrganizInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizInfoData);
    ajax.start();
}

$(function() {
	Feng.initImage("surfaceId", "/vasImage/upload/organiz");
	Feng.initImage("organizLogo", "/vasImage/upload/organizvl");
	Feng.initImage("logo", "/vasImage/upload/organizl");
	Feng.initImage("organizLogoInvalid", "/vasImage/upload/organizivl");
});
