/**
 * 初始化微信详情对话框
 */
var WeiXinInfoDlg = {
    weiXinInfoData : {}
};

/**
 * 清除数据
 */
WeiXinInfoDlg.clearData = function() {
    this.weiXinInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WeiXinInfoDlg.set = function(key, val) {
    this.weiXinInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WeiXinInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WeiXinInfoDlg.close = function() {
    parent.layer.close(window.parent.WeiXin.layerIndex);
}

/**
 * 收集数据
 */
WeiXinInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
WeiXinInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/weiXin/add", function(data){
        Feng.success("添加成功!");
        window.parent.WeiXin.table.refresh();
        WeiXinInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.weiXinInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WeiXinInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/weiXin/update", function(data){
        Feng.success("修改成功!");
        window.parent.WeiXin.table.refresh();
        WeiXinInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.weiXinInfoData);
    ajax.start();
}

$(function() {

});
