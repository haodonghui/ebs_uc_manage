/**
 * 初始化页面内容详情对话框
 */
var PageContentInfoDlg = {
    pageContentInfoData : {}
};

/**
 * 清除数据
 */
PageContentInfoDlg.clearData = function() {
    this.pageContentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PageContentInfoDlg.set = function(key, val) {
    this.pageContentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PageContentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PageContentInfoDlg.close = function() {
    parent.layer.close(window.parent.PageContent.layerIndex);
}

/**
 * 收集数据
 */
PageContentInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
PageContentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pageContent/add", function(data){
        Feng.success("添加成功!");
        window.parent.PageContent.table.refresh();
        PageContentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pageContentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PageContentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pageContent/update", function(data){
        Feng.success("修改成功!");
        window.parent.PageContent.table.refresh();
        PageContentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pageContentInfoData);
    ajax.start();
}

$(function() {

});
