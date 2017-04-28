<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">修改手术鞋</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="editShoesForm" class="smart-form">
	    <input type="hidden" name="id" value="${hosShoes.id}">
	    <input type="hidden" name="yfp" value="${yfp}" id="yfp"> 
	    <input type="hidden" name="oldCount" value="${hosShoes.count}" id="old_count">
		<fieldset>
			<section>
				<div class="row">
					<label class="label col col-2">总数量</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="count" value="${hosShoes.count}" id="count_input">
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateShoes();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
function validateShoes(){
	return $("#editShoesForm").validate({
		rules : {
			count : {required : true,maxlength : 50}
		},
		messages : {
			count : {required : '请输入数量'}
		},
		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateShoes();
});

function updateShoes(){
	 var oldCount=parseInt($("#old_count").val());//原数量
	var count=parseInt($("#count_input").val());//新数量
	var yfp=$("#yfp").val();//已分配   回收中，使用中，分配中的总和
	if(yfp>count){
		alertSwal.warningText("数量不能小于已分配数量000");
		return false;
	}  
	var ajaxUrl = "hosShoes/updatehosShoes";
	zfesAjax.ajaxTodo(ajaxUrl, $("#editShoesForm").serialize(), function(data) {
		alertSwal.successText(data.message);
		$('#shoesModal').modal('hide');
	});
}
</script>