<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-6">
        <input class="form-control" type="text" id="${id}" name="${id}"
        readonly="readonly" style="background-color: #ffffff !important;"
        @if(isNotEmpty(value)){
             value="${value}"
        @}
        @if(isNotEmpty(clickFun)){
             onclick="${clickFun}; return false;"
        @}
        >
        <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}">
    </div>
    <div class="col-sm-3">
    	<#button btnCss="danger" name="清除" id="" icon="fa-eraser" clickFun="Feng.clearInputData(['${id}', '${hidden}'])"/>
    </div>
</div>