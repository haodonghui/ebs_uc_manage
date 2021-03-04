/**
 * 初始化用户详情对话框
 */
var YestaeUserInfoDlg = {
    yestaeUserInfoData : {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '用户名不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
YestaeUserInfoDlg.clearData = function() {
    this.yestaeUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeUserInfoDlg.set = function(key, val) {
    this.yestaeUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}
/**
 * 设置默认地址
 *
 */
YestaeUserInfoDlg.setAddress = function() {
	var yestaeUserAddress = {id: $("#addressId").val(), consignee: $("#consignee").val(), 
			consigneePhone: $("#consigneePhone").val(), consigneePrivince: $("#privince").find("option:selected").text(), 
			consigneePrivinceCode: $("#privinceCode").val(), consigneeCity: $("#city").find("option:selected").text(), 
			consigneeCityCode: $("#cityCode").val(), consigneeArea: $("#area").find("option:selected").text(), 
			consigneeAreaCode: $("#areaCode").val(), consigneeFullAddress: $("#fullAddress").val(), };
	//地址是否完整
	var flag = true;
	var addressKeys = Object.keys(yestaeUserAddress);
	for(var i = 0; i < addressKeys.length; i++){
		var key = addressKeys[i];
		if(key != "id" && yestaeUserAddress[key] == ""){
			flag = false;
			break;
		}
		
	}
	if(flag){
		this.yestaeUserInfoData.yestaeUserAddress = JSON.stringify(yestaeUserAddress);
	}
	
	return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeUserInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeUser.layerIndex);
}

/**
 * 收集数据
 */
YestaeUserInfoDlg.collectData = function() {
    this.set('id').set('status').set('name').set('gender').set('birthday').set('teaTeacherLevel')
    .set('cardNo').set('type').set('avatar').setAddress();
}

/**
 * 提交添加
 */
YestaeUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeUser.table.refresh();
        YestaeUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeUserInfoDlg.editSubmit = function() {

	this.clearData();
	this.collectData();
	if(this.yestaeUserInfoData.status != 0){
		Feng.info("请先停用该用户！");
		return false;
	}
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeUser.table.refresh();
        YestaeUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeUserInfoData);
    ajax.start();
}

/**
 * 初始化用户地址
 * @domId: html标签id
 * @pid:父级地址ID
 */
YestaeUserInfoDlg.initAddress = function(domId, pid){
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
YestaeUserInfoDlg.doBind = function(){
	$("#privince").change(function(){
		$("#privinceCode").val($(this).val());
		if($(this).val() == 0){
			$("#city").html('<option value="0" selected="selected">请选择</option>');
			$("#area").html('<option value="0" selected="selected">请选择</option>');
		}else{
			YestaeUserInfoDlg.initAddress("city", $(this).val());
		}
	});
	$("#city").change(function(){
		$("#cityCode").val($(this).val());
		if($(this).val() == 0){
			$("#area").html('<option value="0" selected="selected">请选择</option>');
		}else{
			YestaeUserInfoDlg.initAddress("area", $(this).val())
		}
	})
	$("#area").change(function(){
		$("#areaCode").val($(this).val());
	})
}

$(function() {

	//初始化来源渠道下拉框
	$("#source").val($("#sourceValue").val());
	//初始化性别下拉框
	$("#gender").val($("#genderValue").val());
	//初始化会员等级下拉框
	$("#type").val($("#typeValue").val());
	//初始化茶道师阶位下拉框
	$("#teaTeacherLevel").val($("#teaTeacherLevelValue").val());
	
	YestaeUserInfoDlg.doBind();
	//初始化省
	YestaeUserInfoDlg.initAddress("privince", 0);
	//初始化市
	var privinceCode = $("#privinceCode").val();
	if(privinceCode){
		YestaeUserInfoDlg.initAddress("city", privinceCode);
	}
	//初始化区
	var cityCode = $("#cityCode").val();
	if(cityCode){
		YestaeUserInfoDlg.initAddress("area", cityCode);
	}
	
	// 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.setUploadUrl("/yestaeUserImage/upload");
    avatarUp.setFileSizeLimit(1);
    avatarUp.init();
});

