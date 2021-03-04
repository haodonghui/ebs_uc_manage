/**
 * 初始化用户账户详情对话框
 */
var UserAccountInfoDlg = {
    userAccountInfoData : {}
};

/**
 * 清除数据
 */
UserAccountInfoDlg.clearData = function() {
    this.userAccountInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAccountInfoDlg.set = function(key, val) {
    this.userAccountInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAccountInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserAccountInfoDlg.close = function() {
    parent.layer.close(window.parent.UserAccount.layerIndex);
}

/**
 * 收集数据
 */
UserAccountInfoDlg.collectData = function() {
    this.set('id').set('updateTime').set('type').set('amount').set('remark');
}

/**
 * 表单数据验证
 */
UserAccountInfoDlg.validate = function(){
	
	var amount = this.get("amount");
	var remark = this.get("remark");
	
	if(amount == ""){
		Feng.info("请填写金额！");
		return false;
	}else if(!/^\d{1,7}(\.\d{1,2})?$/.test(amount)){
		Feng.info("请填写正确的金额！");
		return false;
	}
	if(remark == ""){
		Feng.info("请填写备注！");
		return false;
	}
	
	return true;
}

/**
 * 提交修改
 */
UserAccountInfoDlg.editSubmit = function() {
	

    this.clearData();
    this.collectData();

    if (!this.validate()) {
    	return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userAccount/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserAccount.table.refresh();
        UserAccountInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userAccountInfoData);
    ajax.start();
}

$(function() {
});
