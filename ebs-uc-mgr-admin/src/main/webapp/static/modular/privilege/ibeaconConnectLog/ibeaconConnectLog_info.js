/**
 * 初始化ibeacon连接记录详情对话框
 */
var IbeaconConnectLogInfoDlg = {
    ibeaconConnectLogInfoData : {}
};

/**
 * 清除数据
 */
IbeaconConnectLogInfoDlg.clearData = function() {
    this.ibeaconConnectLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IbeaconConnectLogInfoDlg.set = function(key, val) {
    this.ibeaconConnectLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
IbeaconConnectLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
IbeaconConnectLogInfoDlg.close = function() {
    parent.layer.close(window.parent.IbeaconConnectLog.layerIndex);
}

/**
 * 收集数据
 */
IbeaconConnectLogInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
IbeaconConnectLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ibeaconConnectLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.IbeaconConnectLog.table.refresh();
        IbeaconConnectLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ibeaconConnectLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
IbeaconConnectLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ibeaconConnectLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.IbeaconConnectLog.table.refresh();
        IbeaconConnectLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ibeaconConnectLogInfoData);
    ajax.start();
}

$(function() {

});
