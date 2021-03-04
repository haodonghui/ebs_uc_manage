@/*
文本域
@*/
<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
        <textarea class="form-control" id="${id}" name="${id}"
               @if(isNotEmpty(readonly)){
                    readonly="${readonly}"
               @}
               @if(isNotEmpty(clickFun)){
                    onclick="${clickFun}"
               @}
               @if(isNotEmpty(style)){
                    style="${style}"
               @}
               @if(isNotEmpty(disabled)){
                    disabled="${disabled}"
               @}
               @if(isNotEmpty(maxlength)){
                    maxlength="${maxlength}"
               @}
               @if(isNotEmpty(placeholder)){
                    placeholder="${placeholder}"
               @}
               @if(isNotEmpty(onkeyup)){
                    onkeyup="${onkeyup}"
               @}
               @if(isNotEmpty(rows)){
                    rows="${rows}"
               @}
        >${value!}</textarea>
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


