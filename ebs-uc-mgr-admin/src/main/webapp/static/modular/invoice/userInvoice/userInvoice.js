/**
 * 增票审核管理初始化
 */
var UserInvoice = {
    id: "UserInvoiceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
UserInvoice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '用户ID', field: 'userIdStr', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index, field) {
                if (value) {
                    return '<a href="javascript: UserInvoice.openUserInvoiceDetail()" >'+value+'</a>'
                }
                return '';
            }
        },
        {title: '用户昵称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '用户账号', field: 'mobile', visible: true, align: 'center', valign: 'middle'},
        {title: '是否实名', field: 'realNameFlagName', visible: true, align: 'center', valign: 'middle'},
        {title: '真实姓名', field: 'realName', visible: true, align: 'center', valign: 'middle'},
        {title: '授权委托书', field: 'large', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index, field) {
                if (value) {
                    return '<a id="IMG_'+ row.id +'" href="javascript: parent.Feng.showImg('+"'"+value+"'"+')" >查看</a>'
                }
                return '';
            }
        },
        // {title: '审核状态', field: 'verifyState', visible: true, align: 'center', valign: 'middle'},
        {title: '审核状态', field: 'verifyStateName', visible: true, align: 'center', valign: 'middle'},
        {title: '审核原因', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '单位名称', field: 'companyName', visible: true, align: 'center', valign: 'middle'},
        {title: '纳税人识别码/信用代码', field: 'taxpayerIdentificationNumber', visible: true, align: 'center', valign: 'middle'},
        {title: '公司注册地址', field: 'companyRegisteredAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '公司注册电话', field: 'companyRegisteredPhone', visible: true, align: 'center', valign: 'middle'},
        {title: '开户银行', field: 'depositBank', visible: true, align: 'center', valign: 'middle'},
        {title: '银行账号', field: 'bankAccount', visible: true, align: 'center', valign: 'middle'},
        {title: '申请时间', field: 'createTimeStr', visible: true, align: 'center', valign: 'middle'},
        {title: '审核时间', field: 'updateTimeStr', visible: true, align: 'center', valign: 'middle'},
        {title: '审核人', field: 'verifyBy', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
UserInvoice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        UserInvoice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加增票审核
 */
UserInvoice.openAddUserInvoice = function () {
    var index = layer.open({
        type: 2,
        title: '添加增票审核',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userInvoice/userInvoice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看增票审核详情
 */
UserInvoice.openUserInvoiceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '增票审核详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/userInvoice/detailOrVerify/' + UserInvoice.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 打开增票资质配置
 */
UserInvoice.openUserInvoiceConfig = function () {
    var index = layer.open({
        type: 2,
        title: '增票资质配置',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/userInvoice/userInvoice_config/'
    });
    this.layerIndex = index;
};

/**
 * 删除增票审核
 */
UserInvoice.delete = function () {
    if (this.check()) {

        var operation = function() {

            var ajax = new $ax(Feng.ctxPath + "/userInvoice/delete", function (data) {
                Feng.success("删除成功!");
                UserInvoice.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userInvoiceId",UserInvoice.seItem.id);
            ajax.start();
        }

        //Feng.confirm("是否刪除该增票审核?", operation);
    }
};

/**
 * 增票撤销审核
 */
UserInvoice.cancel = function () {
    if (this.check()) {
        var operation = function() {
            var ajax = new $ax(Feng.ctxPath + "/userInvoice/cancel", function (data) {
                Feng.success("操作成功!");
                UserInvoice.table.refresh();
            }, function (data) {
                Feng.error("操作失败!" + data.responseJSON.message + "!");
            });
            ajax.set("userInvoiceId",UserInvoice.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否撤销增票审核?", operation);
    }
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
UserInvoice.formParams = function () {
    var queryData = {};

    var verifyStartTime = $("#verifyStartTime").val();
    var verifyEndTime = $("#verifyEndTime").val();
    if(verifyStartTime != ""){
        queryData['verifyStartTime'] = (new Date(verifyStartTime)).getTime() - 8 * 60 * 60 * 1000;
    }else{
        queryData['verifyStartTime'] = null;
    }
    if(verifyEndTime != ""){
        queryData['verifyEndTime'] = (new Date(verifyEndTime)).getTime() + 16 * 60 * 60 * 1000;
    }else{
        queryData['verifyEndTime'] = null;
    }

    var applyStartTime = $("#applyStartTime").val();
    var applyEndTime = $("#applyEndTime").val();
    if(applyStartTime != ""){
        queryData['applyStartTime'] = (new Date(applyStartTime)).getTime() - 8 * 60 * 60 * 1000;
    }else{
        queryData['applyStartTime'] = null;
    }
    if(applyEndTime != ""){
        queryData['applyEndTime'] = (new Date(applyEndTime)).getTime() + 16 * 60 * 60 * 1000;
    }else{
        queryData['applyEndTime'] = null;
    }

    queryData['verifyState'] = $("#verifyState").val();
    queryData['realNameFlag'] = $("#realNameFlag").val();
    queryData['userId'] = $("#userId").val();
    queryData['mobile'] = $("#mobile").val();
    queryData['realName'] = $("#realName").val();
    queryData['verifyBy'] = $("#verifyBy").val();
    queryData['realNameFlag'] = $("#realNameFlag").val();

    return queryData;
}

/**
 * 查询增票审核列表
 */
UserInvoice.search = function () {
    UserInvoice.table.refresh({query: UserInvoice.formParams()});
};

/**
 * 下载
 */
UserInvoice.download = function () {
    var imgId = document.getElementById("templateImg").click();
    document.getElementById('IMG_'+ imgId).click();
};

$(function () {
    Feng.findCodeType("verifyState", "verifyState", null);
    Feng.findCodeType("realNameFlag", "realNameFlag", null);

    var defaultColunms = UserInvoice.initColumn();
    var table = new BSTable(UserInvoice.id, Feng.ctxPath + "/userInvoice/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(UserInvoice.formParams());
    UserInvoice.table = table.init();

});
