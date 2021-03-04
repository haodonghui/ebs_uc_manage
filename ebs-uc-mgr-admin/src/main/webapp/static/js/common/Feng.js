var Feng = {
    ctxPath: "",
    addCtx: function (ctx) {
        if (this.ctxPath == "") {
            this.ctxPath = ctx;
        }
    },
    imageAngle: 0,
    bind: function(){
    	$("#indexImage").on("click", function(){
    		Feng.hideImg();
    	});
    	$("#indexRotateBtn").on("click", function(){
    		Feng.imageAngle -= 90;
    		if(Feng.imageAngle < -300){
    			Feng.imageAngle = 0;
    		}
    		var rotate = "rotate(" + Feng.imageAngle + "deg)";
    		$("#indexImage").css("transform", rotate)
    		.css("-moz-transform", rotate)
    		.css("-webkit-transform", rotate);
    	});
    	$("#indexCloseBtn").on("click", function(){
    		Feng.hideImg();
    	});
    	
    },
    showImg: function(imgUrl){
    	if(imgUrl){
    		$("#indexImage").attr("src", imgUrl);
    		$("#indexImageWindow").show();
    	}
    },
    hideImg: function(){
    	$("#indexImageWindow").hide();
		$("#indexImage")
			.attr("src", "")
			.css("transform", "rotate(0deg)")
			.css("-moz-transform", "rotate(0deg)")
			.css("-webkit-transform", "rotate(0deg)");
    },
    confirm: function (tip, ensure) {//询问框
        parent.layer.confirm(tip, {
            btn: ['确定', '取消']
        }, function (index) {
            ensure();
            parent.layer.close(index);
        }, function (index) {
            parent.layer.close(index);
        });
    },
    tip: function (tip, area) {//询问框
    	parent.layer.confirm(tip, {
    		btn: ['关闭'],
    		area: area? area: 'auto'
    	},function (index) {
    		parent.layer.close(index);
    	});
    },
    log: function (info) {
        console.log(info);
    },
    alert: function (info, iconIndex) {
        parent.layer.msg(info, {
            icon: iconIndex
        });
    },
    loadOpen: function (info, iconIndex) {
    	parent.layer.load();
    },
    loadClose: function (info, iconIndex) {
    	parent.layer.closeAll('loading');
    },
    info: function (info) {
        Feng.alert(info, 0);
    },
    success: function (info) {
        Feng.alert(info, 1);
    },
    error: function (info) {
        Feng.alert(info, 2);
    },
    infoDetail: function (title, info) {
        var display = "";
        if (typeof info == "string") {
            display = info;
        } else {
            if (info instanceof Array) {
                for (var x in info) {
                    display = display + info[x] + "<br/>";
                }
            } else {
                display = info;
            }
        }
        parent.layer.open({
            title: title,
            type: 1,
            skin: 'layui-layer-rim', //加上边框
            area: ['950px', '600px'], //宽高
            content: '<div style="padding: 20px;">' + display + '</div>'
        });
    },
    writeObj: function (obj) {
        var description = "";
        for (var i in obj) {
            var property = obj[i];
            description += i + " = " + property + ",";
        }
        layer.alert(description, {
            skin: 'layui-layer-molv',
            closeBtn: 0
        });
    },
    showInputTree: function (inputId, inputTreeContentId, leftOffset, rightOffset) {
        var onBodyDown = function (event) {
            if (!(event.target.id == "menuBtn" || event.target.id == inputTreeContentId || $(event.target).parents("#" + inputTreeContentId).length > 0)) {
                $("#" + inputTreeContentId).fadeOut("fast");
                $("body").unbind("mousedown", onBodyDown);// mousedown当鼠标按下就可以触发，不用弹起
            }
        };

        if(leftOffset == undefined && rightOffset == undefined){
            var inputDiv = $("#" + inputId);
            var inputDivOffset = $("#" + inputId).offset();
            $("#" + inputTreeContentId).css({
                left: inputDivOffset.left + "px",
                top: inputDivOffset.top + inputDiv.outerHeight() + "px"
            }).slideDown("fast");
        }else{
            $("#" + inputTreeContentId).css({
                left: leftOffset + "px",
                top: rightOffset + "px"
            }).slideDown("fast");
        }

        $("body").bind("mousedown", onBodyDown);
    },
    baseAjax: function (url, tip) {
        var ajax = new $ax(Feng.ctxPath + url, function (data) {
            Feng.success(tip + "成功!");
        }, function (data) {
            Feng.error(tip + "失败!" + data.responseJSON.message + "!");
        });
        return ajax;
    },
    changeAjax: function (url) {
        return Feng.baseAjax(url, "修改");
    },
    zTreeCheckedNodes: function (zTreeId) {
        var zTree = $.fn.zTree.getZTreeObj(zTreeId);
        var nodes = zTree.getCheckedNodes();
        var ids = "";
        for (var i = 0, l = nodes.length; i < l; i++) {
            ids += "," + nodes[i].id;
        }
        return ids.substring(1);
    },
    eventParseObject: function (event) {//获取点击事件的源对象
        event = event ? event : window.event;
        var obj = event.srcElement ? event.srcElement : event.target;
        return $(obj);
    },
    sessionTimeoutRegistry: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                if (sessionstatus == "timeout") {
                    //如果超时就处理 ，指定要跳转的页面
                    window.location = Feng.ctxPath + "/global/sessionError";
                }
            }
        });
    },
    initValidator: function(formId,fields){
        $('#' + formId).bootstrapValidator({
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields,
            live: 'enabled',
            message: '该字段不能为空'
        });
    },
    convertStartTime: function(startTime){
    	if(startTime != null && startTime != ""){
        	return (new Date(startTime)).getTime() - 8 * 60 * 60 * 1000;
        }
    	return null;
    },
    convertEndTime: function(endTime){
    	if(endTime != null && endTime != ""){
    		return (new Date(endTime)).getTime() + 16 * 60 * 60 * 1000;
    	}
    	return null;
    },
    convertTime: function(time){
    	if(time != null && time != ""){
    		return (new Date(time)).getTime();
    	}
    	return null;
    },
    convertTimePicker: function(time){
    	if(time != null && time != ""){
    		var t = time.split(':');
    		if(t.length == 2){
    			
    			var hour = t[0] * 1;
    			if(hour >= 8){
    				hour -= 8;
    			}else{
    				hour += 16;
    			}
    			return (hour * 60 + t[1] * 1) * 60 * 1000;
    		}
    	}
    	return null;
    },
    clearInputData: function(idList){
    	for(i in idList){
    		$('#' + idList[i]).val('');
    	}
    },
    initImage: function(id, url, limit){
    	var avatarUp = new $WebUpload(id);
        avatarUp.setUploadBarId("progressBar");
        avatarUp.setUploadUrl(Feng.ctxPath + url);
        avatarUp.setFileSizeLimit(limit? limit: 500);
        avatarUp.init();
    },
    //手机号校验，第一位为1，可用在onkeyup事件上
    validatePhone: function(inputId){
    	var phone = $("#" + inputId).val();
    	phone = isNaN(parseInt(phone))? "": parseInt(phone) + "";
    	phone = phone.match(/^[1]/g) == null? "": phone;
    	$("#" + inputId).val(phone);
    },
    validateNumber: function(inputId){
    	var num = $("#" + inputId).val();
    	num = isNaN(parseInt(num))? "": parseInt(num);
    	$("#" + inputId).val(num);
    },
    validateDecimal: function(inputId){
    	var num = $("#" + inputId).val();
    	if((num.length == 1 && num != 0) || ((num.length - 1) != num.indexOf('.') && (num.length - 1) != num.lastIndexOf('0'))){
    		num = isNaN(parseFloat(num))? "": parseFloat(num);
    	}
    	$("#" + inputId).val(num);
    },
    /**
     * domeId: selectId标签ID
     * pcode: 父级类型CODE
     * selectValue: 值
     */
    findCodeType: function(selectId, pcode, selectValue){
        this.initSelect(selectId, "/dict/list", {"pcode": pcode}, 'name', 'code', selectValue);
    },
    /**
     * selectId: select标签ID
     * url: 请求地址
     * queryParam: 请求参数
     * optionName: 返回的option名称
     * optionValue: 返回的option值
     * selectValue: select值
     */
    initSelect: function(selectId, url, queryParam, optionName, optionValue, selectValue){
    	selectId = "#" + selectId;
    	var ajax = new $ax(Feng.ctxPath + url, function (data) {
    		$(selectId).html('');
    		$(selectId).append('<option value="" >请选择</option>');
    		if(data){
    			for(var i = 0; i < data.length; i++){
    				var obj = data[i];
    				$(selectId).append("<option value="+eval('obj.'+optionValue)+">"+eval('obj.'+optionName)+"</option>");
    			}
    			if(selectValue){
    				$(selectId).val($("#" + selectValue).val());
    			}
    		}
    	}, function (data) {
    		$(selectId).html('');
    		$(selectId).append('<option value="" >请选择</option>');
    	});
    	ajax.set(queryParam);
    	ajax.start();
    }
};

Feng.bind();
