@/*
    时间查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    isTime : 日期是否带有小时和分钟(true/false)
    pattern : 日期的正则表达式(例如:"YYYY-MM-DD")
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle"
                type="button">${name}
        </button>
    </div>
    <input type="text" class="form-control layer-date" readonly="readonly"
        onclick="laydate({istime: ${isTime}, format: '${pattern}'
        @if(id == 'beginTime' || id == 'startTime'){
        ,max: $('#endTime').val()
        @}
        @if(isNotEmpty(min)){
        ,min: $('#${min}').val()
        @}
        @if(id == 'endTime'){
        ,min: $('#beginTime').val()? $('#beginTime').val(): $('#startTime').val()
        @}
        @if(id == 'startUpgradeTime'){
        ,max: $('#endUpgradeTime').val()
        @}
        @if(id == 'endUpgradeTime'){
        ,min: $('#startUpgradeTime').val()
        @}
        @if(id == 'startRegistTime'){
        ,max: $('#endRegistTime').val()
        @}
        @if(id == 'endRegistTime'){
        ,min: $('#startRegistTime').val()
        @}
        @if(id == 'startRegistTime' && isNotEmpty(choose) && choose == 'true'){
        ,choose: function(dates){
 			if($('#startTime').val() == '' || new Date($('#startTime').val()) < new Date(dates)){
 				$('#startTime').val(dates)
 			}
  		}
        @}
        })" id="${id}"/>
</div>