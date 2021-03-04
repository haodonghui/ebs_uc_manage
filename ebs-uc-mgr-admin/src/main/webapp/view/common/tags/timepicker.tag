@/*
    时间查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    isTime : 日期是否带有小时和分钟(true/false)
    pattern : 日期的正则表达式(例如:"YYYY-MM-DD")
@*/
<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
       <div id="${id}_div" class="controls input-append date form_time" data-date="" data-date-format="hh:ii" data-link-format="hh:ii">
          <input id="${id}" class="form-control" type="text" value="" readonly 
          @if(isNotEmpty(placeholder)){
               placeholder="${placeholder}"
          @}
          >
          <span class="add-on"><i class="icon-remove"></i></span>
		  <span class="add-on"><i class="icon-th"></i></span>
       </div>
	</div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}

<script>
$(function(){
	$('#${id}_div').datetimepicker({
        language:  'zh-CN',
        weekStart: 0,
        todayBtn:  0,
		autoclose: 1,
		todayHighlight: 0,
		startView: 0,
		minView: 0,
		maxView: 0,
		forceParse: 0
    });$('#${id}').val('${value!}');
})
</script>