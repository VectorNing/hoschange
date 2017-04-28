<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
		&times;
	</button>
	<h4 class="modal-title" id="myModalLabel">编辑手术衣</h4>
</div>
<div class="modal-body no-padding">

	<form action="" id="updateHosOperationForm" class="smart-form">
		<input type="hidden" name="id" value="${hosOperation.id}">
		<fieldset>
		
			<c:if test="${strategy == 2}">
				<section>
					<div class="row">
						<label class="label col col-2">编号</label>
						<div class="col col-10">
							<label class="input"> 
								<input type="text" name="number" value="${hosOperation.number}">
							</label>
							
						</div>
					</div>
				</section>
			</c:if>
			
			<section>
				<div class="row">
					<label class="label col col-2">尺码</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="clothSize" value="${hosOperation.clothSize}">
						</label>
					</div>
				</div>
			</section>

			<section>
				<div class="row">
					<label class="label col col-2">使用状态</label>
					<div class="col col-10">
						<label class="input"> 
							<input type="text" name="state" value="${hosOperation.state}">
						</label>
						
					</div>
				</div>
			</section>
			
		</fieldset>

		<footer>
			<button type="button" class="btn btn-primary" onclick="updateSterilizer();">
				保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				取消
			</button>
		</footer>

	</form>
</div>
<script>
$("#updateHosOperationForm").validate({
	// Rules for form validation
	rules : {
		number : {
			required : true
		},
		code : {
			required : true
		},
		count : {
			required : true
		}
	},

	// Messages for form validation
	messages : {
		number : {
			required : '请输入尺码'
		},
		code : {
			required : '请输入编号'
		},
		count : {
			required : '请输入数量'
		}
	},

	// Do not change code below
	errorPlacement : function(error, element) {
		error.insertAfter(element.parent());
	}
});

function updateSterilizer(){
	var ajaxUrl = "hosOperation/updateHosOperation";
	zfesAjax.ajaxTodo(ajaxUrl, $("#updateHosOperationForm").serialize(), function(data) {
		$('#operationModal').modal('hide');
		zfesBstable.refresh(jqTableDom);
	});
}
</script>