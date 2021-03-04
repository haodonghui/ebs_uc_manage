@/*
    头像参数的说明:
    name : 名称
    id : 头像的id
@*/
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${name}</label>
    <div class="col-sm-4">
        <div id="${id}PreId">
            <div>
                @if(isEmpty(avatarImg)){
            		<img width="100px" height="100px" src="${ctxPath}/static/img/yestae.jpg">
                @}else{
	            	<a href="javascript: parent.parent.Feng.showImg('${imgPath}${avatarImg}')">
	            		<img id="${id}Vi" width="100px" height="100px" src="${imgPath}${avatarImg}" >
	            		
	            	</a>
                @}
            </div>
        </div>
    </div>
    @if(isNotEmpty(upload) && upload == 'true'){
    <div class="col-sm-2">
        <div class="head-scu-btn upload-btn" id="${id}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    @}
    <input type="hidden" id="${id}" value=""/>
    @if(!isEmpty(avatarImg)){
    <div style="position: fixed; z-index:1000; top: 0; left: 0; display: none; " id="${id}Vd">
    	<img  width="100%" height="auto" src="${imgPath}${avatarImg}" >
    </div>
	@}
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


