/**
 * 初始化增票审核详情对话框
 */
var UserInvoiceInfoDlg = {
    userInvoiceInfoData : {}
};

/**
 * 清除数据
 */
UserInvoiceInfoDlg.clearData = function() {
    this.userInvoiceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInvoiceInfoDlg.set = function(key, val) {
    this.userInvoiceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInvoiceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
UserInvoiceInfoDlg.close = function() {
    parent.layer.close(window.parent.UserInvoice.layerIndex);
}

/**
 * 收集数据
 */
UserInvoiceInfoDlg.collectData = function() {
    this.set('id').set('userId').set('remark').set("validQuantity");
    this.userInvoiceInfoData.verifyState = $("input[name='verifyState']:checked").val();

}

/**
 * 提交添加
 */
UserInvoiceInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInvoice/add", function(data){
        Feng.success("添加成功!");
        window.parent.UserInvoice.table.refresh();
        UserInvoiceInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInvoiceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
UserInvoiceInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInvoice/update", function(data){
        Feng.success("修改成功!");
        window.parent.UserInvoice.table.refresh();
        UserInvoiceInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInvoiceInfoData);
    ajax.start();
}

/**
 * 审核提交
 */
UserInvoiceInfoDlg.verify = function() {

    this.clearData();
    this.collectData();

    var verifyState = $("input[name='verifyState']:checked").val();
    var remark = this.get("remark");

    if(verifyState == '2' && (remark == '' || remark == null)){
        Feng.info("请填写审核原因！")
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInvoice/verify", function(data){
        Feng.success("审核成功!");
        window.parent.UserInvoice.table.refresh();
        UserInvoiceInfoDlg.close();
    },function(data){
        Feng.error("审核失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInvoiceInfoData);
    ajax.start();
}

/**
 * 增票资质配置
 */
UserInvoiceInfoDlg.config = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/userInvoice/config", function(data){
        Feng.success("配置成功!");
        UserInvoiceInfoDlg.close();
    },function(data){
        Feng.error("配置失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInvoiceInfoData);
    ajax.start();
}

UserInvoiceInfoDlg.upload = function() {

    var file = $('#btnFile').val();
    if(!file){
        Feng.info("请选择文件后再点击上传按钮");
        return;
    }
    var formdata = new FormData();
    formdata.append('file', $('#btnFile').get(0).files[0]);
    $.ajax({
        type: 'POST',
        url: Feng.ctxPath + "/userInvoice/upload/",
        contentType: false,
        data: formdata,
        processData: false,
        dataType: "json",
        success: function (data) {
            // $("#logoImg").attr('src','${_b}/upload/banklogo/'+data.msg);
            Feng.success("上传成功!");
        },
        error: function (data) {
            Feng.error("上传失败!");

        }
    })
}

UserInvoiceInfoDlg.download = function() {
    const a = document.createElement('a');
    a.setAttribute('href',  Feng.ctxPath + "/userInvoice/download");
    a.setAttribute('download', '授权委托书.doc');
    a.click();
}



$(function() {



});



