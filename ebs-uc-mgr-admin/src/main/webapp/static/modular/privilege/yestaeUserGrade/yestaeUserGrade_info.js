/**
 * 初始化用户会员等级详情对话框
 */
var YestaeUserGradeInfoDlg = {
    yestaeUserGradeInfoData : {},
	validateFields: {
	    name: {
	        validators: {
	            notEmpty: {
	                message: '等级名称不能为空'
	            }
	        }
	    },
    	money: {
	    	validators: {
	    		notEmpty: {
	    			message: '所需消费不能为空'
	    		}
	    	}
	    },
	    discount: {
	    	validators: {
	    		notEmpty: {
	    			message: '会员折扣不能为空'
	    		}
	    	}
	    }
	}
};

/**
 * 清除数据
 */
YestaeUserGradeInfoDlg.clearData = function() {
    this.yestaeUserGradeInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeUserGradeInfoDlg.set = function(key, val) {
    this.yestaeUserGradeInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
YestaeUserGradeInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
YestaeUserGradeInfoDlg.close = function() {
    parent.layer.close(window.parent.YestaeUserGrade.layerIndex);
}

/**
 * 收集数据
 */
YestaeUserGradeInfoDlg.collectData = function() {
    this.set('id').set('name').set('money').set('discount');
    this.yestaeUserGradeInfoData.ifDefault = $("input[name='ifDefault']:checked").val();
}

/**
 * 表单数据验证
 */
YestaeUserGradeInfoDlg.validate = function(){
	
	var name = this.get("name");
	var money = this.get("money");
	var discount = this.get("discount");
	
	if(name == ""){
		Feng.info("请填写等级名称！");
		return false;
	}
	if(money == ""){
		Feng.info("请填写所需消费金额！");
		return false;
	}else if(!/^\d{1,10}(\.\d{1,2})?$/.test(money)){
		Feng.info("请填写正确的所需消费金额！");
		return false;
	}
	if(discount == ""){
		Feng.info("请填写会员折扣！");
		return false;
	}else if(!/^0{1}(\.\d{1,2})?$/.test(discount)
			&& discount != 1){
		Feng.info("请填写正确的会员折扣！");
		return false;
	}
	
	return true;
}

/**
 * 提交添加
 */
YestaeUserGradeInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeUserGrade/add", function(data){
        Feng.success("添加成功!");
        window.parent.YestaeUserGrade.table.refresh();
        YestaeUserGradeInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeUserGradeInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
YestaeUserGradeInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
    	return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/yestaeUserGrade/update", function(data){
        Feng.success("修改成功!");
        window.parent.YestaeUserGrade.table.refresh();
        YestaeUserGradeInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.yestaeUserGradeInfoData);
    ajax.start();
}

$(function() {

});
