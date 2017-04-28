<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="cloth" var="cloth"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增手术衣</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addHosOperationForm" class="smart-form">

		<fieldset>
			
			<section>
				<div class="row">
					<label class="label col col-2">尺码</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="cloth" name="clothSize" id="clothSize">
							</select>
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">存放位置</label>
					<div class="col col-10" id="theater_asfasfasaa">
						<label class="select"> 
							<km-select host="baseServer"  url="hosTheater/loadTheByNumberForDict" id="theaterNumber" name="theNumber" vmodel="theaterNumber" label=""></km-select>
						</label>	
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">数量</label>
					<div class="col col-10">
						     <label class="input"> 
						     <input type="text" value="1" name="count">
						</label>
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="saveOperation();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$(function(){
	validateFrom();
	ZVue.init();
});
function validateFrom(){
	return $("#addHosOperationForm").validate({
		rules : {
			clothSize : {required : true,maxlength: 11},
			count : {required : true,maxlength: 4}
		},
		messages : {
			clothSize : {required : '请输入尺码',maxlength:"长度不能超过11位"},
			count : {required : '请输入数量',maxlength:"数量不能大于999"}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

function saveOperation(){
	if(validateFrom().form()){
		var ajaxUrl = "hosOperation/addHosOperation";
		zfesAjax.ajaxTodo(ajaxUrl, $("#addHosOperationForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#operationModal').modal('hide');
		});
	}
}
</script>