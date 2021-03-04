/**
 * 初始化机构服务详情对话框
 */
var OrganizVasInfoDlg = {
    organizVasInfoData : {}
};

/**
 * 清除数据
 */
OrganizVasInfoDlg.clearData = function() {
    this.organizVasInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizVasInfoDlg.set = function(key, val) {
    this.organizVasInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizVasInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrganizVasInfoDlg.close = function() {
    parent.layer.close(window.parent.OrganizVas.layerIndex);
}

/**
 * 收集数据
 */
OrganizVasInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
OrganizVasInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organizVas/add", function(data){
        Feng.success("添加成功!");
        window.parent.OrganizVas.table.refresh();
        OrganizVasInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizVasInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrganizVasInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organizVas/update", function(data){
        Feng.success("修改成功!");
        window.parent.OrganizVas.table.refresh();
        OrganizVasInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizVasInfoData);
    ajax.start();
}

$(function() {

});
