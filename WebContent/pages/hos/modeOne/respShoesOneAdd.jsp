<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sx" uri="http://www.sdsesxh.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:DataDict type="shoes" var="shoes"></t:DataDict>
<script type="text/javascript" src="js/dict.js"></script>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">新增手术鞋</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="addShoesForm" class="smart-form">

		<fieldset>

			<section>
				<div class="row">
					<label class="label col col-2">尺码</label>
					<div class="col col-10">
						<label class="select"> 
							<select dict="shoes" name="shoeSize" id="shoeSize">
							</select>
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">手术区</label>
					<div class="col col-10">
						<label class="select"> 
							<select  name="theaterNumber" id="theater">
							<c:forEach items="${roomLists}" var="theater">
							<option value="${theater.number}">${theater.name}</option>
							</c:forEach>
							</select>
						</label>
						
					</div>
				</div>
			</section>
			
			<section>
				<div class="row">
					<label class="label col col-2">数量</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="count" >
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="saveShoesForm();">
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
	return $("#addShoesForm").validate({
		rules : {
			shoeSize : {required : true,maxlength : 100},
			count : {required : true,maxlength : 50}
		},

		messages : {
			shoeSize : {required : '请输入手术鞋编号'},
			count : {required : '请输入添加数量'}
		},

		errorPlacement : function(error, element) {
			error.insertAfter(element.parent());
		}
	});
}

$(function(){
	validateShoes();
});
function saveShoesForm(){
	if(validateShoes().form()){
		var ajaxUrl = "hosShoes/addhosShoes";
		zfesAjax.ajaxTodo(ajaxUrl, $("#addShoesForm").serialize(), function(data) {
			alertSwal.successText(data.message);
			$('#shoesModal').modal('hide');
		});
	}
}
</script>