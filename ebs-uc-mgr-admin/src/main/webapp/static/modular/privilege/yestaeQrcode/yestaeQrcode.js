/**
 * 二维码管理初始化
 */
var YestaeQrcode = {
    id: "YestaeQrcodeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
YestaeQrcode.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: 'qrcodeId', field: 'qrcodeId', visible: false, align: 'center', valign: 'middle'},
        {title: '推广人', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '推荐码', field: 'recommendCode', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userTypeName', visible: true, align: 'center', valign: 'middle'},
        {title: '用户业务类型', field: 'userType', visible: false, align: 'center', valign: 'middle'},
        {title: '推广渠道', field: 'channelName', visible: true, align: 'center', valign: 'middle'},
        {title: '场景名称', field: 'sceneName', visible: true, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScope', visible: false, align: 'center', valign: 'middle'},
        {title: '应用范围', field: 'applyScopeName', visible: true, align: 'center', valign: 'middle'},
        {title: '跳转页面', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: 'H5外链页URL', field: 'jumpPageUrl', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
        {title: '二维码', field: 'codeUrl', visible: true, align: 'center', valign: 'middle', 
        	formatter: function(value, row, index, field) {
        		if (value) {
        			return '<a id="IMG_'+ row.id +'" href="' + Feng.ctxPath + '/yestaeQrcode/download?yestaeQrcodeId='+ row.id +'" download="'+ row.name +'.png" target="_Black"></a>'
//        			return '<a id="IMG_'+ row.id +'" href="' + value +'" target="_Black">'
        			+'<img width="100px" height="100px" src="'+value+'">';
    			}
    			return '';
    		}
        }
    ];
};

/**
 * 检查是否选中
 */
YestaeQrcode.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        YestaeQrcode.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加二维码
 */
YestaeQrcode.openAddYestaeQrcode = function () {
    var index = layer.open({
        type: 2,
        title: '添加二维码',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/yestaeQrcode/yestaeQrcode_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看二维码详情
 */
YestaeQrcode.openYestaeQrcodeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '二维码详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/yestaeQrcode/yestaeQrcode_update/' + YestaeQrcode.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除二维码
 */
YestaeQrcode.delete = function () {
    if (this.check()) {
    	
    	var operation = function() {
    		
    		var ajax = new $ax(Feng.ctxPath + "/yestaeQrcode/delete", function (data) {
    			Feng.success("删除成功!");
    			YestaeQrcode.table.refresh();
    		}, function (data) {
    			Feng.error("删除失败!" + data.responseJSON.message + "!");
    		});
    		ajax.set("yestaeQrcodeId",YestaeQrcode.seItem.id);
    		ajax.start();
    	}
    	
    	Feng.confirm("是否刪除该二维码?", operation);
    }
};

/**
 * 下载二维码
 */
YestaeQrcode.download = function () {
	if (this.check()) {
		var imgId = YestaeQrcode.seItem.id;
		document.getElementById('IMG_'+ imgId).click();
	}
};

/**
 * 查询表单提交参数对象
 * @returns {{}}
 */
YestaeQrcode.formParams = function () {
	var queryData = {};

	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
    if(startTime != ""){
    	queryData['startTime'] = (new Date(startTime)).getTime() - 8 * 60 * 60 * 1000;
    }else{
    	queryData['startTime'] = null;
    }
    if(endTime != ""){
    	queryData['endTime'] = (new Date(endTime)).getTime() + 16 * 60 * 60 * 1000;
    }else{
    	queryData['endTime'] = null;
    }
	
    queryData['name'] = $("#name").val();
    queryData['recommendCode'] = $("#recommendCode").val();
    queryData['channelName'] = $("#channelName").val();
    queryData['sceneName'] = $("#sceneName").val();
    queryData['type'] = $("#type").val();
    queryData['applyScope'] = $("#applyScope").val();
    queryData['jumpType'] = $("#jumpType").val();
    queryData['userType'] = $("#userType").val();

    return queryData;
}

/**
 * 查询二维码列表
 */
YestaeQrcode.search = function () {
    YestaeQrcode.table.refresh({query: YestaeQrcode.formParams()});
};

$(function () {
    var defaultColunms = YestaeQrcode.initColumn();
    var table = new BSTable(YestaeQrcode.id, Feng.ctxPath + "/yestaeQrcode/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParams(YestaeQrcode.formParams());
    YestaeQrcode.table = table.init();
    
    Feng.findCodeType("userType", "platformUserType", null);
    Feng.findCodeType("type", "qrcodeSceneType", null);
	Feng.findCodeType("applyScope", "qrcodeSceneApplyScope", null);
	Feng.findCodeType("jumpType", "qrcodeSceneJumpType", null);
});
