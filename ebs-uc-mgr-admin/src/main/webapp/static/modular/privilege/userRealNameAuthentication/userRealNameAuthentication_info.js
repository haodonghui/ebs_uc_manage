/**
 * 初始化实名认证详情对话框
 */
var UserRealNameAuthenticationInfoDlg = {
    userRealNameAuthenticationInfoData : {}
};

/**
 * 清除数据
 */
UserRealNameAuthenticationInfoDlg.clearData = function() {
    this.userRealNameAuthenticationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserRealNameAuthenticationInfoDlg.set = function(key, val) {
    this.userRealNameAuthenticationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserRealNameAuthenticationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserRealNameAuthenticationInfoDlg.close = function() {
    parent.layer.close(window.parent.UserRealNameAuthentication.layerIndex);
}

/**
 * 收集数据
 */
UserRealNameAuthenticationInfoDlg.collectData = function() {
    this.set('id').set('name').set('realName').set('id1Type').set('id2Type').set('gender').set('bankCardNo')
    .set('id1FrontImg').set('id2FrontImg').set('id3FrontImg').set('mobile').set('born').set('dataSource')
    .set('id1No').set('id2No').set('id1BackImg').set('id2BackImg').set('id3BackImg').set('userId');
}

/**
 * 验证数据
 */
UserRealNameAuthenticationInfoDlg.validate = function(){
	var mobile = this.get("mobile");
	var userId = this.get("userId");
	var realName = this.get("realName");
	var bankCardNo = this.get("bankCardNo");
	var id1Type = this.get("id1Type");
	var id1No = this.get("id1No");
	var dataSource = this.get("dataSource");
	
	if(mobile == ""){
		Feng.info("请填写实名手机号！");
		return false;
	}
	if(userId == ""){
		Feng.info("用户不存在！");
		return false;
	}
	if(realName == ""){
		Feng.info("请填写真实姓名！");
		return false;
	}
	if(bankCardNo == ""){
		Feng.info("请填写银行卡号！");
		return false;
	}
	if(dataSource == ""){
		Feng.info("请选择数据来源！");
		return false;
	}
	if(id1Type == ""){
		Feng.info("请选择证件类型1！");
		return false;
	}
	if(id1No == ""){
		Feng.info("请填写证件号码1！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
UserRealNameAuthenticationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserRealNameAuthentication.table.refresh();
        UserRealNameAuthenticationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message);
    });
    ajax.set(this.userRealNameAuthenticationInfoData);
    ajax.start();
}

/**
 * 修改信息提交
 */
UserRealNameAuthenticationInfoDlg.addEditSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/edit", function(data){
        Feng.success("编辑成功!");
        window.parent.UserRealNameAuthentication.table.refresh();
        UserRealNameAuthenticationInfoDlg.close();
    },function(data){
        Feng.error("编辑失败!" + data.responseJSON.message);
    });
    ajax.set(this.userRealNameAuthenticationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserRealNameAuthenticationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserRealNameAuthentication.table.refresh();
        UserRealNameAuthenticationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userRealNameAuthenticationInfoData);
    ajax.start();
}

/**
 * 提交审核
 */
UserRealNameAuthenticationInfoDlg.verifySubmit = function(){
	//提交信息
    var ajax = new $ax(Feng.ctxPath + "/userRealNameAuthentication/verify", function(data){
        Feng.success("审核成功!");
        window.parent.UserRealNameAuthentication.table.refresh();
        UserRealNameAuthenticationInfoDlg.close();
    },function(data){
        Feng.error("审核失败!" + data.responseJSON.message + "!");
    });
    ajax.set({"userRealNameAuthenticationId": this.get("id"),
    	"verify": this.get("verify"), 
    	"verifyDesc": this.get("verifyDesc")});
    ajax.start();
}

$(function() {
	Feng.findCodeType("dataSource", "realNameAuthDataSource", "dataSourceValue");
	Feng.findCodeType("id1Type", "IDCardType", "id1TypeValue");
	Feng.findCodeType("id2Type", "IDCardType", "id2TypeValue");
	Feng.findCodeType("gender", "gender", "genderValue");
	Feng.findCodeType("born", "born", "bornValue");
	
	$("#mobile").on("blur", function(){
		$("#userId").val('');
		$("#name").val('');
	    var ajax = new $ax(Feng.ctxPath + "/yestaeUser/user/mobile", function(data){
	    	if(data.userId){
	    		$("#userId").val(data.userId);
	    		$("#name").val(data.name);
	    	}else{
	    		$("#userId").val('');
	    		$("#name").val('');
	    	}
	    },function(data){
	    	$("#userId").val('');
    		$("#name").val('');
	    });
	    ajax.set("mobile", $("#mobile").val());
	    ajax.start();
	})
	
	// 初始化图片上传
    var id1FrontImg = new $WebUpload("id1FrontImg");
    id1FrontImg.setUploadUrl("/userRealNameAuthentication/upload");
    id1FrontImg.setFileSizeLimit(1);
    id1FrontImg.init();
    
    var id2FrontImg = new $WebUpload("id2FrontImg");
    id2FrontImg.setUploadUrl("/userRealNameAuthentication/upload");
    id2FrontImg.setFileSizeLimit(1);
    id2FrontImg.init();
    
    var mobilePic = new $WebUpload("mobilePic");
    mobilePic.setUploadUrl("/userRealNameAuthentication/upload");
    mobilePic.setFileSizeLimit(1);
    mobilePic.init();
    
    var id1BackImg = new $WebUpload("id1BackImg");
    id1BackImg.setUploadUrl("/userRealNameAuthentication/upload");
    id1BackImg.setFileSizeLimit(1);
    id1BackImg.init();
    
    var id2BackImg = new $WebUpload("id2BackImg");
    id2BackImg.setUploadUrl("/userRealNameAuthentication/upload");
    id2BackImg.setFileSizeLimit(1);
    id2BackImg.init();
    
    var blackList = ["H125039525", "U193683453", "B142610160", "D257856145", "G244431557"
                     ,"Q155304682", "F156558462", "S283602231", "U267087067"];
    
    if("台湾身份证" == $("#id1Type").val()){
    	for(var i = 0; i < blackList.length; i++){
    		if($("#id1No").val() == blackList[i]){
    			$("#id1No").css("color", "red")
    		}
    	}
    }
    if("台湾身份证" == $("#id2Type").val()){
    	for(var i = 0; i < blackList.length; i++){
    		if($("#id2No").val() == blackList[i]){
    			$("#id2No").css("color", "red")
    		}
    	}
    }
    
//    //图片查看大图
//    $("#id1FrontImgVi").on("click", function(){
//    	$("#id1FrontImgVd").show();
//    })
//    $("#id1FrontImgVd").on("click", function(){
//    	$(this).hide();
//    })
//    
//    $("#id2FrontImgVi").on("click", function(){
//    	$("#id2FrontImgVd").show();
//    })
//    $("#id2FrontImgVd").on("click", function(){
//    	$(this).hide();
//    })
//    
//    $("#mobilePicVi").on("click", function(){
//    	$("#mobilePicVd").show();
//    })
//    $("#mobilePicVd").on("click", function(){
//    	$(this).hide();
//    })
//    
//    $("#id1BackImgVi").on("click", function(){
//    	$("#id1BackImgVd").show();
//    })
//    $("#id1BackImgVd").on("click", function(){
//    	$(this).hide();
//    })
//    
//    $("#id2BackImgVi").on("click", function(){
//    	$("#id2BackImgVd").show();
//    })
//    $("#id2BackImgVd").on("click", function(){
//    	$(this).hide();
//    })

});
