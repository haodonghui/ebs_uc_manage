/**
 * 初始化平台用户详情对话框
 */
var PlatformUserInfoDlg = {
    platformUserInfoData : {}
};

/**
 * 清除数据
 */
PlatformUserInfoDlg.clearData = function() {
    this.platformUserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlatformUserInfoDlg.set = function(key, val) {
    this.platformUserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PlatformUserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PlatformUserInfoDlg.close = function() {
    parent.layer.close(window.parent.PlatformUser.layerIndex);
}

/**
 * 收集数据
 */
PlatformUserInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
PlatformUserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/platformUser/add", function(data){
        Feng.success("添加成功!");
        window.parent.PlatformUser.table.refresh();
        PlatformUserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.platformUserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PlatformUserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/platformUser/update", function(data){
        Feng.success("修改成功!");
        window.parent.PlatformUser.table.refresh();
        PlatformUserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.platformUserInfoData);
    ajax.start();
}

$(function() {

});
