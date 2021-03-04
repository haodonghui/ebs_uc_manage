/**
 * 初始化数据字典详情对话框
 */
var DictInfoDlg = {
    dictInfoData : {}
};

/**
 * 清除数据
 */
DictInfoDlg.clearData = function() {
    this.dictInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictInfoDlg.set = function(key, val) {
    this.dictInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
DictInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function() {
    parent.layer.close(window.parent.Dict.layerIndex);
}

/**
 * 收集数据
 */
DictInfoDlg.collectData = function() {
    this.set('id').set('name').set('code').set('pcode');
}

DictInfoDlg.validate = function () {
	if(this.get('name') == ''){
		Feng.info("名称不能为空！");
		return false;
	}
	if(this.get('code') == ''){
		Feng.info("编码不能为空！");
		return false;
	}
	return true;
}

/**
 * 提交添加
 */
DictInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dict/add", function(data){
        Feng.success("添加成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dictInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dict/update", function(data){
        Feng.success("修改成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.dictInfoData);
    ajax.start();
}

$(function() {
	Feng.findCodeType("pcode", "ROOT", "pcodeValue");
});
