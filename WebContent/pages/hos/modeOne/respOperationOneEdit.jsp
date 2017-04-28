<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑手术衣</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="updateHosOperationForm" class="smart-form">
		<input type="hidden" name="id" value="${id}">
		<input type="hidden" name="id" value="${yfp}" id="yfp">
		<input type="hidden" name="clothSize" value="${clothSize}">
		<%-- <input type="hidden" name="oldCount" value="${sumCnt}">
		<input type="hidden" name="residueCount" value="${residueCount}"> --%>
		<fieldset>
		

			<section>
				<div class="row">
					<label class="label col col-2">总数量</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="count" id="count_input" value="${sumCount}">
						</label>
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateHosOperation();">
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
});
function validateFrom(){
	return $("#updateHosOperationForm").validate({
		rules : {
			updateCount : {required : true,maxlength: 2}
		},
		messages : {
			updateCount : {required : '请输入尺码',maxlength:"数字不能大于99"}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}
$("#").validate({
	rules : {
		updateCount : {required : true}
	},

	messages : {
		updateCount : {required : '请输入数量'}
	},

	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});

function updateHosOperation(){
	var count=parseInt($("#count_input").val());//新数量
	var yfp=$("#yfp").val();//已分配   回收中，使用中，分配中的总和
	if(yfp>count){
		alertSwal.warningText("数量不能小于已分配数量");
		return ;
	}  
	var ajaxUrl = "hosOperation/updateHosOperationNum";
	zfesAjax.ajaxTodo(ajaxUrl, $("#updateHosOperationForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#operationModal').modal('hide');
	});
}
</script>