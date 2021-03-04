/**
 * 初始化增值服务权益详情对话框
 */
var VasEquityInfoDlg = {
    vasEquityInfoData : {}
};

/**
 * 清除数据
 */
VasEquityInfoDlg.clearData = function() {
    this.vasEquityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VasEquityInfoDlg.set = function(key, val) {
    this.vasEquityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VasEquityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VasEquityInfoDlg.close = function() {
    parent.layer.close(window.parent.VasEquity.layerIndex);
}

/**
 * 收集数据
 */
VasEquityInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
VasEquityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vasEquity/add", function(data){
        Feng.success("添加成功!");
        window.parent.VasEquity.table.refresh();
        VasEquityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vasEquityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VasEquityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/vasEquity/update", function(data){
        Feng.success("修改成功!");
        window.parent.VasEquity.table.refresh();
        VasEquityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.vasEquityInfoData);
    ajax.start();
}

$(function() {

});
